// Here is the main class, where everything is run and directed. The Main Class is the basic structure of this program and other programs as well.

public class Main {
    public static void main(String[] args) {


        /* First of all, we have to create an instance of a class so that we could go in and get data from other classes.
           Here, we created an instance for the School Class, which let us get information from the School Class. Without
           creating an instance of a class, we will not be able to get data from that class and it will result in an error.*/
        new School();


        /* Then, here we have some methods that will be dived into to start running and shaping our program. First, we have
           an introductory class which basically introduce the name,size of the school.*/
        School.introduction();


        /* After that, the following block of code are the parts where the main codes are done. Inside the for loop,
           it's where the list of students and teachers are being created, adding students and teachers. After it has been constructed,
           both of the students and teachers list will be shown as well as the total number of pupils and teachers in this school.
           Last of all, 2 students and 1 teacher will be erased from the separate list and once again, the total number of staff and
           students will be printed again.*/
        for(int a=0;a<10;a++){
            School.addStudent();
            if (a < 3){
                School.addTeacher();
            }
        }
        School.showAllStudent();
        System.out.println();
        School.showAllTeacher();
        System.out.println();
        School.totalMember();
        for(int a=0;a<2;a++){
            School.delStudent();
            if (a < 1){
                School.delTeacher();
            }
        }
        School.showAllStudent();
        System.out.println();
        School.showAllTeacher();
        System.out.println();
        School.totalMember();
    }
}
