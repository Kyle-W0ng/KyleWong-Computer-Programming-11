import org.junit.Before;
import org.junit.Test;
import sample.LoadSurveys;
import sample.Questions;
import sample.Surveys;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SurveyTest {
    //Code
    int a;
    String line1;
    Surveys surveys;
    Questions questions;
    ArrayList<String> tempSurveySaves;
    ArrayList<Questions> questionsArraylistTest;

    //Imported
    File file;
    FileReader fileReader;
    BufferedReader bufferedReader;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;

    //Sets up the instances and objects
    @Before
    public void setUp(){
        surveys = new Surveys("namePH", "creatorPH", "datePH");
        questions = new Questions(0, "questionPH","ch1PH", "ch2PH", "ch3PH", "ch4PH");
        file = new File("src/saves/namePH_SAVES.txt");
        questionsArraylistTest = new ArrayList<>();
        tempSurveySaves = new ArrayList<>();
    }

    //This test tests for if the toString method parsed the Survey object to a String
    @Test
    public void surveysToString(){
        //Creates a new survey object
        surveys = new Surveys("namePH", "creatorPH", "datePH");
        //Checks if the survey name matches the string returned by the toString method
        assertEquals("namePH", surveys.toString());
    }

    //This test tests for if the toString method parsed the Question object to a String
    @Test
    public void questionsToString(){
        //Creates a new question object
        questions = new Questions(0, "questionPH","ch1PH", "ch2PH", "ch3PH", "ch4PH");
        //Checks if the question name matches the string returned by the toString method
        assertEquals("questionPH", questions.toString());
    }

    //This test tests for if the markDownArchive method marks down the newly created survey in the existingSurveys text file
    @Test
    public void markDownArchiveTest() throws IOException{
        //Sets up file/buffered Reader
        fileReader = new FileReader("existingSurveys.txt");
        bufferedReader = new BufferedReader(fileReader);

        //Calls the method with the survey object
        Surveys.markDownSurveyArchive(surveys);

        //Puts all the existing Surveys in a temporary list
        while ((line1 = bufferedReader.readLine()) != null){
            if (!line1.isEmpty()){
                tempSurveySaves.add(line1);
            }
        }

        //Checks if that list has the survey object
        assertTrue(tempSurveySaves.contains("namePH=}creatorPH=}datePH=}"));

        //Sets up the file/buffered Writer
        fileWriter = new FileWriter("existingSurveys.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //For each object in the temporary list, except the survey placeholder, each object will be rewritten in the existingSurveys text file
        for (String s:tempSurveySaves){
            if (!s.equals("namePH=}creatorPH=}datePH=}")){
                bufferedWriter.write("\r" + s);
            }
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    //This test is for testing if the saveFile method works
    @Test
    public void saveFileTest() throws IOException{
        //Sets up the text files
        file.createNewFile();
        fileReader = new FileReader("src/saves/" + surveys.surveyName + "_SAVES.txt");
        bufferedReader = new BufferedReader(fileReader);
        a = 0;

        //Clears the surveys to ensure there's nothing inside
        surveys.questionsArrayList.clear();

        //Adds the question to the arraylist and checks if it has been actually added
        surveys.questionsArrayList.add(questions);
        assertTrue(surveys.questionsArrayList.contains(questions));

        //Calls the saveFile method
        Surveys.saveFile(surveys);

        //Checks if the "namePH_SAVES.txt" has the appropriate content, for example, for Multiple Choice
        /*
        !0
        ?<question>
        ><choice1>&%&%&<choice2>&%&%&<choice3>&%&%&<choice4>
        ;
         */
        while((line1 = bufferedReader.readLine()) != null){
            if (!line1.isEmpty()){
                switch (a){
                    case 0:
                        assertEquals("!0", line1);
                        break;
                    case 1:
                        assertEquals("?questionPH", line1);
                        break;
                    case 2:
                        assertEquals(">ch1PH&%&%&ch2PH&%&%&ch3PH&%&%&ch4PH&%&%&", line1);
                        break;
                    case 3:
                        assertEquals(";", line1);
                }
                a++;
            }
        }
        //Deletes the text file when the testing is done
        file.deleteOnExit();
        bufferedReader.close();
    }

    //This test is for testing loadAllQuestions method
    @Test
    public void loadExportTest() throws IOException {
        //Creates a new placeholder survey
        file.createNewFile();

        //Clears the survey arraylist to ensure there are no other questions
        surveys.questionsArrayList.clear();
        assertTrue(true);
        //Adds a question and ensure it is actually added
        surveys.questionsArrayList.add(questions);
        assertTrue(surveys.questionsArrayList.contains(questions));

        //Calls the loadAllQuestions method
        Surveys.saveFile(surveys);

        //Checks that when after the question is added and ensure the question is added
        surveys.questionsArrayList.add(questions);
        assertTrue(surveys.questionsArrayList.contains(questions));

        //Checks if all the information are matching will the question loaded from the method
        assertEquals("questionPH", LoadSurveys.loadAllQuestions(surveys).get(0).question);
        assertEquals("ch1PH", LoadSurveys.loadAllQuestions(surveys).get(0).choice1);
        assertEquals("ch2PH", LoadSurveys.loadAllQuestions(surveys).get(0).choice2);
        assertEquals("ch3PH", LoadSurveys.loadAllQuestions(surveys).get(0).choice3);
        assertEquals("ch4PH", LoadSurveys.loadAllQuestions(surveys).get(0).choice4);
    }
}
