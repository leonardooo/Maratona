import java.io.*;
public class Frog {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            String[] linha = br.readLine().split(" ");
            long a = Long.parseLong(linha[0]);
            long b = Long.parseLong(linha[1]);
            long k = Long.parseLong(linha[2]);

            long res = (a-b)*(k/2);
            
            if(k % 2 != 0) {
                res += a;
            }
            
            System.out.println(res);
        }
    }
}