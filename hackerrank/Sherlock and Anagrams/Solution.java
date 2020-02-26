import java.io.*;
import java.util.*;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {

//        System.out.println(s);

        Map<String, Integer> hash = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            for(int j = i+1; j <= s.length(); j++) {
                char[] chars = s.substring(i, j).toCharArray();
                Arrays.sort(chars);
                String sub = String.valueOf(chars);
                if(hash.get(sub) == null)
                    hash.put( sub, 1 );
                else
                    hash.put( sub, hash.get(sub)+1 );

//                System.out.print(sub + " - ");
            }
        }

//        System.out.println();

        int count = 0;
        for(String subs : hash.keySet()) {
            int c = hash.get(subs);
            count += c * (c-1) / 2;
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            System.out.println(String.valueOf(result));
        }

        scanner.close();
    }
}
