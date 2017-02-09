import java.io.File;
import java.util.StringTokenizer;
import java.io.*;
import java.util.Scanner;


public class NaiveDifferential {
  public static void main(String args[])
  {
	  try{
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


public static String retrieveRecord(String Key1)
	{
boolean found1=false;
try
{
	 FileInputStream d = new FileInputStream("C:/Users/Apurva/workspace/AlgoForMassiveDataSet/src/DiffFile.txt");
	 Scanner src = new Scanner(d);
	 
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
				found1=true;
				System.out.println("Key found in DiffFile");
				return Line;
			}
		}
	}
	if(found1==false)
	{
		System.out.println("Key not found in DiffFile.... Searching in Database");
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
				return Line;
			}
		}
	}
	}
	
}
    catch (FileNotFoundException e)
     { 
		System.out.println("File not found.");
		System.exit(0);	
		}
		
String s="Record doesnt exist";
			return s;
		
	}
}