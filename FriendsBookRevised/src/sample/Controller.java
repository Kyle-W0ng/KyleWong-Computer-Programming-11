package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Controller {

    //Code
    Friends friends;
    DateTimeFormatter dateTimeFormatter;
    static FileReader fileReader;
    static BufferedReader bufferedReader;
    static FileWriter fileWriter;
    static BufferedWriter bufferedWriter;
    static ObservableList<Friends> friendsListItems;
    static int saveNotSavedNum = 0;
    static ArrayList<Friends> friendsRecentlyAdded = new ArrayList<>();

    //Duplication Check
    static ArrayList<String> friendsDuplicationCheck;
    static String namesForChecking;
    static int nullCounter = 1;
    static String line1 = "";

    //Tabs
    public TabPane tabPane;
    public Tab tabPaneHP;
    public Tab tabFriendList;
    public Tab tabAddFriend;
    public Tab tabRemoveFriend;
    public Tab tabPaneSaves;
    public Tab tabPaneLoad;

    //HomePage
    public Button btnHP_AddFriend;
    public Button btnHP_RemoveFriend;
    public Button btn_HPLoadFriends;
    public Button btn_HPSaveFriends;
    public Label lblTime;
    public Label lblHP_recentAdded1;
    public Label lblHP_recentAdded2;
    public Label lblHP_recentAdded3;
    public Label lblHP_recentAdded4;
    public Label lblHP_recentAdded5;
    public Label lblHP_recentAdded6;
    public Label lblHP_recentAdded7;
    public Label lblHP_recentAdded8;
    public Label lblHP_recentAdded9;
    public Label lblHP_recentAdded10;
    public Label lblHP_recentAddedNum;
    public Label lblHP_loadSaveSuccess;
    public Label lbl_HPSaveNotSaved;

    //Friends List
    public ListView<Friends> friendsList;
    public Label lblFL_Name;
    public Label lblFL_Phone;
    public Label lblFL_Email;
    public Label lblFL_Address;

    //Add Friend
    public Label lblAF_Name;
    public Label lblAF_Address;
    public Label lblAF_Phone;
    public Label lblAF_Email;
    public Label lblAF_success;
    public TextField tfName;
    public TextField tfPhone;
    public TextField tfEmail;
    public TextField tfAddress;
    public Button btnAF_AddFriend;
    public Button btnAF_DuplicateCheckYes;
    public Button btnAF_DuplicateCheckNo;
    public Label AF_DuplicateCheck;

    //Remove Friend
    public ListView<Friends> RF_friendsList;
    public Button btnRF_removeFriend;
    public Label lblRF_Name;
    public Label lblRF_Phone;
    public Label lblRF_Email;
    public Label lblRF_Address;

    //Save Files
    public Button SF_preset1;
    public Button SF_preset2;
    public Button SF_preset3;
    public Button SF_preset4;
    public Button SF_preset5;
    public Button SF_preset6;
    public Button SF_preset7;
    public Button SF_preset8;
    public Button SF_preset9;
    public Button SF_preset10;

    //Load Files
    public Button LF_preset1;
    public Button LF_preset2;
    public Button LF_preset3;
    public Button LF_preset4;
    public Button LF_preset5;
    public Button LF_preset6;
    public Button LF_preset8;
    public Button LF_preset7;
    public Button LF_preset9;
    public Button LF_preset10;

    public void addFriend() throws IOException {
        //Setup
        lblAF_Name.setStyle("-fx-text-fill: black");
        lblAF_Email.setStyle("-fx-text-fill: black");
        lblAF_Phone.setStyle("-fx-text-fill: black");
        boolean isDuplicated = false;
        fileReader = new FileReader("presetRecorder.txt");
        bufferedReader = new BufferedReader(fileReader);
        friendsDuplicationCheck = new ArrayList<>();
        int a;

        //Code
        //Check if name is null
        if (!tfName.getText().equals("")) {
            //Check if email or phone is null
            if (!tfEmail.getText().equals("") || !tfPhone.getText().equals("")) {



                //Checks if all friends file are empty,if yes, skips the Duplication check
                for (a = 1; a <= Integer.parseInt(bufferedReader.readLine()); a++) {
                    //Opens up respective friend file
                    fileReader = new FileReader("friends" + (a) + ".txt");
                    bufferedReader = new BufferedReader(fileReader);
                    //If the file is null, nullCounter increases
                    if (bufferedReader.readLine() == null) {
                        nullCounter++;
                    }
                    //Resets process and open up PresetRecorder to look for the number again
                    bufferedReader.close();
                    fileReader = new FileReader("presetRecorder.txt");
                    bufferedReader = new BufferedReader(fileReader);
                }
                //If nullCounter equals to a, that means that all existing friend files are null, skips the duplication check
                if (nullCounter == a) {
                    friends = new Friends(tfName.getText(), tfPhone.getText(), tfEmail.getText(), tfAddress.getText());
                    Friends.friendsArrayList.add(friends);
                    friendsRecentlyAdded.add(0, friends);
                    //Visuals
                    tabFriendList.setText("Friend List (" + Friends.friendsArrayList.size() + ")");
                    lblAF_success.setVisible(true);
                    friendsList.getItems().add(friends);
                    RF_friendsList.getItems().add(friends);
                    tfName.clear();
                    tfPhone.clear();
                    tfEmail.clear();
                    tfAddress.clear();
                }



                //If not, checks for duplications
                else {
                    //1. Adds all friends from saves files first
                    //Setup
                    //fileReader = new FileReader("presetRecorder.txt");
                    //bufferedReader = new BufferedReader(fileReader); (Backup)

                    //Preset
                    for (int b = 1; b <= 10; b++) {
                        //Opens a new friend text file
                        //bufferedReader.close(); (Backup)
                        fileReader = new FileReader("friends" + (b) + ".txt");
                        bufferedReader = new BufferedReader(fileReader);
                        //Reads every non-null line from preset #
                        while ((line1 = bufferedReader.readLine()) != null) {
                            //Checks if line contains anything
                            if (!line1.equals("")) {
                                //Scans every index for a name
                                for (int c = 0; c < line1.length(); c++) {
                                    //If the index equals ":", the name is in front of ":"
                                    if (line1.charAt(c) == ':') {
                                        //namesForChecking will equals the substring for later checking
                                        namesForChecking = line1.substring(0, c);
                                        //Add friends from preset # to Checklist
                                        friendsDuplicationCheck.add(namesForChecking);
                                        break;
                                    }
                                }
                            }
                        }
                        bufferedReader.close();
                        //fileReader = new FileReader("presetRecorder.txt"); (Backup)
                        //bufferedReader = new BufferedReader(fileReader); (Backup)
                    }


                    //2. Checks for current friends in listView
                    for (int c = 0;c < friendsList.getItems().size();c++){
                        friendsDuplicationCheck.add(friendsList.getItems().get(c).name);
                    }


                    //Then checks for duplicate in friendsDupCheck (contains both saves files and current friends)
                    for (String s : friendsDuplicationCheck) {
                        if (s.equals(tfName.getText())) {
                            isDuplicated = true;
                        }
                    }

                    //Then duplicates and non-duplicates split off to two different parts
                    if (!isDuplicated) {
                        friends = new Friends(tfName.getText(), tfPhone.getText(), tfEmail.getText(), tfAddress.getText());
                        Friends.friendsArrayList.add(friends);
                        friendsRecentlyAdded.add(0, friends);
                        lblAF_success.setVisible(true);
                        friendsList.getItems().add(friends);
                        RF_friendsList.getItems().add(friends);
                        tfName.clear();
                        tfPhone.clear();
                        tfEmail.clear();
                        tfAddress.clear();
                        tabFriendList.setText("Friend List (" + Friends.friendsArrayList.size() + ")");
                    } else {
                        tfName.setDisable(true);
                        tfPhone.setDisable(true);
                        tfEmail.setDisable(true);
                        tfAddress.setDisable(true);
                        btnAF_AddFriend.setDisable(true);
                        AF_DuplicateCheck.setVisible(true);
                        btnAF_DuplicateCheckNo.setVisible(true);
                        btnAF_DuplicateCheckYes.setVisible(true);
                        AF_DuplicateCheck.setText("The friend \n\"" + tfName.getText() + "\"\n has been added before in one of the save files.\nAre you sure you want to add them again?");
                        btnAF_DuplicateCheckYes.setOnAction(event -> addFriendFinal());
                        if (!btnAF_DuplicateCheckYes.isPressed()) {
                            btnAF_DuplicateCheckNo.setOnAction(event -> addFriendFinalFalse());
                        }
                    }
                }
                RF_friendsList.setDisable(false);
                friendsList.setDisable(false);
                btnRF_removeFriend.setDisable(false);

                for (int e = 0; e < friendsRecentlyAdded.size(); e++) {
                    lblHP_recentAddedNum.setText("Recently Added (" + (friendsRecentlyAdded.size()) + ") :");
                    switch (e) {
                        case 0:
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                        case 1:
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                        case 2:
                            lblHP_recentAdded3.setText(friendsRecentlyAdded.get(2).name);
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                        case 3:
                            lblHP_recentAdded4.setText(friendsRecentlyAdded.get(3).name);
                            lblHP_recentAdded3.setText(friendsRecentlyAdded.get(2).name);
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                        case 4:
                            lblHP_recentAdded5.setText(friendsRecentlyAdded.get(4).name);
                            lblHP_recentAdded4.setText(friendsRecentlyAdded.get(3).name);
                            lblHP_recentAdded3.setText(friendsRecentlyAdded.get(2).name);
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                        case 5:
                            lblHP_recentAdded6.setText(friendsRecentlyAdded.get(5).name);
                            lblHP_recentAdded5.setText(friendsRecentlyAdded.get(4).name);
                            lblHP_recentAdded4.setText(friendsRecentlyAdded.get(3).name);
                            lblHP_recentAdded3.setText(friendsRecentlyAdded.get(2).name);
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                        case 6:
                            lblHP_recentAdded7.setText(friendsRecentlyAdded.get(6).name);
                            lblHP_recentAdded6.setText(friendsRecentlyAdded.get(5).name);
                            lblHP_recentAdded5.setText(friendsRecentlyAdded.get(4).name);
                            lblHP_recentAdded4.setText(friendsRecentlyAdded.get(3).name);
                            lblHP_recentAdded3.setText(friendsRecentlyAdded.get(2).name);
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                        case 7:
                            lblHP_recentAdded8.setText(friendsRecentlyAdded.get(7).name);
                            lblHP_recentAdded7.setText(friendsRecentlyAdded.get(6).name);
                            lblHP_recentAdded6.setText(friendsRecentlyAdded.get(5).name);
                            lblHP_recentAdded5.setText(friendsRecentlyAdded.get(4).name);
                            lblHP_recentAdded4.setText(friendsRecentlyAdded.get(3).name);
                            lblHP_recentAdded3.setText(friendsRecentlyAdded.get(2).name);
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                        case 8:
                            lblHP_recentAdded9.setText(friendsRecentlyAdded.get(8).name);
                            lblHP_recentAdded8.setText(friendsRecentlyAdded.get(7).name);
                            lblHP_recentAdded7.setText(friendsRecentlyAdded.get(6).name);
                            lblHP_recentAdded6.setText(friendsRecentlyAdded.get(5).name);
                            lblHP_recentAdded5.setText(friendsRecentlyAdded.get(4).name);
                            lblHP_recentAdded4.setText(friendsRecentlyAdded.get(3).name);
                            lblHP_recentAdded3.setText(friendsRecentlyAdded.get(2).name);
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                        case 9:
                            lblHP_recentAdded10.setText(friendsRecentlyAdded.get(9).name);
                            lblHP_recentAdded9.setText(friendsRecentlyAdded.get(8).name);
                            lblHP_recentAdded8.setText(friendsRecentlyAdded.get(7).name);
                            lblHP_recentAdded7.setText(friendsRecentlyAdded.get(6).name);
                            lblHP_recentAdded6.setText(friendsRecentlyAdded.get(5).name);
                            lblHP_recentAdded5.setText(friendsRecentlyAdded.get(4).name);
                            lblHP_recentAdded4.setText(friendsRecentlyAdded.get(3).name);
                            lblHP_recentAdded3.setText(friendsRecentlyAdded.get(2).name);
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                        default:
                            lblHP_recentAdded10.setText(friendsRecentlyAdded.get(9).name + " + More...");
                            lblHP_recentAdded9.setText(friendsRecentlyAdded.get(8).name);
                            lblHP_recentAdded8.setText(friendsRecentlyAdded.get(7).name);
                            lblHP_recentAdded7.setText(friendsRecentlyAdded.get(6).name);
                            lblHP_recentAdded6.setText(friendsRecentlyAdded.get(5).name);
                            lblHP_recentAdded5.setText(friendsRecentlyAdded.get(4).name);
                            lblHP_recentAdded4.setText(friendsRecentlyAdded.get(3).name);
                            lblHP_recentAdded3.setText(friendsRecentlyAdded.get(2).name);
                            lblHP_recentAdded2.setText(friendsRecentlyAdded.get(1).name);
                            lblHP_recentAdded1.setText(friendsRecentlyAdded.get(0).name);
                            break;
                    }
                }
            } else {
                lblAF_Email.setStyle("-fx-text-fill: red");
                lblAF_Phone.setStyle("-fx-text-fill: red");
            }
        } else {
            lblAF_Name.setStyle("-fx-text-fill: red");
        }
    }

    public void addFriendFinal() {
        friends = new Friends(tfName.getText(), tfPhone.getText(), tfEmail.getText(), tfAddress.getText());
        Friends.friendsArrayList.add(friends);
        friendsRecentlyAdded.add(0, friends);
        lblAF_success.setVisible(true);
        friendsList.getItems().add(friends);
        RF_friendsList.getItems().add(friends);
        btnAF_AddFriend.setDisable(false);
        AF_DuplicateCheck.setVisible(false);
        btnAF_DuplicateCheckNo.setVisible(false);
        btnAF_DuplicateCheckYes.setVisible(false);
        tfName.clear();
        tfPhone.clear();
        tfEmail.clear();
        tfAddress.clear();
        tfName.setDisable(false);
        tfPhone.setDisable(false);
        tfEmail.setDisable(false);
        tfAddress.setDisable(false);
        tabFriendList.setText("Friend List (" + Friends.friendsArrayList.size() + ")");
    }

    public void addFriendFinalFalse() {
        tfName.clear();
        tfPhone.clear();
        tfEmail.clear();
        tfAddress.clear();
        tfName.setDisable(false);
        tfPhone.setDisable(false);
        tfEmail.setDisable(false);
        tfAddress.setDisable(false);
        AF_DuplicateCheck.setVisible(false);
        btnAF_DuplicateCheckNo.setVisible(false);
        btnAF_DuplicateCheckYes.setVisible(false);
        btnAF_AddFriend.setDisable(false);
    }

    public void removeFriend() {
        friends = RF_friendsList.getSelectionModel().getSelectedItem();
        RF_friendsList.getItems().remove(friends);
        friendsList.getItems().remove(friends);
        Friends.friendsArrayList.remove(RF_friendsList.getSelectionModel().getSelectedItem());
        tabFriendList.setText("Friend List (" + friendsList.getItems().size() + ")");
        lblFL_Name.setText("---------------");
        lblFL_Phone.setText("---------------");
        lblFL_Email.setText("---------------");
        lblFL_Address.setText("---------------");
        lblRF_Name.setText("---------------");
        lblRF_Phone.setText("---------------");
        lblRF_Email.setText("---------------");
        lblRF_Address.setText("---------------");
        if (friendsList.getItems().isEmpty()){
            friendsList.setDisable(true);
            RF_friendsList.setDisable(true);
            btnRF_removeFriend.setDisable(true);
        }
    }

    public void saveFriends() {
        if (friendsList.getItems().isEmpty()){
            lblHP_loadSaveSuccess.setStyle("-fx-text-fill: red");
            lblHP_loadSaveSuccess.setVisible(true);
            lblHP_loadSaveSuccess.setText("There are no friends in the list.");
        }else{
            tabPane.getSelectionModel().select(tabPaneSaves);
            tabPaneSaves.setDisable(false);
            tabFriendList.setDisable(true);
            tabAddFriend.setDisable(true);
            tabRemoveFriend.setDisable(true);
        }
    }

    public void savesPreset1() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f: friendsListItems){
            f.writeToSaveFile(1);
        }
        clearNReset(true);
    }

    public void savesPreset2() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f: friendsListItems){
            f.writeToSaveFile(2);
        }
        clearNReset(true);
    }

    public void savesPreset3() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f: friendsListItems){
            f.writeToSaveFile(3);
        }
        clearNReset(true);
    }

    public void savesPreset4() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f: friendsListItems){
            f.writeToSaveFile(4);
        }
        clearNReset(true);
    }

    public void savesPreset5() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f : friendsListItems) {
            f.writeToSaveFile(5);
        }
        clearNReset(true);
    }

    public void savesPreset6() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f: friendsListItems){
            f.writeToSaveFile(6);
        }
        clearNReset(true);
    }

    public void savesPreset7() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f: friendsListItems){
            f.writeToSaveFile(7);
        }
        clearNReset(true);
    }

    public void savesPreset8() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f: friendsListItems){
            f.writeToSaveFile(8);
        }
        clearNReset(true);
    }

    public void savesPreset9() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f: friendsListItems){
            f.writeToSaveFile(9);
        }
        clearNReset(true);
    }

    public void savesPreset10() throws IOException {
        friendsListItems = friendsList.getItems();
        for (Friends f: friendsListItems){
            f.writeToSaveFile(10);
        }
     clearNReset(true);
    }

    public void loadFriends() throws IOException {
        //Setup
        int lineCounter;
        String line2;

        tabPane.getSelectionModel().select(tabPaneLoad);
        tabPaneLoad.setDisable(false);
        tabFriendList.setDisable(true);
        tabAddFriend.setDisable(true);
        tabRemoveFriend.setDisable(true);
        for (int a = 1;a <= 10;a++){
            //Setup
            lineCounter = 0;
            fileReader = new FileReader("friends" + a + ".txt");
            bufferedReader = new BufferedReader(fileReader);

            while((line2 = bufferedReader.readLine()) != null){
                if (!line2.equals("")){
                    lineCounter++;
                }
            }
            if (lineCounter == 0){
                switch (a){
                    case 1:
                        LF_preset1.setDisable(true);
                        break;
                    case 2:
                        LF_preset2.setDisable(true);
                        break;
                    case 3:
                        LF_preset3.setDisable(true);
                        break;
                    case 4:
                        LF_preset4.setDisable(true);
                        break;
                    case 5:
                        LF_preset5.setDisable(true);
                        break;
                    case 6:
                        LF_preset6.setDisable(true);
                        break;
                    case 7:
                        LF_preset7.setDisable(true);
                        break;
                    case 8:
                        LF_preset8.setDisable(true);
                        break;
                    case 9:
                        LF_preset9.setDisable(true);
                        break;
                    case 10:
                        LF_preset10.setDisable(true);
                        break;
                }
            }else{
                switch (a){
                    case 1:
                        LF_preset1.setDisable(false);
                        break;
                    case 2:
                        LF_preset2.setDisable(false);
                        break;
                    case 3:
                        LF_preset3.setDisable(false);
                        break;
                    case 4:
                        LF_preset4.setDisable(false);
                        break;
                    case 5:
                        LF_preset5.setDisable(false);
                        break;
                    case 6:
                        LF_preset6.setDisable(false);
                        break;
                    case 7:
                        LF_preset7.setDisable(false);
                        break;
                    case 8:
                        LF_preset8.setDisable(false);
                        break;
                    case 9:
                        LF_preset9.setDisable(false);
                        break;
                    case 10:
                        LF_preset10.setDisable(false);
                        break;
                }
            }
            bufferedReader.close();
        }
    }

    public void loadPreset1() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(1));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends1.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
    }

    public void loadPreset2() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(2));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends2.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public void loadPreset3() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(3));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends3.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public void loadPreset4() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(4));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends4.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public void loadPreset5() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(5));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends5.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public void loadPreset6() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(6));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends6.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public void loadPreset7() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(7));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends7.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public void loadPreset8() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(8));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends8.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public void loadPreset9() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(9));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends9.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public void loadPreset10() throws IOException {
        Friends.friendsArrayList.addAll(LoadFriends.loadAllFriends(10));
        friendsList.getItems().addAll(Friends.friendsArrayList);
        RF_friendsList.getItems().addAll(Friends.friendsArrayList);

        //Setup
        fileWriter = new FileWriter("friends10.txt");
        bufferedWriter = new BufferedWriter(fileWriter);

        //Aftermath
        friendsList.setDisable(false);
        RF_friendsList.setDisable(false);
        btnRF_removeFriend.setDisable(false);
        clearNReset(false);
        bufferedWriter.write("");
        bufferedWriter.close();
    }

    public void addPage() {
        tabPane.getSelectionModel().select(tabAddFriend);
    }

    public void removePage() {
        tabPane.getSelectionModel().select(tabRemoveFriend);
    }

    public void displayProperties() {
        if (tabRemoveFriend.isSelected()){
            friends = RF_friendsList.getSelectionModel().getSelectedItem();
            lblRF_Name.setText(friends.name);
            lblRF_Phone.setText(friends.phone);
            lblRF_Email.setText(friends.email);
            lblRF_Address.setText(friends.address);
        } else if (tabFriendList.isSelected()){
            friends = friendsList.getSelectionModel().getSelectedItem();
            lblFL_Name.setText(friends.name);
            lblFL_Phone.setText(friends.phone);
            lblFL_Email.setText(friends.email);
            lblFL_Address.setText(friends.address);
        }
    }

    public void HPselected() {
        if (saveNotSavedNum == -1){
            lbl_HPSaveNotSaved.setVisible(false);
        }else if (saveNotSavedNum == 1){
            lbl_HPSaveNotSaved.setVisible(true);
        }
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss LLLL dd yyyy eeee");
            lblTime.setText("Time: " + LocalDateTime.now().format(dateTimeFormatter));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void clearSuccessText() {
        lblAF_success.setVisible(false);
        AF_DuplicateCheck.setVisible(false);
        btnAF_DuplicateCheckNo.setVisible(false);
        btnAF_DuplicateCheckYes.setVisible(false);
    }

    public void clearNReset(boolean tOrF){
        if (tOrF){
            saveNotSavedNum = -1;
        }else{
            saveNotSavedNum = 1;
        }
        tabPane.getSelectionModel().select(tabPaneHP);
        if (tOrF){
            lblFL_Name.setText("---------------");
            lblFL_Phone.setText("---------------");
            lblFL_Email.setText("---------------");
            lblFL_Address.setText("---------------");
            lblRF_Name.setText("---------------");
            lblRF_Phone.setText("---------------");
            lblRF_Email.setText("---------------");
            lblRF_Address.setText("---------------");
            Friends.friendsArrayList.clear();
            friendsList.getItems().clear();
            RF_friendsList.getItems().clear();
            friendsList.setDisable(true);
            RF_friendsList.setDisable(true);
            btnRF_removeFriend.setDisable(true);
            lblHP_loadSaveSuccess.setVisible(true);
            lblHP_loadSaveSuccess.setStyle("-fx-text-fill: #5fbc25");
            lblHP_loadSaveSuccess.setText("Saved successfully~");
        }else{
            lblHP_loadSaveSuccess.setVisible(true);
            lblHP_loadSaveSuccess.setStyle("-fx-text-fill: #5fbc25");
            lblHP_loadSaveSuccess.setText("Loaded successfully~");
        }

        tabFriendList.setText("Friend List (" + friendsList.getItems().size() + ")");
    }

    public void savesNLoadsSelectionChanged() {
        tabFriendList.setDisable(false);
        tabAddFriend.setDisable(false);
        tabRemoveFriend.setDisable(false);
        tabPaneSaves.setDisable(true);
        tabPaneLoad.setDisable(true);
        lblHP_loadSaveSuccess.setVisible(false);
    }
}