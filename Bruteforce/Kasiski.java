import java.io.*;
import java.util.*;

public class Kasiski {

    private String msg = "";
    private String spaceMsg = "";
    private String keyK = "";
    private String skeyk = "";
    private String kMap = "";
    private String sKMap = "";
    private String cipherTxt = "";
    private String spacedTxt = "";
    private String spaceCipher = "";

    private char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    private String decry = "";
    public ArrayList<String> words = new ArrayList<>();
    public ArrayList<Integer> space = new ArrayList<>();

    public Kasiski() {
        this.words = new ArrayList<>();
        this.space = new ArrayList<>();
    }

    public String map(String fileName, String kMapF, String key) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        String msg = "";
        while (input.hasNextLine()) {
            msg = msg + input.nextLine();
        }
        input.close();
        String mapped = "";
        int k = 0;
        for (int i = 0; i < msg.length(); i++) {
            int plain = (int) msg.charAt(i);
            if (plain != 32) {// as long as there no space
                if (k < key.length()) {
                    mapped = mapped + key.charAt(k);
                    k++;
                } else {
                    k = 0;
                    mapped = mapped + key.charAt(k);
                    k++;
                }
            } else {
                mapped = mapped + (char) 32;
            }

        }
        // System.out.println(mapped);
        try {
            PrintWriter outputStream = new PrintWriter(kMapF);
            outputStream.println(mapped);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapped;
    }

    /*
     * attack the cipher
     */
    public int findKeyLength() throws FileNotFoundException {
        File file = new File("cipher.txt");
        Scanner input = new Scanner(file);
        String content = "";
        while (input.hasNextLine()) {
            content = content + input.nextLine();
        }
        content = content.toUpperCase();
        input.close();

        File file1 = new File("words.txt");
        Scanner dictionary = new Scanner(file1);
        while (dictionary.hasNextLine()) {
            String temp = dictionary.nextLine();
            words.add(temp);
        }
        dictionary.close();

        ArrayList<String> dectxt = new ArrayList<String>();
        ArrayList<Integer> keyLen = new ArrayList<Integer>();
        ArrayList<Integer> al = new ArrayList<>();
        ArrayList<String> substrings = new ArrayList<String>();// will store substrings that are found
        Map<String, Integer> repeatSub = new HashMap<String, Integer>();// will store substrings that repeat in the
                                                                        // cipher
        Map<String, Integer> throcc = new HashMap<String, Integer>();
        Map<String, Integer> fourocc = new HashMap<String, Integer>();
        Map<String, Integer> fiveocc = new HashMap<String, Integer>();
        Map<String, Integer> sixocc = new HashMap<String, Integer>();
        Map<String, Integer> sevocc = new HashMap<String, Integer>();
        int difference = 0;
        // find substrings and the difference between each occurance of the substring
        for (int sublen = 8; sublen >= 2; sublen--) {
            for (int pos = 0; pos <= content.length() - sublen; pos++) {
                String subStr = content.substring(pos, pos + sublen);
                if (substrings.contains(subStr)) {// substring has already been discovered before
                    if (!(repeatSub.values().contains(subStr))) {
                        repeatSub.put(subStr, pos);
                        difference = pos - content.indexOf(subStr);
                        if (difference < 0) {
                            difference = difference * (-1);
                        }
                        for (int pKeyLen = 3; pKeyLen < 10; pKeyLen++) {
                            if (difference % pKeyLen == 0) {
                                al.add(pKeyLen);

                                if (!(keyLen.contains(pKeyLen))) {
                                    keyLen.add(pKeyLen);
                                }

                            }
                        }
                    } else if (repeatSub.values().contains(subStr)) {
                        throcc.put(subStr, pos);
                        difference = pos - repeatSub.get(subStr);
                        if (difference < 0) {
                            difference = difference * (-1);
                        }
                        for (int pKeyLen = 3; pKeyLen < 10; pKeyLen++) {
                            if (difference % pKeyLen == 0) {
                                al.add(pKeyLen);

                                if (!(keyLen.contains(pKeyLen))) {
                                    keyLen.add(pKeyLen);

                                }

                            }
                        }
                    } else if (throcc.values().contains(subStr)) {
                        fourocc.put(subStr, pos);
                        difference = pos - throcc.get(subStr);
                        if (difference < 0) {
                            difference = difference * (-1);
                        }
                        for (int pKeyLen = 3; pKeyLen < 10; pKeyLen++) {
                            if (difference % pKeyLen == 0) {
                                al.add(pKeyLen);

                                if (!(keyLen.contains(pKeyLen))) {
                                    keyLen.add(pKeyLen);

                                }

                            }
                        }
                    } else if (fourocc.values().contains(subStr)) {
                        fiveocc.put(subStr, pos);
                        difference = pos - fourocc.get(subStr);
                        if (difference < 0) {
                            difference = difference * (-1);
                        }
                        for (int pKeyLen = 3; pKeyLen < 10; pKeyLen++) {
                            if (difference % pKeyLen == 0) {
                                al.add(pKeyLen);

                                if (!(keyLen.contains(pKeyLen))) {
                                    keyLen.add(pKeyLen);

                                }

                            }
                        }
                    } else if (fiveocc.values().contains(subStr)) {
                        sixocc.put(subStr, pos);
                        difference = pos - fiveocc.get(subStr);
                        if (difference < 0) {
                            difference = difference * (-1);
                        }
                        for (int pKeyLen = 3; pKeyLen < 10; pKeyLen++) {
                            if (difference % pKeyLen == 0) {
                                al.add(pKeyLen);

                                if (!(keyLen.contains(pKeyLen))) {
                                    keyLen.add(pKeyLen);

                                }

                            }
                        }
                    } else if (sixocc.values().contains(subStr)) {
                        sevocc.put(subStr, pos);
                        difference = pos - sixocc.get(subStr);
                        if (difference < 0) {
                            difference = difference * (-1);
                        }
                        for (int pKeyLen = 3; pKeyLen < 10; pKeyLen++) {
                            if (difference % pKeyLen == 0) {
                                al.add(pKeyLen);

                                if (!(keyLen.contains(pKeyLen))) {
                                    keyLen.add(pKeyLen);

                                }

                            }
                        }
                    }
                } else {
                    substrings.add(subStr); // new substring found so add to array list of substrings
                }
            }
        }
        return findOccurances(al);
    }

    public String bruteForce(int keyLength) throws FileNotFoundException {
        File file = new File("spacemsg.txt");
        Scanner input = new Scanner(file);
        String temp = "";
        while (input.hasNextLine()) {
            temp = temp + input.nextLine();
        }
        input.close();
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // str = str.toLowerCase();
        ArrayList<String> keys = new ArrayList<>();
        permute(str, "", keyLength, keys);

        //////////////////////////// CHECK WORD DECRYPTED IN DICTIONARY ////////////////////////////
        String pbKey = "";
        int maxOccurance = 0;
        String decryptMsg = "";
        // System.out.println(keys);

        for (String possibleKey : keys) {
            int tempCounter = 0;
            decryptMsg = decrypt(possibleKey, "cipher_space.txt");
            String[] decryptWordArr = decryptMsg.split(" ");
            
            for (String decryptWord : decryptWordArr) {
                for (String wordInDictionary : words) {
                    if (wordInDictionary.equalsIgnoreCase(decryptWord)) {
                        tempCounter++;
                    }
                }
            }

            if (tempCounter > maxOccurance) {
                maxOccurance = tempCounter;
                pbKey = possibleKey;
            }

			//at least close to the length
            if (maxOccurance == decryptWordArr.length || maxOccurance == decryptWordArr.length-1 || maxOccurance == decryptWordArr.length-2 || maxOccurance == decryptWordArr.length-3) {
                break;
            }
            
        }

        return pbKey;
    }

    public void permute(String alphabet, String prefix, int keyLen, ArrayList<String> c) {
        if (prefix.length() == keyLen) {
            c.add(prefix);
        } else {
            for (int i = 0; i < alphabet.length(); i++) {
                permute(alphabet, prefix + alphabet.charAt(i), keyLen, c);
            }
        }
    }

    public int findOccurances(ArrayList<Integer> al) {
        int counter = 0, temp = 0;
        int arr[] = new int[1000];
        for (int i = 0; i < al.size(); i++) {
            temp = al.get(i);
            arr[temp]++;
        }
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[max]) {
                max = i;
            }
        }
        return max;
    }

    public String decrypt(String key, String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        String content = "";
        while (input.hasNextLine()) {
            content = content + input.nextLine();
        }
        input.close();
        cipherTxt = content;

        String mapped = "";
        int k = 0;
        for (int i = 0; i < cipherTxt.length(); i++) {
            int plain = (int) cipherTxt.charAt(i);
            if (plain != 32) {// as long as there no space
                if (k < key.length()) {
                    mapped = mapped + key.charAt(k);
                    k++;
                } else {
                    k = 0;
                    mapped = mapped + key.charAt(k);
                    k++;
                }
            } else {
                mapped = mapped + (char) 32;
            }
        }
        kMap = mapped;
        String decr = "";
        for (int j = 0; j < cipherTxt.length(); j++) {
            int cipher = 0;
            if ((int) cipherTxt.charAt(j) != 32) {// as long as there is no space
                for (int a = 0; a < alphabet.length; a++) {
                    if (alphabet[a] == cipherTxt.charAt(j)) {
                        cipher = a;
                    }
                }
            }

            int keyd = 0;
            if ((int) kMap.charAt(j) != 32) {// as long as there is no space
                for (int al = 0; al < alphabet.length; al++) {
                    if (alphabet[al] == kMap.charAt(j)) {
                        keyd = al;
                    }
                }
            }

            int cip = 0;
            if (cipherTxt.charAt(j) != 32) {// as long as there is no space
                if (0 > (cipher - keyd)) {
                    cip = 26 + (cipher - keyd);
                } else {
                    cip = ((cipher - keyd) % 26);
                }
                decr = decr + alphabet[cip];
            } else {
                decr = decr + (char) 32;
            }
        }
        decry = decr;
        return decr;
    }
}