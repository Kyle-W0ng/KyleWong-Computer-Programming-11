package sample;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;

public class Controller {

    //Code
    Questions questions;
    Surveys surveys;
    File file;
    FileReader fileReader;
    BufferedReader bufferedReader;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    String surveyMem;

    //Tabs
    public TabPane tabPane;
    public Tab tab_Home;
    public Tab tab_VE;
    public Tab tab_Add;
    public Tab tab_Remove;

    //Home
    public Button btn_Home_createNew;
    public Button btn_Home_start;
    public Label lbl_Home_Creator;
    public Label lbl_Home_newName;
    public Label lbl_Home_Date;
    public TextField tf_Home_Creator;
    public TextField tf_Home_newName;
    public TextField tf_Home_Date;
    public Button btn_Home_OpenFile;
    public Button btn_Home_DeleteFile;
    public ListView<Surveys> listV_Home_ExistingSurveys;
    public String line1;
    public int separator;
    public String existingSurveyName;
    public String existingCreator;
    public String existingDate;
    public Label lbl_Home_dupDeleteText;
    public Button btn_Home_dupYes;
    public Button btn_Home_dupNo;
    public int firstTime = 0;

    //View/Edit
        //Creator/Date
    public Label lbl_VE_date;
    public TextField tf_VE_Date;
    public Label lbl_VE_creator;
    public TextField tf_VE_creator;
        //View
    public ListView<Questions> listV_VE_questions = new ListView<>();
        //Edit
    public AnchorPane ap_VE_generalInfo;
    public AnchorPane ap_VE_editMC;
    public AnchorPane ap_VE_editWritten;
            //General
    public Label lbl_VE_questionNumber;
    public Label lbl_VE_questionType;
    public TextField tf_VE_editQuestion;
            //MC
    public Label lbl_VE_choice1;
    public Label lbl_VE_choice2;
    public Label lbl_VE_choice3;
    public Label lbl_VE_choice4;
    public TextField tf_VE_editChoice1;
    public TextField tf_VE_editChoice2;
    public TextField tf_VE_editChoice3;
    public TextField tf_VE_editChoice4;
            //Written
    public Label lbl_VE_editWrittenLength;
    public Slider s_VE_editResponseLength;
    public Label lbl_VE_writtenLength;

    //Add
        //Anchor Pane
    public AnchorPane ap_Add_mc;
    public AnchorPane ap_Add_written;
        //Top
    public Label lbl_Add_surveyName;
    public Button btn_Add_chooseType_MC;
    public Button btn_Add_chooseType_Written;
        //MC
    public Label lbl_Add_mc_Question;
    public Label lbl_Add_choice1;
    public Label lbl_Add_choice2;
    public Label lbl_Add_choice3;
    public Label lbl_Add_choice4;
    public TextField tf_Add_mc_Question;
    public TextField tf_Add_mc_Choice1;
    public TextField tf_Add_mc_Choice2;
    public TextField tf_Add_mc_Choice3;
    public TextField tf_Add_mc_Choice4;
    public Label lbl_Add_mc_sampleQuestion;
    public ToggleGroup rb_Add_sampleChoices;
    public RadioButton rb_Add_sampleChoice1;
    public RadioButton rb_Add_sampleChoice2;
    public RadioButton rb_Add_sampleChoice3;
    public RadioButton rb_Add_sampleChoice4;
        //Written
    public Label lbl_Add_written_Question;
    public TextField tf_Add_written_Question;
    public Label lbl_Add_written_questionLength;
    public Slider s_Add_written_Length;
    public Label lbl_Add_written_Length;
    public Label lbl_Add_written_sampleQuestion;
    public TextArea ta_Add_written_answer;
        //Add
    public Button btn_Add_addQuestion;

    //Remove
    public ListView<Questions> listV_Remove_removeQuestions  = new ListView<>();
    public Label lbl_Remove_text;
    public Button btn_Remove_Remove;

    //This configures the components necessary for creating a new survey (Name, creator, date text fields and labels)
    public void createNewSetUp() {
        //Enabling these components will allow the user to type in their information about the survey
        lbl_Home_newName.setDisable(false);
        tf_Home_newName.setDisable(false);
        lbl_Home_Creator.setDisable(false);
        tf_Home_Creator.setDisable(false);
        lbl_Home_Date.setDisable(false);
        tf_Home_Date.setDisable(false);
        btn_Home_start.setDisable(false);
    }

    //This creates a brand new survey when the user clicks the "Start!" button
    public void createFile() throws IOException {
        //If any of the text fields are missing information, the label beside the respective text fields will be turned red
        //Checks for the three text fields
        if (tf_Home_newName.getText().isEmpty() || tf_Home_Creator.getText().isEmpty() || tf_Home_Date.getText().isEmpty()) {
            //Checks for the "Name" text field, changes it to red if it's not filled in and black if it is
            if (tf_Home_newName.getText().isEmpty()) {
                lbl_Home_newName.setStyle("-fx-text-fill: red");
            } else{
                lbl_Home_newName.setStyle("-fx-text-fill: black");
            }
            //Checks for the "Creator" text field, changes it to red if it's not filled in and black if it is
            if (tf_Home_Creator.getText().isEmpty()){
                lbl_Home_Creator.setStyle("-fx-text-fill: red");
            } else{
                lbl_Home_Creator.setStyle("-fx-text-fill: black");
            }
            //Checks for the "Date" text field, changes it to red if it's not filled in and black if it is
            if (tf_Home_Date.getText().isEmpty()){
                lbl_Home_Date.setStyle("-fx-text-fill: red");
            } else{
                lbl_Home_Date.setStyle("-fx-text-fill: black");
            }
        } else {
            //Creating new Saves file for the survey
            //If the file doesn't exist yet, it will create a new survey as usual
            //If not, an error message pops up and asks for the user's opinion
            file = new File("src/saves/" + tf_Home_newName.getText() + "_SAVES.txt");

            //Block of code for if the file doesn't exist
            //To ensure the "existingSurveys.txt" would not be overwritten in case the user wants to create a file called existingSurveys,
            //the name "existingSurveys" would not be accepted
            if (!file.exists()){
                //Creates a new file in the saves folder
                file.createNewFile();

                //Create a new Survey Object as well as marking down the new survey created in the survey saves file text
                surveys = new Surveys(tf_Home_newName.getText(), tf_Home_Creator.getText(), tf_Home_Date.getText());
                Surveys.markDownSurveyArchive(surveys);

                //Configuring ListViews
                listV_Home_ExistingSurveys.setDisable(false);
                listV_Home_ExistingSurveys.getItems().add(surveys);
                listV_Home_ExistingSurveys.getSelectionModel().select(surveys);

                //Configuring tabs
                tabPane.getSelectionModel().select(tab_Add);
                tab_Add.setDisable(false);

                //Configuring Buttons
                btn_Home_OpenFile.setDisable(false);
                btn_Home_DeleteFile.setDisable(false);

                //Resetting "Create new" buttons and labels
                lbl_Home_newName.setDisable(true);
                lbl_Home_Creator.setDisable(true);
                lbl_Home_Date.setDisable(true);
                lbl_Home_newName.setStyle("-fx-text-fill: black");
                lbl_Home_Creator.setStyle("-fx-text-fill: black");
                lbl_Home_Date.setStyle("-fx-text-fill: black");
                tf_Home_newName.setDisable(true);
                tf_Home_Creator.setDisable(true);
                tf_Home_Date.setDisable(true);
                btn_Home_start.setDisable(true);

                //Changes information labels to the current survey
                lbl_Add_surveyName.setText(tf_Home_newName.getText());
                tf_VE_creator.setText(tf_Home_Creator.getText());
                tf_VE_Date.setText(tf_Home_Date.getText());

                //Clearing TextFields
                tf_Home_newName.clear();
                tf_Home_Creator.clear();
                tf_Home_Date.clear();
                tf_VE_editQuestion.clear();
                tf_VE_editChoice1.clear();
                tf_VE_editChoice2.clear();
                tf_VE_editChoice3.clear();
                tf_VE_editChoice4.clear();

                //Disabling components because of the new file
                tab_VE.setDisable(true);
                tab_Remove.setDisable(true);
                btn_Remove_Remove.setDisable(false);
            }

            //Block of code for if the file does exist
            else{
                //Block of code if the user wants to use existingSurveys as a survey name
                if (tf_Home_newName.getText().startsWith("existingSurveys")){
                    lbl_Home_dupDeleteText.setText("Error. This name is invalid. Please use another name.");
                    lbl_Home_dupDeleteText.setVisible(true);
                    btn_Home_dupYes.setVisible(true);
                    btn_Home_dupNo.setVisible(true);
                }

                //Disabling all the JavaFX components in the "Home" Pane except a few
                //Enabling error components
                lbl_Home_dupDeleteText.setVisible(true);
                lbl_Home_dupDeleteText.setText("Error. This file as been created before. Open Instead?");
                btn_Home_dupYes.setVisible(true);
                btn_Home_dupNo.setVisible(true);

                //Disabling not related components
                tab_VE.setDisable(true);
                tab_Add.setDisable(true);
                tab_Remove.setDisable(true);
                btn_Home_createNew.setDisable(true);
                tf_Home_newName.setDisable(true);
                tf_Home_Creator.setDisable(true);
                tf_Home_Date.setDisable(true);
                btn_Home_start.setDisable(true);
                listV_Home_ExistingSurveys.setDisable(true);
                btn_Home_OpenFile.setDisable(true);
                btn_Home_DeleteFile.setDisable(true);
            }
        }
    }

    //This loads the selected survey in the list of Existing Surveys, it can list as many surveys as possible except for one of the duplicates
    public void loadExistingSurveys() throws IOException{
        //Setup Readers to read for each existing surveys in "existingSurveys.txt"
        fileReader = new FileReader("existingSurveys.txt");
        bufferedReader = new BufferedReader(fileReader);

        //Check if the application has just been opened or not; if no, it memorize what survey has been selected beforehand
        if (firstTime != 0){
            surveyMem = listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem().surveyName;
        }

        //Clears ListView beforehand to prevent duplication
        listV_Home_ExistingSurveys.getItems().clear();

        //Reads each line from the text file, resets separator with each new line
            while ((line1 = bufferedReader.readLine()) != null){
                separator = 0;
                if (!line1.equals("")){
                        for (int a = 0; a < line1.length();a++){
                            //If "=}" is spotted, it will mark down the Survey Name, Creator, Date respectively according to the separator;
                            if (line1.startsWith("=}", a)){
                                switch (separator){
                                    case 0:
                                        //Case 0: the substring 0 to the current a of the for loop is the Name marked in the line, separator increments to prepare for the next case
                                        existingSurveyName = line1.substring(0, a);
                                        separator++;
                                        break;
                                    case 1:
                                        //Case 1: the substring (survey name's length + "=}" to the current a of the for loop) is the Creator marked in the line, separator increments to prepare for the next case
                                        existingCreator = line1.substring(existingSurveyName.length() + 2, a);
                                        separator++;
                                        break;
                                    case 2:
                                        //Case 2: the substring (survey name's length + survey creator's length + two "=}"s to the current a of the for loop) is the Date marked in the line,
                                        //separator increments to prepare for the next case
                                        //Creates a new survey object as the third "=}" was detected, which marks the end of the line, also meaning all the information needed for creating a new set of surveys
                                        //Then clears all the questions in that respective survey's question Arraylist and reloads and adds all the questions back using the LoadSurveys Class
                                        existingDate = line1.substring(existingSurveyName.length() + existingCreator.length() + 4, a);
                                        surveys = new Surveys(existingSurveyName, existingCreator, existingDate);
                                        listV_Home_ExistingSurveys.getItems().add(surveys);
                                        surveys.questionsArrayList.clear();
                                        listV_VE_questions.getItems().clear();
                                        listV_Remove_removeQuestions.getItems().clear();
                                        surveys.questionsArrayList.addAll(LoadSurveys.loadAllQuestions(surveys));
                                        listV_VE_questions.getItems().addAll(surveys.questionsArrayList);
                                        listV_Remove_removeQuestions.getItems().addAll(surveys.questionsArrayList);
                                        break;
                                }
                            }
                        }
                }
            }
        //Identifies if it's the first time opening the program
        //If yes, there were no surveys selected beforehand, so it automatically selects the first one
            if (firstTime == 0){
                listV_Home_ExistingSurveys.getSelectionModel().select(0);
                firstTime++;
            }
        //If not, the memorized survey name is used to find out which survey was selected before and it gets selected with the for loop searching within the List View
            else{
                for (int a = 0; a < listV_Home_ExistingSurveys.getItems().size(); a++){
                    if (listV_Home_ExistingSurveys.getItems().get(a).surveyName.startsWith(surveyMem)){
                        listV_Home_ExistingSurveys.getSelectionModel().select(a);
                    }
                }
            }
            //BufferedReader is flushed and closed
            bufferedReader.close();

            //If the List View is empty, it would be disabled along with the buttons that needs the List View to function. This is mainly used when the application is opened for the first time
            if (listV_Home_ExistingSurveys.getItems().isEmpty()){
                listV_Home_ExistingSurveys.setDisable(true);
                btn_Home_OpenFile.setDisable(true);
                btn_Home_DeleteFile.setDisable(true);
            }
    }

    //This method is for when there is a duplicate file and the user decides not to open it
    public void rejectDuplication() {
        //All of the components of the Home Page will be enabled again after being disable
        btn_Home_createNew.setDisable(false);
        tf_Home_newName.setDisable(false);
        tf_Home_Creator.setDisable(false);
        tf_Home_Date.setDisable(false);
        tf_Home_newName.clear();
        tf_Home_Creator.clear();
        tf_Home_Date.clear();
        btn_Home_start.setDisable(false);

        //If the List View is empty, it would be disabled along with the buttons that needs the List View to function.
        if (!listV_Home_ExistingSurveys.getItems().isEmpty()){
            listV_Home_ExistingSurveys.setDisable(false);
            btn_Home_OpenFile.setDisable(false);
            btn_Home_DeleteFile.setDisable(false);
        }

        //The labels for duplication error will be hidden
        lbl_Home_dupDeleteText.setVisible(false);
        btn_Home_dupYes.setVisible(false);
        btn_Home_dupNo.setVisible(false);
    }

    //This method is for adding a question into a survey
    public void addQuestion() throws IOException {
        //Set the survey object to the one selected in the Existing Surveys List View
        surveys = listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem();
        //Checks which panel is enabled at the moment, the Multiple Choices Panel or the Written Panel
        //This block of code is for the Multiple Choices panel is enabled
        if (!ap_Add_mc.isDisabled()) {
            //Checks that the question and at least 2 choices has been filled in,
            //If not, the labels beside the text fields will turn red until it is all filled
            if (tf_Add_mc_Question.getText().isEmpty() || tf_Add_mc_Choice1.getText().isEmpty() || tf_Add_mc_Choice2.getText().isEmpty()) {
                lbl_Add_mc_Question.setStyle("-fx-text-fill: red");
                lbl_Add_choice1.setStyle("-fx-text-fill: red");
                lbl_Add_choice2.setStyle("-fx-text-fill: red");
            }
            //If all the required text fields has been filled,
            //A new Question object will be created and resets all the components inside the Multiple Choice Panel
            else {
                //Reset labels' colour to black
                lbl_Add_mc_Question.setStyle("-fx-text-fill: black");
                lbl_Add_choice1.setStyle("-fx-text-fill: black");
                lbl_Add_choice2.setStyle("-fx-text-fill: black");

                //Create a new Multiple Choice Question object with the information using the text fields
                //QuestionType: 0 = Multiple Choice 1 = Written
                questions = new Questions(0, tf_Add_mc_Question.getText(), tf_Add_mc_Choice1.getText(), tf_Add_mc_Choice2.getText(), tf_Add_mc_Choice3.getText(), tf_Add_mc_Choice4.getText());

                //Reset the text fields and clears them
                tf_Add_mc_Question.clear();
                tf_Add_mc_Choice1.clear();
                tf_Add_mc_Choice2.clear();
                tf_Add_mc_Choice3.clear();
                tf_Add_mc_Choice4.clear();
                tf_Add_mc_Choice3.setDisable(true);
                tf_Add_mc_Choice4.setDisable(true);

                //Reset the radio boxes' text and deselects them
                lbl_Add_mc_sampleQuestion.setText("e.g. ) ---");
                rb_Add_sampleChoice1.setText("---");
                rb_Add_sampleChoice2.setText("---");
                rb_Add_sampleChoice3.setText("---");
                rb_Add_sampleChoice4.setText("---");
                rb_Add_sampleChoices.selectToggle(null);

                //Adds the Multiple Choice question into the survey's question list, the View & Edit List View and the Remove List View
                surveys.questionsArrayList.add(questions);
                listV_VE_questions.getItems().add(questions);
                listV_Remove_removeQuestions.getItems().add(questions);

                //Auto-saves after adding a question
                saveSurvey();
            }
        }
        //This block of code is for the Written panel is enabled
        else {
            //Checks that the question has been filled in,
            //If not, the question label beside the text field will turn red until it is all filled
            if (tf_Add_written_Question.getText().isEmpty()) {
                lbl_Add_written_Question.setStyle("-fx-text-fill: red");
            }
            //If the text field is filled in, a new Question object will be created
            else {
                //Reset label's colour to black
                lbl_Add_written_Question.setStyle("-fx-text-fill: black");

                //Create a new Written Question object using the text field and a slider
                //QuestionType: 0 = Multiple Choice 1 = Written
                questions = new Questions(1, tf_Add_written_Question.getText(), (int) s_Add_written_Length.getValue());

                //Reset the labels, text field, text area and slider to default
                s_Add_written_Length.setValue(0);
                lbl_Add_written_Length.setText("(Short)");
                lbl_Add_written_sampleQuestion.setText("e.g. ) ---");
                ta_Add_written_answer.clear();
                tf_Add_written_Question.clear();

                //Adds the Written question into the survey's question list, the View & Edit List View and the Remove List View
                surveys.questionsArrayList.add(questions);
                listV_VE_questions.getItems().add(questions);
                listV_Remove_removeQuestions.getItems().add(questions);

                //Auto-saves after adding a question
                saveSurvey();
            }
        }
        //After adding a question, the respective survey should now have at least one question.
        //Therefore, the other tabs would be enabled along with the controls within
        tab_VE.setDisable(false);
        tab_Remove.setDisable(false);
        btn_Remove_Remove.setDisable(false);
        listV_VE_questions.setDisable(false);
        listV_Remove_removeQuestions.setDisable(false);
    }

    //This method is for viewing the question. E.g., listing what you had inserted before for creating a question
    public void viewQuestions() throws IOException {
        surveys = listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem();

        surveys.questionsArrayList.clear();
        listV_VE_questions.getItems().clear();
        listV_Remove_removeQuestions.getItems().clear();

        //Load all existing information from saves files into code (Arraylist and ListViews)
        surveys.questionsArrayList.addAll(LoadSurveys.loadAllQuestions(listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem()));
        listV_VE_questions.getItems().addAll(surveys.questionsArrayList);
        listV_Remove_removeQuestions.getItems().addAll(surveys.questionsArrayList);
        lbl_Add_surveyName.setText(listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem().surveyName);

        if (tab_VE.isSelected()){
            //Gets which question is selected by getting the Selection Model of the question List View
            listV_VE_questions.getSelectionModel().select(0);
            questions = listV_VE_questions.getSelectionModel().getSelectedItem();

            //Depending on the question type, different block of code will be used
            switch (questions.questionType) {
                //This block of code is for the Multiple Choices
                case 0:
                    //The question type label will be changed to "Multiple Choice" since it is a Multiple Choice
                    lbl_VE_questionType.setText("Question Type: Multiple Choice");

                    //The Written Anchor Pane is hidden while the Multiple Choice Anchor Pane is visible
                    ap_VE_editMC.setVisible(true);
                    ap_VE_editWritten.setVisible(false);

                    //The label beside the question text field indicates the order of number of the question in the question arraylist
                    //It will be changed accordingly everytime a new question is selected on the question arraylist
                    lbl_VE_questionNumber.setText((listV_VE_questions.getSelectionModel().getSelectedIndex() + 1) + ".");

                    //The text fields for each choice will show the text the user typed before when creating a Multiple Choice question
                    //Everytime a different question is selected, different choices will be shown accordingly
                    tf_VE_editQuestion.setText(questions.question);
                    tf_VE_editChoice1.setText(questions.choice1);
                    tf_VE_editChoice2.setText(questions.choice2);
                    tf_VE_editChoice3.setText(questions.choice3);
                    tf_VE_editChoice4.setText(questions.choice4);
                    break;

                //This block of code is for Written questions
                case 1:
                    //The question type label will be changed to "Written" since it is a Written question
                    lbl_VE_questionType.setText("Question Type: Written");

                    //The Written Anchor Pane is visible while the Multiple Choice Anchor Pane is hidden
                    ap_VE_editWritten.setVisible(true);
                    ap_VE_editMC.setVisible(false);

                    //The label beside the question text field indicates the order of number of the question in the question arraylist
                    //It will be changed accordingly everytime a new question is selected on the question arraylist
                    lbl_VE_questionNumber.setText((listV_VE_questions.getSelectionModel().getSelectedIndex() + 1) + ".");

                    //The question text field will show the question the suer typed in before while creating a Written question
                    tf_VE_editQuestion.setText(questions.question);
                    //The Expected Response Length slider will be set to the question's length from before while creating a Written question
                    //Everytime a different Written question, the slider will be set
                    switch (questions.questionLength) {
                        //If the length is 20, the slider is View and Edit tab will be set to the first slot
                        //And the text beside it will be changed to "Short"
                        case 20:
                            s_VE_editResponseLength.setValue(20);
                            lbl_VE_writtenLength.setText("(Short)");
                            break;

                        //If the length is 40, the slider is View and Edit tab will be set to the second slot
                        //And the text beside it will be changed to "Normal"
                        case 40:
                            s_VE_editResponseLength.setValue(40);
                            lbl_VE_writtenLength.setText("(Normal)");
                            break;

                        //If the length is 60, the slider is View and Edit tab will be set to the third slot
                        //And the text beside it will be changed to "Long"
                        case 60:
                            s_VE_editResponseLength.setValue(60);
                            lbl_VE_writtenLength.setText("(Long)");
                            break;

                        //If the length is 80, the slider is View and Edit tab will be set to the fourth slot
                        //And the text beside it will be changed to "Very Long"
                        case 80:
                            s_VE_editResponseLength.setValue(80);
                            lbl_VE_writtenLength.setText("(Very Long)");
                            break;

                        //If the length is 100, the slider is View and Edit tab will be set to the fifth slot
                        //And the text beside it will be changed to "Paragraph"
                        case 100:
                            s_VE_editResponseLength.setValue(100);
                            lbl_VE_writtenLength.setText("(Paragraph)");
                            break;
                    }
                    break;
            }
        }
    }

    //This method is for editing a question selected in the View and Edit tab
    public void editQuestion() throws IOException {
        //Gets which survey is selected by getting the Selection Model of the Existing Surveys List View
        surveys = listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem();
        //Gets which question is selected by getting the Selection Model of the question List View
        questions = listV_VE_questions.getSelectionModel().getSelectedItem();

        //Collects the new, updated question edited by the user and changes the original question to the new one
        questions.question = tf_VE_editQuestion.getText();
        //Depending on the question type of the selected question, it collects the new, updated choices or question length and changes the original information to the new ones in the text fields and slider
        switch (questions.questionType) {
            //Updates the Multiple Choices if the question type is Multiple Choice
            case 0:
                questions.choice1 = tf_VE_editChoice1.getText();
                questions.choice2 = tf_VE_editChoice2.getText();
                questions.choice3 = tf_VE_editChoice3.getText();
                questions.choice4 = tf_VE_editChoice4.getText();
                break;
            //Updates the question length if the question type is Written
            case 1:
                questions.questionLength = (int) s_VE_editResponseLength.getValue();
                break;
        }
        //Auto-saves after editing a question
        saveSurvey();

        //After editing a question, the list views gets refreshed by clearing it and loading it with the newly updated arraylist
        listV_VE_questions.getItems().clear();
        listV_Remove_removeQuestions.getItems().clear();
        listV_VE_questions.getItems().addAll(surveys.questionsArrayList);
        listV_Remove_removeQuestions.getItems().addAll(surveys.questionsArrayList);
        //Selects the question again to avoid Null Pointer Exception since it will have errors if nothing was selected
        listV_VE_questions.getSelectionModel().select(questions);
    }

    //This method is for removing a question from a survey
    public void removeQuestion() throws IOException {
        //Gets which survey is selected by getting the Selection Model of the Existing Surveys List View
        surveys = listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem();
        //Gets which question is selected by getting the Selection Model of the question List View
        questions = listV_Remove_removeQuestions.getSelectionModel().getSelectedItem();

        //Removes the selected question from the respective arraylist
        surveys.questionsArrayList.remove(questions);
        listV_VE_questions.getItems().remove(questions);
        listV_Remove_removeQuestions.getItems().remove(questions);
        lbl_Remove_text.setVisible(true);

        //Auto-saves after editing a question
        saveSurvey();

        //Scans if the list view is empty for that respective survey, if yes, some components of the program that require at least one question in the survey will be disabled
        if (surveys.questionsArrayList.isEmpty()) {
            listV_VE_questions.setDisable(true);
            listV_Remove_removeQuestions.setDisable(true);
            btn_Remove_Remove.setDisable(true);
            tab_VE.setDisable(true);
        }

        //To ensure that the information from the question before does not accidentally appear after being deleted
        //The text fields in View and Edit will be cleared
        tf_VE_editQuestion.clear();
        tf_VE_editChoice1.clear();
        tf_VE_editChoice2.clear();
        tf_VE_editChoice3.clear();
        tf_VE_editChoice4.clear();
    }

    //This method saves the survey so it could be loaded next time and you could get your progress back
    public void saveSurvey() throws IOException {
        //Gets which survey is selected by getting the Selection Model of the Existing Surveys List View
        surveys = listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem();
        tf_VE_creator.setText(surveys.creator);
        tf_VE_Date.setText(surveys.date);

        //If one of the text fields for creator or date is empty,
        //the program will not proceed to save the file; instead, it will turn some labels red to remind the user to fill in those fields
        //This if statement gets whether or not that one of the creator or date text fields are empty
        if (tf_VE_creator.getText().isEmpty() || tf_VE_Date.getText().isEmpty()){
            //If the creator field is not filled in, the label beside the creator text field will turn red
            if (tf_VE_creator.getText().isEmpty()){
                lbl_VE_creator.setStyle("-fx-text-fill: red");
            }
            //If the date field is not filled in, the label beside the date text field will turn red
            else if (tf_VE_Date.getText().isEmpty()){
                lbl_VE_date.setStyle("-fx-text-fill: red");
            }
        } else {
            //Changes the labels to black since the related text fields have been filled in
            lbl_VE_creator.setStyle("-fx-text-fill: black");

            //Updates the respective survey's creator and date by getting the text from the creator and date text fields in the View and Edit tab
            surveys.creator = tf_VE_creator.getText();
            surveys.date = tf_VE_Date.getText();

            //Saves the file using the method in Surveys class
            Surveys.saveFile(surveys);
        }
    }

    //This method is for opening existing surveys when you first opened the program, or you want to switch to a different file after creating or opening a file for the first time
    public void openExistingSurveys(){
        //Selects the survey selected
        surveys = listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem();

        //Changes the name text to the respective survey name
        lbl_Add_surveyName.setText(surveys.surveyName);

        //Enable tabs
        tab_VE.setDisable(false);
        tab_Add.setDisable(false);
        tab_Remove.setDisable(false);

        //Switch tabs according to if there are questions in there
        if (listV_VE_questions.getItems().isEmpty()){
            tabPane.getSelectionModel().select(tab_Add);
        } else{
            tabPane.getSelectionModel().select(tab_VE);
        }

        //If the question list view is not empty, some controls would be enabled
        if (!listV_VE_questions.getItems().isEmpty()){
            tab_VE.setDisable(false);
            tab_Remove.setDisable(false);
            btn_Remove_Remove.setDisable(false);
            listV_VE_questions.setDisable(false);
            listV_Remove_removeQuestions.setDisable(false);
        }
        //Else, the two tabs would be disabled
        else{
            tab_VE.setDisable(true);
            tab_Remove.setDisable(true);
        }
    }

    //This method is for deleting surveys in the Existing Surveys list
    public void deleteExistingSurveys() throws IOException {
        //Create a new File instance for the selected survey in the List View
        file = new File("src/saves/" + listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem().surveyName + "_SAVES.txt");

        //Setup file/buffered Writer
        fileWriter = new FileWriter("existingSurveys.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //If the file exist, the file would be deleted after the user exit the program
        if (file.exists()){
            file.deleteOnExit();

            //Removes the selected survey from the Existing Survey List view
            listV_Home_ExistingSurveys.getItems().remove(listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem());

            //Rewrites the whole Existing Surveys text file to update the deleted file from the List View
            for(int a = 0; a < listV_Home_ExistingSurveys.getItems().size();a++){
                bufferedWriter.write(listV_Home_ExistingSurveys.getItems().get(a).surveyName + "=}" +
                        listV_Home_ExistingSurveys.getItems().get(a).creator + "=}" + listV_Home_ExistingSurveys.getItems().get(a).date + "=}\r");
            }
            bufferedWriter.close();

            //Selects the list view to avoid errors
            listV_Home_ExistingSurveys.getSelectionModel().select(0);

            //If the List View is empty, some components is set disabled
            if (listV_Home_ExistingSurveys.getItems().isEmpty()){
                listV_Home_ExistingSurveys.setDisable(true);
                btn_Home_OpenFile.setDisable(true);
                btn_Home_DeleteFile.setDisable(true);
            }

            //Disables all the tabs
            tab_Add.setDisable(true);
            tab_VE.setDisable(true);
            tab_Remove.setDisable(true);

            //Cleaning up
            listV_VE_questions.getItems().clear();
            listV_Remove_removeQuestions.getItems().clear();
            tf_VE_creator.clear();
            tf_VE_Date.clear();
            tf_VE_editQuestion.clear();
            tf_VE_editChoice1.clear();
            tf_VE_editChoice2.clear();
            tf_VE_editChoice3.clear();
            tf_VE_editChoice4.clear();
            s_VE_editResponseLength.setValue(0);

            //Set Deleted text visible
            lbl_Home_dupDeleteText.setVisible(true);
            lbl_Home_dupDeleteText.setText("File will be deleted after you exit the program. \nPlease do not create a new file with the same new unless you restarted.");

            //Sets some components disabled
            listV_VE_questions.setDisable(true);
            listV_Remove_removeQuestions.setDisable(true);
            btn_Remove_Remove.setDisable(true);
        }
    }

    //This method is for exporting the survey as a text file
    public void exportSurvey() throws IOException{
        //Selects the respective survey on the List View
        int a = 1;
        surveys = listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem();

        //Sets up file/buffered Writers to a new export file
        fileWriter = new FileWriter("src/exports/" + listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem().surveyName + "_exported.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Writer writes the first line with the survey name, creator and date with some cosmetics
        bufferedWriter.write(surveys.surveyName + "     (Created by: " + surveys.creator + ")     Date: " + surveys.date + "\n\n" +
                "*****************************************************************");

        //For each questions in the selected survey, the writer will write a new question and the answers in a ordered pattern
        //First will be the questions
        for (Questions questions:LoadSurveys.loadAllQuestions(surveys)){
            if (a == 1){
                bufferedWriter.write( "\n" + a + ". " + questions.question + "\n\n");
            } else{
                bufferedWriter.write( "\n\n" + a + ". " + questions.question + "\n\n");
            }

            //Now for the answers
            //Below will be for MC answers
            if (questions.questionType == 0) {
                //First choice will be written down first
                bufferedWriter.write("o " + questions.choice1 + "\t\t");

                //If the either choice 1 is longer than choice 3 or vice versa, the placement will need to be different since there are different lengths, or else the answers will be very messy in terms of placement.
                //Below will be for if Choice 1 is longer than Choice 3
                if (questions.choice1.length() >= questions.choice3.length()) {
                    //Placement of choice 2 is not affected in this scenario
                    bufferedWriter.write("o " + questions.choice2 + "\n\n");
                    //Checks if that question has any choice 3 or 4s, if there are none two new lines will replace them instead
                    if (!questions.choice3.equals("")){
                        //Proceeds to write down choice 3 if there is content for choice 3
                        bufferedWriter.write("o " + questions.choice3 + "\t\t");
                        if (!questions.choice4.equals("")){
                            //According to how much longer choice 1 is than choice 3, different amount of distance will be needed
                            //How this works:
                            //If choice 1 is longer than choice 3 by 0-4 characters, no extra distance will be required
                            //If choice 1 is longer than choice 3 by 5-8 characters, 1 extra tab will be required
                            //If choice 1 is longer than choice 3 by 9-12 characters, 2 extra tabs will be required
                            //If choice 1 is longer than choice 3 by 13-16 characters, 3 extra tabs will be required
                            //If choice 1 is longer than choice 3 by 17-20 characters, 4 extra tabs will be required
                            switch (questions.choice1.length() - questions.choice3.length()) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    bufferedWriter.write("o " + questions.choice4 + "\n\n");
                                    break;
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                    bufferedWriter.write("\to " + questions.choice4 + "\n\n");
                                    break;
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                    bufferedWriter.write("\t\to " + questions.choice4 + "\n\n");
                                    break;
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                    bufferedWriter.write("\t\t\to " + questions.choice4 + "\n\n");
                                    break;
                                case 17:
                                case 18:
                                case 19:
                                case 20:
                                    bufferedWriter.write("\t\t\t\to " + questions.choice4 + "\n\n");
                                    break;
                            }
                        }
                    } else{
                        bufferedWriter.write("\n\n");
                    }
                }
                //Vice versa, if choice 3 is longer than choice 1, different amount of distance will be needed as well for choice 2 instead of choice 4 this time
                //How this works:
                //If choice 3 is longer than choice 1 by 1-4 characters, 1 extra tab will be required
                //If choice 3 is longer than choice 1 by 5-8 characters, 2 extra tabs will be required
                //If choice 3 is longer than choice 1 by 9-12 characters, 3 extra tabs will be required
                //If choice 3 is longer than choice 1 by 13-16 characters, 4 extra tabs will be required
                //If choice 3 is longer than choice 1 by 17-20 characters, 5 extra tabs will be required
                else {
                    switch (questions.choice3.length() - questions.choice1.length()) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            bufferedWriter.write("\to " + questions.choice2 + "\n\n");
                            break;
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            bufferedWriter.write("\t\to " + questions.choice2 + "\n\n");
                            break;
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                            bufferedWriter.write("\t\t\to " + questions.choice2 + "\n\n");
                            break;
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                            bufferedWriter.write("\t\t\t\to " + questions.choice2 + "\n\n");
                            break;
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                            bufferedWriter.write("\t\t\t\t\to " + questions.choice2 + "\n\n");
                            break;
                    }
                    //After writing down choice 2, writer proceeds to choice 3 and choice 4
                    bufferedWriter.write("o " + questions.choice3 + "\t\t");
                    bufferedWriter.write("o " + questions.choice4 + "\n\n");
                }
            }
            //Now for the written part of the export
            else{
                //According to the expected response length the user chosen, different amount of lines will be added
                //How this works:
                //Length 20 will have 1 line
                //Length 40 will have 2 lines
                //Length 60 will have 3 lines
                //Length 80 will have 4 lines
                //Length 100 will have 5 lines
                switch (questions.questionLength){
                    case 20:
                        bufferedWriter.write("_________________________________________________________________________________________________\n\n");
                        break;
                    case 40:
                        bufferedWriter.write("__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________\n\n");
                        break;
                    case 60:
                        bufferedWriter.write("__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________\n\n");
                        break;
                    case 80:
                        bufferedWriter.write("__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________\n\n");
                        break;
                    case 100:
                        bufferedWriter.write("__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________\n\n");
                        break;
                }
            }
            //At the end a double line will be added to separate the questions
            bufferedWriter.write("=================================================================");
            a++;
        }
        bufferedWriter.close();
    }

    //This method is for when different tabs are switched
    public void tabsChanged() throws IOException {
        //Get the selected survey in the List View
        surveys = listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem();

        //Make the labels' colour
        lbl_VE_creator.setStyle("-fx-text-fill: black");
        lbl_VE_date.setStyle("-fx-text-fill: black");

        //Set the text fields to the appropriate names
        tf_VE_creator.setText(surveys.creator);
        tf_VE_Date.setText(surveys.date);

        //Make the duplication warning components invisible
        lbl_Home_dupDeleteText.setVisible(false);
        btn_Home_dupYes.setVisible(false);
        btn_Home_dupNo.setVisible(false);

        //Reset the create new file components
        btn_Home_createNew.setDisable(false);
        tf_Home_newName.setDisable(true);
        tf_Home_newName.clear();
        tf_Home_Creator.setDisable(true);
        tf_Home_Creator.clear();
        tf_Home_Date.setDisable(true);
        tf_Home_Date.clear();

        //Make the removed question message invisible
        lbl_Remove_text.setVisible(false);

        //Checks for if Existing Survey list view is empty, disable it and the relative components
        if (!listV_Home_ExistingSurveys.getItems().isEmpty()){
            listV_Home_ExistingSurveys.setDisable(false);
            btn_Home_OpenFile.setDisable(false);
            btn_Home_DeleteFile.setDisable(false);
        }

        //Load all existing information from saves files into code (Arraylist and ListViews)
        surveys.questionsArrayList.addAll(LoadSurveys.loadAllQuestions(listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem()));
        listV_VE_questions.getItems().addAll(surveys.questionsArrayList);
        listV_Remove_removeQuestions.getItems().addAll(surveys.questionsArrayList);
        lbl_Add_surveyName.setText(listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem().surveyName);


        //Checks if the lists have questions
        if (!listV_VE_questions.getItems().isEmpty()){
            //If not, the list view will be enabled
            listV_VE_questions.setDisable(false);
            listV_Remove_removeQuestions.setDisable(false);

            //Clear the lists to reset it after reading
            surveys.questionsArrayList.clear();
            listV_VE_questions.getItems().clear();
            listV_Remove_removeQuestions.getItems().clear();

            //Load all existing information from saves files into code (Arraylist and ListViews)
            surveys.questionsArrayList.addAll(LoadSurveys.loadAllQuestions(listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem()));
            listV_VE_questions.getItems().addAll(surveys.questionsArrayList);
            listV_Remove_removeQuestions.getItems().addAll(surveys.questionsArrayList);
            lbl_Add_surveyName.setText(listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem().surveyName);

            //Selects a question to prevent errors
            listV_Remove_removeQuestions.getSelectionModel().select(0);
            listV_VE_questions.getSelectionModel().select(0);

            //Set the survey information to the respective information
            lbl_Add_surveyName.setText(listV_Home_ExistingSurveys.getSelectionModel().getSelectedItem().surveyName);
            tf_VE_creator.setText(surveys.creator);
            tf_VE_Date.setText(surveys.date);

            //If the View and Edit tab is selected this method will be called
            viewQuestions();
        }
        //Else, disables the list views
        else{
            listV_VE_questions.setDisable(true);
            listV_Remove_removeQuestions.setDisable(true);
        }
    }

    //This method is for refreshing the samples in the Add tab while creating a question when a key is released
    public void refreshSamples() {
        //Work when only the Add tab is selected
        if (tab_Add.isSelected()) {
            //If the Multiple Choice pane is selected, refreshing will only happen to Multiple Choice
            if (!ap_Add_mc.isDisable()) {

                //If the question text field is empty, changes the question sample to default
                if (tf_Add_mc_Question.getText().isEmpty()) {
                    lbl_Add_mc_sampleQuestion.setText("e.g. ) ---");
                }
                //If the question text field is empty, changes the question sample to the the text inside the text field
                else {
                    lbl_Add_mc_sampleQuestion.setText("e.g. ) " + tf_Add_mc_Question.getText());
                }

                //If the question text field is empty, changes the question sample to default
                if (tf_Add_mc_Choice1.getText().isEmpty()) {
                    rb_Add_sampleChoice1.setText("---");
                }
                //If the question text field is empty, changes the question sample to the the text inside the text field
                else {
                    rb_Add_sampleChoice1.setText(tf_Add_mc_Choice1.getText());
                }

                //If the question text field is empty, changes the question sample to default
                if (tf_Add_mc_Choice2.getText().isEmpty()) {
                    rb_Add_sampleChoice2.setText("---");
                }
                //If the question text field is empty, changes the question sample to the the text inside the text field
                else {
                    rb_Add_sampleChoice2.setText(tf_Add_mc_Choice2.getText());
                }

                //If the question text field is empty, changes the question sample to default
                if (tf_Add_mc_Choice3.getText().isEmpty()) {
                    rb_Add_sampleChoice3.setText("---");
                }
                //If the question text field is empty, changes the question sample to the the text inside the text field
                else {
                    rb_Add_sampleChoice3.setText(tf_Add_mc_Choice3.getText());
                }

                //If the question text field is empty, changes the question sample to default
                if (tf_Add_mc_Choice4.getText().isEmpty()) {
                    rb_Add_sampleChoice4.setText("---");
                }
                //If the question text field is empty, changes the question sample to the the text inside the text field
                else {
                    rb_Add_sampleChoice4.setText(tf_Add_mc_Choice4.getText());
                }

                //If the choice 1 & 2 is not empty, choice 3 text field will be enabled
                if ((!tf_Add_mc_Choice1.getText().isEmpty()) && (!tf_Add_mc_Choice2.getText().isEmpty())){
                    tf_Add_mc_Choice3.setDisable(false);
                    //If choice 3 is not empty, then choice 4 will be opened as well
                    if (!tf_Add_mc_Choice3.getText().isEmpty()){
                        tf_Add_mc_Choice4.setDisable(false);
                    }
                }

                //Else, if the text fields does not have content, choice 3 & 4 text fields will be disabled
                else{
                    tf_Add_mc_Choice3.clear();
                    tf_Add_mc_Choice3.setDisable(true);
                    rb_Add_sampleChoice3.setText("---");
                    tf_Add_mc_Choice4.clear();
                    tf_Add_mc_Choice4.setDisable(true);
                    rb_Add_sampleChoice4.setText("---");
                }
            }

            //If the written tab is chosen instead, samples in the written tab will be refreshed instead
            else if (!ap_Add_written.isDisable()) {
                //If the question text field is empty, changes the question sample to default
                if (tf_Add_written_Question.getText().isEmpty()) {
                    lbl_Add_written_sampleQuestion.setText("e.g. ) ---");
                }
                //If the question text field is empty, changes the question sample to the the text inside the text field
                else {
                    lbl_Add_written_sampleQuestion.setText("e.g. ) " + tf_Add_written_Question.getText());
                }

                //Depending on the slider's value, the sample will be refreshed to a certain length
                //If the length is 20, 1 line will be shown
                //If the length is 40, 2 lines will be shown
                //If the length is 60, 3 lines will be shown
                //If the length is 80, 4 lines will be shown
                //If the length is 100, 5 lines will be shown

                switch ((int) s_Add_written_Length.getValue()) {
                    case 20:
                        lbl_Add_written_Length.setText("(Short)");
                        ta_Add_written_answer.setText("__________________________________________________________________________________________________");
                        break;
                    case 40:
                        lbl_Add_written_Length.setText("(Normal)");
                        ta_Add_written_answer.setText("__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________");
                        break;
                    case 60:
                        lbl_Add_written_Length.setText("(Long)");
                        ta_Add_written_answer.setText("__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________");
                        break;
                    case 80:
                        lbl_Add_written_Length.setText("(Very Long)");
                        ta_Add_written_answer.setText("__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________");
                        break;
                    case 100:
                        lbl_Add_written_Length.setText("(Paragraph)");
                        ta_Add_written_answer.setText("__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________" +
                                "\n__________________________________________________________________________________________________");
                        break;
                }
            }
        }

        //Again, depending on the slider's value, the sample label will be refreshed to represent a certain length
        //If the length is 20, "Short" will be shown
        //If the length is 40, "Normal" will be shown
        //If the length is 60, "Long" will be shown
        //If the length is 80, "Very Long" lines will be shown
        //If the length is 100, "Paragraph" lines will be shown
        else {
            switch ((int) s_VE_editResponseLength.getValue()) {
                case 20:
                    lbl_VE_writtenLength.setText("(Short)");
                    break;
                case 40:
                    lbl_VE_writtenLength.setText("(Normal)");
                    break;
                case 60:
                    lbl_VE_writtenLength.setText("(Long)");
                    break;
                case 80:
                    lbl_VE_writtenLength.setText("(Very Long)");
                    break;
                case 100:
                    lbl_VE_writtenLength.setText("(Paragraph)");
                    break;
            }
        }
    }

    //This method is for when different modes are selected (MC and Written)
    public void chooseType(){
        //Checks if the button for Written is enabled, if yes, Multiple Choice pane will be enabled and Written pane will be disabled
        if (!btn_Add_chooseType_Written.isDisabled()) {
            //Panes disabled and enabled
            ap_Add_written.setDisable(false);
            ap_Add_mc.setDisable(true);

            //Clear all the text fields to reset it
            tf_Add_mc_Question.clear();
            tf_Add_mc_Choice1.clear();
            tf_Add_mc_Choice2.clear();
            tf_Add_mc_Choice3.clear();
            tf_Add_mc_Choice4.clear();

            //Reset all the samples
            lbl_Add_mc_sampleQuestion.setText("e.g. ) ---");
            rb_Add_sampleChoice1.setText("---");
            rb_Add_sampleChoice2.setText("---");
            rb_Add_sampleChoice3.setText("---");
            rb_Add_sampleChoice4.setText("---");

            //Reset the colour for the labels
            lbl_Add_mc_Question.setStyle("-fx-text-fill: black");
            lbl_Add_choice1.setStyle("-fx-text-fill: black");
            lbl_Add_choice2.setStyle("-fx-text-fill: black");
        } else {
            //Panes disabled and enabled
            ap_Add_written.setDisable(true);
            ap_Add_mc.setDisable(false);

            //Clear text field and reset slider
            tf_Add_written_Question.clear();
            s_Add_written_Length.setValue(0);

            //Reset all the samples
            lbl_Add_written_sampleQuestion.setText("e.g. ) ---");
            lbl_Add_written_Length.setText("(Short)");
            ta_Add_written_answer.setText("__________________________________________________________________________________________________");

            //Reset colours for the labels
            lbl_Add_written_Question.setStyle("-fx-text-fill: black");
        }
    }
}