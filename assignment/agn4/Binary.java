import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays ;
import java.util.Scanner;

public class Binary{
    private char[] bin1; 
    private char[] bin2;
    public Binary(String s1, String s2){
    	
    	this.bin1 = s1.toCharArray();
    	this.bin2 = s2.toCharArray();
    	reverse();
    	if ( bin1.length != bin2.length ){
    		if (bin1.length > bin2.length ){
    			bin2 = extend(bin2, bin1.length);
    		}
    		else{
    			bin1 = extend(bin1, bin2.length );
    		}
    	}
    }
    public Binary(char[] s1, char[] s2){
    	this.bin1 = s1; 
    	this.bin2 = s2;
    	reverse();
    	if ( bin1.length != bin2.length ){
    		if (bin1.length > bin2.length ){
    			bin2 = extend(bin2, bin1.length-bin2.length);
    		}
    		else{
    			bin1 = extend(bin1, bin2.length-bin1.length );
    		}
    	}
    }
    
    public char[] multiply(char[] a, char[] b){
    	char[] prod = new char[a.length+b.length];
    	char[] sum = new char[a.length+b.length];
    	Arrays.fill(prod,'0');
    	Arrays.fill(sum,'0');
    	for ( int i = 0 ; i < b.length; i++ ){
    		if ( b[i] == '1' ){
    			sum = add(shiftL(a,i),extend(sum,sum.length+i));
    		}
    	}
    	return sum ;
    }
    public char[] multiply(){
    	char[] prod = new char[bin1.length+bin2.length];
    	char[] sum = new char[bin1.length+bin2.length];
    	Arrays.fill(prod,'0');
    	Arrays.fill(sum,'0');
    	for ( int i = 0 ; i < bin2.length; i++ ){
    		char[] nAdd = shiftL(bin1,i);
    		//print(nAdd,bin2[i]+" n2Add: ");
    		
    		if ( bin2[i] == '1' ){
				sum = add(nAdd,sum);
    		}
    	}
    	
    	return sum;
    }
	
    public char[] extend(char[] ch, int n){
    	if ( n <= 0 ){
    		return ch ; 
    	}
    	else{
    		char[] nn = new char[ch.length+n];
    		Arrays.fill(nn,'0');
    		System.arraycopy(ch, 0, nn, 0, ch.length);

    		return nn ; 
    	}
    }
	public void reverse(){
		char[] ch = new char[bin1.length] ; 
		for ( int i = 0 ; i < bin1.length/2 ; i++ ){
			ch[i] = bin1[bin1.length-i-1];
			ch[bin1.length-i-1] = bin1[i] ; 
		}
		this.bin1 = ch ;
		char[] dh = new char[bin2.length] ;
		for ( int i = 0 ; i < bin2.length/2 ; i++ ){
			dh[i] = bin2[bin2.length-i-1];
			dh[bin2.length-i-1] = bin2[i] ; 
		}
		this.bin2 = dh ; 
	}
    public char[] shiftL(char[] ch, int n){
    	if ( n <= 0 ){
    		return ch ; 
    	}
    	else{
    		char[] nn= new char[n+ch.length];
    		Arrays.fill(nn,'0');
    		System.arraycopy(ch, 0, nn, n, ch.length);
    		return nn ; 
    	}
    }
    
    public char[] add(){
    	char[] sol = new char[bin1.length];
    	char carry = '0';
    	for ( int i = 0 ; i < bin1.length; i++ ){
    		sol[i] = xor(xor(bin1[i],bin2[i]),carry);
    		carry = or(and(bin1[i],bin2[i]), and(carry,xor(bin1[i],bin2[i])));
    	}
    	if ( carry == '1' ){
    		char[] ch = new char[bin1.length+1];
    		Arrays.fill(ch, '0');
    		System.arraycopy(sol, 0, ch, 0, sol.length);
    		ch[ch.length-1] = carry ;
    		sol = ch ; 
    	}
    	
    	return sol ; 
    }
    
    public char[] add(char[] a, char[] b){
    	if ( a.length > b.length ){
    		b = extend(b,a.length-b.length);
    	}
    	else{
    		a = extend(a,b.length-a.length);
    	}
    	char[] sol = new char[a.length];
    	char carry = '0';
    	for ( int i = 0 ; i < a.length; i++ ){
    		sol[i] = xor(xor(a[i],b[i]),carry);
    		carry = or(and(a[i],b[i]), and(carry,xor(a[i],b[i])));
    	}
    	if ( carry == '1' ){
    		char[] ch = new char[a.length+1];
    		Arrays.fill(ch, '0');
    		System.arraycopy(sol, 0, ch, 0, sol.length);
    		ch[ch.length-1] = carry ;
    		sol = ch ; 
    	}
    	
    	return sol ; 
    }
    
    
    public char and(char a, char b){
    	if ( a == '1' && b == '1') return '1';
    	else return '0';
    }
    public char or(char a, char b){
    	if ( a == '1' || b =='1') return '1';
    	else return '0';
    }
    public char xor(char a, char b){
    	if ( a != b ) return '1';
    	else return '0';
    }
    public void print(char[] str, String msg){
    	System.out.print("\n"+msg);
    	for ( int i = str.length-1 ; i > -1 ; i-- ){
    		System.out.printf("%c",str[i]);
    	}
    }
    public void print(int in){
    	if ( in == 0 ){
	    	System.out.print("\nBinary 1: ");
	    	for ( int i = bin1.length-1 ; i > -1 ; i-- ){
	    		System.out.printf("%c",bin1[i]);
	    	}
	    	System.out.println();
    	}
    	else if ( in==1) {
    		System.out.print("\nBinary 2: ");
	    	for ( int i = bin2.length-1 ; i > -1 ; i-- ){
	    		System.out.printf("%c",bin2[i]);
	    	}
	    	
    	}
    	else{
    		System.out.print("Binary1: ");
	    	for ( int i = 0 ; i < bin2[i] ; i++ ){
	    		System.out.printf("%c",bin2[i]);
	    	}
	    	for ( int i = 0 ; i < bin2[i] ; i++ ){
	    		System.out.printf("%c",bin2[i]);
	    	}
    	}
    }
    public static char[] reverse(char[] ch){
    	for ( int i = ch.length-1 ; i > -1 ; i-- ){
    		char tmp = ch[ch.length-i-1];
    		ch[ch.length-i-1] = ch[i];
    		ch[i] = tmp ; 
    	}
    	return ch ; 
    }
    public char[] getBin(int i){
    	if ( i == 0 ){
    		return bin1; 
    	}
    	else return bin2 ; 
    }
    
    
    
    public static void readIn(String filename){
		int i;
		int numMult;
 
		
		File textFile = new File(filename);
		
		try{
			Scanner in = new Scanner(textFile);
			numMult = Integer.parseInt(in.nextLine().split("\\s+")[0]);
			int curProb = 0 ; 
			while (in.hasNextLine() & curProb < numMult){	
				
				for (i = 0; i < numMult; i++){
					String str1 = in.nextLine().split("\\s+")[1];
					String str2 = in.nextLine().split("\\s+")[1];

					Binary b = new Binary(reverse(str1.toCharArray()),(str2.toCharArray()));
					//b.print(0);
			    	//b.print(1);
			    	//b.print(b.add(),"Sol: ");
					b.print(b.stripLead(b.multiply()),"");
			    	b.print(b.stripLead(b.karatsuba(b.getBin(0), b.getBin(1))),"");
			    	
			    	curProb++ ;
				}
			}
		}catch(FileNotFoundException ex){
			System.out.println("File Not found");
		}
		
	}
    public char[] stripLead(char[] a){
    	int i = a.length-1 ; 
    	while ( a[i] != '1' ){
    		i--;
    	}
    	i++ ; 
    	char[] rtn = new char[i];
    	System.arraycopy(a, 0, rtn, 0, i);
    	return rtn ; 
    }
    public char[] invert(char[] a){
    	char[] rtn = new char[a.length];
    	for ( int i = 0 ; i < a.length ; i++ ){
    		if (a[i] == '0'){
    			rtn[i] = '1';
    		}
    		else rtn[i] = '0';
    	}
    	return rtn ; 
    }
    public char[] twosComp (char[] a){
    	char[] mask = new char[a.length];
    	Arrays.fill(mask,'0');
    	mask[0] = '1';
    	mask = add (a,mask);
    	
    	System.arraycopy(mask, 0, mask, 0, a.length);
    	return mask;
    }
    public char[] sub(char[] a, char[] b ){
    	return add(a,twosComp(b));
    }
    public char[] karatsuba(char[] a, char[] b){
    	int max = max(a,b);
    	if ( a.length <= max || b.length <= max ){
    		return multiply(a,b);
    	}
    	int m = max(a,b);
    	m = m/2; 
    	
    	char[] high1 = splitAt(a, m);
    	char[] low1 = splitAt(a,m,a.length);
    	char[] high2 = splitAt(b, m);
    	char[] low2 = splitAt(b,m,b.length);
    	char[] z0 = karatsuba(low1,low2);
		char[] z1 = karatsuba(add(high1,high2), add(low1,low2));
    	char[] z2 = karatsuba(high1, high2);
    	
    	return add( shiftL(z2,2*m), add(sub(z1,add(z1,z2)),z0));
    }
    public char[] splitAt(char[] a, int n){
    	char[] s = new char[n];
    	System.arraycopy(a, 0, s, 0, n);
    	return s ; 
    }
    public char[] splitAt(char[] a, int n, int k){
    	char[] s = new char[a.length-n];
    	System.arraycopy(a, n, s, 0, a.length-n);
    	return s; 
    }
    
    public int max( char[] a, char[] b ){
    	if ( a.length > b.length ){
    		return a.length ;
    	}
    	else{
    		return b.length; 
    	}
    }
    
    public static void main(String[] args){
    	
    	readIn("infile.txt");
    	
    }
}
