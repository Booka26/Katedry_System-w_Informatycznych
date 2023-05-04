package cipherproject.Classes;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

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
    private JToggleButton cipherButton;
    private JToggleButton decipherButton;

    private String cipherName;
    public TextEditor() {
        // Создаем главное окно приложения
        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Создаем панель инструментов
        toolBar = new JToolBar();

        // Создаем комбо-бокс для выбора шрифта
        cipherComboBox = new JComboBox<>(new String[]{"A1Z26", "Cesar", "Morse", "Vigener", "Binary"});
        cipherComboBox.addActionListener(e -> {
            cipherName = cipherComboBox.getSelectedItem().toString();
        });
        toolBar.add(cipherComboBox);

        //Create new cipher button
        cipherButton = new JToggleButton();
        //Adding cipher button to toolbar
        toolBar.add(cipherButton);

        //Create new cipher button
        decipherButton = new JToggleButton();
        //Adding cipher button to toolbar
        toolBar.add(decipherButton);

        // Создаем текстовую область
        textPane = new JTextPane();
        textPane.setContentType("text/html");

        // Создаем попап-меню
        popupMenu = new JPopupMenu();

        // Создаем пункты меню
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        deleteItem = new JMenuItem("Delete");
        selectAllItem = new JMenuItem("Select All");

        // Добавляем слушателей на пункты меню
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        deleteItem.addActionListener(this);
        selectAllItem.addActionListener(this);

        // Создаем пункты меню для форматирования текста
        boldItem = new JCheckBoxMenuItem("Bold");
        italicItem = new JCheckBoxMenuItem("Italic");
        underlineItem = new JCheckBoxMenuItem("Underline");

        // Добавляем слушателей на пункты меню для форматирования текста
        boldItem.addActionListener(this);
        italicItem.addActionListener(this);
        underlineItem.addActionListener(this);

        // Создаем комбо-боксы для выбора шрифта и размера шрифта
        fontCombo = new JComboBox<>(new String[] { "Arial", "Times New Roman", "Verdana" });
        sizeCombo = new JComboBox<>(new Integer[] { 8, 10, 12, 14, 16, 18, 20, 22, 24 });

        // Добавляем слушателей на комбо-боксы
        fontCombo.addActionListener(this);
        sizeCombo.addActionListener(this);

        // Добавляем пункты меню в попап-меню
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

        // Добавляем попап-меню к текстовой области
        textPane.setComponentPopupMenu(popupMenu);

        frame.add(toolBar, BorderLayout.PAGE_START);
        // Добавляем текстовую область в главное окно
        frame.getContentPane().add(textPane, BorderLayout.CENTER);

        // Отображаем главное окно
        frame.setVisible(true);
    }

    public JToggleButton getCipherButton() {
        return cipherButton;
    }

    public JToggleButton getDecipherButton() {
        return decipherButton;
    }

    public String getCipherName() {
        return cipherName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Обработка нажатий на пункты меню
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

            // Форматирование текста жирным
            MutableAttributeSet attrs = textPane.getInputAttributes();
            Boolean isBold = (Boolean) attrs.getAttribute(StyleConstants.Bold);
            System.out.println(isBold);
            StyleConstants.setBold(attrs, !isBold);
            textPane.setCharacterAttributes(attrs, true);
        } else if (e.getSource() == italicItem) {

            // Форматирование текста курсивом
            MutableAttributeSet attrs = textPane.getInputAttributes();
            Boolean italic = (Boolean) attrs.getAttribute(StyleConstants.Italic);
            
            StyleConstants.setItalic(attrs, !italic);
            textPane.setCharacterAttributes(attrs, true);
        } else if (e.getSource() == underlineItem) {

            // Форматирование текста подчеркиванием
            MutableAttributeSet attrs = textPane.getInputAttributes();
            Boolean underline = (Boolean) attrs.getAttribute(StyleConstants.Underline);
            StyleConstants.setUnderline(attrs, !underline);
            textPane.setCharacterAttributes(attrs, true);
        } else if (e.getSource() == fontCombo) {

            // Изменение шрифта текста
            MutableAttributeSet attrs = textPane.getInputAttributes();
            String fontName = (String) fontCombo.getSelectedItem();
            StyleConstants.setFontFamily(attrs, fontName);
            textPane.setCharacterAttributes(attrs, true);
        } else if (e.getSource() == sizeCombo) {

            // Изменение размера шрифта текста
            MutableAttributeSet attrs = textPane.getInputAttributes();
            Integer fontSize = (Integer) sizeCombo.getSelectedItem();
            StyleConstants.setFontSize(attrs, fontSize);
            textPane.setCharacterAttributes(attrs, true);
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public JMenuItem getCutItem() {
        return cutItem;
    }

    public JMenuItem getCopyItem() {
        return copyItem;
    }

    public JMenuItem getPasteItem() {
        return pasteItem;
    }

    public JMenuItem getDeleteItem() {
        return deleteItem;
    }

    public JMenuItem getSelectAllItem() {
        return selectAllItem;
    }

    public JCheckBoxMenuItem getBoldItem() {
        return boldItem;
    }

    public JCheckBoxMenuItem getItalicItem() {
        return italicItem;
    }

    public JCheckBoxMenuItem getUnderlineItem() {
        return underlineItem;
    }

    public JComboBox<String> getFontCombo() {
        return fontCombo;
    }

    public void setFontCombo(JComboBox<String> fontCombo) {
        this.fontCombo = fontCombo;
    }

    public JComboBox<Integer> getSizeCombo() {
        return sizeCombo;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public JComboBox<String> getCipherComboBox() {
        return cipherComboBox;
    }
}