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

public class TarsnTest {

    //This first test runs 3 files into Tarsn/Untars
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

        String testArchive1 = "src/files/testArchive1.tar";
        int fileNameSize = test1a.getPath().length();
        Tarsn.main(new String[] {testArchive1, test1a.getPath(), test1b.getPath(), test1c.getPath()});

        //Deleting original txt files
        test1a.delete();
        test1b.delete();
        test1c.delete();

        Untars.main(new String[] {testArchive1});

        BinaryIn bin1 = new BinaryIn("src/files/test1a.txt");
        assertEquals(input1, bin1.readString());
        bin1 = new BinaryIn("src/files/test1b.txt");
        assertEquals(input2, bin1.readString());
        bin1 = new BinaryIn("src/files/test1c.txt");
        assertEquals(input3, bin1.readString());
    }

    //This first test runs 4 files into Tarsn/Untars
    @Test
    public void Test2() throws IOException {
        BinaryOut out = null;
        File test2a = new File("src/files/test2a.txt");
        out = new BinaryOut(test2a.getPath());
        //Storing string here
        String input1 = "Hello this is some file input!";
        out.write(input1);
        out.close();

        File test2b = new File("src/files/test2b.txt");
        out = new BinaryOut(test2b.getPath());
        //Storing string here
        String input2 = "Hello this the second file input!";
        out.write(input2);
        out.close();

        File test2c = new File("src/files/test2c.txt");
        out = new BinaryOut(test2c.getPath());
        //Storing string here
        String input3 = "This is the third file input!";
        out.write(input3);
        out.close();

        File test2d = new File("src/files/test2d.txt");
        out = new BinaryOut(test2d.getPath());
        //Storing string here
        String input4 = "This is the fourth file input!";
        out.write(input4);
        out.close();

        String testArchive1 = "src/files/testArchive2.tar";
        int fileNameSize = test2a.getPath().length();
        Tarsn.main(new String[] {testArchive1, test2a.getPath(), test2b.getPath(), test2c.getPath(), test2d.getPath()});

        //Deleting original txt files
        test2a.delete();
        test2b.delete();
        test2c.delete();
        test2d.delete();

        Untars.main(new String[] {testArchive1});

        BinaryIn bin1 = new BinaryIn("src/files/test2a.txt");
        assertEquals(input1, bin1.readString());
        bin1 = new BinaryIn("src/files/test2b.txt");
        assertEquals(input2, bin1.readString());
        bin1 = new BinaryIn("src/files/test2c.txt");
        assertEquals(input3, bin1.readString());
        bin1 = new BinaryIn("src/files/test2d.txt");
        assertEquals(input4, bin1.readString());
    }

    //This first test runs 5 files into Tarsn/Untars
    @Test
    public void Test3() throws IOException {
        BinaryOut out = null;
        File test3a = new File("src/files/test3a.txt");
        out = new BinaryOut(test3a.getPath());
        //Storing string here
        String input1 = "Hello this is some file input!";
        out.write(input1);
        out.close();

        File test3b = new File("src/files/test3b.txt");
        out = new BinaryOut(test3b.getPath());
        //Storing string here
        String input2 = "This is the second input";
        out.write(input2);
        out.close();

        File test3c = new File("src/files/test3c.txt");
        out = new BinaryOut(test3c.getPath());
        //Storing string here
        String input3 = "This is the third file input!";
        out.write(input3);
        out.close();

        File test3d = new File("src/files/test3d.txt");
        out = new BinaryOut(test3d.getPath());
        //Storing string here
        String input4 = "This is the fourth file input!";
        out.write(input4);
        out.close();

        File test3e = new File("src/files/test3e.txt");
        out = new BinaryOut(test3e.getPath());
        //Storing string here
        String input5 = "This is the fifth file input!";
        out.write(input5);
        out.close();

        String testArchive1 = "src/files/testArchive3.tar";
        int fileNameSize = test3a.getPath().length();
        Tarsn.main(new String[] {testArchive1, test3a.getPath(), test3b.getPath(), test3c.getPath(), test3d.getPath(), test3e.getPath()});

        //Deleting original txt files
        test3a.delete();
        test3b.delete();
        test3c.delete();
        test3d.delete();
        test3e.delete();

        Untars.main(new String[] {testArchive1});

        BinaryIn bin1 = new BinaryIn("src/files/test3a.txt");
        assertEquals(input1, bin1.readString());
        bin1 = new BinaryIn("src/files/test3b.txt");
        assertEquals(input2, bin1.readString());
        bin1 = new BinaryIn("src/files/test3c.txt");
        assertEquals(input3, bin1.readString());
        bin1 = new BinaryIn("src/files/test3d.txt");
        assertEquals(input4, bin1.readString());
        bin1 = new BinaryIn("src/files/test3e.txt");
        assertEquals(input5, bin1.readString());
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
