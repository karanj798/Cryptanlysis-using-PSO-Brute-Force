import java.io.*;
import java.util.*;

public class Vigenere {
    private static String msg = "";
    private static String spaceMsg = "";
    private static String keyK = "";
    private static String sKeyk = "";
    private static String kMap = "";
    private static String skMap = "";
    private static String cipherTxt = "";
    private static String spacedTxt = "";
    private static String spaceCipher = "";

    private static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    private static String decry = "";
    public static ArrayList<String> words = new ArrayList<>();
    public static ArrayList<Integer> space = new ArrayList<>();

    public static void getContents(int limit) throws FileNotFoundException {
        File file = new File("message.txt");
        Scanner input = new Scanner(file);
        String content = "";
        while (input.hasNextLine()) {
            content = content + input.nextLine();
        }
        input.close();

        // int startingIndex = (int)(Math.random() * content.length() - limit +1);
        content = content.toLowerCase();

        content = content.replaceAll("[^a-z ]+", "");
        for (int i = 0; i < content.length(); i++) {
            if ((int) content.charAt(i) == 32) {
                space.add(i);
            }
        }
        int ch = 0;
        int count = 0;
        while (ch != limit) {
            if ((int) content.charAt(count) != 32) {
                ch++;
            }
            count++;
        }
        StringBuilder sb = new StringBuilder("");

        for (int l = 0; l < count; l++) {
            sb.append(content.charAt(l));
        }
        content = content.replaceAll("[^a-z]+", "");

        String message = "";
        for (int l = 0; l < limit; l++) {
            message = message + content.charAt(l);
        }

        msg = message;

        String fileName = "limit.txt";
        try {
            PrintWriter outputStream = new PrintWriter(fileName);
            outputStream.println(message.toUpperCase());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String space = "spacemsg.txt";

        try {
            PrintWriter outputStream = new PrintWriter(space);
            outputStream.println((new String(sb)).toUpperCase());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String filename, String cipher, String keymap, String exportfilename)
            throws FileNotFoundException {
        File file = new File(filename);
        Scanner input = new Scanner(file);
        String msg = "";
        while (input.hasNextLine()) {
            msg = msg + input.nextLine();
        }
        input.close();

        String mapping = "";
        File keym = new File(keymap);
        Scanner in = new Scanner(keym);
        while (in.hasNextLine()) {
            mapping = mapping + in.nextLine();
        }
        in.close();
        for (int j = 0; j < msg.length(); j++) {
            int plain = 0;
            if ((int) msg.charAt(j) != 32) {// as long as there is no space
                for (int a = 0; a < alphabet.length; a++) {
                    if (alphabet[a] == msg.charAt(j)) {
                        plain = a;
                    }
                }
            } else {
                plain = plain + (char) 32;
            }
            int map = 0;
            if ((int) mapping.charAt(j) != 32) {// as long as there is no space
                for (int al = 0; al < alphabet.length; al++) {
                    if (alphabet[al] == mapping.charAt(j)) {
                        map = al;
                    }
                }
            } else {
                map = map + (char) 32;
            }
            int cip = 0;
            if ((int) msg.charAt(j) != 32) {// as long as there is no space
                cip = ((plain + map) % 26);
                cipher = cipher + alphabet[cip];
            } else {
                cipher = cipher + (char) 32;
            }
        }

        try {
            PrintWriter outputStream = new PrintWriter(exportfilename);
            outputStream.println(cipher);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        cipherTxt = cipher;
        return cipher;
    }

    public static String decrypt(String key, String filename) throws FileNotFoundException {
        File file = new File(filename);
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

    public static void main(String[] args) throws FileNotFoundException {
        Scanner characters = new Scanner(System.in);
        System.out.println("Press 1 to encrypt or 0 to decrypt: ");
        int dore = characters.nextInt();

        Kasiski kasiski = new Kasiski();

        String foundKey = "";
        if (dore == 1) {
            System.out.println("Please enter the number of characters to be fetched from the file: ");
            int num = characters.nextInt();
            getContents(num);
            System.out.println("Enter the key: ");
            String key = characters.next().toUpperCase();
            keyK = key;
            
            kasiski.map("limit.txt", "keymap.txt", key);
            encrypt("limit.txt", cipherTxt, "keymap.txt", "cipher.txt");
            System.out.println("\nPlaintext: " + msg.toUpperCase() + "\n");
            System.out.println("Ciphertext: " + cipherTxt + "\n");

            kasiski.map("spacemsg.txt", "spacekeymap.txt", key);
            encrypt("spacemsg.txt", spaceCipher, "spacekeymap.txt", "cipher_space.txt");
            // encrypt("spacemsg.txt", cipherTxt, "keymap.txt", "cipher_space.txt");

        } else if (dore == 0) {
            System.out.println(
                    "Press 1 to enter key or press 0 to enable program to find the key and decrypt the ciphertext: ");
            int kornk = characters.nextInt();
            if (kornk == 1) {
                System.out.println("Enter the key: ");
                String uskey = characters.next();
                decrypt(uskey, "cipher.txt");
                System.out.println("\nDecrypted: " + decry + "\n");
            } else if (kornk == 0) {
                long start_time = System.nanoTime();
                int foundKeyLength = kasiski.findKeyLength(); // find keylength using modified kisiski
                long end_time = System.nanoTime();
                double difference = (end_time - start_time) / 1e6;
                System.out.println("\nLength of key might be of length: " + foundKeyLength);
                System.out.println("Time it took to find the length of the key: " + difference + " ms");

                System.out.println("\nPerforming Bruteforce...");
                start_time = System.nanoTime();
                foundKey = kasiski.bruteForce(foundKeyLength); // attack vigenere cipher using bruteforce
                end_time = System.nanoTime();
                difference = (end_time - start_time) / 1e9;
                System.out.println("\n\nFound key is: " + foundKey);
                System.out.println("Time it took to perform brute froce: " + difference + " sec");

            } else {
                System.out.println("Incorrect choice");
            }
        } else {
            System.out.println("Incorrect choice");
        }
        characters.close();
    }

}