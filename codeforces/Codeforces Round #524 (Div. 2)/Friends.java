import java.util.*;
public class Friends {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int i = 0; i < t; i++) {
            long n = sc.nextLong();
            long m = sc.nextLong();

            long x1 = sc.nextLong();
            long y1 = sc.nextLong();
            long x2 = sc.nextLong();
            long y2 = sc.nextLong();

            long x3 = sc.nextLong();
            long y3 = sc.nextLong();
            long x4 = sc.nextLong();
            long y4 = sc.nextLong();

            long white = white(1,1,n,m);
            long black = black(1,1,n,m);

            white += black(x1,y1,x2,y2);
            black -= black(x1,y1,x2,y2);

            white -= white(x3,y3,x4,y4);
            black += white(x3,y3,x4,y4);

            if(Math.max(x1,x3) <= Math.min(x2,x4) && Math.max(y1,y3) <= Math.min(y2,y4)) {
                white -= black(Math.max(x1,x3), Math.max(y1,y3), Math.min(x2,x4), Math.min(y2,y4));
                black += black(Math.max(x1,x3), Math.max(y1,y3), Math.min(x2,x4), Math.min(y2,y4));
            }

            System.out.println(white + " " + black);

        }
    }

    static long allwhite(long x, long y) {
        return (x/2 + (x%2!=0?1:0)) * (y/2 + (y%2!=0?1:0)) + (x/2) * (y/2);
    }

    static long white(long x1, long y1, long x2, long y2) {
        return allwhite(x2, y2) - allwhite(x2, y1-1) - allwhite(x1-1, y2) + allwhite(x1-1, y1-1);
    }

    static long black(long x1, long y1, long x2, long y2) {
        return (y2 - y1 + 1) * (x2 - x1 + 1) - white(x1,y1,x2,y2);
    }
}