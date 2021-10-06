import javax.crypto.CipherInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        SmartArray array = new SmartArray();

        for (int i = 0; i < 16; i++) {
            array.add(i + 1);
        }

        while(true){
            array.add(13);
            for (int i = 0; i < 8; i++) {
                array.remove(0);
            }
            System.out.println();
        }

    }
}