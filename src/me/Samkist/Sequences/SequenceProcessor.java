package me.Samkist.Sequences;

public class SequenceProcessor {
    private String rawString;
    private int[] parsedNumbers;
    private SequenceGUI gui;
    private int[][] sequences;


    public SequenceProcessor(String str, SequenceGUI gui) {
        rawString = str;
        this.gui = gui;
        calculateSequence();
        populateSequences();
        int[] last = sequences[0];
        int longest = sequences[0].length;
        String string = "";
        for(int i = 0; i < sequences[0].length; i++) {
            string += sequences[0][i] + " ";
        }
        for(int i = 1; i < sequences.length; i++) {
            if(sequences[i].length > last.length && sequences[i].length > longest) {
                string = "";
                longest = sequences[i].length;
                for(int j = 0; j < sequences[i].length; j++) {
                    string += sequences[i][j] + " ";
                }
            } else if(sequences[i].length == last.length && sequences[i].length == longest) {
                string += " : ";
                for(int j = 0; j < sequences[i].length; j++) {
                    string += sequences[i][j] + " ";
                }
            }
            last = sequences[i];
        }
        gui.getOutputField().setText(string);
    }

    private void populateSequences() {
        int last = parsedNumbers[0];
        //Length of current sequence
        int seqLength = 1;
        int sequenceIndex = 0;
        for(int i = 1; i < parsedNumbers.length; i++) {
            if(parsedNumbers[i] >= last) {
                seqLength++;
                if(i == parsedNumbers.length -1) {
                    int[] temp = new int[seqLength];
                    int x = 0;
                    for(int j = i - seqLength+2; j <= i+1; j++) {
                        temp[x] = parsedNumbers[j-1];
                        x++;
                    }
                    sequences[sequenceIndex] = temp;
                    sequenceIndex++;
                }
            } else if(seqLength > 1) {
                int[] temp = new int[seqLength];
                int x = 0;
                for(int j = i - seqLength+1; j <= i; j++) {
                    temp[x] = parsedNumbers[j-1];
                    x++;
                }
                sequences[sequenceIndex] = temp;
                seqLength = 0;
                sequenceIndex++;
                i-= 2;
            }
            last = parsedNumbers[i];
        }
    }

    private void calculateSequence() {
        parsedNumbers = initialParse();

        if(parsedNumbers != null) {
            sequences = new int[getNumSequences()][];
        }
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
