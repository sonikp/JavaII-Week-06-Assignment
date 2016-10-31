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
	 * Method to convert string information and character array.
	 * This method reads input file and create a character array from the
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
	 * The method takes the output of the encoded message object
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
	 * Method for extracting the encoded message hidden inside the picture
	 * This method takes the file stored on the HDD
	 * @param filename file with the encoded message
	 */
	public void decodePicture(String fileName)
	{
		
		//Picture picObj = new Picture(fileName);
		
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
	 * This method take the return of the output of the character array
	 * if called directly from the main method
	 */
	
	// current working method although the prime numbers creates too many pixels
	public void encodePicture(char[] charArray)
	{
		Pixel[] pixelArray = this.getPixels();
		Pixel currPixel = null;

		int numCount = 0;
		int colorValue = 0;
		int x = 0;	// char to value conversion 
		int charArrayCount = 0;
		
		// max buffersize
		int bufferSize = charArray.length;
		
		// step through the pixels in the array
		for ( int i = 0; i < pixelArray.length; i++)
		{
			
			// determine how to hid the message within the picture. Was using primeNumbers, however there are too many of these and it effects the picture display
   			if ( i % 100 == 0 && charArrayCount < (int)bufferSize)
   			{
   				// get blue color from each of the 100th pixel
   				currPixel = pixelArray[i];
   	   			colorValue = currPixel.getBlue();
   	   			
	   	 		// step through the character array and take each consecutive character
	   			x = (int) charArray[charArrayCount];

	   			// set character as blue value for the 
   	   			currPixel.setBlue(x);
   	   			
   	   			// character array counter
   	   			charArrayCount++;
   			}

		}

	}
	
	/**
	 * Method to encode a secret text message into a picture file
	 * This method takes the text file to be encoded.
	 * if called directly from the main method
	 * @param filename file containing the message to be encoded
	 */
	
	public void encodePicture(String fileName)
	{
		// create character object to retrieve the character array
		Steganographer charBuff = new Steganographer(fileName);
		char[] charArray = charBuff.stringToCharArray(fileName);
		
		// create a pixel array
		Pixel[] pixelArray = this.getPixels();		//this.getPixels();
		Pixel currPixel = null;
		
		// writing section the writing pixels for BufferedWriter
		Pixel writePixels = null;
		
		// debug variables variables to reduce the decoded array size
		int numCount = 0;
		int colorValue = 0;
		int x = 0;				// char to value conversion 
		int charArrayCount = 0;
		
		// max buffersize
		int bufferSize = charArray.length;
		
		// to step through the picture array
		for ( int i = 0; i < pixelArray.length; i++)
		{
			
   			// encoding method, I've left all this debug code on purpose. The write function is not yet working
   			if ( i % 100 == 0 && charArrayCount < (int)bufferSize)
   			{

   				currPixel = pixelArray[i];
   	   			colorValue = currPixel.getBlue();
   	   			
	   	 		// Character in character array assigned to value x
	   			x = (int) charArray[charArrayCount];

	   			// value of character written to blue color setting
   	   			currPixel.setBlue(x);

   	   			charArrayCount++;
   			}

		}
				
		// write the file to disk
		try 
	    {
			
			String file = new String("/Users/Shared/Java-Libraries/CourseCD/results/encodedKart.png");
			this.write(file);
	        System.out.println("File written Successfully");

	      }
		  catch (Exception ex) 
	      {
			  ex.printStackTrace();
		  } 

	}
	
	   

	
	///////////////main////////////
	
	
	public static void main(String[] args)
	{
		
		// Files (two) containing the message to be encoded into picture
		FileChooser.setMediaPath("/Users/Shared/Java-Libraries/CourseCD/mediasources/");
		String fileInput = FileChooser.getMediaPath("LittleMessage.txt");		// 	two messages (short)
		//String fileInput = FileChooser.getMediaPath("SecretMessage.txt");		// 	two messages (long)

		// Picture file to be used for hiding the encoded message
		String fileName = FileChooser.getMediaPath("gokart.png");	
		Steganographer steganObj = new Steganographer(fileName);
		
		steganObj.explore();					// origin picture without encoded message
		steganObj.encodePicture(fileInput);		// encoding message with passing text file to be encoded
		steganObj.explore();					// picture containing encoded message notice location X:0 Y:0 b = 34, " first encoded character

		// decoding message hidden within file
		FileChooser.setMediaPath("/Users/Shared/Java-Libraries/CourseCD/results/");		// encoded picture written to /results/ folder
		String encodedFile = FileChooser.getMediaPath("encodedKart.png");		
		Steganographer steganObjDec = new Steganographer(encodedFile);
		steganObjDec.decodePicture(encodedFile);						// passes file to the decode method
		steganObjDec.explore();											// explore used to validate the encoded blue picture pixels
		


		
	}

	
	
	
}