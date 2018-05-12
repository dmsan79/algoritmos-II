import java.lang.*;
public class Alineamiento{
  private String a;
  private String b;
  private int[][] matrix;
  
  public Alineamiento(String a, String b, int[][] matrix){
    this.a = a;
    this.b = b;
    this.matrix = new int[a.length()][b.length()];
    for(int i=0;i<matrix.length;i++){
      for(int j=0;j<matrix[i].length;j++){
        this.matrix [i][j] = matrix[i][j];
      }
    }
  }

  public String getA(){
  	return a;
  }

  public String getB(){
  	return b;
  }

  public static int maximium(int a, int b, int c){
    return Math.max(a, Math.max(b,c));
  }

  public static int computeLevenshteinDistance(String str1, String str2) {
    int[][] distance = new int[str1.length()+1][];
    int repi, repj;

      for(int i=0; i<=str1.length(); i++){
        distance[i] = new int[str2.length()+1];
        distance[i][0] = i;
      }
      for(int j=0; j<str2.length()+1; j++)
        distance[0][j]=j;

      for(int i=1; i<=str1.length(); i++)
        for(int j=1;j<=str2.length(); j++) {
          distance[i][j]= minimum(distance[i-1][j]+1,
          distance[i][j-1]+1,
          distance[i-1][j-1]+((str1.charAt(str1.length()-i)==str2.charAt(str2.length()-j))?0:1));
      }
      return distance[str1.length()][str2.length()];
    }

  private static int minimum(int a, int b, int c){
      if (a<=b && a<=c)
        return a;
      if (b<=a && b<=c)
        return b;
      return c;
  }

  public int[][] getMatrix(){
  	return this.matrix; 
  }

  public void arrayMatrix(){
    int choice1,choice2,choice3;
    int d=-2;
    for(int i=0; i<this.a.length(); i++){
     this.matrix[i][0] = d*i;
    }
    for(int j=0;j<this.b.length();j++){
     this.matrix[0][j]=d*j;
    }
    System.out.println();
    System.out.println(computeLevenshteinDistance(a,b));
    System.out.println();
    for(int i=1;i<a.length();i++){
     for(int j=1;j<b.length();j++){
      choice1 = matrix[i-1][j-1] + computeLevenshteinDistance(a, b);
      choice2 = matrix[i-1][j] + d;
      choice3 = matrix[i][j-1] + d;  
      matrix[i][j] = maximium(choice1,choice2,choice3);
     }
    }
  }

  public void aline(){
    String alignmentA= "";
    String alignmentB= "";
    int d = -2;
    int i = a.length()-1; 
    int j = b.length()-1;
    
    while(i>0 && j>0){
      int s = matrix[i][j];
      int sd = matrix[i-1][j-1];
      int su = matrix[i][j-1];
      int sl = matrix[i-1][j];
      if (s == sd + computeLevenshteinDistance(a, b)){
         alignmentA = a.charAt(i-1) + alignmentA;
         alignmentB = b.charAt(j-1) + alignmentB;
         i--;
         j--;
      }else if (s == sl + d){
         alignmentA = a.charAt(i-1) + alignmentA;
         alignmentB = "-" + alignmentB;
         i = i - 1;
       	}else if(s == su + d){
            alignmentA = "-" + alignmentA;
            alignmentB = b.charAt(j-1) + alignmentB;
            j = j - 1;
      	}	
      i--;
      j--;
    }
    while (i > 0){
      alignmentA = a.charAt(i-1) + alignmentA;
      alignmentB = "-" + alignmentB;
      i = i - 1;
    }
    while (j > 0){
      alignmentA = "-" + alignmentA;
      alignmentB = b.charAt(j-1) + alignmentB;
      j= j - 1;
    }
    this.a = alignmentA;
    this.b = alignmentB;
  }

	public void showMatrix(){
		for(int i=0;i<a.length();i++) {
			System.out.print("-- ");
		}
		System.out.println();
		for(int i=0;i<b.length();i++){
			if(matrix[0][i]>=0){
				System.out.print("+"+matrix[0][i]+"|");
			}else{
				System.out.print(matrix[0][i]+"|");
			}
		}
		System.out.println();
		for (int i=1;i<matrix.length-1;i++){
			for (int j=0;j<matrix[i].length;j++){
				if(matrix[i][j]>=0){
				System.out.print("+"+matrix[i][j]+"|");
				}else{
				System.out.print(matrix[i][j]+"|");
				}
			}
			System.out.println();
		}
		for(int j=0; j<b.length()-1;j++){
			if(matrix[matrix.length-1][j]>=0){
				System.out.print("+"+matrix[matrix.length-1][j]+"|");
			}else{
				System.out.print(matrix[matrix.length-1][j]+"|");
			}
		}
		if(matrix[a.length()-1][b.length()-1]>=0){
				System.out.println("+"+matrix[a.length()-1][b.length()-1]+"|");
			}else{
				System.out.println(matrix[a.length()-1][b.length()-1]+"|");
		}
		for(int i=0;i<a.length();i++) {
			System.out.print("-- ");
		}
		System.out.println();
	}
}