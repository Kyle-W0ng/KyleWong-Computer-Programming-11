/* Last of all, the Student class. The Student Class contains all the possible students' first name, last name, grade, and their special, unique student ID. */

import java.util.Random;

public class Student {
    private String[] sFirstName;
    private String[] sLastName;
    private int[] grade = new int[50];
    private int[] studentID = new int[50];

    /* This is the constructor of the Student class, which holds all potential names for students. Below the bank of names, is the grade and student ID generator. */

    public Student(){
        Random random = new Random();

        //Bank of Student's First Name
        sFirstName = new String[]{"Elvina", "Maverick","Marcy","Rolland","Layne","Tiffany","Pat","Kobe"
                                ,"Kristie","Kerry","Simon","Maria","Kenny","Bryan","David","Lyle","Benny"
                                ,"Kevin","Lauren","Kelly","Peter","Rowan","Isabella","Bernice","Vanessa"
                                ,"Dickson","Tyler","Steve","Abraham","Steven","Andrew","Brandon","Edwin"
                                ,"Thea","Ruby","Clara","Scarlet","Lily","Lisa","Tom","Bruce","Sara"
                                ,"Thomas","Lara","Sarah","Hannah","Bosco","Miles","Ivy","Kenneth"};
        //Bank of Student's Last name
        sLastName = new String[]{"Xin","Margaret","Hughie","Albert","Calvin","Victoria","Maudie","Alexander"
                                ,"Jacobson","Maxwell","Fraser","Wong","Leung","Chan","Zhang","Jing","Wang"
                                ,"Yamazaki","Inoue","Sun","Hamilton","Wells","Kim","Shinobu","Ryu","Kei"
                                ,"Hui","Ling","Arnold","Cheng","Xiao","Ma","Zhou","Jia","Cai","Yuan","Evans"
                                ,"Holmes","Price","Parker","Lewis","Hall","Giyu","Hinata","Yoshida","Sato"
                                ,"Tanaka","Daiki","Natsuki","Hiro"};
        //Grade and Student ID generator
        for(int i = 0;i < 50;i++){
            grade[i] = random.nextInt(6) + 7;
            studentID[i] = i + 10000;
        }
    }

    public String[] getsFirstName() {
        return sFirstName;
    }

    public void setsFirstName(String[] sFirstName) {
        this.sFirstName = sFirstName;
    }

    public String[] getsLastName() {
        return sLastName;
    }

    public void setsLastName(String[] sLastName) {
        this.sLastName = sLastName;
    }

    public int[] getGrade() {
        return grade;
    }

    public void setGrade(int[] grade) {
        this.grade = grade;
    }

    public int[] getStudentID() {
        return studentID;
    }

    public void setStudentID(int[] studentID) {
        this.studentID = studentID;
    }

}
