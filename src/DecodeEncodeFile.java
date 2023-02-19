import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DecodeEncodeFile {
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

    public static void bruteForce(Path pathInpFile, Path pathOutFile, CharCrypto charCrypto)  {
        ArrayList<Integer> numberOfCoincidences = new ArrayList<>();
        int totalCout=0;
        int coutChar=0;
        int coutCharO=0;
        for (int i = 1; i <= CharCrypto.kyeSize ; i++) {
            charCrypto.setKey(i);
            decodeFile(pathInpFile, pathOutFile, charCrypto);
            try (BufferedReader bufferedReader = Files.newBufferedReader(pathOutFile, StandardCharsets.UTF_8)){
                String line;
                totalCout=0;
                coutChar=0;
                coutCharO=0;
                while ((line = bufferedReader.readLine()) != null) {
                    char[] charBuf = line.toCharArray();
                    totalCout=totalCout + (charBuf.length+1);
                    for (int j = 0; j < charBuf.length; j++) {
                        if(charBuf[j] == ' '){
                            coutChar++;
                        } else if (charBuf[j] == 'о' || charBuf[j] == 'О') {
                            coutCharO++;
                        }
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            numberOfCoincidences.add((coutCharO*100)/totalCout);
            if (((coutChar*100)/totalCout)>9 && ((coutCharO*100)/totalCout)>4 && ((coutCharO*100)/totalCout)<70 &&
                    ((coutChar*100)/totalCout)<70 ){
                System.out.println("Probability KEY - "+ charCrypto.getKey());
                System.out.println("Probability show \" \" - "+(coutChar*100)/totalCout + "%");
                System.out.println("Probability show \"O or o\" - "+(coutCharO*100)/totalCout+ "%");
            }
        }
        try {
            Files.delete(pathOutFile);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println(numberOfCoincidences.toString());


    }
}