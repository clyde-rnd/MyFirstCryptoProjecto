import java.io.*;
import java.nio.file.Path;

public class StartConsole {
    /**
     * Start Console Caesar Encoder, Decoder, bruteForce
     *Need cheng mode:
     * 1-Encode
     * 2-Decode
     * 2-bruteForce
     *
     * Next need input cryptoKEY
     *
     * Input and Output path
     *
     * for bruteForce automatically created tmp file
     */
    public static void main(String[] args) {
        CharCrypto charCrypto = new CharCrypto();
        int sw = Validator.encodeDecodeSwitchConsole();
        if (sw==1){
            Path pathInpFile = Validator.inputFilePathConsole();
            Path pathOutFile = Validator.outputFilePathConsole();
            Validator.keyReaderConsole(charCrypto);
            DecodeEncodeFile.encodeFile(pathInpFile,pathOutFile, charCrypto);
        }else if(sw==2) {
            Path pathInpFile = Validator.inputFilePathConsole();
            Path pathOutFile = Validator.outputFilePathConsole();
            Validator.keyReaderConsole(charCrypto);
            DecodeEncodeFile.decodeFile(pathInpFile,pathOutFile, charCrypto);
        }else {
            Path pathInpFile = Validator.inputFilePathConsole();
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