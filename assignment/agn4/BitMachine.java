public class BitMachine{

    public String binary1 ;
    public String binary2 ;
    public BitMachine( String bin1, String bin2 ){
        // Set this classes's fields
        this.binary1 = bin1 ;
        this.binary2 = bin2 ;
        // Sign extend to match
        this.matchExt();
        System.out.println("Bin1: "+this.binary1+"\nBin2: "+this.binary2);
    }

    /**
     * Shift Left
     */
    public String shiftLeft( int bin1Orbin2, int num ){
        if ( bin1Orbin2 == 1 ){
            for ( int i = 0 ; i < num ; i++ ){
                this.binary1+="0";
            }
            return binary1;
        }
        else {
            for ( int i = 0 ; i < num ; i++ ){
                this.binary2+="0";
            }
            return binary2;
        }
    }
    public String shiftLeft( String bin, int num ){
        for ( int i = 0 ; i < num ; i++ ){
            bin+="0";
        }
        return bin;
    }


    /**
     * Sign extend to match bit width
     */
    public boolean matchExt(){
        if ( binary1.length() > binary2.length() ){
            String rtn = new String();
            for ( int i = 0; i < binary1.length()-binary2.length() ; i++ ){
                rtn+="0";
            }
            rtn+=binary2;
            this.binary2 = rtn ;
            return true ;
        }
        else if ( binary1.length() < binary2.length() ){
            String rtn = new String();
            for ( int i = 0; i < binary2.length()-binary1.length() ; i++ ){
                rtn+="0";
            }
            rtn+=binary1;
            this.binary1 = rtn ;
            return true ;
        }
        else {
            return true ;
        }
    }

    public void sum(int a, int b){
        int result = a ^ b ; // Exclusive OR for result of bit
        int carry = a & b ;  // AND for carry

        if ( carry != 0 ){
            return sum(result,carry);
        }
        return result ;
    }

    public static void main( String[] args ){
        BitMachine machina = new BitMachine("0010", "00");
        System.out.println(machina.shiftLeft(1,2));
    }
}
