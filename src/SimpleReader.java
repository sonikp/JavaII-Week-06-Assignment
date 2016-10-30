import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Program 102: Sample class to read from a file
 * 
 * Class that allows you to easily read and print out the contents of a file
 * @author sonikp
 *
 */

public class SimpleReader
{
	/**
	 * Method to read a file and print out the contents
	 * @param fileName the name of the file to read from
	 */
	public void readAndPrintFile(String fileName)
	{
		String line = null;
		
		// try to do the following
		try
		{
			// create the buffered reader
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			
			// Loop while there is more data
			while((line = reader.readLine()) != null)
			{
				// print the current line
				System.out.println(line);
			}
			
			// close the reader
			reader.close();
		}
		catch(FileNotFoundException ex)
		{
//			System.out.println("this is the function to pick the file");
//			SimpleOutput.showError("Couldn't find " + fileName + " please pick it.");
//			System.out.println("test before pickAFile");
			//fileName = FileChooser.pickAFile();
			fileName = FileChooser.getMediaPath("test.txt");
			System.out.println("test after pickAFile");
			readAndPrintFile(fileName);
		}
		catch(Exception ex)
		{
			SimpleOutput.showError("Error reading file " + fileName);
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		//String fileName = FileChooser.pickAFile();
		
		
		SimpleReader reader = new SimpleReader();
		reader.readAndPrintFile(FileChooser.getMediaPath("her.txt"));	//		 //400x300.jpg
		
	}
}