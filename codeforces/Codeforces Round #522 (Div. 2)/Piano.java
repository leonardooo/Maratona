import java.util.*;
public class Piano {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] notes = new int[n];
        for (int i = 0; i < n; i++) {
            notes[i] = sc.nextInt();
        }

        if(n == 1) {
            System.out.println("3");
            return;
        }

        int[] fingers = new int[n];
        if(notes[0] == notes[1]) fingers[0] = 3;
        if(notes[0] > notes[1]) fingers[0] = 5;
        if(notes[0] < notes[1]) fingers[0] = 1;

        for (int i = 0; i < n-1; i++) {

            if(notes[i] > notes[i+1] && fingers[i] == 1) {
                System.out.println(-1);
                return;
            }

            if(notes[i] < notes[i+1] && fingers[i] == 5) {
                System.out.println(-1);
                return;
            }

            int finger = 0;
            if(notes[i] > notes[i+1]) {
                finger = fingers[i] - 1;
                if(i < n-2) {
                    if(notes[i+1] < notes[i+2])
                        finger = 1;
                }
            }

            if(notes[i] < notes[i+1]) {
                finger = fingers[i] + 1;
                if(i < n-2) {
                    if(notes[i+1] > notes[i+2])
                        finger = 5;
                }
            }

            if(notes[i] == notes[i+1]) {
                finger = fingers[i] == 3 ? 2 : 3;
                if(i < n-2) {
                    if(notes[i+1] < notes[i+2])
                        finger = fingers[i] == 1 ? 2 : 1;
                    if(notes[i+1] > notes[i+2])
                        finger = fingers[i] == 5 ? 4 : 5;
                }
            }

            fingers[i+1] = finger;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(fingers[0]);
        for (int i = 1; i < n; i++) {
            sb.append(" ");
            sb.append(fingers[i]);
        }

        System.out.println(sb.toString());
    }
}