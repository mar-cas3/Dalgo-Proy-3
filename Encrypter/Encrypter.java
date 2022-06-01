import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Encrypter {

    // private static FileWriter P3in;
    // private static FileWriter P3out;


     private static PrintWriter P3in;
     private static PrintWriter P3out;
// writer.println("The first line");
// writer.println("The second line");
// writer.close();


    public static void encrypt(String password) throws IOException {
        String ogPassword = password; 

        String t = ""; 
        Random r = new Random();

        ArrayList<String> letters = new ArrayList<>();
        for(int i = 0; i < password.length(); i++) {
            String current = password.substring(i, i+1);
            if(!letters.contains(current)) {
                letters.add(current);
            }
        }

        ArrayList<String> removedOrder = new ArrayList<>();
        while(password.length() > 0) {
            
            t = t.concat(password); 
            int removeAt = r.nextInt(letters.size());
            String s = letters.get(removeAt);
            removedOrder.add(s);
            letters.remove(removeAt);
            password = password.replace(s, "");
        }

        //! P3.in
        P3in.println(t);
        // System.out.println(t);
        //! P3.out
        String removedOrderStr = "";
        for(String s: removedOrder) {
            removedOrderStr = removedOrderStr.concat(s);
        }
        P3out.println(ogPassword + " " + removedOrderStr);
        // System.out.println(ogPassword + " " + removedOrder);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numeroCasos = Integer.parseInt(br.readLine().trim());
        long tiempo = 0L;
        // P3in = new FileWriter("./P3.in");
        // P3out = new FileWriter("./P3.out");
        P3in =  new PrintWriter("P3.in", "UTF-8");
        P3out = new PrintWriter("P3.out", "UTF-8");
        P3in.println(numeroCasos);
        for (int i = 0; i < numeroCasos; i++) {
            String encryption = br.readLine();
            long startTime = System.currentTimeMillis();
            encrypt(encryption);
            long endTime = System.currentTimeMillis();
            tiempo += (endTime - startTime);
        }
        P3in.close();
        P3out.close();
        System.out.println("Tiempo total: " + tiempo);

    }
}
