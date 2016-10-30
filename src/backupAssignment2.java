import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


import javax.imageio.ImageIO;

/**
 * Class to take a string and encode the string into a picture file. Also
 * then decode that same string out of the picture file.
 * @author Michael Floerchinger
 * 
 */


public class Steganographer extends Picture
{
	
	/////////////fields///////////////////
	private String fileName;

	
	/**
	 * Constructors
	 */
	
	/////////////constructors/////////
	public Steganographer()
	{
		super();
	}
	
	public Steganographer(String fileName)
	{
		super(fileName);
	}
	
	public Steganographer(File fileName)
	{
		super();
	}
	////////////methods//////////////////
	
	/**
	 * Method to read input file and create a character array from the
	 * message
	 * @param fileName containing the message to hide inside the picture
	 * @return charArray character array containing the message as a 
	 * sequence of characters represented as numbers
	 */
	public char[] stringToCharArray(String fileName)
	{
		// creates a file buffer
		File f = new File(fileName);
		long bufferSize = f.length();
		System.out.println(bufferSize);
		
		// creates a character array
		char[] charArray = null;
		
		// create stream buffers to read the input file and add this into the character array
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
			
			/*
			// debug function, display the characters added to the array
			for (char c:charArray)
	         {
	            System.out.print(c);
	         }
			*/
			
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
	
	
	/**
	 * Method for extracting the encoded message hidden inside the picture
	 * 
	 */
	public void decodePicture()
	{
		
		System.out.println("Message method for decoding message hidden inside picture file.\n\n");
		Pixel[] pixelArray = this.getPixels();
		Pixel getPixel = null;
		
		// message array for extracting hidden message
		char[] messageFromPic = null;
		char[] tempMessage = new char[pixelArray.length];
		
		
		// counter for counting the number of random character locations for extracting the message
		int messageCounter = 0;

		int charArrayCount = 0;
		int value = 0;			// value of the encoded hidden message in the blue color value
		
		// used in order to attempt to reduce the size of the message array
		int validCharCount = 0;
		int nullCount = 0;
		
		for ( int i = 0; i < pixelArray.length; i++)
		{
		
			if ( i % 100 == 0 )
			{

				getPixel = pixelArray[i];
				value = getPixel.getBlue();
				
				// assumed readable characters. Yes, I know CRLF characters are also required, I ran out of time.
				if (value > 0 && value < 127)		
				{
					tempMessage[messageCounter] = (char)value;
					validCharCount++;							// counter used to finally reduce the message array size
				}
				else
				{
					tempMessage[messageCounter] = (char) 46;	// fill with . characters after message
					nullCount++;								// counter used to finally reduce the message array size
				}

				messageCounter++;
			}

		}
		
		// reduce size of the message array
		int newLength = messageCounter - nullCount;	// message counter - number of times a null character was substituted
		messageFromPic = new char[newLength];
		messageFromPic = Arrays.copyOf(tempMessage, messageCounter);
		
		// Loop for looping through the character array to reveal the secret message
		for (int i = 0; i < newLength; i++)
		{
			System.out.print(messageFromPic[i]);
		}
		
		System.out.println("\n-------------------------\nEnd of extracted message!!");
		
		
	}
	
	
	/**
	 * Method to encode a secret text message into a picture file
	 */
	
	// current working method although the prime numbers creates too many pixels
	public void encodePicture(char[] charArray)
	{
		Pixel[] pixelArray = this.getPixels();
		//Pixel value = null;
		Pixel currPixel = null;
		//Pixel pixel = null;
		//Pixel writePixels = null;
		//int x,y;
		int numCount = 0;
		int colorValue = 0;
		int x = 0;	// char to value conversion 
		int charArrayCount = 0;
		
		// max buffersize
		int bufferSize = charArray.length;
		
		//BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		
		
		for ( int i = 0; i < pixelArray.length; i++)
		{
			
			
			
   			
   			if ( i % 100 == 0 && charArrayCount < (int)bufferSize)
   			{
   				//System.out.println(i);
   				currPixel = pixelArray[i];
   				//System.out.println(i);
   	   			colorValue = currPixel.getBlue();
   	   			
	   	 		
	   			x = (int) charArray[charArrayCount];
	   			//shows chars for message
//	   			System.out.println(x);
   	   			
   	   			//System.out.println(colorValue);
   	   			currPixel.setBlue(x);
   	   			// shows byte output for message
//   	   			System.out.println(currPixel.getBlue());
   	   			
   	   			
   	   			charArrayCount++;
   			}

		}

	}
	
	public void encodePicture(String fileName)
	{
		Steganographer charBuff = new Steganographer(fileName);
		char[] charArray = charBuff.stringToCharArray(fileName);
		
//		BufferedCharTemp charBuff =  new BufferedCharTemp(fileInput);
//		//System.out.println(charBuff);		// debug
//		char[] charArray = charBuff.stringToCharArray(fileInput);
		
		Pixel[] pixelArray = this.getPixels();
		//Pixel value = null;
		Pixel currPixel = null;
		//Pixel pixel = null;
		//Pixel writePixels = null;
		//int x,y;
		int numCount = 0;
		int colorValue = 0;
		int x = 0;	// char to value conversion 
		int charArrayCount = 0;
		
		// max buffersize
		int bufferSize = charArray.length;
		
		//BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		
		
		for ( int i = 0; i < pixelArray.length; i++)
		{
			
			
			
   			
   			if ( i % 100 == 0 && charArrayCount < (int)bufferSize)
   			{
   				//System.out.println(i);
   				currPixel = pixelArray[i];
   				//System.out.println(i);
   	   			colorValue = currPixel.getBlue();
   	   			
	   	 		
	   			x = (int) charArray[charArrayCount];
	   			//shows chars for message
//	   			System.out.println(x);
   	   			
   	   			//System.out.println(colorValue);
   	   			currPixel.setBlue(x);
   	   			// shows byte output for message
//   	   			System.out.println(currPixel.getBlue());
   	   			
   	   			
   	   			charArrayCount++;
   			}

		}

	}
	
	
	/**
	 * Method to attempt to write the picture with the encoded message to a file on the 
	 * HDD. Can't get this completed. Have type mismatch issues
	 * @param initialImage
	 */
	
	   
   public void imageIoWrite(File initialImage) 
   {

	   //File f = new File(fileName);  
	   
	   BufferedImage bImage = null;
       try 
       {
    	
           // File initialImage = new File("/Users/Shared/Java-Libraries/CourseCD/mediasources/gokart.png");
           //File initialImage = f;
             
           bImage = ImageIO.read(initialImage);
           ImageIO.write(bImage, "png", new File("/Users/Shared/Java-Libraries/CourseCD/results/image.png"));

       } 
       catch (IOException e) 
       {
    	  
    	   System.out.println("Exception occured :" + e.getMessage());

       }

         System.out.println("Images were written succesfully.");

    }

	
	///////////////main////////////
	
	
	public static void main(String[] args)
	{
		
		//create objects
		String writeLocation = "/Users/Shared/Java-Libraries/CourseCD/results/";
		FileChooser.setMediaPath("/Users/Shared/Java-Libraries/CourseCD/mediasources/");
		
		// Files (two) containing the message to be encoded into picture
		String fileInput = FileChooser.getMediaPath("LittleMessage.txt");		// 	two messages (short)
		//String fileInput = FileChooser.getMediaPath("SecretMessage.txt");		// 	two messages (long)

		
		
		// Picture file to be used for hiding the encoded message
		String fileName = FileChooser.getMediaPath("gokart.png");	
		Steganographer steganObj = new Steganographer(fileName);
		
		steganObj.explore();					// origin picture without encoded message
		steganObj.encodePicture(fileInput);		// encoding message with passing text file to be encoded
		steganObj.explore();					// picture containing encoded message notice location X:0 Y:0 b = 34, " first encoded character

		
		// decoding message hidden within file
		steganObj.decodePicture();


		//steganObj.encodePicture(charArray);
		
		
		
		/// Image io write works but not using the results from the encoded output
		/*
		File image = new File("/Users/Shared/Java-Libraries/CourseCD/mediasources/gokart.png");
    	//ImageIOExample image = new ImageIOExample();
    	Steganographer encodedImage = new Steganographer(image);
    	encodedImage.imageIoWrite(image);
    	*/
		
		/*
		steganObj.imageIoWrite(steganObj.encodePicture(charArray));
		String resultFile = "/Users/Shared/Java-Libraries/CourseCD/results/image.png";
		Steganographer encodedImage = new Steganographer(resultFile);
		encodedImage.explore();
		//steganObj.clearBlue3(steganObj);
		*/
		
		/*
		//Working main method from above saved as backup. Saved before cleanup
		
				//create objects
		String writeLocation = "/Users/Shared/Java-Libraries/CourseCD/results/";
		FileChooser.setMediaPath("/Users/Shared/Java-Libraries/CourseCD/mediasources/");
		
		
		// get read file and add characters to an array
		String secretMessage = FileChooser.getMediaPath("LittleMessage.txt");
		Steganographer stegoMessage = new Steganographer(secretMessage);
		
		
		// String to char array
		
		String fileInput = FileChooser.getMediaPath("LittleMessage.txt");		// 	two messages (short)
		//String fileInput = FileChooser.getMediaPath("SecretMessage.txt");		// 	two messages (long)
		//BufferedCharTemp charBuff =  new BufferedCharTemp(fileInput);
		//System.out.println(charBuff);		// debug
		//char[] charArray = charBuff.stringToCharArray(fileInput);
		//steganObj.encodePicture(charArray);
		
		
		/// get picture working///////
		String fileName = FileChooser.getMediaPath("gokart.png");	
		Steganographer steganObj = new Steganographer(fileName);
		Picture p = new Picture();
//		System.out.println(steganObj);
//		System.out.println("Height:\t" + steganObj.getHeight() + "\tWidth\t" + steganObj.getWidth());
		steganObj.explore();
		steganObj.encodePicture(fileInput);
		steganObj.explore();
		steganObj.decodePicture();


		//steganObj.encodePicture(charArray);
		
		
		
		
		*/
	}

	
	
	
}