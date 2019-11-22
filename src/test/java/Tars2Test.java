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
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Tars2Test {

    //This first test checks to see if the first filename size is correct
    @Test
    public void Test1() throws IOException {
        BinaryOut out = null;
        File test1a = new File("src/files/test1a.txt");
        out = new BinaryOut(test1a.getPath());
        out.write("Hello this is some file input!");
        out.close();
        File test1b = new File("src/files/test1b.txt");
        out = new BinaryOut(test1b.getPath());
        out.write("Hello this is some different file input!");
        out.close();
        String testArchive1 = "src/files/testArchive1.tar";
        int fileNameSize = test1a.getPath().length();
        Tars2.main(new String[] {testArchive1, test1a.getPath(), test1b.getPath()});
        BinaryIn bin1 = new BinaryIn(testArchive1);
        assertEquals(fileNameSize, bin1.readInt());
    }

    //This second test will check for the first separator that comes after the first filenamesize
    @Test
    public void Test2() throws IOException {
        BinaryOut out = null;
        char separator =  (char) 255;
        File test2a = new File("src/files/test2a.txt");
        out = new BinaryOut(test2a.getPath());
        out.write("Hello this is some file input!");
        out.close();
        File test2b = new File("src/files/test2b.txt");
        out = new BinaryOut(test2b.getPath());
        out.write("Hello this is some different file input!");
        out.close();
        String testArchive2 = "src/files/testArchive2.tar";
        int fileNameSize = test2a.getPath().length();
        Tars2.main(new String[] {testArchive2, test2a.getPath(), test2b.getPath()});
        BinaryIn bin1 = new BinaryIn(testArchive2);
        bin1.readInt();
        assertEquals(separator, bin1.readChar());
    }

    //This next test will check filenamesize for a different filename length
    @Test
    public void Test3() throws IOException {
        BinaryOut out = null;
        File test3a = new File("src/files/ThisIsAVeryLongFileNameForTesting.txt");
        out = new BinaryOut(test3a.getPath());
        out.write("Hello this is some file input!");
        out.close();
        File test3b = new File("src/files/ThisIsAnotherLongFileName.txt");
        out = new BinaryOut(test3b.getPath());
        out.write("Hello this is some different file input!");
        out.close();
        String testArchive3 = "src/files/testArchive3.tar";
        int fileNameSize = test3a.getPath().length();
        Tars2.main(new String[] {testArchive3, test3a.getPath(), test3b.getPath()});
        BinaryIn bin1 = new BinaryIn(testArchive3);
        assertEquals(fileNameSize, bin1.readInt());
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
