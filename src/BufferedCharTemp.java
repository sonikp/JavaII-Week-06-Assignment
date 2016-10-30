import java.io.*;

public class BufferedCharTemp
{
	
	private String fileName;
	/////////////////constructors////////////////
	public BufferedCharTemp()
	{
		
	}
	
	public BufferedCharTemp(String fileName)
	{
		this.fileName = fileName;
	}
	
	public char[] stringToCharArray(String fileName)
	{
		// creates a file buffer
		File f = new File(fileName);
		long bufferSize = f.length();
		System.out.println(bufferSize);
		
		// creates a character array
		char[] charArray = null;
		
		
		try
		{
			// open input stream for reading purposes
			InputStream inStr = new FileInputStream(fileName);
			
			// create a buffered reader
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStr));
			
			// created character array to receive input
			charArray = new char[inStr.available()];
			
			// reads file characters into 
			reader.read(charArray, 0, (int)bufferSize);		// 103 or 1142
			System.out.println(charArray[5]);				// tests by printing character
			for (char c:charArray)
	         {
	            System.out.print(c);
	         }
			
			// close reader
			reader.close();
		}
		catch (FileNotFoundException ex)
		{
			SimpleOutput.showError("Could not find " + fileName);
			fileName = FileChooser.pickAFile();
			ex.printStackTrace();
		}
		catch (Exception ex)
		{
			SimpleOutput.showError("An error has occured with " + fileName);
			ex.printStackTrace();
		}
		return charArray;
	}
	
	public static void main(String[] args)
	{
		//String fileInput = FileChooser.pickAFile("/Users/Shared/Java-Libraries/CourseCD/mediasources/SecretMessage.txt");
//		String fileInput = FileChooser.getMediaPath("SecretMessage.txt");	// SecretMessage.txt
		String fileInput = FileChooser.getMediaPath("LittleMessage.txt");	// SecretMessage.txt
		BufferedCharTemp charBuff =  new BufferedCharTemp(fileInput);
//		System.out.println(charBuff);
		//charBuff.stringToCharArray(fileInput);
		char[] charArray = charBuff.stringToCharArray(fileInput);
		
		
	}
}