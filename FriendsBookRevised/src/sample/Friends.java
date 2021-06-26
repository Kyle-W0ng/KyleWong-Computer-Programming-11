package sample;

import java.io.*;
import java.util.ArrayList;

public class Friends {

    static ArrayList<Friends> friendsArrayList = new ArrayList<>();
    String name;
    String phone;
    String email;
    String address;
    static FileWriter fileWriter;
    static BufferedWriter bufferedWriter;

    Friends(String name, String phone, String email, String address){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String toString(){
        return name;
    }

    public void writeToSaveFile(int saveNo) throws IOException {
        //Setup
         fileWriter = new FileWriter("friends" + saveNo + ".txt",true);
         bufferedWriter = new BufferedWriter(fileWriter);

        //Code
        bufferedWriter.write("\r" + name + ":");
        bufferedWriter.write(phone + ":");
        bufferedWriter.write(email + ":");
        bufferedWriter.write(address + ":");
        bufferedWriter.write("|");
        bufferedWriter.close();
    }
}
