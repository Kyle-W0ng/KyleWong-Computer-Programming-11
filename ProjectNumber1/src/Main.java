import com.sun.java.accessibility.util.TopLevelWindowListener;

public class Main {
    //Code your solution to problem number one here
    static int problemOne(String s){
        int answer = 0;
        for (int a = 0;a<s.length();a++){
            if (s.substring(a,(a+1)).equals("a")||s.substring(a,(a+1)).equals("e")||s.substring(a,(a+1)).equals("i")||
                    s.substring(a,(a+1)).equals("o")||s.substring(a,(a+1)).equals("u")){
                answer++;
            }
        }
        return answer;
    }
    //Code you problem number two here
    static int problemTwo(String s){
        int answer = 0;
        for (int a = 0;a < s.length();a++){
            if (s.substring(a,(a+3)).equals("bob"))
                answer++;
            if (a == s.length()-3)break;
        }
        return answer;
    }
    //Code your solution to problem number 3 here
    static String problemThree(String s){
        int[] numberArr = new int[s.length()];
        for (int i = 0;i<s.length();i++){
            switch (s.substring(i,i+1)){
                case "a":
                    numberArr[i] = 1;
                    break;
                case "b":
                    numberArr[i] = 2;
                    break;
                case "c":
                    numberArr[i] = 3;
                    break;
                case "d":
                    numberArr[i] = 4;
                    break;
                case "e":
                    numberArr[i] = 5;
                    break;
                case "f":
                    numberArr[i] = 6;
                    break;
                case "g":
                    numberArr[i] = 7;
                    break;
                case "h":
                    numberArr[i] = 8;
                    break;
                case "i":
                    numberArr[i] = 9;
                    break;
                case "j":
                    numberArr[i] = 10;
                    break;
                case "k":
                    numberArr[i] = 11;
                    break;
                case "l":
                    numberArr[i] = 12;
                    break;
                case "m":
                    numberArr[i] = 13;
                    break;
                case "n":
                    numberArr[i] = 14;
                    break;
                case "o":
                    numberArr[i] = 15;
                    break;
                case "p":
                    numberArr[i] = 16;
                    break;
                case "q":
                    numberArr[i] = 17;
                    break;
                case "r":
                    numberArr[i] = 18;
                    break;
                case "s":
                    numberArr[i] = 19;
                    break;
                case "t":
                    numberArr[i] = 20;
                    break;
                case "u":
                    numberArr[i] = 21;
                    break;
                case "v":
                    numberArr[i] = 22;
                    break;
                case "w":
                    numberArr[i] = 23;
                    break;
                case "x":
                    numberArr[i] = 24;
                    break;
                case "y":
                    numberArr[i] = 25;
                    break;
                case "z":
                    numberArr[i] = 26;
                    break;
            }
        }
        int currTop = 0;
        int current = 0;
        String currentTopString = "";
        for (int i = 0;i < s.length() - 1;i++){
            if (i == 0){
                current++;
            }
            else if((numberArr[i - 1] <= numberArr[i] && numberArr[i] <= numberArr[i + 1])) {
                current++;
                if (current > currTop) {
                    currTop = current;
                    if (currTop > 1) {
                            currentTopString = s.substring(i - (currTop - 1), i + 1);
                    } else {
                        currentTopString = s.substring(i - (currTop), i);
                    }
                }
            }
            else if ((numberArr[i - 1] > numberArr[i] && numberArr[i] > numberArr[i + 1])) {
                if (current > currTop) {
                    currTop = current;
                    if (currTop > 1) {
                        currentTopString = s.substring(i - (currTop - 1), i + 1);
                    }
                    else{
                        currentTopString = s.substring(i - (currTop), i);
                    }
                    current = 0;
                }
            }
            else if ((numberArr[i - 1] <= numberArr[i] && numberArr[i] > numberArr[i + 1])) {
                current++;
                if (current > currTop) {
                    currTop = current;
                    currentTopString = s.substring(i - (currTop - 1), i + 1);
                    current = 0;
                }
            }
            else if ((numberArr[i - 1] > numberArr[i] && numberArr[i] <= numberArr[i + 1])) {
                current = 1;
            }
            if (i == s.length() - 2 && numberArr[i] <= numberArr[i+1]){
                current++;
                if (current > currTop) {
                    currTop = current;
                    currentTopString = s.substring(i - (currTop - 2), i + 2);
                }
            }
            }
        s = currentTopString;
        return s;
    }
    public static void main(String[] args) {
        /*
        Set s to a string and run your method using s as the parameter
        Run your method in a println statement to determine what the output was
        Once you think you have it working try running the tests.
        The tests will put your method through several different Strings to test
        all possible cases.  If you have 100% success then there is no bugs in your methods.
         */
    }
}
