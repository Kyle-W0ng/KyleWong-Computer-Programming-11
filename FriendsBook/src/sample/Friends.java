package sample;

public class Friends {

    String name;
    String phone;
    String email;
    String address;

    Friends(String name, String phone, String email, String address){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String toString(){
        return name;
    }
}
