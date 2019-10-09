package me.Samkist.Sequences;

public class SequenceProcessor {
    private String rawString;
    private int[] parsedNumbers;
    private SequenceGUI gui;
    private int[] sequenceLengths;
    private int longestSequenceLength;

    public SequenceProcessor(String str, SequenceGUI gui) {
        rawString = str;
        this.gui = gui;
        calculateSequence();
    }

    public int getLongestSequenceLength() {
        int previousValue = sequenceLengths[0];
        int highestValue = 0;
        for(int num : sequenceLengths) {
            if(num >= previousValue)
                highestValue = num;
        }

        return highestValue;
    }

    private void calculateSequence() {
        parsedNumbers = initialParse();

        if(parsedNumbers != null) {
            System.out.println(getNumSequences());
            sequenceLengths = new int[getNumSequences()];
            sequenceLengths = getSequenceLengths();
            longestSequenceLength = getLongestSequenceLength();
            int i = 0;
            for(int num : sequenceLengths) {
                System.out.println("Sequence " + i + ": " + num);
                i++;
            }
            System.out.println("Longest Sequence Length: " + longestSequenceLength);
        }
    }

    private int[] getSequenceLengths() {
        int[] localArr = sequenceLengths;
        int i = 0;
        int currentSequenceLength = 0;
        int[] localNumbers = parsedNumbers;
        int previousValue = localNumbers[0];
        for(int num : parsedNumbers) {
            if(num >= previousValue) {
                currentSequenceLength++;
            } else {
                localArr[i] = currentSequenceLength;
                i++;
            }
        }
        return localArr;
    }

    private int getNumSequences() {
        int sequences = 1;
        int currentSequenceLength = 0;
        int[] localNumbers = parsedNumbers;
        int previousValue = localNumbers[0];
        for(int num : parsedNumbers) {
            if(num >= previousValue) {
                currentSequenceLength++;
            }

            if(currentSequenceLength >= 2 && num < previousValue) {
                sequences++;
            }
            previousValue = num;
        }
        return sequences;
    }

    private int[] initialParse() {
        String[] splitStrings = rawString.split(",");
        int[] returnedArr = new int[splitStrings.length];
        int i = 0;
        for(String str : splitStrings) {
            try {
                returnedArr[i] = Integer.parseInt(str);
            } catch(Exception e) {
                gui.messageBox("Invalid data received, please try again.\n" +
                        "Please make sure integers are separated by a \",\"");
                return null;
            }
            i++;
        }
        return returnedArr;
    }
}
