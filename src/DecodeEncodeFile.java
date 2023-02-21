import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DecodeEncodeFile {
    /**
     *
     *encodeFile - encode input file in output file
     */
    public static void encodeFile (Path pathInpFile, Path pathOutFile, CharCrypto charCrypto){
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathInpFile, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOutFile, StandardCharsets.UTF_8)){
            int symbol;
            while((symbol=bufferedReader.read())!=-1){
                bufferedWriter.write(charCrypto.encodeChar((char) symbol));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     *decodeFile - decode input file in output file
     */

    public static void decodeFile (Path pathInpFile, Path pathOutFile, CharCrypto charCrypto){
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathInpFile, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOutFile, StandardCharsets.UTF_8)){
            int symbol;
            while((symbol=bufferedReader.read())!=-1){
                bufferedWriter.write(charCrypto.decodeChar((char) symbol));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * bruteForce - decode input file analyzes the probability of char in text, chat ' ' and char 'o' or 'O'
     * average statistic contains symbol ' ' in text 16%
     * average statistic contains symbol 'o' or 'O' in text 9%
     */

    public static int bruteForce(Path pathInpFile, Path pathOutFile, CharCrypto charCrypto)  {
        int totalCharInText=0;
        int countChar_;
        int countCharOo;
        int returnKey=0;
        for (int i = 1; i <= CharCrypto.kyeSize ; i++) {
            charCrypto.setKey(i);
            decodeFile(pathInpFile, pathOutFile, charCrypto);
            try (BufferedReader bufferedReader = Files.newBufferedReader(pathOutFile, StandardCharsets.UTF_8)){
                String line;
                totalCharInText=0;
                countChar_=0;
                countCharOo=0;
                while ((line = bufferedReader.readLine()) != null) {
                    char[] charBuf = line.toCharArray();
                    totalCharInText=totalCharInText + (charBuf.length+1);
                    for (int j = 0; j < charBuf.length; j++) {
                        if(charBuf[j] == ' '){
                            countChar_++;
                        } else if (charBuf[j] == 'о' || charBuf[j] == 'О') {
                            countCharOo++;
                        }
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (((countChar_*100)/totalCharInText)>9 && ((countCharOo*100)/totalCharInText)>4 && ((countCharOo*100)/totalCharInText)<70 &&
                    ((countChar_*100)/totalCharInText)<70 ){
                System.out.println("Probability KEY - "+ charCrypto.getKey());
                System.out.println("Probability show \" \" - "+(countChar_*100)/totalCharInText + "%");
                System.out.println("Probability show \"O or o\" - "+(countCharOo*100)/totalCharInText+ "%");
                returnKey = charCrypto.getKey();
                try {
                    Files.delete(pathOutFile); //delete tmp file
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
                return returnKey;
            }
        }
        try {
            Files.delete(pathOutFile); //delete tmp file
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return returnKey;

    }
}
