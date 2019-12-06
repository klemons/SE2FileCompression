import java.io.IOException;

public class Deschubs {
    // alphabet size of extended ASCII
    private static final int R = 256;        // number of input chars
    private static final int L = 4096;       // number of codewords = 2^W
    private static final int W = 12;         // codeword width
    public static boolean logging = true;

    private static BinaryIn in;
    private static BinaryOut out;
    private static String ext1 = "";

    // Huffman trie node
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert (left == null && right == null) || (left != null && right != null);
            return (left == null && right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }


    public static void err_print(String msg)
    {
        if (logging)
            System.err.print(msg);
    }

    public static void err_println(String msg)
    {
        if (logging)
        {
            System.err.println(msg);
        }
    }

    // build the Huffman trie given frequencies
    private static Node buildTrie(int[] freq) {

        // initialize priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            err_println("buildTrie parent " + left.freq + " " + right.freq);
            pq.insert(parent);
        }
        return pq.delMin();
    }


    // write bitstring-encoded trie to standard output
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            out.write(true);
            out.write(x.ch);
            err_println("T" + x.ch);
            return;
        }
        out.write(false);
        err_print("F");

        writeTrie(x.left);
        writeTrie(x.right);
    }

    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        }
        else {
            st[x.ch] = s;
            err_println("buildCode " + x.ch + " " + s);
        }
    }


    // expand Huffman-encoded input from standard input and write to standard output
    public static void expandHuff() {

        // read in Huffman trie from input stream
        Node root = readTrie();

        // number of bytes to write
        int length = in.readInt();

        // decode using the Huffman trie
        for (int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = in.readBoolean();
                if (bit) x = x.right;
                else     x = x.left;
            }
            out.write(x.ch);
        }
        out.flush();
    }

    public static void expandLZW() {
        String[] st = new String[L];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int codeword = in.readInt(W);
        String val = st[codeword];

        while (true) {
            out.write(val);
            codeword = in.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L) st[i++] = val + s.charAt(0);
            val = s;
        }
        out.close();
    }


    private static Node readTrie() {
        boolean isLeaf = in.readBoolean();
        if (isLeaf) {
            char x = in.readChar();
            err_println("t: " + x );
            return new Node(x, -1, null, null);
        }
        else {
            err_print("f");
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            in = new BinaryIn(args[i]);

            //This code determines the files extension
            int n = args[i].lastIndexOf('.');
            if (n > 0) {
                ext1 = args[i].substring(n + 1);
            }

            if (ext1.equals("ll")) {
                String str = args[i].replace(".ll", "");
                out = new BinaryOut(str);
                expandLZW();
            } else if (ext1.equals("hh")) {
                String str = args[i].replace(".hh", "");
                out = new BinaryOut(str);
                expandHuff();
            } else if(ext1.equals("zl")) {
                String str = args[i].replace(".zl", "");
                out = new BinaryOut(str);
                expandLZW();
                Untars.main(new String[] {str});
            } else if(ext1.equals("zh")){
                throw new RuntimeException("This file type not supported");
            } else throw new RuntimeException("Illegal command line argument");
//        in = new BinaryIn(args[0]);
//        out= new BinaryOut(args[1]);
//        compress();
        }
    }
}
