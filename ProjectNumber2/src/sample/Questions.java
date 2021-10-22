package sample;

public class Questions {

    public int questionType;
    public String question;
    public String choice1;
    public String choice2;
    public String choice3;
    public String choice4;
    public int questionLength;

    //This constructor creates a new Multiple Choice question with the parameters provided
    public Questions(int questionType, String question, String choice1, String choice2, String choice3, String choice4){
        this.questionType = questionType;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
    }

    //This constructor creates a new Written question with the parameters provided
    public Questions(int questionType, String question, int questionLength){
        this.questionType = questionType;
        this.question = question;
        this.questionLength = questionLength;
    }

    //This method is for parsing the question object to a string
   public String toString() {
       return question;
   }
}
