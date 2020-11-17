import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Rock = 1-3, Paper = 4-6, Scissors = 7-9 for the console
        //and for user Rock = 1, Paper = 2, Scissors = 3
        System.out.println("Choose rock paper or scissors. '1' for rock,'2' for paper, '3' for scissors.");

        //This produces a random number for the console between 0-1
        int aiChoice = (int) (Math.random() * 10);
        //Backup if aiChoice is 0, and it keeps repeating until it's not 0.(Gives a fair chance for all three Rck, Ppr, Sic)
        while (aiChoice == 0) {
            aiChoice = (int) (Math.random() * 10);
        }
        // This is the command for the user's input (e.g. 1,2 and 3)
        //and the Integer.parseInt changes the user's input from a string(which contains of characters in java, to a integer)
        int userChoice = Integer.parseInt(scan.next());

        //This "if" statement ensures that no program following will run if the user provides a invalid input(e.g. 0, -1, 5)
        if ((userChoice <= 3) && (userChoice != 0)) {
            //If the AI's number falls in 1-3 in the randomly-generated number, the "aiChoice" variable will change to 1, which = rock
            if (aiChoice <= 3) {
                aiChoice = 1;
            }
            //If the AI's number falls in 4-6 in the randomly-generated number, the "aiChoice" variable will change to 2, which = paper
            else if ((aiChoice <= 6) && (aiChoice > 3)) {
                aiChoice = 2;
            }
            //If the AI's number falls in 7-9 in the randomly-generated number, the "aiChoice" variable will change to 3, which = scissors
            else if (aiChoice >= 7) {
                aiChoice = 3;
            }
            //if user gives an invalid answer(Integers), an error will pop up
        } else {
            System.out.println("-Invalid answer, please try again.-");
        }

        //If it draws
        if (aiChoice == userChoice) {
            //If the aiChoice is 1, it prints out a draw statement. It doesn't matter if the user input is 1,2 or 3 because the result is 'draw' already.
            if (aiChoice == 1){
                System.out.println("Draw!\n" + "Computer's Choice: Rock(1) Your Choice: Rock(1)");
            }
            //prints draw statement when aiChoice is 2(Paper)
            else if (aiChoice ==2){
                System.out.println("Draw!\n" + "Computer's Choice: Paper(2) Your Choice: Paper(2)");
            }
            //prints draw statement when aiChoice is 3(scissors)
            else{
                System.out.println("Draw!\n" + "Computer's Choice: Scissors(3) Your Choice: Scissors(3)");
            }

            //if ai wins, there are three options
        } else if (((aiChoice == 1) && (userChoice == 3)) || ((aiChoice == 3) && (userChoice == 2)) || ((aiChoice == 2) && (userChoice == 1))) {
            //If ai has rock,and ai wins, it will print a sentence saying that it won by having Rock.
            if ((aiChoice == 1) && (userChoice == 3)) {
                System.out.println("\nYou lose!\n" + "Computer's Choice: Rock(1) Your Choice: Scissors(3)");
                // and each statement has a different scenario, for this one AI has scissors and wins
            } else if ((aiChoice == 3) && (userChoice == 2)) {
                System.out.println("\nYou lose!\n" + "Computer's Choice: Scissors(3) Your Choice: Paper(2)");
                //and for this one, ai has paper and wins
            } else {
                System.out.println("\nYou lose!\n" + "Computer's Choice: Paper(2) Your Choice: Rock(1)");
            }
            //then the following ones are for the scenario when the user wins
        } else {
            //This one if when user wins by having Paper and ai loses by having rock
            if ((aiChoice == 1) && (userChoice == 2)) {
                System.out.println("\nYou win!\n" + "Computer's Choice: Rock(1) Your Choice: Paper(2)");
                // This is when user wins by having scissors
            } else if ((aiChoice == 2) && (userChoice == 3)) {
                System.out.println("\nYou win!\n" + "Computer's Choice: Paper(2) Your Choice: Scissors(3)");
                //and the user's last option which is obviously Rock
            } else {
                System.out.println("\nYou win!\n" + "Computer's Choice: Scissors(3) Your Choice: Rock(1)");

            }
        }
    }
}