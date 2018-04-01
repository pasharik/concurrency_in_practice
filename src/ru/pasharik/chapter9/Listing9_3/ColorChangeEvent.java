package ru.pasharik.chapter9.Listing9_3;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by pasharik on 01/04/18.
 */
public class ColorChangeEvent {
    private static Random r = new Random();
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Change Color");
        button.addActionListener(e -> button.setBackground(new Color(r.nextInt())));
        frame.getContentPane().add(button);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
