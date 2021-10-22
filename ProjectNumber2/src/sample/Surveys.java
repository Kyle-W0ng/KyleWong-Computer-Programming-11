package sample;

import java.io.*;
import java.util.ArrayList;


public class Surveys {
    public String surveyName;
    public String creator;
    public String date;
    public ArrayList<Questions> questionsArrayList;
    static String line1;
    static ArrayList<String> temporaryStringArray = new ArrayList<>();
    static FileWriter fileWriter;
    static BufferedWriter bufferedWriter;
    static FileReader fileReader;
    static BufferedReader bufferedReader;

    //This constructor creates a new survey with the parameters provided
    public Surveys(String surveyName, String creator, String date){
        this.questionsArrayList = new ArrayList<>();
        this.surveyName = surveyName;
        this.creator = creator;
        this.date = date;
    }

    //This method is for parsing the survey object to a string
    public String toString() {
        return surveyName;
    }

    //This method marks down the survey's name, creator and date in a file to have them ready to load next time
    public static void markDownSurveyArchive(Surveys surveys) throws IOException {
        //Setup file/buffered Writer
        fileWriter = new FileWriter("existingSurveys.txt", true);
        bufferedWriter = new BufferedWriter(fileWriter);

        //Writes down the information with separators
        bufferedWriter.write("\r" + surveys.surveyName + "=}" + surveys.creator + "=}" + surveys.date + "=}\r");
        bufferedWriter.close();
    }

    //This method is for saving the file to a text file to make the progress retrievable
    public static void saveFile(Surveys surveys) throws IOException {
        //Setup file/buffered Reader
        fileReader = new FileReader("existingSurveys.txt");
        bufferedReader = new BufferedReader(fileReader);

        //Resets and adds all the surveys marked in the Survey Saves file into a temporary list
        temporaryStringArray.clear();
        while ((line1 = bufferedReader.readLine()) != null) {
            if (!line1.equals("")){
                temporaryStringArray.add(line1);
            }
        }

        //Since the creator and date of a survey could be changed, if the same survey name could be detected, this updates the new survey creator and date from the text fields
        for (int b = 0; b < temporaryStringArray.size(); b++){
            if (temporaryStringArray.get(b).startsWith(surveys.surveyName)){
                temporaryStringArray.set(b, surveys.surveyName + "=}" + surveys.creator + "=}" + surveys.date + "=}");
                break;
            }
        }

        //Setup file/buffered Writer
        fileWriter = new FileWriter("existingSurveys.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //After updating the information, for each item in the list, it will be written down again in the Surveys Saves text file
        for (String s : temporaryStringArray) {
            bufferedWriter.write("\r" + s);
        }
        bufferedWriter.close();
        bufferedReader.close();

        //Setup file/buffered Writer for saving the questions
        fileWriter = new FileWriter("src/saves/" + surveys.surveyName + "_SAVES.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //For each item in the question Arraylist for the respective survey
        for (int d = 0; d < surveys.questionsArrayList.size(); d++){
            //Depending on what question type it is
            switch (surveys.questionsArrayList.get(d).questionType){
                //Firstly the question type will be written with a "!"
                //Secondly the question will be written with "?"
                //Thirdly the first Choice will be written with a ">" in front, the 2nd, 3rd, 4th choice will be added with a "&%&%&" behind
                //At the end a ";" will be written to indicate the end of a question
                case 0:
                    bufferedWriter.write("!" + surveys.questionsArrayList.get(d).questionType + "\r");
                    bufferedWriter.write("?" + surveys.questionsArrayList.get(d).question + "\r");
                    bufferedWriter.write(">" + surveys.questionsArrayList.get(d).choice1 + "&%&%&");
                    bufferedWriter.write(surveys.questionsArrayList.get(d).choice2 + "&%&%&");
                    if (surveys.questionsArrayList.get(d).choice3.isEmpty()){
                        bufferedWriter.write(" &%&%&");
                    } else {
                        bufferedWriter.write(surveys.questionsArrayList.get(d).choice3 + "&%&%&");
                    }
                    if (surveys.questionsArrayList.get(d).choice4.isEmpty()){
                        bufferedWriter.write(" &%&%&\r;\r");
                    } else {
                        bufferedWriter.write(surveys.questionsArrayList.get(d).choice4 + "&%&%&\r;\r");
                    }
                    break;

                //Firstly the question type will be written with a "!"
                //Secondly the question will be written with a "?"
                //Thirdly the expected response length will be written down with a "."
                case 1:
                    bufferedWriter.write("!" + surveys.questionsArrayList.get(d).questionType + "\r");
                    bufferedWriter.write("?" + surveys.questionsArrayList.get(d).question + "\r");
                    bufferedWriter.write("." + surveys.questionsArrayList.get(d).questionLength + "\r;\r");
                    break;
            }
        }
        bufferedWriter.close();
    }
}
