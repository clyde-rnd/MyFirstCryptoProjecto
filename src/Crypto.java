import java.util.HashMap;
import java.util.Map;

public class Crypto {
    private static final Map<Character,Integer> INPUT_CHAR_MAP = new HashMap<>() {{
        put('a',1); put('б',2); put('в',3); put('г',4); put('д',5);  put('е',6); put('ё',7);
        put('ж',8); put('з',9); put('и',10); put('й',11); put('к',12); put('л',13); put('м',14);
        put('н',15); put('о',16); put('п',17); put('р',18); put('с',19); put('т',20); put('у',21);
        put('ф',22); put('х',23); put('ц',24); put('ч',25); put('ш',26); put('щ',27); put('ъ',28);
        put('ы',29); put('ь',30); put('э',31); put('ю',32); put('я',33); put('.',34); put(',',35);
        put('"',36); put(':',37); put(';',38); put('-',39); put('!',40); put('?',41); put('%',42);
    }};
    private static final Map<Integer,Character> OUTPUT_CHAR_MAP = new HashMap<>() {{
        put(1,'a'); put(2,'б'); put(3,'в'); put(4,'г'); put(5,'д'); put(6,'е'); put(7,'ё');
        put(8,'ж'); put(9,'з'); put(10,'и'); put(11,'й'); put(12,'к'); put(13,'л'); put(14,'м');
        put(15,'н'); put(16,'о'); put(17,'п'); put(18,'р'); put(19,'с'); put(20,'т'); put(21,'у');
        put(22,'ф'); put(23,'х'); put(24,'ц'); put(25,'ч'); put(26,'ш'); put(27,'щ'); put(28,'ъ');
        put(29,'ы'); put(30,'ь'); put(31,'э'); put(32,'ю'); put(33,'я'); put(34,'.'); put(35,',');
        put(36,'"'); put(37,':'); put(38,';'); put(39,'-'); put(40,'!'); put(41,'?'); put(42,'%');
    }};


    private int key;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = CheckKeySize(key);
    }

    private  int CheckKeySize (int keyInp){
        int outKey;
       if (keyInp<=INPUT_CHAR_MAP.size()){
           outKey = keyInp;
       }else {
           outKey = keyInp%INPUT_CHAR_MAP.size();
       }
       if (outKey ==INPUT_CHAR_MAP.size()){
           return outKey+1;
       }else {
           return outKey;
       }
    }
    public char CodChar (char inputChar){
        boolean consistChar = Character.isUpperCase(inputChar); // upper or lower case state
        int inpCharNum;
        if (INPUT_CHAR_MAP.get(Character.toLowerCase(inputChar)) == null){
            return inputChar;
        }else {
            inpCharNum = INPUT_CHAR_MAP.get(Character.toLowerCase(inputChar));
        }
        int outputCharNum = CheckKeySize(inpCharNum + key);
        if (consistChar){
           Character outChar = OUTPUT_CHAR_MAP.get(outputCharNum);
           return Character.toUpperCase(outChar);
        } else {
            return OUTPUT_CHAR_MAP.get(outputCharNum);
        }

    }

    public char DecodeChar (char inputChar){
        boolean consistChar = Character.isUpperCase(inputChar); // upper or lower case state
        int inpCharNum;
        if (INPUT_CHAR_MAP.get(Character.toLowerCase(inputChar)) == null){
            return inputChar;
        }else {
            inpCharNum = INPUT_CHAR_MAP.get(Character.toLowerCase(inputChar));
        }
        int outputCharNum = Math.abs(CheckKeySize(inpCharNum - key));
        if (consistChar){
            Character outChar = OUTPUT_CHAR_MAP.get(outputCharNum);
            return Character.toUpperCase(outChar);
        } else {
            return OUTPUT_CHAR_MAP.get(outputCharNum);
        }

    }
}
