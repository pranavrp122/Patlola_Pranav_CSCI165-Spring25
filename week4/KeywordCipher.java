import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class KeywordCipher {

    // removes duplicate letters from the keyword
    public static String prepareKeyWord(String keyword) {
        StringBuilder result = new StringBuilder();
        keyword = keyword.toUpperCase();
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            if (result.indexOf(String.valueOf(c)) == -1) {
                result.append(c);
            }
        }
        return result.toString();
    }

    // generates the cipher alphabet from the keyword then appends remaining letters of the alphabet
    public static String generateCipherAlphabet(String keyword) {
        String uniqueKey = prepareKeyWord(keyword);
        StringBuilder cipherAlphabet = new StringBuilder(uniqueKey);
        // append remaining letters from A to Z that are not in the keyword
        for (char c = 'A'; c <= 'Z'; c++) {
            if (uniqueKey.indexOf(c) == -1) {
                cipherAlphabet.append(c);
            }
        }
        return cipherAlphabet.toString();
    }

    // enciphers the message using the provided cipher alphabet
    public static String encipher(String cipherAlphabet, String message) {
        message = message.toUpperCase();
        StringBuilder result = new StringBuilder();
        String plainAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int index = plainAlphabet.indexOf(c);
            if (index != -1) {
                result.append(cipherAlphabet.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // deciphers the message using the provided cipher alphabet
    public static String decipher(String cipherAlphabet, String message) {
        message = message.toUpperCase();
        StringBuilder result = new StringBuilder();
        String plainAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int index = cipherAlphabet.indexOf(c);
            if (index != -1) {
                result.append(plainAlphabet.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // formats the given text into groups of five characters (ignoring whitespace and punctuation)
    public static String formatInFives(String text) {
        // remove whitespace and punctuation keeping only letters
        StringBuilder cleaned = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                cleaned.append(c);
            }
        }
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < cleaned.length(); i++) {
            if (i > 0 && i % 5 == 0) {
                formatted.append(" ");
            }
            formatted.append(cleaned.charAt(i));
        }
        return formatted.toString();
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("write in this form - java keywordcipher <filename> <keyword> [e/d]");
            return;
        }

        String fileName = args[0];
        String keyword = args[1];
        String mode = "e"; // default to encrypt
        if (args.length >= 3) {
            mode = args[2];
        }

        // check if file exists
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("file " + fileName + " does not exist");
            return;
        }

        // read file content
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
        } catch (IOException ex) {
            System.out.println("error reading file");
            return;
        }

        // generate the cipher alphabet from the keyword
        String cipherAlphabet = generateCipherAlphabet(keyword);
        String output = "";
        if (mode.equals("e")) {
            // encrypt the message and format output in columns of five characters
            String enciphered = encipher(cipherAlphabet, content.toString());
            output = formatInFives(enciphered);
        } else if (mode.equals("d")) {
            // decrypt the message; remove spaces first
            String cleaned = content.toString().replace(" ", "");
            output = decipher(cipherAlphabet, cleaned);
        } else {
            System.out.println("invalid mode, use e for encrypt or d for decrypt");
            return;
        }

        // determine output file name based on mode
        String outputFileName = "";
        if (mode.equals("e")) {
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex != -1) {
                outputFileName = fileName.substring(0, dotIndex) + "_encrypted" + fileName.substring(dotIndex);
            } else {
                outputFileName = fileName + "_encrypted";
            }
        } else {
            outputFileName = fileName.replace("_encrypted", "");
        }

        // write the output to the new file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            bw.write(output);
        } catch (IOException ex) {
            System.out.println("error writing to file");
            return;
        }

        System.out.println("output written to " + outputFileName);
    }
}

// when given a keyword, use the keywordcipher file and write the command line in this form - java KeywordCipher filename.txt keyword e/d
// otherwise, use ROT file and write the command in this form - JAVA ROT filename.txt e/d 13/47

// I had a little bit of trouble figuring out if the two classes should be in separate files and have different usecases
// so please let me know if I should change anything