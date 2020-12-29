import java.util.Scanner;

public class main {
    static int[] plusArr;
    static int startNo;
    static int noOfVal;
    static boolean valid;
    static boolean valid2;
    static int indexInit = 0;
    static String choice;
    static boolean choice2;
    static boolean choice3;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        do {
            choice2 = true;
            do {
                valid = true;
                System.out.println("Please insert the whole number (>= 0) you would like to start on.");
                startNo = Integer.parseInt(scan.next());
                do{
                    valid2 = true;
                if (startNo >= 0) {
                    System.out.println("How many values would you like? (Minimum: 2)");
                    noOfVal = Integer.parseInt(scan.next());
                    if (noOfVal < 2) {
                        System.out.println("Please provide a number higher or equal to 2.");
                        valid2 = false;
                    }
                }else {
                        System.out.println("Invalid Response. Try Again");
                        valid = false;
                    }
                }while (!valid2);
            } while (!valid);
            System.out.println("Starting number: " + startNo + " | No. of Values: " + noOfVal);

            int index = 0;
            System.out.println("Here are your numbers: ");
            for (int i = startNo; index <= noOfVal - 1; index++) {
                plusArr = new int[noOfVal];
                plusArr[index] = i;
                System.out.println(plusArr[index]);
                i++;
            }

            boolean valid2;
            do {
                valid2 = true;
                System.out.println("=========================================================================================");
                System.out.println("Would you like to add(+) or delete(-) a value at the end or insert(^) a value in between?");
                System.out.println("Please use the respective symbol behind the features.");
                String choice2 = scan.next();
                if (choice2.equals("+")) {
                    int[] plusInit = plus();
                    for (int a = indexInit; a <= index; a++) {
                        System.out.println(plusInit[a]);
                    }
                } else if (choice2.equals("-")) {
                    int[] deleteInit = delete();
                    for (int a = indexInit; a < noOfVal - 1; a++) {
                        System.out.println(deleteInit[a]);
                    }
                } else if (choice2.equals("^")) {
                    int[] insertInit = insert();
                    for (int a = indexInit; a <= index; a++) {
                        System.out.println(insertInit[a]);
                    }
                } else {
                    System.out.println("Invalid Response. Try Again");
                    valid2 = false;
                }


            } while (!valid2);

            do {
                choice3 = true;
                System.out.println("Do you want to use this function again?");
                choice = scan.next();
                if (choice.equals("yes") || choice.equals("Yes")) {
                    System.out.println();
                } else if (choice.equals("no") || choice.equals("No")) {
                    choice2 = false;
                    System.out.println("Thank you for using this application!");
                } else {
                    System.out.println("Invalid Response. Try Again.");
                    choice3 = false;
                }
            }while (!choice3);
        }while (choice2);
    }


    public static int[] plus() {
        int index = 0;
        int[] plusInit = new int[noOfVal + 1];
        for (int i = startNo; index <= noOfVal; index++) {
            plusInit[index] = i;
            i++;
        }
            return plusInit;
    }
    public static int[] delete() {
        int index = 0;
        int[] deleteInit = new int[noOfVal-1];
        for (int i = startNo; index < noOfVal-1; index++) {
            deleteInit[index] = i;
            i++;
        }
        return deleteInit;
    }
    public static int[] insert(){
        boolean choice2;
        boolean choice3;
        Scanner scan = new Scanner(System.in);
        int[] insertInit = new int[noOfVal+1];
        int insertValPlace;
        do {
            do {
                choice2 = true;
                choice3 = true;
                System.out.println("**Please be noted that your starting no. is the 0th place, 2nd no. is the 1st place, 3rd no. is 2nd place, etc**");
                System.out.println("Do you want an example of the number placing system?");
                String choice = scan.nextLine();
                if (choice.equals("yes") || choice.equals("Yes")) {
                    System.out.println("Index\tNumber\n0\t\t1\n1\t\t2\n2\t\t3\n3\t\t4\n4\t\t5");
                } else if (choice.equals("no") || choice.equals("No")) {
                } else {
                    System.out.println("Invalid response. Please try again.");
                    System.out.println("===================================");
                    choice3 = false;
                }
            }while (!choice3);
            if (noOfVal == 2){
                System.out.println("What index place do want to insert your additional number? (Your number must be 1)");
            }
            else {
                System.out.println("What index place do want to insert your additional number? (Your number must be in between 1 and " + (noOfVal - 1) + ")");
            }
        insertValPlace = Integer.parseInt(scan.next());
        if (insertValPlace > noOfVal){
            System.out.println("Your number doesn't not have as much value as the number you given to insert to. Please try again");
            System.out.println("=================================================================================================");
            choice2 = false;
            }
        }while (!choice2);

        System.out.println("What number do you want to insert in the value place you selected? (e.g. 3, 42, 96, 1055, 2071)");
        int insertValue = Integer.parseInt(scan.next());

        int index = 0;
        int i;
        for (i = startNo; index <= noOfVal; index++) {
            insertInit[index] = i;
            i++;
            insertInit[insertValPlace] = insertValue;
            if(index==insertValPlace)break;
            }
        for (int a = insertInit[insertValPlace-1];index<=noOfVal;index++){
            insertInit[insertValPlace+1] = a + 1;
            a++;
            insertValPlace++;
            if(index==noOfVal-1)break;
        }
        return insertInit;
    }
}
