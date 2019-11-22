/**
 * Kyle Lemons
 * 11/13/19
 * CS 375 - SE 2
 * Tars2
 *
 * Base Code From:
 * Software Engineering II
 * Fall 2019
 * Brent Reeves
 *
 *
 * execute: java Tars archive-name file1 [file2...]
 */

import java.io.File;
import java.io.IOException;

public class Tars2
{
    public static void main(String[] args) throws IOException {
        File in1 = null;
        BinaryIn bin1 = null;
        BinaryOut out = null;
        String input = "";

        char separator =  (char) 255;  // all ones 11111111

        try {
            for (int i = 1; i < args.length; i++) {
                // notice the input files start at arg[1], not arg[0]
                if (in1 != null) {
                    in1.delete();
                    in1.createNewFile();
                    in1 = new File(args[i]);
                } else
                    in1 = new File(args[i]);

                if (!in1.exists() || !in1.isFile()) return;

                long filesize = in1.length();
                int filenamesize = args[i].length();


                // archive file is at args[0]
                // layout: file-name-length, separator, filename, file-size, file
                //

                if (out == null) {
                    out = new BinaryOut(args[0]);
                }

                //file-name-length, sep
                out.write(filenamesize);
                out.write(separator);

                //filename, sep
                out.write(args[i]);
                out.write(separator);

                // write the file size, sep
                out.write(filesize);
                out.write(separator);

                // now copy the input file to the output, one character at a time
                bin1 = new BinaryIn(args[i]);
                input="";
                while (!bin1.isEmpty()) {
                    input += bin1.readChar();
                }
                out.write(input);
                //write a seperator before the next file
                out.write(separator);
            }        }
        finally {
            if (out != null)
                out.close();
        }

    }

}