import java.util.*;
public class Cup {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        int length = line.length();
        int rows = numberOfRows(length);
        int perRows = length / rows;
        int res = length % rows;
        int ast = numberOfAst(rows,res);

        if(res > 0) perRows++;

        //System.out.println(length % rows);
        System.out.println(rows + " " + perRows);

        int count = 0;
        while(count < length) {
            if(ast-- > 0) {
                System.out.print(line.substring(count, count+perRows-1) + "*");
                count += perRows-1;
            } else {
                System.out.print(line.substring(count, count+perRows));
                count += perRows;
            }
            
            System.out.println();
            
        }

    }

    static int numberOfRows(int length) {
        if(length <= 20) return 1;
        if(length <= 40) return 2;
        if(length <= 60) return 3;
        if(length <= 80) return 4;
        return 5;
    }

    static int numberOfAst(int rows, int res) {
        if(res == 0) return 0;
        return rows-res;
    }
}