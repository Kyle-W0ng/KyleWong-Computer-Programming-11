package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadFriends {
    private static String name;
    private static String phone;
    private static String email;
    private static String address;
    private static int tempColon;
    private static final ArrayList<Friends> loadFriendsArrList = new ArrayList<>();

    public static ArrayList<Friends> loadAllFriends(int savesNo) throws IOException {
        //Setup
        FileReader fileReader = new FileReader("friends" + savesNo + ".txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //Load
        Friends.friendsArrayList.clear();
        loadFriendsArrList.clear();
        int colonCounter;
        String line;

        while ((line = bufferedReader.readLine()) != null){
            colonCounter = 1;
            if (!line.equals("")){
                for (int a = 0; a < line.length(); a++){
                    if (line.charAt(a) == ':'){
                        switch (colonCounter){
                            case 1:
                                name = line.substring(0,a);
                                tempColon = a;
                                colonCounter++;
                                break;
                            case 2:
                                if (line.charAt(a-1) == ':'){
                                    phone = "";
                                }else{
                                    phone = line.substring(tempColon+1,a);
                                }
                                tempColon = a;
                                colonCounter++;
                                break;
                            case 3:
                                if (line.charAt(a-1) == ':'){
                                    email = "";
                                } else{
                                    email = line.substring(tempColon+1,a);
                                }
                                tempColon = a;
                                colonCounter++;
                                break;
                            case 4:
                                if (line.charAt(a-1) == ':'){
                                    address = "";
                                } else{
                                    address = line.substring(tempColon+1,a);
                                }
                                break;
                        }
                    }
                    if (line.charAt(a) == '|'){
                        Friends friends = new Friends(name, phone, email, address);
                        loadFriendsArrList.add(friends);
                    }
                }
            }
        }
        return loadFriendsArrList;
    }
}
