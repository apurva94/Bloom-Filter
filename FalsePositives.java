import java.util.*;
import java.math.BigDecimal;
public class FalsePositives {
	static BloomFilterDet A= new BloomFilterDet(200,10);
	  static BloomFilterRan B= new BloomFilterRan(200,10);
	  static ArrayList<Integer> total = new ArrayList<Integer>();
	  static ArrayList<Integer> test = new ArrayList<Integer>();
	  //static Collection total=new ArrayList();
	  //static ArrayList test=new ArrayList();
	  public static void main(String args[])
	  {
	    for(int i=0; i<200; i++ )
	      total.add((int)(Math.random()*100));
	    for(int i=0; i<100; i++ )
	      test.add((int)(Math.random()*100));
	    System.out.println("Elements in total before removal");
	    for(int i=0; i<total.size();i++)	
	    	System.out.print(total.get(i)+" ");
	    for(int j=0; j<test.size();j++)
	    for(int i=0; i<total.size(); i++ )
	    	if(total.get(i)==test.get(j)){
	    		
	    total.remove(i);
	    i--;
	    	}
	    System.out.println("\nElements in total after removal");
	    for(int i=0; i<total.size();i++)	
	    	System.out.print(total.get(i)+" ");
	    System.out.println("\nElements in Test");
	    for(int i=0; i<test.size();i++)
	    	System.out.print(test.get(i)+" ");
	    System.out.println();
    CheckDet();
		CheckRan();
	  }
	  public static void CheckDet()
	  {
	    int FalsePositive=0;
		double result;
	    for(int i=0;i<total.size();i++){
	      A.add(total.toArray()[i].toString());
	     // System.out.println(A.hashvalue(total.toArray()[i].toString())[0]);
	    }
	    for(int i=0; i<test.size();i++){
	      if(A.appear(test.toArray()[i].toString())){
	        FalsePositive++;
	      }
	    }
		result=(double)FalsePositive/(FalsePositive+A.dataSize());
		Double truncatedDouble = new BigDecimal(result)
	    .setScale(3, BigDecimal.ROUND_HALF_UP)
	    .doubleValue();
	    System.out.println("\nFalsePositive rate for Deterministic function:"+truncatedDouble);
	  }
	  public static void CheckRan()
	  {
	    int FalsePositive=0;
		double result;
	    for(int i=0;i<total.size();i++){
	      B.add(total.toArray()[i].toString());
	    }
	    for(int i=0; i<test.size();i++){
	      if(B.appear(test.toArray()[i].toString())){
	        FalsePositive++;
	      }
	    }
		result=(double)FalsePositive/(FalsePositive+B.dataSize());
		Double truncatedDouble = new BigDecimal(result)
	    .setScale(3, BigDecimal.ROUND_HALF_UP)
	    .doubleValue();
	    System.out.println("FalsePositive rate for Random function:"+truncatedDouble);
	  }
}
