/**
 * Kyle Lemons
 * 11/15/19
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

import java.io.IOException;

public class Untars {
    public static void main(String[] args) throws IOException {
        BinaryIn in = null;
        BinaryOut out = null;

        char sep = (char) 255;  //All ones 11111 seperator

        // archive file is at args[0]
        // layout: int file-name-length, separator, filename, seperator, file-size, file

        try {
            in = new BinaryIn(args[0]);

            int filenamesize =in.readInt();
            sep = in.readChar();

            String filename = "";
            for (int i=0; i < filenamesize; i++)
                filename += in.readChar();
            sep = in.readChar();

            long filesize = in.readLong();
            sep = in.readChar();

            out = new BinaryOut(filename);
            for (int i=0; i <filesize; i++)
                out.write(in.readChar());

        } finally {
            if(out != null) {
                out.close();
            }
        }
    }
}
