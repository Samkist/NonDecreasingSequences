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
    }

    private void populateSequences() {
        int sequenceIndex = 0;
        int currentSeqLength = 0;

        int last = parsedNumbers[0];

        for(int num : parsedNumbers) {
            if(num >= last) {
                currentSeqLength++;

                if(num == parsedNumbers[parsedNumbers.length -1]) {
                    int[] temp = new int[currentSeqLength];
                    int endingIndex = 0;
                    for(int i = 0; i < parsedNumbers.length; i++) {
                        if(num == parsedNumbers[i])
                            endingIndex = i;
                    }
                    int x = 0;
                    for(int i = endingIndex - currentSeqLength+1; i < endingIndex+1; i++) {
                        temp[x] = parsedNumbers[i];
                        x++;
                    }
                    sequences[sequenceIndex] = temp;
                }
            } else if(num < last) {
                int[] temp = new int[currentSeqLength];
                int endingIndex = 0;
                for(int i = 0; i < parsedNumbers.length; i++) {
                    if(num == parsedNumbers[i])
                        endingIndex = i;
                }
                int x = 0;
                for(int i = endingIndex - currentSeqLength; i < endingIndex; i++) {
                    temp[x] = parsedNumbers[i];
                    x++;
                }
                sequences[sequenceIndex] = temp;
                currentSeqLength = 0;
                sequenceIndex++;
                
            }

            last = num;
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
