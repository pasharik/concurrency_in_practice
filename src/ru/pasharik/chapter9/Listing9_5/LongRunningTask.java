package ru.pasharik.chapter9.Listing9_5;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pasharik on 02/04/18.
 */
public class LongRunningTask {
    ExecutorService backgroundExec = Executors.newCachedThreadPool();

    private void createAndShowGUI() {
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton(" Idle ");
        button.addActionListener(e -> {
            button.setEnabled(false);
            button.setText("Busy");
            backgroundExec.execute(() -> {
                try {
                    doBigComputation();
                } finally {
                    SwingUtilities.invokeLater(() -> {
                        button.setText(" Idle ");
                        button.setEnabled(true);
                    });
                }
            });
        });
        frame.getContentPane().add(button);
        frame.pack();
        frame.setVisible(true);
    }

    public void doBigComputation() {
        for (int i = 0; i < 4; i++) {
            try {
                System.out.println("Current value: " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) { return; }
        }
    }

    public static void main(String[] args) {
        new LongRunningTask().createAndShowGUI();
    }
}
