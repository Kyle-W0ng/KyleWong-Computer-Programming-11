import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean playAgain;
        boolean playAgain2 = false;
        do {
            int wins = 0;
            int losses = 0;
            int draws = 0;
            int rounds = 0;
            int wrong = 0;
            String aiChoice = null;
            String playAgainChoice;

            do {
                //Rock = 1-3, Paper = 4-6, Scissors = 7-9 for the console
                //and for user Rock = 1, Paper = 2, Scissors = 3
                System.out.println("Choose rock paper or scissors. 'r' for rock,'p' for paper, 's' for scissors.");

                //This produces a random number for the console between 0-1
                int aiChoicePre = (int) (Math.random() * 10);
                //Backup if aiChoice is 0, and it keeps repeating until it's not 0.(Gives a fair chance for all three Rck, Ppr, Sic)
                while (aiChoicePre == 0) {
                    aiChoicePre = (int) (Math.random() * 10);
                }
                // This is the command for the user's input (e.g. 1,2 and 3)
                //and the Integer.parseInt changes the user's input from a string(which contains of characters in java, to a integer)
                String userChoice = scan.next();

                //This "if" statement ensures that no program following will run if the user provides a invalid input(e.g. 0, -1, 5)
                if (userChoice.equals("r") || userChoice.equals("p") || userChoice.equals("s")) {
                    //If the AI's number falls in 1-3 in the randomly-generated number, the "aiChoice" variable will change to rock
                    if (aiChoicePre <= 3) {
                        aiChoice = "r";
                    }
                    //If the AI's number falls in 4-6 in the randomly-generated number, the "aiChoice" variable will change to paper
                    else if (aiChoicePre <= 6) {
                        aiChoice = "p";
                    }
                    //If the AI's number falls in 7-9 in the randomly-generated number, the "aiChoice" variable will change to scissors
                    else {
                        aiChoice = "s";
                    }
                    //If it draws
                    if (userChoice.equals(aiChoice)) {
                        //If the aiChoice is rock, it prints out a draw statement. It doesn't matter if the user input is r,p or s because the result is 'draw' already.
                        if (aiChoice.equals("r")) {
                            System.out.println("Draw!\nComputer's Choice: Rock | Your Choice: Rock");
                        }
                        //prints draw statement when aiChoice is 2(Paper)
                        else if (aiChoice.equals("p")) {
                            System.out.println("Draw!\nComputer's Choice: Paper | Your Choice: Paper");
                        }
                        //prints draw statement when aiChoice is 3(scissors)
                        else {
                            System.out.println("Draw!\nComputer's Choice: Scissors | Your Choice: Scissors");
                        }
                        draws++;

                        //if ai wins, there are three options
                    } else if (((aiChoice.equals("r")) && (userChoice.equals("s")))
                            || ((aiChoice.equals("s")) && (userChoice.equals("p")))
                            || ((aiChoice.equals("p")) && (userChoice.equals("r")))) {
                        //If ai has rock,and ai wins, it will print a sentence saying that it won by having Rock.
                        if (aiChoice.equals("r")) {
                            System.out.println("You lose!\nComputer's Choice: Rock | Your Choice: Scissors");
                            // and each statement has a different scenario, for this one AI has scissors and wins
                        } else if (aiChoice.equals("s")) {
                            System.out.println("You lose!\nComputer's Choice: Scissors | Your Choice: Paper");
                            //and for this one, ai has paper and wins
                        } else {
                            System.out.println("You lose!\nComputer's Choice: Paper | Your Choice: Rock");
                        }
                        losses++;
                    }
                    //then the following ones are for the scenario when the user wins
                    else {
                        //This one if when user wins by having Paper and ai loses by having rock
                        if (((aiChoice.equals("r") && (userChoice.equals("p"))))) {
                            System.out.println("You win!\nComputer's Choice: Rock | Your Choice: Paper");
                            // This is when user wins by having scissors
                        } else if (((aiChoice.equals("p")) && (userChoice.equals("s")))) {
                            System.out.println("You win!\nComputer's Choice: Paper | Your Choice: Scissors");
                            //and the user's last option which is obviously Rock
                        } else {
                            System.out.println("You win!\nComputer's Choice: Scissors | Your Choice: Rock");
                        }
                        wins++;
                    }
                }
                //if user gives an invalid answer(Integers), an error will pop up
                else {
                    System.out.println("-Invalid answer, please try again.-");
                    wrong++;
                }

                rounds++;
                System.out.println("\n=======================================================");
                System.out.println("Wins: " + wins + "\tLosses: " + losses + "\t[Draws: " + draws + "]");

                //The final statement of losing or winning.
            } while (rounds < 10);
            if (wins > losses) {
                System.out.println("YOU WIN!!!");
            } else if (wrong >= 7) {
                System.out.println("Did you even try to play?");
            } else if ((wins == losses) && (wins != 0)) {
                System.out.println("DRAW!!!");
            } else System.out.println("YOU LOSE!!!");

            System.out.println("Do you want to play again? [Y]/[N]");
            do {
                //If the user chooses to play again, they "hopefully" will type y/Y and the game will run again
                playAgainChoice = scan.next();
                if (playAgainChoice.equals("Y") || playAgainChoice.equals("y")) {
                    playAgain = true;
                    playAgain2 = true;
                    //If the user chooses not to play again, they can type n/N and the game will end
                } else if (playAgainChoice.equals("N") || playAgainChoice.equals("n")) {
                    playAgain = true;
                    playAgain2 = false;
                    System.out.println("Thank you for playing!");
                } else {
                    //If the user provides a invalid answer(Other characters, text will pop out and tell they to choose correctly again
                    System.out.println("Invalid answer. Please choose again. [Y]/[N]");
                    playAgain = false;
                }
                // This while loop states that until the user provides a valid answer, the choice to pick whether or not you want to play again will repeat
            } while (!playAgain);
            //This While loop states that the whole game will repeat if the user said yes
        } while (playAgain2);
    }
}
