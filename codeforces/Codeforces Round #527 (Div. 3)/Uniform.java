import java.util.*;
public class Uniform {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        char[] alf = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
                    'n','o','p','q','r','s','t','u','v','w','x','y', 'z'};

        if(alf.length != 26) {
            System.out.println("ERROU");
            System.exit(1);
        }

        int t = sc.nextInt();
        for(int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            int cur = 0;
            while(n-- > 0) {
                sb.append(alf[cur++]);
                if(cur == k )
                    cur = 0;
            }

            System.out.println(sb.toString());
        }
    }
}