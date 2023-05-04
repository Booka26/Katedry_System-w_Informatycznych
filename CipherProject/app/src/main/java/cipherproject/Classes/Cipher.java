package cipherproject.Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 * A1Z26, Cesar, Morse, Vigener, Binary
 * 
 */
public class Cipher {
    private static final int NUM_LETTERS = 26;
    private String text;
    private final String[] morseCode = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
            ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
            "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----." };

    // Empty constructor
    public Cipher() {
        this.text = "";
    }

    // Constructor with text
    public Cipher(String text) {
        this.text = text;
    }

    // Print text in console
    public void Print() {
        System.out.println(text);
    }

    // Setter text
    public void SetText(String text) {
        this.text = text;
    }

    // Getter text
    public String GetText() {
        return this.text;
    }

    // Load text from file
    public void FileLoadText(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            this.text += line + "\n";
        }
        reader.close();
    }

    // Save text to file
    public void FileSaveText(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(this.text);
        writer.close();
    }

    // Encrypts using the Cesar cipher with the given key
    public void Cesar(int key) {
        String cipherText = "";
        for (char c : text.toCharArray())
            if (Character.isLetter(c))
                if (Character.isUpperCase(c))
                    cipherText += (char) (((c + key) % 96) + 96);
                else if (Character.isLowerCase(c))
                    cipherText += (char) (((c + key) % 65) + 65);
                else if (Character.isDigit(c))
                    cipherText += (char) (((c + key) % 48) + 48);
                else
                    cipherText += c;

        text = cipherText;
    }

    // Decrypt using the Cesar cipher with the given key
    public void DeCesar(int key) {
        String cipherText = "";
        for (char c : text.toCharArray())
            if (Character.isLetter(c))
                if (Character.isUpperCase(c))
                    cipherText += (char) (((c + key) % 96) - 96);
                else if (Character.isLowerCase(c))
                    cipherText += (char) (((c + key) % 65) - 65);
                else if (Character.isDigit(c))
                    cipherText += (char) (((c + key) % 48) - 48);
                else
                    cipherText += c;

        text = cipherText;
    }

    // Encrypts using the Morse cipher
    public void Morse() {
        String cipherText = "";
        for (char c : text.toCharArray()) {
            if (c == 32)
                cipherText += "/";
            else if (Character.isDigit(c))
                cipherText += morseCode[(c % 48) + 26];
            else if (Character.isLetter(c))
                cipherText += morseCode[c % 97];

            cipherText += " ";
        }

        text = cipherText;
    }

    // Decrypt using the Cesar cipher
    public void DeMorse() {
        String cipherText = "";
        String word = "";
        for (char c : text.toCharArray()) {
            if (c == '.' || c == '-')
                word += c;
            if (c == ' ') {
                for (int j = 0; j < 36; j++) {
                    if (j >= 26 && j < 36) {
                        if (morseCode[j].equals(word)) {
                            cipherText += (char) (48 + (j - 26));
                            break;
                        }
                    } else if (morseCode[j].equals(word)) {
                        cipherText += (char) (97 + j);
                        break;
                    }
                }
                word = "";
            } else if (c == '/') {
                cipherText += " ";
            }
        }
        text = cipherText;
    }

    // Encrypts using the A-1 Z-26 cipher
    public void A1Z26() {
        String cipherText = "";
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                cipherText += (c % 96);
                cipherText += '-';
            } else {
                if (c == ' ')
                    cipherText += ' ';
                else
                    cipherText += c;
            }
        }
        text = cipherText;
    }

    // Decrypts using the A-1 Z-26 cipher
    public void DeA1Z26() {
        String cipherText = "";
        String word = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                cipherText += ' ';
                word = "";
            } else if (Character.isDigit(text.charAt(i)) && text.charAt(i + 1) == '-') {
                word += text.charAt(i);
                cipherText += (char) (Integer.parseInt(word.toString()) + 96);
                i++;
                word = "";
            } else if (Character.isDigit(text.charAt(i)) && text.charAt(i + 1) != '-') {
                if (text.charAt(i + 1) >= '!' && text.charAt(i + 1) <= '/'
                        || text.charAt(i + 1) >= ':' && text.charAt(i + 1) <= '@'
                        || text.charAt(i + 1) >= '[' && text.charAt(i + 1) <= '`'
                        || text.charAt(i + 1) >= '{' && text.charAt(i + 1) <= '~') {
                    cipherText += text.charAt(i);
                }
                word += text.charAt(i);
            } else if (text.charAt(i) == '-') {
                word = "";
            } else {
                cipherText += text.charAt(i);
            }
        }
        text = cipherText;
    }

    // Encrypts a message using the Vigener cipher with the given key
    public void Vigener(String key) {
        StringBuilder cipherText = new StringBuilder();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                // Convert letter to 0-based index (A=0, B=1, ..., Z=25)
                int letterIndex = Character.toUpperCase(c) - 'A';

                // Convert key letter to 0-based index and shift the letter
                int keyLetterIndex = Character.toUpperCase(key.charAt(keyIndex)) - 'A';
                int shiftedIndex = (letterIndex + keyLetterIndex) % NUM_LETTERS;

                // Convert shifted index back to letter and append to ciphertext
                char shiftedLetter = (char) ('A' + shiftedIndex);
                cipherText.append(shiftedLetter);

                // Move to next letter in key, wrapping around if necessary
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                // Non-letter characters are left unchanged
                cipherText.append(c);
            }
        }

        text = cipherText.toString();
    }

    // Decrypts a ciphertext using the Vigener cipher with the given key
    public void DeVigener(String key) {
        StringBuilder cipherText = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                // Convert letter to 0-based index (A=0, B=1, ..., Z=25)
                int letterIndex = Character.toUpperCase(c) - 'A';

                // Convert key letter to 0-based index and shift the letter back
                int keyLetterIndex = Character.toUpperCase(key.charAt(keyIndex)) - 'A';
                int shiftedIndex = (letterIndex - keyLetterIndex + NUM_LETTERS) % NUM_LETTERS;

                // Convert shifted index back to letter and append to message
                char shiftedLetter = (char) ('A' + shiftedIndex);
                cipherText.append(shiftedLetter);

                // Move to next letter in key, wrapping around if necessary
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                // Non-letter characters are left unchanged
                cipherText.append(c);
            }
        }

        text = cipherText.toString();
    }

    public int FromDecToBin(int dec) {
        int binary = 0, remainder, product = 1;
        while (dec != 0) {
            remainder = dec % 2;
            binary = binary + (remainder * product);
            dec = dec / 2;
            product *= 10;
        }
        return binary;
    }

    // Encrypts using the Binary code
    public void Binary() {
        int h = 0;
        String cipherText = "";
        for (char c : text.toCharArray()) {
            h = FromDecToBin(c);
            String binH = String.valueOf(h);
            binH = new StringBuilder(binH).insert(0, "0".repeat(8 - binH.length())).toString();
            cipherText += binH;
            cipherText += " ";
        }
        text = cipherText;
    }

    // Decrypts using the Binary code
    public void DeBinary() {
        String cipherText = "";
        int dec = 0;
        int a;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                cipherText += " ";
                i += 8;
            } else {
                for (int j = 0; j < 8; j++) {
                    a = (i + 7) - j;
                    if (text.charAt(a) == '1') {
                        dec += Math.pow(2, j);
                    }
                }
                cipherText += (char) dec;
                dec = 0;
                i += 8;
            }
        }
        text = cipherText;
    }

    @Override
    public String toString() {
        return "\n" + text;
    }
}
