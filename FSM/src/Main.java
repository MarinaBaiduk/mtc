import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            Fsm fcm = new Fsm(args[0]);
            //fcm.init(args[0]);
            System.out.println(fcm.checkSequence(new BufferedReader(new InputStreamReader(new FileInputStream(args[1]))).readLine()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
