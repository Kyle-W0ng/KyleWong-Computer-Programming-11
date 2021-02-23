import java.util.ArrayList;
import java.util.Random;


/* The School Class is the place where most of the information gets stored, such like, the student's list, the teacher's list and all the courses available.*/
/* The following is the fields (properties) of the School. There's the name, the size, all the teachers and students, etc. These fields will be initialized later in
   constructor below */

public class School {
    private static String Name;
    private static int schoolSize;
    private static int totalMem;
    private static ArrayList<String> teachersList = new ArrayList<>();
    private static ArrayList<String> studentsList = new ArrayList<>();
    private static ArrayList<String> coursesList = new ArrayList<>();

    /* The School Class constructor below is a method where the fields are initialized, the name and size are included and the courses are added the the course list */

    public School(){
        Random random = new Random();
        Name = "Athena Polar High ";
        schoolSize = random.nextInt(5) + 1;
        coursesList.add("English");
        coursesList.add("Math");
        coursesList.add("Social Studies");
        coursesList.add("Science");
        coursesList.add("Physical Education");
        coursesList.add("Arts and Music");
        coursesList.add("Home Economics");
        coursesList.add("French");
    }

    // Method introduction: This method is used for the introductory of the school, stating its name and the size. It is used for a basic introduction.

    public static void introduction(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nWelcome to " + Name + "!!! Our School has a size of " + schoolSize + " km square." +
                            "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /* Method addTeacher: This method is used to add teachers to the Teachers list. It gets names by accessing the First Name and Last Name List in the Teacher Class and generate random names and put it in the Teachers List
       In Addition, a course is randomly selected for the teacher and thus generates the teacher's name and their subject in aw whole. */

    public static void addTeacher(){
        Teacher teacher = new Teacher();
        Random random = new Random();
            School.getTeachersList().add("Name: " + teacher.gettFirstName()[random.nextInt(10)] + " " + teacher.gettLastName()[random.nextInt(10)]
                    + "    Subject: " + School.getCoursesList().get(random.nextInt(8)));
            School.setTeachersList(School.getTeachersList());
        }

    /* Method addStudent: This method is used to add students to the Students list. Similar to the addTeacher method, it gets names by accessing the First Name and Last Name List in the Student Class
       and generate random names and put it in the Students List. Also, a grade and a student ID is added for the students.  */

    public static void addStudent(){
        Student student = new Student();
        Random random = new Random();
        School.getStudentsList().add("Name: " + student.getsFirstName()[random.nextInt(50)] + " " + student.getsLastName()[random.nextInt(50)]
                + "    Grade: " + student.getGrade()[random.nextInt(6)+7]);
        School.setStudentsList(School.getStudentsList());
    }

    /* Method delTeacher: This method is simply used to delete teachers on the current Teacher list. */

    public static void delTeacher(){
        Random random = new Random();
        School.getTeachersList().remove(random.nextInt(School.getTeachersList().size()));
        School.setTeachersList(School.getTeachersList());
    }

    /* Method delStudent: This method is simply used to delete students on the current Student list. */

    public static void delStudent(){
        Random random = new Random();
        School.getStudentsList().remove(random.nextInt(School.getStudentsList().size()));
        School.setStudentsList(School.getStudentsList());
    }

    /* Method showAllTeacher: This method is used for showing all the current teachers on the Teacher list. */

    public static void showAllTeacher(){
        for (int a = 0;a < School.getTeachersList().size();a++){
            System.out.println(School.getTeachersList().get(a));
        }
        System.out.println();
    }

    /* Method showAllStudent: This method is used for showing all the current students on the Student list. */

    public static void showAllStudent(){
        for (int a = 0;a < School.getStudentsList().size();a++){
            System.out.println(School.getStudentsList().get(a));
        }
    }

    /* Method totalMember: This method is used for showing the sum of the members on the Student list and the Teacher list. */

    public static void totalMember(){
        totalMem = studentsList.size() + teachersList.size();
        System.out.println("The total members in this school are: " + totalMem);
    }

    public static ArrayList<String> getTeachersList() {
        return teachersList;
    }

    public static void setTeachersList(ArrayList<String> teachersList) {
        School.teachersList = teachersList;
    }
    public static ArrayList<String> getStudentsList() {
        return studentsList;
    }

    public static void setStudentsList(ArrayList<String> studentsList) {
        School.studentsList = studentsList;
    }
    public static ArrayList<String> getCoursesList() {
        return coursesList;
    }

    public static void setCoursesList(ArrayList<String> coursesList) {
        School.coursesList = coursesList;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSchoolSize() {
        return schoolSize;
    }

    public void setSchoolSize(int schoolSize) {
        this.schoolSize = schoolSize;
    }

    public int getTotalMem() {
        return totalMem;
    }

    public void setTotalMem(int totalMem) {
        this.totalMem = totalMem;
    }
}
