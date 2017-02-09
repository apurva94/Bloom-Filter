import java.lang.Math;
import java.math.BigInteger;
import java.util.BitSet;
public class BloomFilterDet {
	
	BitSet b;
	int count=0;
	int SETSIZE;
	int numberOfHashFunctions;
//	double[] ModValues;
	long[] randomvalues;

	public BloomFilterDet(int setSize, int bitsPerElement) {
	    SETSIZE = setSize;
	    b = new BitSet(setSize * bitsPerElement);
	    numberOfHashFunctions =  (int)(Math.log(2) *filterSize() / SETSIZE);
	    randomvalues = new long[numberOfHashFunctions];
	//	ModValues = new double[numberOfHashFunctions];
	  //  for (int i = 0; i < numberOfHashFunctions; i++) {
	 //     ModValues[i] = (filterSize()/2) + (Math.random() *filterSize()/2);
	    for (int i = 0; i < numberOfHashFunctions; i++) {
	   	    randomvalues[i] = 1+(long)Math.random()*(10*numberOfHashFunctions) ; 
	}

	  }

	  public BigInteger[] hashvalue(String S1) {
	    BigInteger hash[] = new BigInteger[numberOfHashFunctions];
	    BigInteger FNV=new BigInteger("14695981039346656037");
	  //  System.out.println(FNV);
	    BigInteger FNV_PRIME=new BigInteger("109951168211");
	//    long FNV_PRIME=Long.parseUnsignedLong("109951168211");
	    for (int i = 0; i < numberOfHashFunctions; i++) {
	      hash[i] =(FNV.multiply(BigInteger.valueOf(randomvalues[i])));
	      for(int j=0;j<S1.length();j++)
	      hash[i] =(hash[i].xor(BigInteger.valueOf((int)S1.charAt(j))));
	      hash[i] =( hash[i].multiply(FNV_PRIME).mod(BigInteger.valueOf((long)Math.pow(2, 64))));
	    }
	    
	    return hash;
	  }

	  public void add(String S2) {
		  
		  S2=S2.toLowerCase();
	    for (int i = 0; i < numberOfHashFunctions; i++) {
	      b.set((int)(Math.abs(hashvalue(S2)[i].longValueExact())%filterSize()));
	      count++;
	    }
	   
	  }

	  public boolean appear(String S) {
		  S=S.toLowerCase();
	    for (int i = 0; i < numberOfHashFunctions; i++) {
	      if (b.get((int)(Math.abs(hashvalue(S)[i].longValueExact())%filterSize())) == false) {
	        return false;
	      }
	    }
	    return true;
	  }

	  public int filterSize() {
	    return b.size();
	  }

	  public int dataSize() {
	    return count;
	  }
	}