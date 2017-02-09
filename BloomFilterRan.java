
import java.util.BitSet;


public class BloomFilterRan {
	 BitSet b;
	 int count=0;
	 int SETSIZE;
	 int p;
	 int numberOfHashFunctions;
	 int a1[];
	 int b1[];
	    public BloomFilterRan(int setSize, int bitsPerElement){
	        SETSIZE=setSize;
	        b= new BitSet(setSize * bitsPerElement);
			numberOfHashFunctions= (int)(Math.log(2)*filterSize()/SETSIZE);
	        a1= new int[numberOfHashFunctions];
			b1= new int[numberOfHashFunctions];
			for(int i = filterSize()+1;; ++i) {
	         boolean isPrime = true;
	        for(int check = 2; check < i; ++check) {
	            if(i % check == 0) 
	                isPrime = false;
	        }
	        if(isPrime) {
	            p=i;
	            break;
	            }
	        }
	        for(int i=0; i<numberOfHashFunctions;i++)
	        {
	            a1[i]=(int)Math.random()*(p-1);
	            b1[i]=(int)Math.random()*(p-1);
	        }
	    }
	    public long[] hashvalue(String S1){
	        long hash[]=new long[numberOfHashFunctions];
	        for(int i=0;i<numberOfHashFunctions;i++){
	            hash[i]=(a1[i]*S1.hashCode()+b1[i])%p;
	        }
	        return hash;
	    }
	            
	    public void add(String S) {
	    	S=S.toLowerCase();
	        for(int i=0;i<numberOfHashFunctions;i++){
	            b.set((int)hashvalue(S)[i]);
	            count++;
	        }
	    }
	    public boolean appear(String S) {
	    	S=S.toLowerCase();
	        for(int i=0;i<numberOfHashFunctions;i++){
	            if(b.get((int)hashvalue(S)[i])==false)
	                return false;
	        }
	        return true;
	    }
	    public int filterSize(){
	        return b.size();
	    }
	    public int dataSize(){
	        return count;
	    }
}
