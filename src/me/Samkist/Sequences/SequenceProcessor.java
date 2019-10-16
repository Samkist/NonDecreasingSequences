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
        for(int[] seq : sequences) {
            for(int num : seq) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private void populateSequences() {
        int sequenceIndex = 0;
        int currentSeqLength = 0;
        boolean endOfSeq = false;
        boolean notEnd = true;

        int last = parsedNumbers[0];
        for(int y = 0; y < parsedNumbers.length; y++) {
            if(parsedNumbers[y] >= last) {
                currentSeqLength++;
                if(y == parsedNumbers.length -1) {
                    int[] temp = new int[currentSeqLength];
                    int x = 0;
                    for(int i = y - currentSeqLength +1; i <= y; i++) {
                        temp[x] = parsedNumbers[i];
                        x++;
                    }
                    sequences[sequenceIndex] = temp;
                    System.out.println("Working?");
                }
            } else if(parsedNumbers[y] < last) {
                int[] temp = new int[currentSeqLength];
                int x = 0;
                for(int i = y - currentSeqLength ; i < y; i++) {
                    temp[x] = parsedNumbers[i];
                    x++;
                }
                if(sequenceIndex < sequences.length)
                    sequences[sequenceIndex] = temp;
                currentSeqLength = 0;
                endOfSeq = true;
            }
            if(endOfSeq) {
                last = parsedNumbers[y-1];
            } else {
                last = parsedNumbers[y];
            }
        }
    }

    private void calculateSequence() {
        parsedNumbers = initialParse();

        if(parsedNumbers != null) {
            System.out.println(getNumSequences());
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
