import java.util.*;
import java.io.*;
class Main {
	
	private static final int a = (int)'a';
	private static final int z = (int)'z';
	private static final int A = (int)'A';
	private static final int Z = (int)'Z';
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader( System.in ));
		
		int n = Integer.parseInt(br.readLine().trim());
		
		for(int i = 0; i < n; i++) {
			
			String linha = br.readLine();
			linha = passada1(linha);		//System.out.println(linha);
			linha = passada2(linha);		//System.out.println(linha);
			linha = passada3(linha);
			
			System.out.println(linha);
			
		}
		
	}
	
	private static String passada1(String linha) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < linha.length(); i++) {
			int c = (int)linha.charAt(i);
			if( (c >= a && c <= z) || (c >= A && c <= Z) ) {
				sb.append( ((char)(c+3)) );
			} else {
				sb.append((char)c);
			}	
		}
		return sb.toString();
	}
	
	private static String passada2(String linha) {
		StringBuilder sb = new StringBuilder();
		for(int i = linha.length()-1; i >= 0; i--) {
			sb.append(linha.charAt(i));
		}
		return sb.toString();
	}
	
	private static String passada3(String linha) {
		StringBuilder sb = new StringBuilder();
		sb.append(linha.substring(0, linha.length()/2));
		for(int i = linha.length()/2; i < linha.length(); i++) {
			int c = (int)linha.charAt(i);
			c -= 1;
			sb.append( (char)c );
		}
		
		return sb.toString();
	}
	
}
