import java.util.*;
public class Shows {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long x = sc.nextLong();
        long y = sc.nextLong();

        Show[] all = new Show[n];
        for(int i = 0; i < n; i++) {
            all[i] = new Show(sc.nextLong(), sc.nextLong());
        }

        Arrays.sort(all);

        PriorityQueue<Long> queue = new PriorityQueue<Long>();
        Stack<Long> stack = new Stack<Long>();
        long res = 0;

        for (int i = 0; i < n; i++) {
            
            while(queue.size() > 0 && queue.peek() < all[i].ini) {
                stack.push(queue.poll());
            }

            if(stack.isEmpty()) {
                res += (all[i].fim - all[i].ini) * y + x;
                res %= MOD;
            } else {
                if((all[i].ini - stack.peek()) * y < x) {
                    res += (all[i].fim - stack.pop()) * y;
                    res %= MOD;
                } else {
                    res += (all[i].fim - all[i].ini) * y + x;
                    res %= MOD; 
                }
            }

            queue.add(all[i].fim);
        }

        System.out.println(res);

    }
}

class Show implements Comparable {
    long ini,fim;
    
    Show(long ini, long fim) {
        this.ini = ini;
        this.fim = fim;
    }
    
    public int compareTo(Object o) {
        Show other = (Show)o;
        return Long.valueOf(ini).compareTo(Long.valueOf(other.ini));
    }
}