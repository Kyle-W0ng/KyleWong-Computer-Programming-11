/* Here we have the Teacher class, where all the basic information of the teachers are stored. For example, their first name, last name, and their teaching subject. */

public class Teacher {
    private String[] tFirstName;
    private String[] tLastName;
    private int[] teachingSubNumArray = new int[10];


    // The following block of code is the constructor for the teacher class; here, the full names of the teachers will be created as well as their teaching subject.
    // Regarding the First names and Last names, the first names and last names are called into the addTeacher method in the School Class for teacher construction.
    //Random combinations will be chosen and that will be the teacher's full name, in addition to the subject, which is also chosen by random.

    public Teacher() {
        //Bank of Teacher's First Name
        tFirstName = new String[]{"Mike", "Neil", "Vikky", "George", "Abigail", "Clara", "Robert", "Anson", "Brittany", "Emma", "Jason", "Alison", "Noah", "Michelle", "Felix", "Eva"};
        //Bank of Teacher's Last Name
        tLastName = new String[]{"McHugh", "Lee", "Brown", "Jones", "Hill", "Chen", "Yeung", "Tsang", "Smith", "Cheung"};
        //Teacher's Major subject generator
        for (int i = 0; i < 10; i++) {
            if (i < 8) {
                teachingSubNumArray[i] = i;
            } else {
                do {
                    teachingSubNumArray[i] = (int) (Math.random() * 10);
                }while (teachingSubNumArray[i] >= 8);
            }
        }
    }
    public String[] gettFirstName() {
        return tFirstName;
    }

    public void settFirstName(String[] tFirstName) {
        this.tFirstName = tFirstName;
    }

    public String[] gettLastName() {
        return tLastName;
    }

    public void settLastName(String[] tLastName) {
        this.tLastName = tLastName;
    }

    public int[] getTeachingSubNumArray() {
        return teachingSubNumArray;
    }

    public void setTeachingSubNumArray(int[] teachingSubNumArray) {
        this.teachingSubNumArray = teachingSubNumArray;
    }

}
