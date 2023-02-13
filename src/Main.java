import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Crypto crypto = new Crypto();
        String inputStringKey;
        while (true){
         System.out.print("Pleas input integer key: ");
         inputStringKey = InputFromKeyboard();
         String regex = "\\d+";
         if (inputStringKey.matches(regex)){
             break;
         }else {
             System.out.println("Not Integer pleas please replace!");
         }

        }
        crypto.setKey(Integer.parseInt(inputStringKey));
        //try () {

        //}
        System.out.println(crypto.CodChar('o'));
        System.out.println(crypto.DecodeChar(crypto.CodChar('o')));



    }

    public static String InputFromKeyboard (){
        String inputFromKeyboard = scanner.nextLine();
        return inputFromKeyboard;
    }
}