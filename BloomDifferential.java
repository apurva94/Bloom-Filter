
import java.io.FileInputStream;
import java.util.StringTokenizer;
import java.io.*;
import java.util.Scanner;


public class BloomDifferential {
  static BloomFilterDet A = new BloomFilterDet(12000000,64);
  public static void main(String args[])
  {
	  try{
	  		System.out.println("Creating Filter....please wait");
			createFilter();
			System.out.println("Enter key to search");
			InputStreamReader input = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(input);
			String query=reader.readLine();
			System.out.println(retrieveRecord(query));
	  }
	  catch (Exception e)
     { 
		System.out.println(e);
		System.exit(0);	
		}
  }

public static void createFilter()
	{

//	File file = new File("C:/Users/Apurva/workspace/AlgoForMassiveDataSet/src/DiffFile.txt");   
	  try{
	 FileInputStream d = new FileInputStream("C:/Users/Apurva/workspace/AlgoForMassiveDataSet/src/DiffFile.txt");
	 Scanner src = new Scanner(d);
	 
	for(int lineNumber = 1; src.hasNextLine()&&lineNumber<=20000; ++lineNumber) {
    String Line = src.nextLine();
	StringTokenizer st = new StringTokenizer(Line);
	int countOfToken=0;
	String key=" ";
	while (st.hasMoreTokens())
		{
			String token = st.nextToken();
				countOfToken++;
				if(countOfToken<5)
					key+=token+" ";
				if(countOfToken==5)
					break;
		}
	System.out.println(lineNumber);
	if(lineNumber==1){
		System.out.println("Key on line 1 was:"+key);
	}
	A.add(key);
	}
	System.out.println("The bloom filter contains hash values:");
	System.out.println(A.b.toString());
	}
    catch (FileNotFoundException e)
     { 
		System.out.println("File not found.");
		System.exit(0);	
		}
	}


public static String retrieveRecord(String Key1)
	{
	 FileInputStream d;
	 Scanner src;
	 System.out.println("1 hash value of the key is:");
	 System.out.println((long)(Math.abs(A.hashvalue(Key1)[0].longValueExact())));

	 try{	
		 
		 if(A.appear(Key1))
		 {
			 System.out.println("Key appears in bloom Filter");
			 boolean found1=false;
			 boolean found2=false;		
			 d = new FileInputStream("C:/Users/Apurva/workspace/AlgoForMassiveDataSet/src/DiffFile.txt");
			 src = new Scanner(d);
	 
			 for(int lineNumber = 1; src.hasNextLine()&&lineNumber<=100; ++lineNumber) {
				 String Line = src.nextLine();
				 StringTokenizer st = new StringTokenizer(Line);
				 int countOfToken=0;
				 String key=" ";
				 while (st.hasMoreTokens())
				 {
					 String token = st.nextToken();
					 countOfToken++;
					 if(countOfToken<5)
						 key+=token+" ";
					 if(key.equalsIgnoreCase(Key1))
					 {
						 found1=true;
						 System.out.println("Key found in DiffFile");
						 return Line;
					 }
				 }
			 }
			 if(found1==false)
			 {
				 System.out.println("Key not found in DiffFile....False Positive. Searching in Database");
				 d = new FileInputStream("C:/Users/Apurva/workspace/AlgoForMassiveDataSet/src/database.txt");
				 src = new Scanner(d);
				 L1 : for(int lineNumber = 1; src.hasNextLine(); ++lineNumber) {
					 String Line = src.nextLine();
					 StringTokenizer st = new StringTokenizer(Line);
					 int countOfToken=0;
					 String key=" ";
					 while (st.hasMoreTokens())
					 {
						 String token = st.nextToken();
						 countOfToken++;
						 if(countOfToken<5)
							 key+=token+" ";
						 if(key.equalsIgnoreCase(Key1))
						 {
							 found2=true;
							 return Line;
						 }
					 }
				 }	
			 }	
		 }
		 else
		 {
			 d = new FileInputStream("C:/Users/Apurva/workspace/AlgoForMassiveDataSet/src/database.txt");
			 src = new Scanner(d);
	 
			 for(int lineNumber = 1; src.hasNextLine(); ++lineNumber) {
				 String Line = src.nextLine();
				 StringTokenizer st = new StringTokenizer(Line);
				 int countOfToken=0;
				 String key=" ";
				 while (st.hasMoreTokens())
				 {
					 String token = st.nextToken();
					 countOfToken++;
					 if(countOfToken<5)
						 key+=token+" ";
					 if(key.equalsIgnoreCase(Key1))
					 {
						 return Line;
					 }
				 }
			 }
	
		 }
	 }
	 catch (FileNotFoundException e)   { 
		 System.out.println("File not found.");
		 System.exit(0);	
	 }
	 String s="Record doesnt exist";
	 return s;	
	}
}