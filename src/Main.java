import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Crypto crypto = new Crypto();
        int sw = encodeDecodeSwitch();
        keyReader(crypto);
        System.out.println("Input the path to the file to read");
        Path pathInpFile = readPath();
        System.out.println("Input the path to the file to write");
        Path pathOutFile = readPath();
        if (sw==1){
            encodeFile(pathInpFile,pathOutFile,crypto);
        }else {
            decodeFile(pathInpFile,pathOutFile,crypto);
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
            System.out.print("Input \"1\" for encode or   \"2\" for decode: ");
            inputStringKey = scanner.nextLine();
            String regex = "[1-2]";
            if (inputStringKey.matches(regex)) {
                return Integer.parseInt(inputStringKey);
            } else {
                System.out.println("Not Integer 1 or 2 pleas please replace!");
            }
        }
    }

    private static Path readPath(){
        String inputPath;
        while (true) {
            System.out.print("Pleas input Path: ");
            inputPath = scanner.nextLine();
            Path path = Path.of(inputPath);
            if (Files.exists(path)) {
                return path;
            } else {
                System.out.println("Not Path please replace!");
            }
        }
    }

    private static void encodeFile (Path pathInpFile,Path pathOutFile, Crypto crypto){
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathInpFile);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOutFile)){
            int symbol;
            while((symbol=bufferedReader.read())!=-1){
                bufferedWriter.write(crypto.encodeChar((char) symbol));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void decodeFile (Path pathInpFile, Path pathOutFile, Crypto crypto){
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathInpFile);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOutFile) ){
            int symbol;
            while((symbol=bufferedReader.read())!=-1){
                bufferedWriter.write(crypto.decodeChar((char) symbol));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}