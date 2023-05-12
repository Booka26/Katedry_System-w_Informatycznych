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
    private JPanel mainPanel;
    private JPanel cipherMenuPanel;
    private JPanel cipherMenuButtonsPanel;
    private JTextPane textPane;
    private JPopupMenu popupMenu;
    private JMenuItem cutItem;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;
    private JMenuItem deleteItem;
    private JMenuItem selectAllItem;
    private JMenuItem saveFileItem;
    private JMenuItem loadFileItem;

    private JComboBox<String> cipherComboBox;
    
    private JButton encryptButton;
    private JButton decryptButton;
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
        frame.setIconImage(new ImageIcon(getClass().getResource("/Icons/mainIco.png")).getImage());

        // Creating a main panel
        mainPanel = new JPanel();
        // Creating a main panel with 2 rows and 1 cols
        mainPanel.setLayout(new GridBagLayout());
        // Creating grid bag constraints
        GridBagConstraints gbc = SetGridBagConstraints();
        // Creating a cipher menu panel
        cipherMenuPanel = new JPanel();
        // Creating a cipher menu panel with 3 rows and 1 cols
        cipherMenuPanel.setLayout(new GridLayout(3, 1));
        // Creating a cipher buttons menu panel
        cipherMenuButtonsPanel = new JPanel();
        // Creating a cipher buttons menu with 1 rows and 2 cols
        cipherMenuButtonsPanel.setLayout(new GridLayout(1, 2));

        // Create a combo box for cipher selection
        cipherComboBox = new JComboBox<>(new String[] { "A1Z26", "Cesar", "Morse", "Vigener", "Binary" });
        cipherComboBox.addActionListener(e -> {
            cipherName = cipherComboBox.getSelectedItem().toString();
        });
        // Creating a encrypt button
        encryptButton = SetButton(encryptButton, "Encrypt", new Dimension(50, 100));
        // Creating a decrypt button
        decryptButton = SetButton(decryptButton, "Decrypt", new Dimension(50, 100));

        // Creating key field
        keyField = new JPasswordField();

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
        saveFileItem = new JMenuItem("Save");
        loadFileItem = new JMenuItem("Open");
        
        // Add listeners to menu items
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        deleteItem.addActionListener(this);
        selectAllItem.addActionListener(this);

        // Adding menu items to the popup menu
        popupMenu.add(cutItem);
        popupMenu.add(copyItem);
        popupMenu.add(pasteItem);
        popupMenu.add(deleteItem);
        popupMenu.addSeparator();
        popupMenu.add(selectAllItem);
        popupMenu.addSeparator();
        popupMenu.add(saveFileItem);
        popupMenu.add(loadFileItem);

        // Adding cipher menu panel to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 0;
        mainPanel.add(cipherMenuPanel, gbc);

        // Adding a encrypt button to toolbar
        cipherMenuButtonsPanel.add(encryptButton);
        // Adding a decrypt button to toolbar
        cipherMenuButtonsPanel.add(decryptButton);

        // Adding cipher combo box to cipher menu panel
        cipherMenuPanel.add(cipherComboBox);
        // Adding a key field to cipher menu panel
        cipherMenuPanel.add(keyField);
        // Adding cipher menu buttons panel to cipher menu panel
        cipherMenuPanel.add(cipherMenuButtonsPanel);

        // Add a popup menu to the text area
        textPane.setComponentPopupMenu(popupMenu);
        // Adding a text area
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 300;
        mainPanel.add(textPane, gbc);
        // Add a main panel to the main window
        frame.add(mainPanel);
        // Displaying the main window
        frame.setVisible(true);
    }

    public JMenuItem getSaveFileItem() {
        return saveFileItem;
    }

    public JMenuItem getLoadFileItem() {
        return loadFileItem;
    }

    public JPasswordField getKeyField() {
        return keyField;
    }

    /**
     * Adding doing to events
     * 
     * @param e
     *          Action event
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
        }
    }

    /**
     * Encrypt button getter
     * 
     * @return
     *         JToggleButton encryptButton
     */
    public JButton getEncryptButton() {
        return encryptButton;
    }

    /**
     * Decrypt button getter
     * 
     * @return
     *         JToggleButton decryptButton
     */
    public JButton getDecryptButton() {
        return decryptButton;
    }

    /**
     * cipher name getter
     * 
     * @return
     *         return which cipher was chosen with cipher combo box
     */
    public String getCipherName() {
        return cipherName;
    }

    /**
     * Frame getter
     * 
     * @return
     *         JFrame frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Text Pane getter
     * 
     * @return
     *         JTextPane textPane
     */
    public JTextPane getTextPane() {
        return textPane;
    }

    /**
     * Pop-up menu getter
     * 
     * @return
     *         JPopupMenu popupMenu
     */
    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    /**
     * Cut pop-up menu item getter
     * 
     * @return
     *         private JMenuItem cutItem
     */
    public JMenuItem getCutItem() {
        return cutItem;
    }

    /**
     * Copy pop-up menu item getter
     * 
     * @return
     *         JMenuItem copyItem
     */
    public JMenuItem getCopyItem() {
        return copyItem;
    }

    /**
     * Paste pop-up menu item getter
     * 
     * @return
     *         JMenuItem pasteItem
     */
    public JMenuItem getPasteItem() {
        return pasteItem;
    }

    /**
     * Delete pop-up menu item getter
     * 
     * @return
     *         JMenuItem deleteItem
     */
    public JMenuItem getDeleteItem() {
        return deleteItem;
    }

    /**
     * Select all pop-up menu item getter
     * 
     * @return
     *         JMenuItem selectAllItem
     */
    public JMenuItem getSelectAllItem() {
        return selectAllItem;
    }

    /**
     * Cipher combo box getter
     * 
     * @return
     *         JComboBox cipherComboBox
     */
    public JComboBox<String> getCipherComboBox() {
        return cipherComboBox;
    }

    /**
     * Setting button in one style
     * 
     * @param but
     *             button what we want to set
     * @param text
     *             button label
     * @param size
     *             button size
     * @return
     *         JButton
     */
    private JButton SetButton(JButton but, String text, Dimension size) {
        but = new JButton();
        but.setText(text);
        but.setSize(size);
        return but;
    }

    /**
     * Tool bar getter
     * 
     * @return
     *         JToolBar toolBar
     */

    private GridBagConstraints SetGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        return gbc;
    }
}