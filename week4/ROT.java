import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ROT {

    // generates the character set for the given rot value (13 or 47) using loops
    public static String rotCharacterSet(int n) {
        StringBuilder sb = new StringBuilder();
        // for rot47, use ascii characters 33 to 126
        if (n == 47) {
            for (int i = 33; i <= 126; i++) {
                sb.append((char) i);
            }
        }
        // for rot13 use ascii characters 65 to 90 (uppercase letters)
        else {
            for (int i = 65; i <= 90; i++) {
                sb.append((char) i);
            }
        }
        return sb.toString();
    }

    // applies rot (either 13 or 47) to the input string 
    public static String applyROT(String input, int n) {
        String alphabet = rotCharacterSet(n);
        int len = alphabet.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int pos = alphabet.indexOf(c);

            if (pos != -1) {
                int newPos = (pos + n) % len;
                result.append(alphabet.charAt(newPos));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // main method uses command line arguments: filename, mode (e/d), and rotation number (13 or 47)
    public static void main(String[] args) {
        // check that exactly 3 arguments are provided
        if (args.length != 3) {
            System.out.println("make sure to give 3 arguments in this form - java rot <filename> <e/d> <13/47>");
            return;
        }

        String fileName = args[0];
        String mode = args[1];
        int rotValue = 13; // default

        // check if file exists
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("file " + fileName + " does not exist");
            return;
        }

        // validate mode argument
        if (!mode.equals("e") && !mode.equals("d")) {
            System.out.println("invalid argument");
            return;
        }

        // chcck the rotation number
        try {
            rotValue = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex) {
            System.out.println("invalid rotation number, defaulting to 13");
            rotValue = 13;
        }
        // if the provided rotation number is not 13 or 47, default to 13
        if (rotValue != 13 && rotValue != 47) {
            System.out.println("invalid rotation number, defaulting to 13");
            rotValue = 13;
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

        // apply rot encoding/decoding
        String processed = applyROT(content.toString(), rotValue);

        // determine output file name
        String outputFileName = "";
        if (mode.equals("e")) {
            // encryption
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex != -1) {
                outputFileName = fileName.substring(0, dotIndex) + "_encrypted" + fileName.substring(dotIndex);
            } else {
                outputFileName = fileName + "_encrypted";
            }
        } else {
            // for decryption, remove _encrypted from the file name
            outputFileName = fileName.replace("_encrypted", "");
        }

        // write to the output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            bw.write(processed);
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