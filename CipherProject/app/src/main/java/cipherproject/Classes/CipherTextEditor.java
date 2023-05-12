package cipherproject.Classes;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 * Encrypt and decrypt text from TextEditor using Cipher class
 */
public class CipherTextEditor {
    TextEditor textEditor;
    Cipher cipherText;

    /**
     * Empty constructor
     */
    // Constructor CipherTextEditor
    public CipherTextEditor() {
        textEditor = new TextEditor();
        cipherText = new Cipher(textEditor.getTextPane().getText());

        textEditor.getSaveFileItem().addActionListener(e -> {
            StyledDocument doc = textEditor.getTextPane().getStyledDocument();
            try {
                cipherText.SetText(doc.getText(0, doc.getLength()));
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    System.out.println(cipherText.GetText());
                    cipherText.FileSaveText(selectedFile.getPath());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        textEditor.getLoadFileItem().addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    cipherText.SetText("");
                    cipherText.FileLoadText(selectedFile.getPath());
                    textEditor.getTextPane().setText(cipherText.GetText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        textEditor.getEncryptButton().addActionListener(e -> {
            StyledDocument doc = textEditor.getTextPane().getStyledDocument();
            try {
                cipherText.SetText(doc.getText(0, doc.getLength()));
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }

            String cipherName = textEditor.getCipherComboBox().getSelectedItem().toString();
            if (cipherName == "A1Z26") {
                cipherText.A1Z26();
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Cesar") {
                String key = new String(textEditor.getKeyField().getPassword());
                int intKey = Integer.parseInt(key);
                cipherText.Cesar(intKey);
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Morse") {
                cipherText.Morse();
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Vigener") {
                String key = new String(textEditor.getKeyField().getPassword());
                cipherText.Vigener(key);
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Binary") {
                cipherText.Binary();
                textEditor.getTextPane().setText(cipherText.GetText());
            }
        });

        textEditor.getDecryptButton().addActionListener(e -> {
            StyledDocument doc = textEditor.getTextPane().getStyledDocument();
            try {
                cipherText.SetText(doc.getText(0, doc.getLength()).replaceAll("\\n", ""));
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }

            String cipherName = textEditor.getCipherComboBox().getSelectedItem().toString();
            if (cipherName == "A1Z26") {
                cipherText.DeA1Z26();
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Cesar") {
                String key = new String(textEditor.getKeyField().getPassword());
                int intKey = Integer.parseInt(key);
                cipherText.DeCesar(intKey);
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Morse") {
                cipherText.DeMorse();
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Vigener") {
                String key = new String(textEditor.getKeyField().getPassword());
                cipherText.DeVigener(key);
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Binary") {
                cipherText.DeBinary();
                textEditor.getTextPane().setText(cipherText.GetText());
            }
        });
    }
}
