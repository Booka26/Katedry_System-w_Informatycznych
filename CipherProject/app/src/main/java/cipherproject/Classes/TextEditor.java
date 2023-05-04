package cipherproject.Classes;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Visual part of program made with swing
 */
public class TextEditor implements ActionListener {
    private JFrame frame;
    private JTextPane textPane;
    private JToolBar toolBar;
    private JPopupMenu popupMenu;
    private JMenuItem cutItem;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;
    private JMenuItem deleteItem;
    private JMenuItem selectAllItem;
    private JCheckBoxMenuItem boldItem;
    private JCheckBoxMenuItem italicItem;
    private JCheckBoxMenuItem underlineItem;
    private JComboBox<String> fontCombo;
    private JComboBox<Integer> sizeCombo;
    private JComboBox<String> cipherComboBox;
    private JToggleButton encryptButton;
    private JToggleButton decryptButton;
    private JPasswordField keyField;

    private String cipherName;

    /**
     * Empty constructor
     */
    public TextEditor() {

        // Create the main application window
        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Creating a toolbar
        toolBar = new JToolBar();

        // Create a combo box for cipher selection
        cipherComboBox = new JComboBox<>(new String[] { "A1Z26", "Cesar", "Morse", "Vigener", "Binary" });
        cipherComboBox.addActionListener(e -> {
            cipherName = cipherComboBox.getSelectedItem().toString();
        });
        toolBar.add(cipherComboBox);

        // Creating a encrypt button
        encryptButton = new JToggleButton();
        // Adding a encrypt button to toolbar
        toolBar.add(encryptButton);

        // Creating a decrypt button
        decryptButton = new JToggleButton();
        // Adding a decrypt button to toolbar
        toolBar.add(decryptButton);
        
        // Creating key field
        keyField = new JPasswordField();

        // Adding a key field to tool bar
        toolBar.add(keyField);

        // Creating a text area
        textPane = new JTextPane();
        textPane.setContentType("text/html");

        // Creating a pop-up menu
        popupMenu = new JPopupMenu();

        // Creating menu items
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        deleteItem = new JMenuItem("Delete");
        selectAllItem = new JMenuItem("Select All");

        // Add listeners to menu items
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        deleteItem.addActionListener(this);
        selectAllItem.addActionListener(this);

        // Create menu items for text formatting
        boldItem = new JCheckBoxMenuItem("Bold");
        italicItem = new JCheckBoxMenuItem("Italic");
        underlineItem = new JCheckBoxMenuItem("Underline");

        // Add listeners to menu items for text formatting
        boldItem.addActionListener(this);
        italicItem.addActionListener(this);
        underlineItem.addActionListener(this);

        // Create combo boxes for font and font size selection
        fontCombo = new JComboBox<>(new String[] { "Arial", "Times New Roman", "Verdana" });
        sizeCombo = new JComboBox<>(new Integer[] { 8, 10, 12, 14, 16, 18, 20, 22, 24 });

        // Adding listeners to combo boxes
        fontCombo.addActionListener(this);
        sizeCombo.addActionListener(this);

        // Adding menu items to the popup menu
        popupMenu.add(cutItem);
        popupMenu.add(copyItem);
        popupMenu.add(pasteItem);
        popupMenu.add(deleteItem);
        popupMenu.addSeparator();
        popupMenu.add(selectAllItem);
        popupMenu.addSeparator();
        popupMenu.add(boldItem);
        popupMenu.add(italicItem);
        popupMenu.add(underlineItem);
        popupMenu.addSeparator();
        popupMenu.add(fontCombo);
        popupMenu.add(sizeCombo);

        // Add a popup menu to the text area
        textPane.setComponentPopupMenu(popupMenu);

        // Add a tool bar to the main window
        frame.add(toolBar, BorderLayout.PAGE_START);

        // Adding a text area 
        frame.getContentPane().add(textPane, BorderLayout.CENTER);

        // Displaying the main window
        frame.setVisible(true);
    }
    public JPasswordField getKeyField() {
        return keyField;
    }

    /**
     * Adding doing to events
     * @param e
     * Action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Processing menu items clicks
        if (e.getSource() == cutItem) {
            textPane.cut();
        } else if (e.getSource() == copyItem) {
            textPane.copy();
        } else if (e.getSource() == pasteItem) {
            textPane.paste();
        } else if (e.getSource() == deleteItem) {
            textPane.replaceSelection("");
        } else if (e.getSource() == selectAllItem) {
            textPane.selectAll();
        } else if (e.getSource() == boldItem) {
            
            // Formatting text in bold
            MutableAttributeSet attrs = textPane.getInputAttributes();
            Boolean isBold = (Boolean) attrs.getAttribute(StyleConstants.Bold);
            System.out.println(isBold);
            StyleConstants.setBold(attrs, !isBold);
            textPane.setCharacterAttributes(attrs, true);
        } else if (e.getSource() == italicItem) {
            
            // Formatting text in italics
            MutableAttributeSet attrs = textPane.getInputAttributes();
            Boolean italic = (Boolean) attrs.getAttribute(StyleConstants.Italic);
            
            StyleConstants.setItalic(attrs, !italic);
            textPane.setCharacterAttributes(attrs, true);
        } else if (e.getSource() == underlineItem) {
            
            // Underline text formatting
            MutableAttributeSet attrs = textPane.getInputAttributes();
            Boolean underline = (Boolean) attrs.getAttribute(StyleConstants.Underline);
            StyleConstants.setUnderline(attrs, !underline);
            textPane.setCharacterAttributes(attrs, true);
        } else if (e.getSource() == fontCombo) {
            
            // Changing the text font
            MutableAttributeSet attrs = textPane.getInputAttributes();
            String fontName = (String) fontCombo.getSelectedItem();
            StyleConstants.setFontFamily(attrs, fontName);
            textPane.setCharacterAttributes(attrs, true);
        } else if (e.getSource() == sizeCombo) {
            
            // Changing the text font size
            MutableAttributeSet attrs = textPane.getInputAttributes();
            Integer fontSize = (Integer) sizeCombo.getSelectedItem();
            StyleConstants.setFontSize(attrs, fontSize);
            textPane.setCharacterAttributes(attrs, true);
        }
    }
    
    /**
     * Encrypt button getter
     * @return
     * JToggleButton encryptButton
     */
    public JToggleButton getEncryptButton() {
        return encryptButton;
    }

    /**
     * Decrypt button getter
     * @return
     * JToggleButton decryptButton
     */
    public JToggleButton getDecryptButton() {
        return decryptButton;
    }

    /**
     * cipher name getter
     * @return
     * return which cipher was chosen with cipher combo box
     */
    public String getCipherName() {
        return cipherName;
    }
    
    /**
     * Frame getter
     * @return
     * JFrame frame
     */
    public JFrame getFrame() {
        return frame;
    }
    
    /**
     * Text Pane getter
     * @return
     * JTextPane textPane
     */
    public JTextPane getTextPane() {
        return textPane;
    }

    /**
     * Pop-up menu getter
     * @return
     * JPopupMenu popupMenu
     */
    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    /**
     * Cut pop-up menu item getter
     * @return
     * private JMenuItem cutItem
     */
    public JMenuItem getCutItem() {
        return cutItem;
    }

    /**
     * Copy pop-up menu item getter
     * @return
     * JMenuItem copyItem
     */
    public JMenuItem getCopyItem() {
        return copyItem;
    }

    /**
     * Paste pop-up menu item getter
     * @return
     * JMenuItem pasteItem
     */
    public JMenuItem getPasteItem() {
        return pasteItem;
    }

    /**
     * Delete pop-up menu item getter
     * @return
     * JMenuItem deleteItem
     */
    public JMenuItem getDeleteItem() {
        return deleteItem;
    }

    /**
     * Select all pop-up menu item getter
     * @return
     * JMenuItem selectAllItem
     */
    public JMenuItem getSelectAllItem() {
        return selectAllItem;
    }

    /**
     * Bold checkbox pop-up menu item getter
     * @return
     * JCheckBoxMenuItem boldItem
    
     */
    public JCheckBoxMenuItem getBoldItem() {
        return boldItem;
    }

    /**
     * Italic checkbox pop-up menu item getter
     * @return
     * JCheckBoxMenuItem italicItem
     */
    public JCheckBoxMenuItem getItalicItem() {
        return italicItem;
    }

    /**
     * Underline checkbox pop-up menu getter
     * @return
     * JCheckBoxMenuItem underlineItem
     */
    public JCheckBoxMenuItem getUnderlineItem() {
        return underlineItem;
    }

    /**
     * Font combo box pop-up menu item getter
     * @return
     * JComboBox fontCombo
     */
    public JComboBox<String> getFontCombo() {
        return fontCombo;
    }

    /**
     * Size combo box pop-up menu item getter
     * @return
     * JComboBox sizeCombo
     */
    public JComboBox<Integer> getSizeCombo() {
        return sizeCombo;
    }

    /**
     * Tool bar getter
     * @return
     * JToolBar toolBar
     */
    public JToolBar getToolBar() {
        return toolBar;
    }

    /**
     * Cipher combo box getter
     * @return
     * JComboBox cipherComboBox
     */
    public JComboBox<String> getCipherComboBox() {
        return cipherComboBox;
    }
}