import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ProblemaP3 {

    public static void decriptCription(String encryption) {

        // System.out.println(encryption);
        
        HashMap<String, Integer> appears = new HashMap<>(); 

        int strLength = encryption.length();

        for(int i = 0; i < strLength; i++) {
            String current = encryption.substring(i, i+1);
            if(appears.containsKey(current)) {
                appears.put(current, appears.get(current) + 1);
            } else {
                appears.put(current, 1);
            }
        }

        
        int charCount = appears.keySet().size(); 

        if(charCount == 1) {
            System.out.println(encryption + " " + encryption.charAt(strLength-1));
            return;
        }

        // System.out.println(appears);

        Stack<String> stack = new Stack<>();
        String prevSubsStr = "";
        for(int i = charCount; i > 0; i--) {

            if(i == charCount) {
                stack.push(encryption.charAt(encryption.length()-1) + "");
            } else {
                for(int j = encryption.length()-1; j >= 0 ; j--) {
                    String current = encryption.substring(j, j+1);
                    if(!stack.contains(current)) {
                        stack.push(current);
                        break;
                    }
                }
            }

            
            
            
            // System.out.println(stack.toString() + " iter: " + i);
            int lettersToRemove = 0; 
            
            for(String s: stack) {
                int currentApps = appears.get(s); 
                int lastCharAppsString = currentApps / i;
                appears.put(s, currentApps - lastCharAppsString);
                lettersToRemove += lastCharAppsString;
            }
            
            // System.out.println("encryption: " + encryption);
            String removedStr = encryption.substring(encryption.length()-lettersToRemove, encryption.length());

            if(i != 1) {
                encryption = encryption.substring(0, encryption.length() - lettersToRemove);
            }
            
            String checkEncryp = removedStr.replaceAll(stack.peek(), ""); 
            
            // System.out.println("encryptionSubtr: " + encryption + " removed: " + removedStr + " check: " + checkEncryp + " prevSubstr: " + prevSubsStr +" stack-top: " + stack.peek());
            
            if(!checkEncryp.equals(prevSubsStr)) {
                System.out.println("NO EXISTE"); 
                return; 
            }
            

            prevSubsStr = removedStr;
        }
        String stackAsString = "";
        while(!stack.isEmpty()) {
            stackAsString += stack.pop();
        }

        System.out.println("" + encryption + " "  + stackAsString);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numeroCasos = Integer.parseInt(br.readLine());
        long tiempo = 0L;
        for (int i = 0; i < numeroCasos; i++) {
            String encryption = br.readLine();
            long startTime = System.currentTimeMillis();
            decriptCription(encryption);
            long endTime = System.currentTimeMillis();
            tiempo += (endTime - startTime);
        }
        System.out.println("Tiempo total: " + tiempo);

    }
}
