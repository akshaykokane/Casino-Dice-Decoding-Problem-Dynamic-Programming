import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CasinoDiceDecodingProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the number of turns");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int s[] = new int[n];
		System.out.println("Enter the elements : ");
		for(int i = 0; i < n; i++)
			s[i] = sc.nextInt();
		CasinoDiceDecodingProblem obj = new CasinoDiceDecodingProblem();
		obj.casinoDiceDecoding(s);
		//6662451
		//LLLFFFF
	}
	
	public void casinoDiceDecoding(int[] s) {
		
		int F = 0; //fair
		int L = 1; // loaded
		System.out.println(s.length);
		double[][] p = new double[s.length][2];
		double q = s[0] == 6 ? 0.5 : 0.1;
		
		p[0][F] =  0.5 * (1/6);
		p[0][L] = 0.5 * q;
		int[][] l = new int[s.length][2];
		int i;
		for(i = 1; i < s.length; i++) {
		
			p[i][F] = Math.max((p[i - 1][F] * 0.95) / 6, (p[i - 1][L] * 0.1) /6);
			
			l[i][F] = (p[i - 1][F] * 0.95) / 6 > (p[i - 1][L] * 0.1) /6 ? F : L;
			
			q = s[i] == 6 ? 0.5 : 0.1;
			
			p[i][L] = Math.max(p[i - 1][L] * 0.9 * q, p[i - 1][F] * 0.05 * q);
			
			l[i][L] = p[i - 1][L] * 0.9 * q > p[i - 1][F] * 0.05 * q ? L : F;
		}
		
		printTable(p, l);
		String ans = p[i - 1][F] > p[i - 1][L] ? "F" : "L";
		System.out.println("\nFinal "+ ans);
		
		
	}

	private void printTable(double[][] p, int [][] l) {
		// TODO Auto-generated method stub
		
		System.out.println();
		for(int i = 0; i < 7; i++)
		{
			System.out.print("  "+round(p[i][0], 10));
		}
		System.out.println();
		for(int i = 0; i < 7; i++)
		{
			System.out.print("  "+round(p[i][1], 10));
		}
		System.out.println();
		for(int i = 0; i < 7; i++)
		{
			String ans = l[i][0] == 0? "F" : "L";
			System.out.print("  "+ans);
		}
		System.out.println();
		for(int i = 0; i < 7; i++)
		{
			if(i == 0) {
				System.out.print(" L");
				continue;
			}
			String ans = l[i][1] == 0? "F" : "L";
			System.out.print("  "+ans);
		}
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
