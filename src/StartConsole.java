import java.io.*;
import java.nio.file.Path;

public class StartConsole {

    public static void main(String[] args) {
        CharCrypto charCrypto = new CharCrypto();
        int sw = Validator.encodeDecodeSwitch();
        if (sw==1){
            Path pathInpFile = Validator.inputFilePath();
            Path pathOutFile = Validator.outputFilePath();
            Validator.keyReader(charCrypto);
            DecodeEncodeFile.encodeFile(pathInpFile,pathOutFile, charCrypto);
        }else if(sw==2) {
            Path pathInpFile = Validator.inputFilePath();
            Path pathOutFile = Validator.outputFilePath();
            Validator.keyReader(charCrypto);
            DecodeEncodeFile.decodeFile(pathInpFile,pathOutFile, charCrypto);
        }else {
            Path pathInpFile = Validator.inputFilePath();
            Path pathOutFile = null;
            try {
                pathOutFile = Validator.tempFile(pathInpFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DecodeEncodeFile.bruteForce(pathInpFile, pathOutFile, charCrypto);
        }
    }


}