import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        boolean restart = false;
        do {
            Scanner scan = new Scanner(System.in);

            System.out.println("Please insert the message you want to encrypt.");
            String original = scan.nextLine();
            int namelth = original.length();
            String encrypted;
            String original2;

            //String 1-3 index places
            // lo + substring 0,1 + *#96 + substring 1 + uiol
            // Yes = Y + *#96 + es + uiol = Y*#96esuiol
            if (namelth == 1 || namelth == 2 || namelth == 3){
                encrypted = ("lo" + original.charAt(0) + "*#96" + original.substring(1) + "uiol");
            }
            //String 3 index places or above
            // lo + substring 1,4 + *#96 + substring 0,1 + uiol
            // Hell = lo + ell + *#96 + H + uiol = loell*#96Huiol
            else{
                encrypted = ("lo" + original.substring(1) + "*#96" + original.charAt(0) + "uiol");
            }
            original = "_";


            System.out.println("--Encrypting...--");
            System.out.println(encrypted);
            System.out.println();
            System.out.println("--Encryption successful.--\n");

            System.out.println("Do you want to decrypt the encrypted message? [Y]/[N]");
            boolean decryption;
            do {
                String choice = scan.nextLine();
                if (choice.equals("Y") || choice.equals("y")) {
                    decryption = true;
                    if (namelth == 1) {
                        original2 = encrypted.substring(2,3);
                        System.out.println("Here's the decrypted message:");
                        System.out.println(original2);

                    }else if (namelth == 2 || namelth == 3){
                        original2 = encrypted.substring(2,3);
                        original2 += encrypted.substring(7,encrypted.length()-4);
                        System.out.println("Here's the decrypted message:");
                        System.out.println(original2);
                    }else {
                        original2 = encrypted.substring(encrypted.length() - 5, encrypted.length() - 4);
                        original2 += encrypted.substring(2, encrypted.length() - 9);
                        System.out.println("Here's the decrypted message:");
                        System.out.println(original2);
                    }
                } else if (choice.equals("N") || choice.equals("n")) {
                    decryption = true;
                    System.out.println("Your message will remain encrypted, Thank you!");
                } else {
                    decryption = false;
                    System.out.println("Invalid answer, please choose again [Y]/[N]");
                }
            } while (!decryption);

            boolean restartopt;
            System.out.println("---------------------------\nWould you like to encrypt another message? [Y]/[N]");
            do {
                String choice2 = scan.nextLine();
                if (choice2.equals("Y") || choice2.equals("y")) {
                    restartopt = false;
                    restart = true;
                } else if (choice2.equals("N") || choice2.equals("n")) {
                    restartopt = false;
                    restart = false;
                    System.out.println("Thank you for using our encryption!");
                } else {
                    restartopt = true;
                    System.out.println("Invalid answer, please choose again [Y]/[N]");
                }
            }while(restartopt);
        }while (restart);
    }
}


