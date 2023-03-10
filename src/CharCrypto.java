import java.util.HashMap;
import java.util.Map;

public class CharCrypto {

    /**
     * OUTPUT_CHAR_MAP for quick search number by char
     */
    private static final Map<Character,Integer> INPUT_CHAR_MAP = new HashMap<>() {{
        put('а',1);  put('б',2);  put('в',3);  put('г',4);  put('д',5);  put('е',6);  put('ё',7);
        put('ж',8);  put('з',9);  put('и',10); put('й',11); put('к',12); put('л',13); put('м',14);
        put('н',15); put('о',16); put('п',17); put('р',18); put('с',19); put('т',20); put('у',21);
        put('ф',22); put('х',23); put('ц',24); put('ч',25); put('ш',26); put('щ',27); put('ъ',28);
        put('ы',29); put('ь',30); put('э',31); put('ю',32); put('я',33);
        put('А',34); put('Б',35); put('В',36); put('Г',37); put('Д',38); put('Е',39); put('Ё',40);
        put('Ж',41); put('З',42); put('И',43); put('Й',44); put('К',45); put('Л',46); put('М',47);
        put('Н',48); put('О',49); put('П',50); put('Р',51); put('С',52); put('Т',53); put('У',54);
        put('Ф',55); put('Х',56); put('Ц',57); put('Ч',58); put('Ш',59); put('Щ',60); put('Ъ',61);
        put('Ы',62); put('Ь',63); put('Э',64); put('Ю',65); put('Я',66);
        put('.',67); put(',',68); put('"',69); put(':',70); put(';',71); put('-',72); put('!',73);
        put('?',74); put('%',75); put(' ',76);
        put('0',77); put('1',78); put('2',79); put('3',80); put('4',81); put('5',82); put('6',83);
        put('7',84); put('8',85);  put('9',86);
    }};

    /**
     * OUTPUT_CHAR_MAP for quick search char by number
     */
    private static final Map<Integer,Character> OUTPUT_CHAR_MAP = new HashMap<>() {{
        put(1,'а');  put(2,'б');  put(3,'в');  put(4,'г');  put(5,'д');  put(6,'е');  put(7,'ё');
        put(8,'ж');  put(9,'з');  put(10,'и'); put(11,'й'); put(12,'к'); put(13,'л'); put(14,'м');
        put(15,'н'); put(16,'о'); put(17,'п'); put(18,'р'); put(19,'с'); put(20,'т'); put(21,'у');
        put(22,'ф'); put(23,'х'); put(24,'ц'); put(25,'ч'); put(26,'ш'); put(27,'щ'); put(28,'ъ');
        put(29,'ы'); put(30,'ь'); put(31,'э'); put(32,'ю'); put(33,'я');
        put(34,'А'); put(35,'Б'); put(36,'В'); put(37,'Г'); put(38,'Д'); put(39,'Е'); put(40,'Ё');
        put(41,'Ж'); put(42,'З'); put(43,'И'); put(44,'Й'); put(45,'К'); put(46,'Л'); put(47,'М');
        put(48,'Н'); put(49,'О'); put(50,'П'); put(51,'Р'); put(52,'С'); put(53,'Т'); put(54,'У');
        put(55,'Ф'); put(56,'Х'); put(57,'Ц'); put(58,'Ч'); put(59,'Ш'); put(60,'Щ'); put(61,'Ъ');
        put(62,'Ы'); put(63,'Ь'); put(64,'Э'); put(65,'Ю'); put(66,'Я');
        put(67,'.'); put(68,','); put(69,'"'); put(70,':'); put(71,';'); put(72,'-'); put(73,'!');
        put(74,'?'); put(75,'%'); put(76,' ');
        put(77,'0'); put(78,'1'); put(79,'2'); put(80,'3'); put(81,'4'); put(82,'5'); put(83,'6');
        put(84,'7'); put(85,'8'); put(86,'9');
    }};
    public static int kyeSize = INPUT_CHAR_MAP.size();
    private int key;  //contains CryptoKey

    public int getKey() {
        return key;
    }

    /**
     * add Crypto Key in variable key
     *
     */
    public void setKey(int key) {
        int inKey = Validator.normolizeKey(key);
        if (inKey ==kyeSize){
            this.key = inKey-1;
        }else {
            this.key = inKey;
       }
    }
    /**
     * Encode char method
     *
     */
    public char encodeChar(char inputChar){
        int inpCharNum;
        if (INPUT_CHAR_MAP.get(inputChar) == null){ //if char no in MAP. skip this char
            return inputChar;
        }else {
            inpCharNum = INPUT_CHAR_MAP.get(inputChar);
        }
        int outputCharNum = Validator.normolizeKey((inpCharNum + key));
        return  OUTPUT_CHAR_MAP.get(outputCharNum);
    }
    /**
     * Decode char method
     *
     */
    public char decodeChar(char inputChar){
        int inpCharNum;
        if (INPUT_CHAR_MAP.get(inputChar) == null){
            return inputChar;
        }else {
            inpCharNum = INPUT_CHAR_MAP.get(inputChar);
        }
        int outputCharNum = Validator.normolizeKey(inpCharNum - key + kyeSize);
        return OUTPUT_CHAR_MAP.get(outputCharNum);
    }
}
