public class Main{
	public static void main(String[] args) {
		String a = "hola";
		String b = "chaucha";
		int[][] matrix = new int[a.length()][b.length()];
		Alineamiento s = new Alineamiento(a,b,matrix);
		s.arrayMatrix();
		s.showMatrix();
		s.aline();
		System.out.println(s.getA());
		System.out.println(s.getB());
	}
}