/**
 * Kyle Lemons
 * 11/13/19
 * CS 375 - SE 2
 * Tars
 *
 * Base Code From:
 * Software Engineering II
 * Fall 2019
 * Brent Reeves
 *
 * begin to copy many files to one, long file
 * execute: java Tars archive-name file1 [file2...]
 */

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileCompressionTest {

    //This first test covers SchubsArc
    @Test
    public void Test1() throws IOException {
        BinaryOut out = null;
        File test1a = new File("src/files/test1a.txt");
        out = new BinaryOut(test1a.getPath());
        //Storing string here
        String input1 = "Hello this is some file input!";
        out.write(input1);
        out.close();

        File test1b = new File("src/files/test1b.txt");
        out = new BinaryOut(test1b.getPath());
        //Storing string here
        String input2 = "Hello this is some different file input!";
        out.write(input2);
        out.close();

        File test1c = new File("src/files/test1c.txt");
        out = new BinaryOut(test1c.getPath());
        //Storing string here
        String input3 = "This is the third file input!";
        out.write(input3);
        out.close();

        String testArchive1 = "src/files/testArchive1";
        int fileNameSize = test1a.getPath().length();
        SchubsArc.main(new String[] {testArchive1, test1a.getPath(), test1b.getPath(), test1c.getPath()});

        //Deleting original txt files
        test1a.delete();
        test1b.delete();
        test1c.delete();

        Deschubs.main(new String[] {"src/files/testArchive1.zl"});

        BinaryIn bin1 = new BinaryIn("src/files/test1a.txt");
        assertEquals(input1, bin1.readString());
        bin1 = new BinaryIn("src/files/test1b.txt");
        assertEquals(input2, bin1.readString());
        bin1 = new BinaryIn("src/files/test1c.txt");
        assertEquals(input3, bin1.readString());
    }

    //This test covers SchubsH
    @Test
    public void Test2() throws IOException {
        BinaryOut out = null;
        File test2a = new File("src/files/test2a.txt");
        out = new BinaryOut(test2a.getPath());
        //Storing string here
        String input1 = "Hello this is some file input!";
        out.write(input1);
        out.close();

        int fileNameSize = test2a.getPath().length();
        SchubsH.main(new String[] {test2a.getPath()});

        //Deleting original txt files
        test2a.delete();

        Deschubs.main(new String[] {"src/files/test2a.txt.hh"});

        BinaryIn bin1 = new BinaryIn("src/files/test2a.txt");
        assertEquals(input1, bin1.readString());
    }

    //This test covers SchubsL
    @Test
    public void Test3() throws IOException {
        BinaryOut out = null;
        File test3a = new File("src/files/test3.txt");
        out = new BinaryOut(test3a.getPath());
        //Storing string here
        String input1 = "Hello this is some file input!";
        out.write(input1);
        out.close();

        int fileNameSize = test3a.getPath().length();
        SchubsL.main(new String[] {test3a.getPath()});

        //Deleting original txt files
        test3a.delete();

        Deschubs.main(new String[] {"src/files/test3.txt.ll"});

        BinaryIn bin1 = new BinaryIn("src/files/test3.txt");
        assertEquals(input1, bin1.readString());
    }

    //Random string method for filling the files (For use in later assignments)
    public String RandomString(int n) {

        // function to generate a random string of length n
        {

            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz";

            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {

                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());

                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                        .charAt(index));
            }

            return sb.toString();
        }
    }

    //Code to create a randomized length string
//    Random rand = new Random();
//    int randomLength1 = rand.nextInt(20);
//    String fileContents1 = RandomString(randomLength1);
}
