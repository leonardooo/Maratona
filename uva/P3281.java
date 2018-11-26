import java.util.Hashtable;
import java.util.StringTokenizer;


class Main {

        static int a = (int)'A';

        public static void main(String[] args) {

                Scanner sc = new Scanner();
                int count = 0;

                while(true) {
                        int team = sc.getInt();
                        int prob = sc.getInt();

                        if(team == 0 && prob == 0) break;

                        Team[] all_teams = new Team[team];
                        for(int i = 0; i < team; i++) {
                                all_teams[i] = new Team(i+1);
                        }

                        Hashtable problem_points = new Hashtable();
                        for(int i = a; i < a+prob; i++) {
                                int value = sc.getInt();
                                problem_points.put( new Character((char)i) , new Integer(value));
                        }

                        int subm = sc.getInt();
                        for(int i = 0; i < subm; i++) {
                                int team_number = sc.getInt();
                                char problem = sc.getString().charAt(0);
                                char res = sc.getString().charAt(0);

                                if(res == 'R') {
                                        all_teams[team_number-1].subs++;
                                } else {
                                        all_teams[team_number-1].subs++;
                                        all_teams[team_number-1].accepted_subs++;
                                        int point = ((Integer) problem_points.get(new Character(problem))).intValue();
                                        all_teams[team_number-1].points += point;
                                }
                        }

                        for(int i = 0; i < all_teams.length - 1; i++) {
                                for(int k = i+1; k < all_teams.length; k++) {

                                        Team team1 = all_teams[i];
                                        Team team2 = all_teams[k];

                                        if(team1.points < team2.points) {
                                                all_teams[i] = team2;
                                                all_teams[k] = team1;
                                        } else if(team1.points == team2.points && team1.accepted_subs > team2.accepted_subs) {
                                                all_teams[i] = team2;
                                                all_teams[k] = team1;
                                        } else if(team1.points == team2.points && team1.accepted_subs == team2.accepted_subs && team1.subs > team2.subs) {
                                                all_teams[i] = team2;
                                                all_teams[k] = team1;
                                        } else if(team1.points == team2.points && team1.accepted_subs == team2.accepted_subs && team1.subs == team2.subs && team1.name > team2.name) {
                                                all_teams[i] = team2;
                                                all_teams[k] = team1;
                                        }

                                }
                        }

                        System.out.println(++count);
                        for(int i = 0; i < all_teams.length; i++) {
                                System.out.println(all_teams[i].name + " " + all_teams[i].points);
                        }

                }

        }

}

class Team {
        int name;
        int points = 0;
        int accepted_subs = 0;
        int subs = 0;
        Team(int number) {
                name = number;
        }
}

class Scanner {

           StringTokenizer st = null;
           String next = null;

           Scanner() {
               next = Reader.readLn();
           }

           int getInt() {
               read();
               return Integer.parseInt(st.nextToken());
           }

           long getLong() {
               read();
               return Long.parseLong(st.nextToken());
           }

           double getDouble() {
               read();
               return new Double(st.nextToken()).doubleValue();
           }

           String getString() {
               read();
               return st.nextToken();
           }

           boolean isEOF() {
               return (st == null && next == null) || (st != null && !st.hasMoreTokens() && next == null);
           }

           private void read() {
               if(st == null) {
                   st = new StringTokenizer(next);
                   next = Reader.readLn();
               }
               while(! st.hasMoreTokens()) {
                   st = new StringTokenizer(next);
                   next = Reader.readLn();
               }
           }

        }

        class Reader {
           static String readLn() {
               String newLine = System.getProperty("line.separator");
               StringBuffer buffer = new StringBuffer();
               int car = -1;
               try {
                   car = System.in.read();
                   while ((car > 0) && (car != newLine.charAt(0))) {
                       buffer.append((char)car);
                       car = System.in.read();
                   }
                   if (car == newLine.charAt(0))
                   System.in.skip(newLine.length() - 1);
               } catch (java.io.IOException e) { return (null); }
               if ((car < 0) && (buffer.length() == 0)) return (null);
               return (buffer.toString()).trim();
           }
        }