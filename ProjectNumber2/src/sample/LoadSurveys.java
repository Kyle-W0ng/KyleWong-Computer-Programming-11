package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadSurveys {
    private static int loadQuestionType;
    private static String loadQuestion;
    private static String loadChoice1;
    private static String loadChoice2;
    private static String loadChoice3;
    private static String loadChoice4;
    private static int loadQuestionLength;

    //This method is for loading the questions from a text file
    public static ArrayList<Questions> loadAllQuestions(Surveys surveys) throws IOException {
        //Setup for arraylist and file/buffered Reader
        ArrayList<Questions> loadingQuestionsArrayList = new ArrayList<>();
        FileReader fileReader = new FileReader("src/saves/" + surveys.surveyName + "_SAVES.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //Scans through every line in the questions Saves File
        String line1;
        while ((line1 = bufferedReader.readLine()) != null){

            //Identifies what type of information they are and marking them down with loading variables
            //"!" for question type
            //"?" for question
            //">" & "&%&%&" for the Multiple Choices
            //"." for expected response length for Written questions
            int loadChoiceSeparator = 0;
            Questions questions;
            switch (line1.substring(0,1)){
                case "!":
                    loadQuestionType = Integer.parseInt(line1.substring(1));
                    break;
                case "?":
                    loadQuestion = line1.substring(1);
                    break;
                case ">":
                    for (int a = 1; a < line1.length(); a++){
                        if (line1.startsWith("&%&%&", a)) {
                            switch (loadChoiceSeparator){
                                //Marks choice 1 as the substring between 1 to a
                                case 0:
                                    loadChoice1 = line1.substring(1, a);
                                    loadChoiceSeparator++;
                                    break;
                                //Marks choice 2 with the length of choice 1 + the length of the symbols to a
                                case 1:
                                    loadChoice2 = line1.substring(loadChoice1.length() + 6, a);
                                    loadChoiceSeparator++;
                                    break;
                                //If Choice 3 has content, it marks choice 3 with the length of choice 1 + choice 2 + the length of the symbols to a
                                case 2:
                                    if (line1.substring(loadChoice1.length() + loadChoice2.length() + 11, a).equals(" ")){
                                        loadChoice3 = " ";
                                    } else{
                                        loadChoice3 = line1.substring(loadChoice1.length() + loadChoice2.length() + 11, a);
                                    }
                                    loadChoiceSeparator++;
                                    break;
                                //If Choice 4 has content, it marks choice 3 with the length of choice 1 + choice 2 + choice 3 + the length of the symbols to a
                                case 3:
                                    if (line1.substring(loadChoice1.length() + loadChoice2.length()
                                            + loadChoice3.length() + 16, a).equals(" ")){
                                        loadChoice4 = "";
                                    } else{
                                        loadChoice4 = line1.substring(loadChoice1.length() + loadChoice2.length()
                                                + loadChoice3.length() + 16, a);
                                    }
                                    break;
                            }
                        }
                    }
                    break;
                //If there is a written question length, after the "." detected will be the question length
                case ".":
                    loadQuestionLength = Integer.parseInt(line1.substring(1));
                    break;
                //If ";" was detected, a new question object will be created
                case ";":
                    //Creates a Multiple Choice question
                    //This if statement is for that the space doesn't duplicate over and over again
                    if (loadQuestionType == 0){
                        if (loadChoice3.equals(" ")){
                            loadChoice3 = "";
                        }
                        questions = new Questions(0, loadQuestion, loadChoice1, loadChoice2, loadChoice3, loadChoice4);
                    }
                    //Creates a Written question
                    else{
                        questions = new Questions(1, loadQuestion, loadQuestionLength);
                    }
                    //Adds question to the arraylist
                    loadingQuestionsArrayList.add(questions);
                    break;
            }
        }
        //Returns arraylist
        bufferedReader.close();
        return loadingQuestionsArrayList;
    }
}
