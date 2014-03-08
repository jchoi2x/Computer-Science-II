import java.util.Arrays ;

public class Binary{
    private char[] binary1 ;
    private char[] binary2 ;

    public Binary(char[] bin1, char[] bin2 ){
        this.binary1 = reverse(bin1) ;
        this.binary2 = reverse(bin2) ;
        signExt();
        print();


        System.out.println();
        print(reverse(binary1));
        print(reverse(binary2));
        System.out.println("--+-------------");
        print((binAdd()));
    }

    public char[] reverse(char[] bin){
        int len = bin.length-1 ;
        char[] newBin = new char[bin.length ];

        for ( int i = 0 ; i < len/2 ; i++ ){
            char tmp = bin[i];
            bin[i] = bin[len-i];
            bin[len-i] = tmp ;
        }
        return bin ;
    }

    public char[] binAdd(){
        char[] newBin = new char[binary1.length+1];
        Arrays.fill(newBin,'0');

        char carry = '0';
        for ( int i = 0 ; i < binary1.length ; i++ ){
            // Calculate Bit result
            newBin[i] = (char) ((binary1[i]-'0') ^ (binary2[i]-'0') + '0');
            newBin[i] = (char) ((newBin[i]-'0') ^ (carry-'0') + '0');

            // Calculate if there is a carry, Carry = AB + AC + BC
            if ( (binary1[i]-'0' & binary2[i]-'0') == 1 || (binary2[i]-'0' & carry-'0') == 1 || (binary1[i]-'0' & carry-'0') == 1 ){
                carry = '1';
            }
            else{
                carry = '0' ;
            }

            if ( i+1 == binary1.length && carry == '1'){
                newBin[binary1.length] = carry ;
            }
            else{
                char[] newnew = new char[binary1.length];
                System.arraycopy(newBin,0,newnew,0,newBin.length-1);
                System.arraycopy(newnew,0,newBin,0,newnew.length);
            }
        }
        return newBin ;
    }

    private void signExt(){
        if ( this.binary1.length > this.binary2.length ){
            char[] newBin = new char[this.binary1.length];
            Arrays.fill(newBin,'0');

            System.arraycopy(binary2,0,newBin,0,binary2.length);
            this.binary2 = newBin ;
            return ;
        }
        else if ( this.binary1.length < this.binary2.length ){
            char[] newBin = new char[this.binary2.length];
            Arrays.fill(newBin,'0');

            int destPos = binary2.length - binary1.length ;
            System.arraycopy(binary1,0,newBin,0,binary1.length);
            this.binary1 = newBin ;
            return ;
        }
        else return ;
    }

    public void print(){
        String str = "" ;
        for ( int i = 0 ; i < binary1.length ; i++ ) str+=binary1[i];
        System.out.println("Binary1: "+str);
        str = "" ;
        for ( int i = 0 ; i < binary2.length ; i++ ) str+=binary2[i];
        System.out.println("Binary2: "+str);
    }
    public void print(int n){
        for ( char c : this.binary1 ) System.out.printf("Binary1: %d",(c-'0'));
        System.out.println();
        for ( char c : this.binary2 ) System.out.printf("Binary2: %d",(c-'0'));
        System.out.println();
    }
    public void print(char[] ch){
        for ( int i = 0 ; i < ch.length ; i++ ) System.out.printf("%d",(ch[i]-'0'));
        System.out.println();
    }

    public static void main(String[] args){
        char[] b1 = {'0','0','1','0','1'};
        char[] b2 = {'0','0','0','0','0','0','0','0','0','0','1','1','1','1','1','1','1'};
        Binary binbin = new Binary(b2,b1);
    }
}
