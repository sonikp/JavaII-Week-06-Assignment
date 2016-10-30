import java.awt.*;
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


import javax.imageio.ImageIO;

/**
 * Class to take a string and encode the string into a picture file. Also
 * then decode that same string out of the picture file.
 * 
 * 
 */


public class Steganographer extends Picture
{
	
	/////////////fields///////////////////
	private String fileName;
	
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
	
	
	
	
	/*
	public void clearBlue()
	{
		
		
		Pixel[] pixelArray = this.getPixels();
		Pixel pixel = null;
		int index = 0;
		
			  
		for ( Pixel value : pixelArray)
		{
		
			//System.out.println(value.getBlue());
			//value.setBlue(0);
			//value.setBlue(value.getBlue());
			System.out.print("index:\t" + index + "\tred: \t" + value.getRed() + "\tgreen:\t" + value.getGreen() + "\tblue:\t" + value.getBlue() + "\n");
			index++;
		}
	}
	*/
	// current working method although the prime numbers creates too many pixels
	public void encodePicture(char[] charArray)
	{
		Pixel[] pixelArray = this.getPixels();
		//Pixel value = null;
		Pixel currPixel = null;
		Pixel pixel = null;
		Pixel writePixels = null;
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
	   			System.out.println(x);
   	   			
   	   			//System.out.println(colorValue);
   	   			currPixel.setBlue(x);
   	   			System.out.println(currPixel.getBlue());
   	   			
   	   			
   	   			charArrayCount++;
   			}
			
   			//writer.write(currPixel.setColor(currPixel.getColor()));
   			//imageIoWrite(currPixel.setColor(currPixel.getColor()));
			
		}
		System.out.println(numCount);
		System.out.println(charArray[0]);
		/*
		String outputFile = "/Users/Shared/Java-Libraries/CourseCD/results/encodedImage.png";
		Pixel writePixel = null;
		
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			for ( int i = 0; i < pixelArray.length; i++)
			{
				writePixel = pixelArray[i];
				System.out.println(writePixel);
				//writer.write(String.valueOf(writePixel.getColor()));
				writer.write(String.valueOf(writePixel));
				
				
			}
		} catch (Exception ex) 
	      {
		   ex.printStackTrace();
		   
		  } 
		*/
		
		/*
		try 
		{
			
			BufferedImage img = null;
		
			
		// retrieve image
	    //BufferedImage bi = getMyImage();
			img = ImageIO.read(new File("strawberry.jpg"));
	    File outputfile = new File("saved.png");
	    ImageIO.write(img, "png", outputfile);
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		} 
		*/
		
		/*
		// the writer does not like writing int to Pixel type. will write the file to disk, however it contains no data
		try 
	      {
	
		 File file = new File("/Users/Shared/Java-Libraries/CourseCD/results/myWRITEDEMOfile.png");
		 

		  FileWriter fileWrite = new FileWriter(file);
		  BufferedWriter writer = new BufferedWriter(fileWrite);
		  BufferedImage bImage = ImageIO.write(fileWrite);
		  
		  
		  
		  for ( int i = 0; i < pixelArray.length; i++)
		  {
			  writePixels = pixelArray[i];
			  int getRed = writePixels.getRed();
			  int getGreen = writePixels.getGreen();
			  int getBlue = writePixels.getBlue();
			  writePixels.setColor(writePixels.getColor());
			  //System.out.println(writePixels);
			  
			  writer.write(writePixels.setColor(writePixels.getColor()));
		  }
		  
		  //writer.write(charArray);
	          System.out.println("File written Successfully");

	      } catch (Exception ex) 
	      {
		   ex.printStackTrace();
		  } 
		*/
//		char c;
//		c = 'A';
//		System.out.println(c);
//		
//		// decoding
//		char myChar = (char) 65;
//		System.out.println(myChar);
		
		// encoding
//		int x;
//		//x = (int) c;
//		x = (int) charArray[0];
//		System.out.println(x);
		
	}
	
	/*
	// back up copy of encode with prime number encoder
	 * // current working method although the prime numbers creates too many pixels
	public void encodePicture()
	{
		Pixel[] pixelArray = this.getPixels();
		//Pixel value = null;
		Pixel currPixel = null;
		//int x,y;
		int numCount = 0;
		int colorValue = 0;
		
		for ( int i = 0; i < pixelArray.length; i++)
		{
			
			
			if (isPrime(i)) 
			{
				System.out.println(i);
				currPixel = pixelArray[i];
				//System.out.println(i);
	   			colorValue = currPixel.getRed();
	   			System.out.println(colorValue);
	   			currPixel.setRed(0);
				
				//DEBUG: System.out.print("index:\t" + i + "\tred: \t" + currPixel.getRed() + "\tgreen:\t" + currPixel.getGreen() + "\tblue:\t" + currPixel.getBlue() + "\n");
	   			numCount++;
			}
    
			
			
			
			
		}
		System.out.println(numCount++);
	}
	
	
	
	*/
	
	
	/*
	public void clearBlue3(Picture fileName )
	{

		Pixel currPixel = null;
		int x,y;
		
		// loop columns
		for ( x = 0; x < this.getWidth(); x++ )	
		{
			// loop rows
			for ( y = 0; y < this.getHeight(); y++ ) 
			{
				currPixel = this.getPixel(x, y);
				System.out.println("x\t" + x + "\ty\t" + y + "\tred: \t" + currPixel.getRed() + "\tgreen:\t" + currPixel.getGreen() + "\tblue:\t" + currPixel.getBlue());
			}
		}
		
		
		
	}
	*/
	
	public void messageArray(String message)
	{
		
	}
	
	public void reDraw()
	{
		
		
		Pixel[] pixelArray = this.getPixels();
		Pixel pixel = null;
		int x, y;
			  
		// loop through the columns
		for ( y = 0; y < this.getWidth(); y++)
		{
			// loop through rows
			for ( x = 0; x < this.getHeight(); x++)
			{
				
				//System.out.println("\ty\t" + y + "\tx\t" + x);
				pixel = this.getPixel(x, y);
				System.out.println("x\t" + x + "\ty\t" + y + "red: \t" + pixel.getRed() + "\tgreen:\t" + pixel.getGreen() + "\tred:\t" + pixel.getRed() + "\n");
			}
		
			
		}
	}
	
	
	   public static boolean isPrime(int n) 
	   {
	       if (n <= 1) {
	           return false;
	       }
	       for (int i = 2; i < Math.sqrt(n); i++) 
	       {
	           if (n % i == 0) {
	               return false;
	           }
	       }
	       return true;
	   }
	
	   
	   public void imageIoWrite(File initialImage) 
	   {

		   //File f = new File(fileName);  
		   
		   BufferedImage bImage = null;

	         try {

	            // File initialImage = new File("/Users/Shared/Java-Libraries/CourseCD/mediasources/gokart.png");
	             
	             //File initialImage = f;
	             
	             bImage = ImageIO.read(initialImage);

	 

	             ImageIO.write(bImage, "png", new File("/Users/Shared/Java-Libraries/CourseCD/results/image.png"));


	 

	         } catch (IOException e) {

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
		
		/*
		// get read file and add characters to an array
		String secretMessage = FileChooser.getMediaPath("LittleMessage.txt");
		Steganographer stegoMessage = new Steganographer(secretMessage);
		*/
		
		// String to char array
//		String fileInput = FileChooser.getMediaPath("SecretMessage.txt");	// SecretMessage.txt
		String fileInput = FileChooser.getMediaPath("LittleMessage.txt");	// SecretMessage.txt
		BufferedCharTemp charBuff =  new BufferedCharTemp(fileInput);
//		System.out.println(charBuff);
		char[] charArray = charBuff.stringToCharArray(fileInput);
		
		
		
		/// get picture working///////
		String fileName = FileChooser.getMediaPath("gokart.png");	//Hungry-small.png	//gokart.png  // on this pic 522105 index's
		Steganographer steganObj = new Steganographer(fileName);
		Picture p = new Picture();
		System.out.println(steganObj);
		System.out.println("Height:\t" + steganObj.getHeight() + "\tWidth\t" + steganObj.getWidth());
		//steganObj.clearBlue();
		steganObj.explore();
		steganObj.encodePicture(charArray);
		steganObj.explore();

		//steganObj.write(steganObj.encodePicture(charArray);
		
		
		
		
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
		
		
//		steganObj.reDraw();
		
//		

		/*
		// dead code from prime num generator
			int start = 1;
		   int end = 20;
		   int numCount = 0;
		   
	       for (int i = start; i <= end; i++) 
	       {
	           if (isPrime(i)) 
	           {
	               System.out.println(i);
	               numCount++;
	           }
	       }
	       System.out.println("\n\n" + numCount);
		
		*/
		
	}

	
	
	
}