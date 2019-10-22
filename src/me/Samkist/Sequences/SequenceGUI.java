/* Created By: Sam Pizette
 * On Date: 10/9/2019
 * Project Name: NonDecreasingSequences
 */
package me.Samkist.Sequences;

import BreezySwing.*;

import javax.swing.*;

public class SequenceGUI extends GBFrame {
    private static JFrame frame = new SequenceGUI();
    private JTextField inputField = addTextField("", 1,1 ,2 ,1);
    private JButton inputButton = addButton("Input", 2, 1, 1, 1);
    private JButton resetButton = addButton("Reset", 2, 2, 1,1);
    private JButton exitButton = addButton("Exit", 3, 1, 2, 1);
    private JTextField outputField = addTextField("", 6,1,2,3);

    public static void main(String[] args) {
        frame.setSize(400, 400);
        frame.setTitle("Non-Decreasing Sequences");
        frame.setVisible(true);
    }

    public JTextField getOutputField() {
        return outputField;
    }

    public void buttonClicked(JButton jButton) {
        if(jButton.equals(inputButton)) {
            new SequenceProcessor(inputField.getText(), this);
            inputField.grabFocus();
        }
        if(jButton.equals(resetButton)) {
            inputField.setText("");
            inputField.grabFocus();
        }
        if(jButton.equals(exitButton)) {
            System.exit(0);
        }
    }
}
