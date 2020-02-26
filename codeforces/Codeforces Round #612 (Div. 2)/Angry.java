import java.io.*;
import java.util.*;
public class Angry {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int casos = Integer.parseInt(br.readLine());
        for(int i = 0; i < casos; i++) {

            int tam = Integer.parseInt(br.readLine());
            String estudantes = br.readLine().trim();

            boolean[] fila = new boolean[tam];
            for(int j = 0;  j < tam; j++) {
                if( estudantes.charAt(j) == 'A' )
                    fila[j] = true;
            }

            int res = solve(fila, tam);
            System.out.println(res);

        }
    }

    static int solve(boolean[] fila, int tam) {

        int minuto = -1;
        boolean mudou = true;

        while(mudou) {
            mudou = false;
            boolean[] clone = Arrays.copyOf(fila, tam);
            for(int j = 0;  j < tam-1; j++) {
                if(clone[j] && !clone[j+1]) {
                    mudou = true;
                    fila[j+1] = true;
                }
            }
            minuto++;
        }

        return minuto;
    }
}