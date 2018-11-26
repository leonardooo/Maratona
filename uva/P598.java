import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

class Main {

        static Vector nodes;
        static Stack stack;
        static Vector names;

    public static void main(String[] args) {
 try {         
        String line = readLn(); readLn();
        int n = Integer.parseInt(line);
        for(int k = 0; k < n; k++) {

                StringTokenizer st = new StringTokenizer(readLn());
                int[] sizes = null;
                if(st.countTokens() == 1) {
                        sizes = new int[1];
                        line = st.nextToken();
                        if(line.equals("*")) sizes[0] = -1;
                        else sizes[0] = Integer.parseInt(line);
                } else {
                        int n1 = Integer.parseInt(st.nextToken());
                        int n2 = Integer.parseInt(st.nextToken());
                        sizes = new int[n2-n1+1];
                        int count = 0;
                        for(int i = n1; i <= n2; i++) {
                                sizes[count++] = i;
                        }
                }

                nodes = new Vector();
                names = new Vector();

                int count = 0;
                line = readLn();
                while(line != null && !line.equals("")) {
                        nodes.addElement( new Node(count++) );
                        names.addElement(line);
                        line = readLn();
                }

                for(int i = 0; i < nodes.size()-1; i++) {
                        for(int j = i+1; j < nodes.size(); j++) {
                                ((Node)nodes.elementAt(i)).add_adj(j);
                        }
                }

                if(sizes[0] == -1) {
                        sizes = new int[nodes.size()];
                        for(int i = 1; i <= sizes.length; i++) {
                                sizes[i-1] = i;
                        }
                }

                if(k > 0) System.out.println();
                for(int i = 0; i < sizes.length; i++) {
                        if(i > 0) System.out.println();
                        System.out.println("Size " + sizes[i]);
                        for(int j = 0; j < nodes.size(); j++) {
                                stack = new Stack(nodes.size());
                                stack.push(j);
                                dfs(sizes[i]);
                        }
                }
        }
 } catch(Exception e) { while(true) {} }
    }

    static void dfs(int length) {

        if(length == stack.size()) {
                print();
                stack.pop();
                return;
        }

        Node current = (Node)nodes.elementAt( stack.top() );

        for(int i = 0; i < current.nadj; i++) {
                stack.push( current.get(i) );
                dfs(length);
        }

        stack.pop();

    }

    static void print() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < stack.index; i++) {
                if(i > 0) sb.append(", ");
                sb.append(names.elementAt(stack.array[i]));
        }
        System.out.println(sb.toString());
    }

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
        } catch (java.io.IOException e) { return (null);}
        if ((car < 0) && (buffer.length() == 0)) return (null);
        return (buffer.toString()).trim();
    }

}

class Node {
        int index;
        int[] adj = new int[30];
        int nadj = 0;

        Node(int n) { this.index = n; }

        void add_adj(int n) {
                adj[nadj++] = n;
        }

        boolean has_adj(int n) {
                for(int i = 0; i < nadj; i++)
                        if(n == adj[i]) return true;
                return false;
        }

        int get(int n) {
                return adj[n];
        }

        void sort() {
                for(int i = 0; i < nadj-1; i++) {
                        for(int j = i+1; j < nadj; j++) {
                                if(adj[i] > adj[j]) {
                                        int temp = adj[i];
                                        adj[i] = adj[j];
                                        adj[j] = temp;
                                }
                        }
                }
        }
}

class Stack {
    int index;
    int[] array;

    Stack(int maxElements) {
            index = 0;
            array = new int[maxElements];
    }

    void push(int s) {
            array[index++] = s;
    }

    int pop() {
            return array[--index];
    }

    int top() {
            return array[index-1];
    }

    int size() {
            return index;
    }

    boolean isEmpty() {
            return this.size() == 0;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < index; i++) {
                if(i > 0) sb.append(", ");
                sb.append( array[i] );
        }
        return sb.toString();
    }
}