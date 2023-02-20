import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Validator {
    private static Scanner scanner = new Scanner(System.in);

    public static void keyReaderConsole(CharCrypto charCrypto){
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
        charCrypto.setKey(Integer.parseInt(inputStringKey));
    }

    public static boolean keyReaderGUI(String string){
            String regex = "\\d+";
            if (string.matches(regex) && Integer.parseInt(string)>0 && Integer.parseInt(string)<75) {
                return true;
            } else {
                return false;
            }
        }


    public static int encodeDecodeSwitch(){
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

    public static Path inputFilePath(){
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

    public static Path outputFilePath()  {
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

    public static Path tempFile(Path inputFile) throws IOException {
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

}
