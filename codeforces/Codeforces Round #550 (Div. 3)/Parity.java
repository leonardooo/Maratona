import java.util.*;
public class Parity {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        List<Integer> even = new LinkedList<>();
        List<Integer> odd = new LinkedList<>();
        
        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();
            if(isEven(x)) even.add(x);
            else odd.add(x);
        }

        Collections.sort(even);
        Collections.sort(odd);

        long sum1 = solve(new LinkedList<>(even), new LinkedList<>(odd));
        long sum2 = solve(odd, even);

        if(sum1 < sum2) System.out.println(sum1);
        else System.out.println(sum2);
    }

    private static long solve(List<Integer> list1, List<Integer> list2) {
        boolean isList1 = true;
        while(true) {
            if(isList1) {
                if(list1.size()>0) list1.remove( list1.size()-1 );
                else break;
            } else {
                if(list2.size()>0) list2.remove( list2.size()-1 );
                else break;
            }

            isList1 = !isList1;
        }

        if(list1.size() == 0 && list2.size() == 0) {
            return 0;
        } else if(list1.size() == 0) {
            return sum(list2);
        }

        return sum(list1);
    }

    private static boolean isEven(int x) {
        return x % 2 == 0;
    }

    private static long sum(List<Integer> lista) {
        long sum = 0;
        for (Integer x : lista) {
            sum += x;
        }

        return sum;
    }
}