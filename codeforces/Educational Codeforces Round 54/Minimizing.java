import java.io.*;
import java.util.*;
public class Minimizing {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();

        boolean broken = false;
        for(int i = 0; i < n-1; i++) {
            char a = line.charAt(i);
            char b = line.charAt(i+1);

            if( ((int)b) < ((int)a) ) {
                line = line.substring(0,i).concat( line.substring(i+1, n) );
                broken = true;
                break;
            }
        }

        if(broken) {
            System.out.println(line);
        } else {
            System.out.println(line.substring(0, n-1));
        }

    }
}