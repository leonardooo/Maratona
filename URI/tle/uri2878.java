import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] linha = br.readLine().split(" ");
        int x = Integer.parseInt(linha[0]);
        int y = Integer.parseInt(linha[1]);

        linha = br.readLine().split(" ");
        int h = Integer.parseInt(linha[0]);
        int v = Integer.parseInt(linha[1]);

        Corte[] horiz = new Corte[h];
        for(int i = 0; i < h; i++) {
            linha = br.readLine().split(" ");
            int ini = Integer.parseInt(linha[0]);
            int fim = Integer.parseInt(linha[1]);

            horiz[i] = new Corte(ini,fim);
        }

        Corte[] verti = new Corte[v];
        for(int i = 0; i < v; i++) {
            linha = br.readLine().split(" ");
            int ini = Integer.parseInt(linha[0]);
            int fim = Integer.parseInt(linha[1]);

            verti[i] = new Corte(ini,fim);
        }

        Arrays.sort(horiz);
        Arrays.sort(verti);

        int res = (h+1)*(v+1);

        TreeSet<Integer> ss = new TreeSet<Integer>();
        for(Corte c: horiz) {
            res += ss.tailSet(c.fim).size();
            ss.add(c.fim);
        }

        ss = new TreeSet<Integer>();
        for(Corte c: verti) {
            res += ss.tailSet(c.fim).size();
            ss.add(c.fim);
        }

        System.out.println(res);

    }
}

class Corte implements Comparable {
    Integer ini, fim;
    public Corte(int x, int y) {
        this.ini = x;
        this.fim = y;
    }
    public int compareTo(Object obj) {
        Corte outro = (Corte)obj;
        return ini.compareTo(outro.ini);
    }
}