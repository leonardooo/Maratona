import java.util.*;
public class Shuffled {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        List<Integer> lista = new LinkedList<>();

        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            lista.add(sc.nextInt());
        }

        if(lista.size() == 1) {
            System.out.println("YES");
            System.out.println("1");
            System.out.println(lista.get(0));
            System.out.println("0");
            System.out.println();
        } else if(lista.size() == 2) {
            System.out.println("YES");
            System.out.println("1");
            System.out.println(lista.get(0));
            System.out.println("1");
            System.out.println(lista.get(1));
        } else {
            Collections.sort(lista);
            List<Integer> array = new ArrayList<>(lista);

            for(int i = 2; i < lista.size(); i++) {
                if( array.get(i).equals(array.get(i-1)) 
                        && array.get(i).equals(array.get(i-2)) ) {

                    System.out.println("NO");
                    System.exit(0);
                }
            }

            List<Integer> lista1 = new LinkedList<>();
            List<Integer> lista2 = new LinkedList<>();

            boolean[] removed = new boolean[array.size()];
            for(int i = 1; i < lista.size(); i++) {
                if( array.get(i).equals(array.get(i-1)) ) {
                    removed[i] = true;
                    lista1.add(array.get(i));
                }
            }

            for(int i = lista.size()-1; i >= 0; i--) {
                if(!removed[i]) {
                    lista2.add(array.get(i));
                }
            }

            System.out.println("YES");
            System.out.println(lista1.size());
            System.out.println(toString(lista1));
            System.out.println(lista2.size());
            System.out.println(toString(lista2));
        }

        

    }

    private static String toString(List<Integer> lista) {
        StringBuffer sb = new StringBuffer();
        for (Integer x : lista) {
            sb.append(x);
            sb.append(" ");
        }
        if(sb.length() > 0)
            return sb.toString().substring(0,sb.length()-1);
        return "";
    }
}