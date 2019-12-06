# SE2FileCompression
### Author: Kyle Lemons
### Project for CS 375 - Software Engineering II
### Prof: Dr. Brent Reeves



WIP
- Huffman compression is based on the idea that we can save bits by encoding frequently used characters with fewer bits than rare characters, thereby lowering the number of bits used. A trie is constructed to contain the corresonding bits to each character. That trie is included in the compressed file, so it will contribute to the size.
- LZW compression reads through and assigns codewords to various patterns of words. That way if a pattern reappears again, the dictionary storing the codewords can easily access and assign the value. This can allow LZW to "look ahead" in the table to have faster performance. Instead of common characters, LZW can build a dictionary of commonly used English words for example. This allows for the compression ratio to end up better than Huffman in the bigger picture.
- tests: prove and illustrate everything
- installation: CLI instructions
- test instructions
- run examples: CLI instructions

java -cp target/classes SchubsH src/files/test1.txt
*
java -cp target/classes Deschubs src/files/test1.hh


java -cp target/classes SchubsL src/files/test1.txt
*
java -cp target/classes Deschubs src/files/test1.ll
