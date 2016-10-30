import java.awt.Color;
import java.io.*;


/**
 * Class that represents a sound.  This class is used by the students
 * to extend the capabilities of SimpleSound. 
 * 
 * Copyright Georgia Institute of Technology 2004
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Sound extends SimpleSound
{
  
  /////////////// consructors ////////////////////////////////////
  
  /**
   * Constructor that takes a file name
   * @param fileName the name of the file to read the sound from
   */
  public Sound(String fileName)
  {
    // let the parent class handle setting the file name
    super(fileName);
  }
  
  /**
   * Constructor that takes the number of samples in
   * the sound
   * @param numSamples the number of samples desired
   */
  public Sound (int numSamples)
  {
    // let the parent class handle this
    super(numSamples);
  }
  
  /**
   * Constructor that takes the number of samples that this
   * sound will have and the sample rate
   * @param numSamples the number of samples desired
   * @param sampleRate the number of samples per second
   */
  public Sound (int numSamples, int sampleRate)
  {
    // let the parent class handle this
    super(numSamples,sampleRate);
  }
  
  /**
   * Constructor that takes a sound to copy
   */
  public Sound (Sound copySound)
  {
    // let the parent class handle this
    super(copySound);
  }
  
  ////////////////// methods ////////////////////////////////////
  /**
   * Program 113: Write a sound to a file as text numbers
   * Method to write out the values on the sound to a file as text
   * @param fileName the name of the file to write to
   */
  public void writeSampleAsText(String fileName)
  {
	  int value = 0;
	  
	  // try the following
	  try
	  {
		  // try to open the buffered writer
		  BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		  
		  // loop through the samples
		  for ( int i = 0; i < this.getLength(); i++)
		  {
			  // get the int value
			  value = this.getSampleValue(i);
			  
			  // write it as a string (text)
			  writer.write(String.valueOf(value));
			  
			  // add the new line
			  writer.newLine();
		  }
		  // close the writer
		  writer.close();
	  }
	  catch (Exception ex)
	  {
		  SimpleOutput.showError("Error during write");
		  ex.printStackTrace();
	  }
  }
 
  /**
   * Program 114:
   * Method to create a sound from a text file
   * @param fileName the name of tje file to read from
   * @return the created sound object
   */
  public static Sound createSoundFromTextFile(String fileName)
  {
	  String line = null;
	  int value = 0;
	  
	  // create the sound to read into
	  Sound s = new Sound(FileChooser.getMediaPath("sec3silence.wav"));
	  
	  // try the following
	  try
	  {
		  // create the buffered reader
		  BufferedReader reader = new BufferedReader(new FileReader(fileName));
		  
		  // loop reading the values
		  int index = 0;
		  while ((line = reader.readLine()) != null && index < s.getLength())
		  {
			  value = Integer.parseInt(line);
			  s.setSampleValueAt(index++, value);
		  }
		  // close reader
		  reader.close();
	  }
	  catch (FileNotFoundException ex)
	  {
		  SimpleOutput.showError("Couldn't find file " + fileName);
		  fileName = FileChooser.pickAFile();
		  s = createSoundFromTextFile(fileName);
	  }
	  catch (Exception ex)
	  {
		  SimpleOutput.showError("Error during read or write");
		  ex.printStackTrace();
	  }
	  return s;
  }
  
  /**
   * Program 115:
   * Method to turn a sound into a picture
   * @return a created picture
   */
  public Picture createPicture()
  {
	  int value = 0;
	  Pixel pixel = null;
	  
	  // create a picture to write to
	  Picture p = new Picture(FileChooser.getMediaPath("640x480.jpg"));
	  
	  
	  // loop through the pixels
	  Pixel[] pixelArray = p.getPixels();
	  
	  
	  // loop through the pixels
	  for ( int i = 0; i < pixelArray.length && i < this.getLength(); i++)
	  {
		  // get this pixel
		  pixel = pixelArray[i];
		  
		  // set the color based on the sample value
		  value = this.getSampleValueAt(i);
		  if ( value > 1000)
		  {
			  
			  pixel.setColor(Color.RED);
		  }
		  else if ( value < -1000)
		  {
			  
			  pixel.setColor(Color.BLUE);
		  }
		  else
		  {
			  System.out.println(value);
			  pixel.setColor(Color.GREEN);
		  }
	  }
	  return p;
  }
  
  /**
   * Method to return the string representation of this sound
   * @return a string with information about this sound
   */
  public String toString()
  {
    String output = "Sound";
    String fileName = getFileName();
    
    // if there is a file name then add that to the output
    if (fileName != null)
      output = output + " file: " + fileName;
    
    // add the length in frames
    output = output + " number of samples: " + getLengthInFrames();
    
    return output;
  }
 
  public static void main(String[] args)
  {

	  // Program 115:
	  Sound s = new Sound(FileChooser.getMediaPath("her.wav"));
	  s.createPicture();
	  
	  
	  /*
	  // Program 113:
	  Sound s = new Sound(FileChooser.getMediaPath("her.wav"));
	  s.writeSampleAsText(FileChooser.getMediaPath("her.txt"));
	  */
	  
	  /*
	  Sound sound1 = new Sound(FileChooser.pickAFile());
	  sound1.explore();
	  */
  }
             
} // this } is the end of class Sound, put all new methods before this