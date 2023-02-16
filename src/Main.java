import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Crypto crypto = new Crypto();
        int sw = encodeDecodeSwitch();
        if (sw==1){
            Path pathInpFile = inputFilePath();
            Path pathOutFile = outputFilePath();
            keyReader(crypto);
            encodeFile(pathInpFile,pathOutFile,crypto);
        }else if(sw==2) {
            Path pathInpFile = inputFilePath();
            Path pathOutFile = outputFilePath();
            keyReader(crypto);
            decodeFile(pathInpFile,pathOutFile,crypto);
        }else {
            Path pathInpFile = inputFilePath();
            Path pathOutFile = null;
            try {
                pathOutFile = tempFile(pathInpFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bruteForse(pathInpFile, pathOutFile, crypto);
        }
    }

    private static void keyReader(Crypto crypto){
        String inputStringKey;
        while (true) {
            System.out.print("Pleas input integer crypto key: ");
            inputStringKey =  scanner.nextLine();
            String regex = "\\d+";
            if (inputStringKey.matches(regex)) {
                break;
            } else {
                System.out.println("Not Integer pleas please replace!");
            }
        }
        crypto.setKey(Integer.parseInt(inputStringKey));
    }

    private static int encodeDecodeSwitch(){
        String inputStringKey;
        while (true) {
            System.out.print("Input \"1\" for encode, \"2\" for decode or \"3\" for bruteForse: ");
            inputStringKey = scanner.nextLine();
            String regex = "[1-3]";
            if (inputStringKey.matches(regex)) {
                return Integer.parseInt(inputStringKey);
            } else {
                System.out.println("Not Integer 1 or 2 pleas please replace!");
            }
        }
    }

    private static Path inputFilePath(){
        System.out.println("Input the path to the file to read");
        String inputPath;
        while (true) {
            System.out.print("Pleas input Path: ");
            inputPath = scanner.nextLine();
            Path path = Path.of(inputPath);
            if (Files.exists(path)) {
                return path;
            } else {
                System.out.println("Not valid Path please replace!");
            }
        }
    }

    private static Path outputFilePath()  {
        System.out.println("Input the path to the file to write");
        String inputPath;
        while (true) {
            System.out.print("Pleas input Path: ");
            inputPath = scanner.nextLine();
            Path path = Path.of(inputPath);
            if (Files.exists(path)) {
                return path;
            } else {
                System.out.println("Not valid Path create file...!");
                try {
                   return Files.createFile(path);
                }catch (IOException e){
                    System.out.println("Not valid Path create file...!");
                }

            }
        }
    }

    private static Path tempFile(Path inputFile) throws IOException {
        Path nameTmp = Path.of("tmp.txt");
        Path path = inputFile.getParent().resolve(nameTmp);
        System.out.println(path);
        if (Files.exists(path)) {
            return path;
        } else {
            System.out.println("Not valid Path create file...!");
            return Files.createFile(path);

            }
    }

    private static void encodeFile (Path pathInpFile,Path pathOutFile, Crypto crypto){
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathInpFile, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOutFile, StandardCharsets.UTF_8)){
            int symbol;
            while((symbol=bufferedReader.read())!=-1){
                bufferedWriter.write(crypto.encodeChar((char) symbol));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void decodeFile (Path pathInpFile, Path pathOutFile, Crypto crypto){
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathInpFile, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOutFile, StandardCharsets.UTF_8)){
            int symbol;
            while((symbol=bufferedReader.read())!=-1){
                bufferedWriter.write(crypto.decodeChar((char) symbol));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bruteForse (Path pathInpFile, Path pathOutFile, Crypto crypto)  {
        ArrayList<Integer> numberOfCoincidences = new ArrayList<Integer>();
        int totalCout=0;
        int coutChar=0;
        int coutCharO=0;
        for (int i = 1; i <= Crypto.kyeSize ; i++) {
            crypto.setKey(i);
            decodeFile(pathInpFile, pathOutFile, crypto);
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
                System.out.println("Probability KEY - "+crypto.getKey());
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