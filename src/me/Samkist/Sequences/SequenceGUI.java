/* Created By: Sam Pizette
 * On Date: 10/9/2019
 * Project Name: NonDecreasingSequences
 */
package me.Samkist.Sequences;

import BreezySwing.*;

import javax.swing.*;

public class SequenceGUI extends GBFrame {
    private static JFrame frame = new SequenceGUI();
    private JTextField inputField = addTextField("", 1,1 ,1 ,1);
    private JButton inputButton = addButton("Input", 2, 1, 1, 1);
    private String rawString;
    private int[] parsedNumbers;
    private boolean errorExists = false;

    public static void main(String[] args) {
        frame.setSize(400, 400);
        frame.setTitle("Non-Decreasing Sequences");
        frame.setVisible(true);
    }

    public void buttonClicked(JButton jButton) {
        if(jButton.equals(inputButton)) {
            calculateSequence();
        }
    }

    public void calculateSequence() {
        rawString = inputField.getText();
        parsedNumbers = initalParse();
        if(errorExists) {
            errorExists = false;
            return;
        }
        System.out.println(getNumSequences());
    }

    public int getNumSequences() {
        int sequences = 1;
        int currentSequenceLength = 0;
        int[] localNumbers = parsedNumbers;
        int previousValue = localNumbers[0];
        for(int j : parsedNumbers) {
            if(j >= previousValue) {
                currentSequenceLength++;
            }

            if(currentSequenceLength >= 2 && j < previousValue) {
                sequences++;
            }
            previousValue = j;
        }

        return sequences;
    }

    public int[] initalParse() {
        String[] splitStrings = rawString.split(",");
        int[] returnedArr = new int[splitStrings.length];
        int i = 0;
        for(String str : splitStrings) {
            try {
                returnedArr[i] = Integer.parseInt(str);
            } catch(Exception e) {
                messageBox("Invalid data received, please try again.\n" +
                        "Please make sure integers are separated by a \",\"");
                System.out.println(str);
                e.printStackTrace();
                errorExists = true;
                return null;
            }
            i++;
        }
        return returnedArr;
    }
}
