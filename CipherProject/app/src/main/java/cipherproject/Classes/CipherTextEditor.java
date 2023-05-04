package cipherproject.Classes;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import cipherproject.Classes.Cipher;
import cipherproject.Classes.TextEditor;

public class CipherTextEditor {
    TextEditor textEditor;
    Cipher cipherText;

    // "A1Z26", "Cesar", "Morse", "Vigener", "Binary"
    public CipherTextEditor() {
        textEditor = new TextEditor();
        cipherText = new Cipher(textEditor.getTextPane().getText());

        textEditor.getCipherButton().addActionListener(e -> {
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

            } else if (cipherName == "A1Z26") {
                cipherText.A1Z26();
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Cesar") {
                cipherText.Cesar(3);
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Morse") {
                cipherText.Morse();
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Vigener") {
                cipherText.Vigener("lemon");
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Binary") {
                cipherText.Binary();
                textEditor.getTextPane().setText(cipherText.GetText());
            }
        });

        textEditor.getDecipherButton().addActionListener(e -> {
            StyledDocument doc = textEditor.getTextPane().getStyledDocument();
            try {
                cipherText.SetText(doc.getText(0, doc.getLength()));
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
            
            String cipherName = textEditor.getCipherComboBox().getSelectedItem().toString();
            if (cipherName == "A1Z26") {
                cipherText.DeA1Z26();
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Cesar") {
                cipherText.DeCesar(3);
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Morse") {
                cipherText.DeMorse();
                textEditor.getTextPane().setText(cipherText.GetText());

            } else if (cipherName == "Vigener") {
                cipherText.DeVigener("lemon");
                textEditor.getTextPane().setText(cipherText.GetText());
                
            } else if (cipherName == "Binary") {
                cipherText.DeBinary();
                textEditor.getTextPane().setText(cipherText.GetText());
            }
        });
    }

}
