import java.io.*;
public class Disturbed {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        boolean[] house = new boolean[n];
        String[] line = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            house[i] = line[i].equals("1");
        }

        int res = 0;
        int index = 1;
        while(!isOk(house)) {
            if(isDist(house, index)) {
                house[index+1] = false;
                res++;
            }
            index++;
        }

        System.out.println(res);
    }

    static boolean isDist(boolean[] house, int i) {
        return !house[i] && house[i-1] && house[i+1]; 
    }

    static boolean isOk(boolean[] house) {
        for(int i = 1; i < house.length-1; i++) {
            if(isDist(house,i))
                return false;
        }
        return true;
    }
}