import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Byte Identification
        byte[] lineByteSeq;
        int[] lineSequence;

        //Scanner
        String inputWord;
        byte[] inputWordByteSeq;
        int[] inputWordSequence;
        Scanner scanner;

        //Text
        String line;
        ArrayList<String> lines = new ArrayList<>();
        int lineNo;

        //Integers placeholder
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;

        //While loop for iteration
        do {
            //txt file Setup
            FileReader fileReader = new FileReader("ProgrammingHistory.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Reset
            lines.clear();
            f = 0;
            lineNo = 1;

            //User Input
            scanner = new Scanner(System.in);
            System.out.println("Please type in what you want to search.");
            inputWord = scanner.nextLine();
            inputWordSequence = new int[inputWord.length()];

            for (a = 0; a < inputWord.length(); a++) {
                //Get the byte sequence for the word the user searched for (Input word)
                inputWordByteSeq = inputWord.getBytes();
                inputWordSequence[a] = inputWordByteSeq[a];
                //Rewrite the bytes (capitals and lowercase) for input word
                inputWordSequence[a] = byteIdentification(inputWordSequence, a)[a];

            }

            //Scan through each line in txt file
            //Adds line into an Arraylist
            while ((line = bufferedReader.readLine()) != null) {
                //Setup
                e = 0;
                lines.add(line);
                lineSequence = new int[line.length()];

                //Article
                for (b = 0; b < line.length(); b++) {
                    //Get the byte sequence for each line in the file (Article)
                    lineByteSeq = line.getBytes();
                    lineSequence[b] = lineByteSeq[b];
                    //Rewrite the bytes (capitals and lowercase) for article
                    lineSequence[b] = byteIdentification(lineSequence, b)[b];
                }

                //Comparison
                //Scans through the article with the help of line size
                for (c = 0; c < (lines.get(lineNo - 1).length() - inputWord.length()); c++) {
                    //Compares the sequence from the input word to the article
                    if (inputWordSequence[0] == lineSequence[c]) {
                        //If the first word is identical, proceeds to compare the rest
                        for (d = 0; d < inputWord.length(); d++) {
                            //Proceeds to compare the rest of the input word with the article's byte sequence
                            //If the byte from the input word continues to match with the article's sequence, a counter increments (e)
                            if (inputWordSequence[d] == lineSequence[c + d]) {
                                e++;
                                //If the counter has reached the input word's length, it means that a section in the article has matched with the input word.
                                //The position of the matching words gets printed out
                                //Resets counter (e) and a separate counter (f) for counting the amount of matches increments
                                if (e == inputWord.length()) {
                                    System.out.println("Line: " + lineNo + " \tIndex: " + c + "-" + (c + inputWord.length()));
                                    f++;
                                    e = 0;
                                }
                                //If the chain breaks, it skips the procedure and resets counter (e)
                            } else {
                                break;
                            }
                        }
                        e = 0;
                    }
                }
                lineNo++;
            }
            //If there was no matches, the below statement will be printed out
            if (f == 0) {
                System.out.println("\nThere are no \"" + inputWord + "\"s found in the text.");
            }
        }while (repeat(scanner));
    }

    //For iteration
    public static boolean repeat(Scanner scanner){
        boolean repeatOpt;
        boolean repeat = false;
        do {
            repeatOpt = true;
            System.out.println("\nDo you want to find another text?");
            String strRepeat = scanner.nextLine();
            if (strRepeat.equals("Yes") || strRepeat.equals("yes")) {
                repeat = true;
            } else if (strRepeat.equals("No") || strRepeat.equals("no")) {
                System.out.println("Thank you for using Text Finder.");
            } else {
                repeatOpt = false;
                System.out.println("Invalid answer, please choose again [Yes]/[No]");
            }
        } while (!repeatOpt);
        return repeat;
    }

    //For identifying bytes for input word and all lines in article
    public static int[] byteIdentification(int [] methodArray, int z){
        switch (methodArray[z]){
            //letters
            //For example, 65 is Capital A and 97 is lowercase a. It will be saved as 1.
            //Vice versa for the rest of the letters, numbers and symbols
            case 65:
            case 97:
                methodArray[z] = 1;
                break;
            case 66:
            case 98:
                methodArray[z] = 2;
                break;
            case 67:
            case 99:
                methodArray[z] = 3;
                break;
            case 68:
            case 100:
                methodArray[z] = 4;
                break;
            case 69:
            case 101:
                methodArray[z] = 5;
                break;
            case 70:
            case 102:
                methodArray[z] = 6;
                break;
            case 71:
            case 103:
                methodArray[z] = 7;

            case 72:
            case 104:
                methodArray[z] = 8;
                break;
            case 73:
            case 105:
                methodArray[z] = 9;
                break;
            case 74:
            case 106:
                methodArray[z] = 10;
                break;
            case 75:
            case 107:
                methodArray[z] = 11;
                break;
            case 76:
            case 108:
                methodArray[z] = 12;
                break;
            case 77:
            case 109:
                methodArray[z] = 13;
                break;
            case 78:
            case 110:
                methodArray[z] = 14;
                break;
            case 79:
            case 111:
                methodArray[z] = 15;
                break;
            case 80:
            case 112:
                methodArray[z] = 16;
                break;
            case 81:
            case 113:
                methodArray[z] = 17;
                break;
            case 82:
            case 114:
                methodArray[z] = 18;
                break;
            case 83:
            case 115:
                methodArray[z] = 19;
                break;
            case 84:
            case 116:
                methodArray[z] = 20;
                break;
            case 85:
            case 117:
                methodArray[z] = 21;
                break;
            case 86:
            case 118:
                methodArray[z] = 22;
                break;
            case 87:
            case 119:
                methodArray[z] = 23;
                break;
            case 88:
            case 120:
                methodArray[z] = 24;
                break;
            case 89:
            case 121:
                methodArray[z] = 25;
                break;
            case 90:
            case 122:
                methodArray[z] = 26;
                break;
            //symbols
            case 32:
                methodArray[z] = 27;
                break;
            case 34:
                methodArray[z] = 28;
                break;
            case 40:
                methodArray[z] = 29;
                break;
            case 41:
                methodArray[z] = 30;
                break;
            case 42:
                methodArray[z] = 31;
                break;
            case 43:
                methodArray[z] = 32;
                break;
            case 44:
                methodArray[z] = 33;
                break;
            case 45:
                methodArray[z] = 34;
                break;
            case 58:
                methodArray[z] = 35;
                break;
            case 59:
                methodArray[z] = 36;
                break;
            case 61:
                methodArray[z] = 37;
                break;
            case 91:
                methodArray[z] = 38;
                break;
            case 93:
                methodArray[z] = 39;
                break;
            //numbers
            case 48:
                methodArray[z] = 0;
                break;
            case 49:
                methodArray[z] = -1;
                break;
            case 50:
                methodArray[z] = -2;
                break;
            case 51:
                methodArray[z] = -3;
                break;
            case 52:
                methodArray[z] = -4;
                break;
            case 53:
                methodArray[z] = -5;
                break;
            case 54:
                methodArray[z] = -6;
                break;
            case 55:
                methodArray[z] = -7;
                break;
            case 56:
                methodArray[z] = -8;
                break;
            case 57:
                methodArray[z] = -9;
                break;
        }
        return methodArray;
    }
}
