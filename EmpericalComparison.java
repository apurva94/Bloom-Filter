import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class EmpericalComparison {

	public static void main(String args[])
	{
		BloomDifferential.createFilter();
		try{
			 FileInputStream d = new FileInputStream("C:/Users/Apurva/workspace/AlgoForMassiveDataSet/src/grams.txt");
			 Scanner src = new Scanner(d);
				long startTime = System.currentTimeMillis();			 
			for(int lineNumber = 1; src.hasNextLine(); ++lineNumber) {
		    String Line = src.nextLine();
			StringTokenizer st = new StringTokenizer(Line);
			int countOfToken=0;
			String key=" ";
			while (st.hasMoreTokens())
				{
					String token = st.nextToken();
					key+=token+" ";			
				}

			System.out.println(BloomDifferential.retrieveRecord(key));

			}
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			BloomDifferential.createFilter();
			endTime   = System.currentTimeMillis();
			long SubtractTime = endTime - startTime;
			
			System.out.println("Time taken after using Bloom Filter"+(totalTime-SubtractTime));
			
			
			src = new Scanner(d);
			startTime = System.currentTimeMillis(); 
			for(int lineNumber = 1; src.hasNextLine(); ++lineNumber) {
		    String Line = src.nextLine();
			StringTokenizer st = new StringTokenizer(Line);
			int countOfToken=0;
			String key=" ";
			while (st.hasMoreTokens())
				{
					String token = st.nextToken();
					key+=token+" ";			
				}
		
			System.out.println(NaiveDifferential.retrieveRecord(key));
			endTime   = System.currentTimeMillis();
			totalTime = endTime - startTime;
			System.out.println("Time taken without using Bloom Filter"+totalTime);			
			}

			}
		    catch (FileNotFoundException e)
		     { 
				System.out.println("File not found.");
				System.exit(0);	
				}
			}
		

	}

