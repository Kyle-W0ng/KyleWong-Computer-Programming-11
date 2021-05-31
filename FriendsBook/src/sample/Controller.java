package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Controller {

    //Code
    Friends friends;
    DateTimeFormatter dateTimeFormatter;

    //Tabs
    public Tab tabAddFriend;
    public Tab tabRemoveFriend;
    public TabPane tabPane;
    public Tab tabPaneHP;
    public Tab tabFriendList;

    //HomePage
    public Button btnHP_AddFriend;
    public Button btnHP_RemoveFriend;
    public Label lblTime;
    public Label lblHP_recentAdded9;
    public Label lblHP_recentAddedNum;
    public Label lblHP_recentAdded8;
    public Label lblHP_recentAdded7;
    public Label lblHP_recentAdded5;
    public Label lblHP_recentAdded4;
    public Label lblHP_recentAdded2;
    public Label lblHP_recentAdded3;
    public Label lblHP_recentAdded1;
    public Label lblHP_recentAdded10;
    public Label lblHP_recentAdded6;

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

    //Remove Friend
    public ListView<Friends> RF_friendsList;
    public Button btnRF_removeFriend;
    public Label lblRF_Name;
    public Label lblRF_Phone;
    public Label lblRF_Email;
    public Label lblRF_Address;

    public void addFriend() {
        lblAF_Name.setStyle("-fx-text-fill: black");
        lblAF_Email.setStyle("-fx-text-fill: black");
        lblAF_Phone.setStyle("-fx-text-fill: black");
        if (!tfName.getText().equals("")){
            if (!tfEmail.getText().equals("") || !tfPhone.getText().equals("")){
                friends = new Friends(tfName.getText(), tfPhone.getText(), tfEmail.getText(), tfAddress.getText());
                friendsList.getItems().add(friends);
                RF_friendsList.getItems().add(friends);
                lblAF_success.setVisible(true);
                tfName.clear();
                tfPhone.clear();
                tfEmail.clear();
                tfAddress.clear();
                RF_friendsList.setDisable(false);
                friendsList.setDisable(false);
                btnRF_removeFriend.setDisable(false);
                for(int a = 0;a<friendsList.getItems().size();a++){
                    lblHP_recentAddedNum.setText("Recently Added (" + (a + 1) + ") :");
                    switch (a){
                        case 0:
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                        case 1:
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                        case 2:
                            lblHP_recentAdded3.setText(lblHP_recentAdded2.getText());
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                        case 3:
                            lblHP_recentAdded4.setText(lblHP_recentAdded3.getText());
                            lblHP_recentAdded3.setText(lblHP_recentAdded2.getText());
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                        case 4:
                            lblHP_recentAdded5.setText(lblHP_recentAdded4.getText());
                            lblHP_recentAdded4.setText(lblHP_recentAdded3.getText());
                            lblHP_recentAdded3.setText(lblHP_recentAdded2.getText());
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                        case 5:
                            lblHP_recentAdded6.setText(lblHP_recentAdded5.getText());
                            lblHP_recentAdded5.setText(lblHP_recentAdded4.getText());
                            lblHP_recentAdded4.setText(lblHP_recentAdded3.getText());
                            lblHP_recentAdded3.setText(lblHP_recentAdded2.getText());
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                        case 6:
                            lblHP_recentAdded7.setText(lblHP_recentAdded6.getText());
                            lblHP_recentAdded6.setText(lblHP_recentAdded5.getText());
                            lblHP_recentAdded5.setText(lblHP_recentAdded4.getText());
                            lblHP_recentAdded4.setText(lblHP_recentAdded3.getText());
                            lblHP_recentAdded3.setText(lblHP_recentAdded2.getText());
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                        case 7:
                            lblHP_recentAdded8.setText(lblHP_recentAdded7.getText());
                            lblHP_recentAdded7.setText(lblHP_recentAdded6.getText());
                            lblHP_recentAdded6.setText(lblHP_recentAdded5.getText());
                            lblHP_recentAdded5.setText(lblHP_recentAdded4.getText());
                            lblHP_recentAdded4.setText(lblHP_recentAdded3.getText());
                            lblHP_recentAdded3.setText(lblHP_recentAdded2.getText());
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                        case 8:
                            lblHP_recentAdded9.setText(lblHP_recentAdded8.getText());
                            lblHP_recentAdded8.setText(lblHP_recentAdded7.getText());
                            lblHP_recentAdded7.setText(lblHP_recentAdded6.getText());
                            lblHP_recentAdded6.setText(lblHP_recentAdded5.getText());
                            lblHP_recentAdded5.setText(lblHP_recentAdded4.getText());
                            lblHP_recentAdded4.setText(lblHP_recentAdded3.getText());
                            lblHP_recentAdded3.setText(lblHP_recentAdded2.getText());
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                        case 9:
                            lblHP_recentAdded10.setText(lblHP_recentAdded9.getText());
                            lblHP_recentAdded9.setText(lblHP_recentAdded8.getText());
                            lblHP_recentAdded8.setText(lblHP_recentAdded7.getText());
                            lblHP_recentAdded7.setText(lblHP_recentAdded6.getText());
                            lblHP_recentAdded6.setText(lblHP_recentAdded5.getText());
                            lblHP_recentAdded5.setText(lblHP_recentAdded4.getText());
                            lblHP_recentAdded4.setText(lblHP_recentAdded3.getText());
                            lblHP_recentAdded3.setText(lblHP_recentAdded2.getText());
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                        default:
                            lblHP_recentAdded10.setText(lblHP_recentAdded9.getText() + " + More");
                            lblHP_recentAdded9.setText(lblHP_recentAdded8.getText());
                            lblHP_recentAdded8.setText(lblHP_recentAdded7.getText());
                            lblHP_recentAdded7.setText(lblHP_recentAdded6.getText());
                            lblHP_recentAdded6.setText(lblHP_recentAdded5.getText());
                            lblHP_recentAdded5.setText(lblHP_recentAdded4.getText());
                            lblHP_recentAdded4.setText(lblHP_recentAdded3.getText());
                            lblHP_recentAdded3.setText(lblHP_recentAdded2.getText());
                            lblHP_recentAdded2.setText(lblHP_recentAdded1.getText());
                            lblHP_recentAdded1.setText(friendsList.getItems().get(a).toString());
                            break;
                    }
                }
            } else{
                lblAF_Email.setStyle("-fx-text-fill: red");
                lblAF_Phone.setStyle("-fx-text-fill: red");
            }
        } else{
            lblAF_Name.setStyle("-fx-text-fill: red");
        }
    }

    public void removeFriend() {
        friends = RF_friendsList.getSelectionModel().getSelectedItem();
        RF_friendsList.getItems().remove(friends);
        friendsList.getItems().remove(friends);
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
    public void clearSuccessText() {
        lblAF_success.setVisible(false);
    }

    public void time(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss LLLL dd yyyy eeee");
            lblTime.setText("Time: " + LocalDateTime.now().format(dateTimeFormatter));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        }
    }
