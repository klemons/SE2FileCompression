/*************************************************************************
 *  Compilation:  javac LZW.java
 *  Execution:    java LZW - < input.txt   (compress)
 *  Execution:    java LZW + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using LZW.
 *
 *
 *************************************************************************/

public class SchubsL {
    private static final int R = 256;        // number of input chars
    private static final int L = 4096;       // number of codewords = 2^W
    private static final int W = 12;         // codeword width
	
	private static BinaryIn in;
    private static BinaryOut out;
    private static String ext1 = "";

    public static void compress() { 
        String input = in.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            out.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            input = input.substring(t);            // Scan past s in input.
        }
        out.write(R, W);
        out.close();
    } 


    public static void expand() {
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



    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            in = new BinaryIn(args[i]);
            int n = args[i].lastIndexOf('.');
            if (n > 0) {
                ext1 = args[i].substring(n + 1);
            }
            System.out.println(ext1);


            if (ext1.equals("txt")) {
                out = new BinaryOut(args[i] + ".ll");
                compress();
            }  else throw new RuntimeException("Illegal command line argument");
//            else if (ext1.equals("ll")) {
//                String str = args[i].replace(".ll", "");
//                out = new BinaryOut(str);
//                expand();
//            }
        }
    }

}
