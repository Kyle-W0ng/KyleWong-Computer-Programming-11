package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.Random;

public class Controller {

    public Label botMonPool;
    public Label yourMonPool;
    public Label winLose;
    public Button btnPlayAgain;
    public Slider rlrStakes;
    public Button BtnHowToPlay;
    public Label howToPlay;
    public Label lblMoneyPool;
    int stake;
    int intYourMonPool = 250;
    int intBotMonPool = 250;
    int moneyPool = 0;
    int yourNum;
    int botNum;
    public Label lblBotNum;
    public Label lblYouNum;
    public Button buttRoll;
    Random r = new Random();

    public void roll(){
        stake = (int) (rlrStakes.getValue());
        yourNum = Math.round(r.nextInt(11));
        botNum = Math.round(r.nextInt(11));
        lblYouNum.setText("[" + yourNum + "]");
        lblBotNum.setText("[" + botNum + "]");
        if (yourNum > botNum) {
            intBotMonPool -= stake;
            if (moneyPool > 0){
                intYourMonPool += (stake + moneyPool);
                lblMoneyPool.setText("Money Pool: 0");
            } else {
                intYourMonPool += stake;
            }
            yourMonPool.setText(intYourMonPool + " (+" + (stake + moneyPool) + ")");
            botMonPool.setText(intBotMonPool + " (-" + stake + ")");
            moneyPool = 0;
        } else if (yourNum < botNum){
            intYourMonPool -= stake;
            if (moneyPool > 0){
                intBotMonPool += (stake + moneyPool);
                lblMoneyPool.setText("Money Pool: 0");
            } else {
                intBotMonPool += stake;
            }
            botMonPool.setText(intBotMonPool + " (+" + (stake + moneyPool) + ")");
            yourMonPool.setText(intYourMonPool + " (-" + stake + ")");
            moneyPool = 0;
        } else {
            intYourMonPool -= stake;
            intBotMonPool -= stake;
            yourMonPool.setText(intYourMonPool + " (-" + stake + ")");
            botMonPool.setText(intBotMonPool + " (-" + stake + ")");
            moneyPool += stake*2;
            lblMoneyPool.setText("Money Pool: " + moneyPool);
        }
        checkLoses(intYourMonPool, intBotMonPool, buttRoll, rlrStakes, btnPlayAgain);
    }

    private void checkLoses(int intYourMonPool, int intBotMonPool, Button ButtRoll, Slider rlrStakes, Button btnPlayAgain){
        if (intYourMonPool <= 0 && intBotMonPool > 0){
            winLose.setText("YOU LOST!");
            rlrStakes.setDisable(true);
            ButtRoll.setDisable(true);
            btnPlayAgain.setVisible(true);
        } else if (intBotMonPool <= 0 && intYourMonPool > 0){
            winLose.setText("YOU WON!");
            rlrStakes.setDisable(true);
            ButtRoll.setDisable(true);
            btnPlayAgain.setVisible(true);
        } else if (intBotMonPool <=0){
            winLose.setText("TIE GAME!");
            rlrStakes.setDisable(true);
            ButtRoll.setDisable(true);
            btnPlayAgain.setVisible(true);
        }
    }

    public void playAgain() {
        buttRoll.setDisable(false);
        rlrStakes.setDisable(false);
        btnPlayAgain.setVisible(false);
        intBotMonPool = 250;
        intYourMonPool = 250;
        yourMonPool.setText("250");
        botMonPool.setText("250");
        lblYouNum.setText("~");
        lblBotNum.setText("~");
        winLose.setText("");
        rlrStakes.adjustValue(0);
    }

    public void howToPlay() {
        if (howToPlay.isVisible()){
            howToPlay.setVisible(false);
        } else{
            howToPlay.setVisible(true);
            howToPlay.setText("How to play:\n1.Slide the amount of money you want to bet\n2.Click the 'Roll' button" +
                    "\nGame rules: 1.First one to reach $0 loses\n2.The side with the bigger number wins\n3.Ties are counted as losses, the money from both sides will be kept" +
                    " and given to the winner in the next round");
        }
    }
}
