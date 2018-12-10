import java.util.*;
public class Origami {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        long k = sc.nextLong();

        long red = (n*2)/k + ((n*2)%k != 0 ? 1 : 0);
        long gre = (n*5)/k + ((n*5)%k != 0 ? 1 : 0);
        long blu = (n*8)/k + ((n*8)%k != 0 ? 1 : 0);

        long res = red+gre+blu;

        System.out.println(res);
    }
}