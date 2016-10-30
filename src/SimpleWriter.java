import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import javax.imageio.*;

public class SimpleWriter extends Picture
{
	
	private String fileName;
	
	public SimpleWriter()
	{
		super();
	}
	
	public SimpleWriter(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * Program 105
	 * Method to write a silly file
	 */
	public void writeSillyFile()
	{
		try {
			// try to open the buffered writer
			BufferedWriter writer = new BufferedWriter(new FileWriter("silly.txt"));
			// file locates into the current workspace.
			
			// write out the file
			writer.write("Here is some text.");
			writer.newLine();
			writer.write("Here is some text.");
			writer.newLine();
			writer.write("Here is some text.");
			writer.newLine();
			writer.newLine();
			writer.write("THE END");
			writer.newLine();
			writer.close();
		} catch (Exception ex) {
			System.out.println("Error during write of silly.txt");
		}
	}
	
	public void writeLetter(String title, String lastName, String city, String eyeColor)
	{
		String fileName = lastName + "Letter.txt";
		
		// try to open the file and write to is
		try {
			// create the buffered writer to use to write the file
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			
			// write the beginning of the letter
			writer.write("Dear " + title + " " + lastName + ",");
			writer.newLine();
			writer.newLine();
			writer.newLine();
			
			// write the body of the letter
			writer.write("I am writing to remind you of the offer");
			writer.newLine();
			writer.write("that we sent you last week. ");
			writer.write("Everyone in");
			writer.newLine();
			writer.write(city + " knows what an exceptional offer this is!");
			writer.newLine();
			writer.write("Especially those with lovely eyes of " + eyeColor + "!");
			writer.newLine();
			writer.write("We hope to hear from you soon.");
			writer.newLine();
			writer.newLine();
			
			// write the ending
			writer.write("Sincerely,");
			writer.newLine();
			writer.write("I. M. Acrook");
			
			// close the file
			writer.close();
		} catch (Exception ex) {
			System.out.println("Error writing to " + fileName);
		}
	}
	
	public void modifyFile(String fileName, String textToChange, String changedText)
	{
		List lineList = new ArrayList();
		String line = null;
		int pos = 0;
		
		// try the following
		try {
			// open the file to read from
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			
			/*
			 * loop while there re more lines in the file and we haven't
			 * found the text to change yet
			 */
			while ((line = reader.readLine()) != null &&
					line.indexOf(textToChange) < 0)
			{
				lineList.add(line);
			}
			
			/*If we get there we either ran out of lines or we found the text to
			 * change
			 */
			if (line != null)
			{
				// get the position of the text to change
				pos = line.indexOf(textToChange);
				
				// modify the string
				lineList.add(line.substring(0, pos) + 
						changedText + line.substring(pos + textToChange.length()));
				
				// loop til the end of the file ad1ding the rest
				while ((line = reader.readLine()) != null)
				{
					lineList.add(line);
				}
			}
			// now close the file
			reader.close();
			
			// create a writer to write out the file
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			
			// loop writing out the lines
			for (int i = 0; i < lineList.size(); i++)
			{
				writer.write((String) lineList.get(i));
				writer.newLine();
			}
			
			// close the writer
			writer.close();
		} 
		catch (FileNotFoundException ex) 
		{
			SimpleOutput.showError("Couldn't find file " + fileName);
			fileName = FileChooser.pickAFile();
			modifyFile(fileName,textToChange,changedText);
		}
		catch (Exception ex)
		{
			SimpleOutput.showError("Error during read or write");
			ex.printStackTrace();
		}
		
	}
	
	public void imageWriter()
	{
		try {
		    // retrieve image
		    BufferedImage bi = getMyImage();
		    SimpleWriter bufImg = new SimpleWriter();
		    File outputfile = new File("saved.png");
		    ImageIO.write(bi, "png", outputfile);
		} catch (Exception ex) 
		{
			SimpleOutput.showError("Error during read or write");
			ex.printStackTrace();
		}
	}
	
	
	public void writeToFile(String name) 
	{
	    
	    try 
	    {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(name)); 
	    	
	    	for (int i = 0; i < this.)
	    	
	    	
	        writer.write(content);
	     }
	    catch(IOException e) {
	       // Handle the exception
	    }
	    finally {   
	        if(bw != null) {
	            bw.close();
	        }
	    }
	}
	
	try {
	    Writer f = new FileWriter(nameOfFile);
	    f.write(stringToWrite);
	    f.close();
	} catch (IOException e) {
	    // unable to write file, maybe the disk is full?
	    // you should log the exception but printStackTrace is better than nothing
	    e.printStackTrace();
	}
	
	
	public static void main(String[] args)
	{
		
		
		
		
		
		
		
		
	
		/*
		String fileName = FileChooser.getMediaPath("gokart.png");
		SimpleWriter imageWriter = new SimpleWriter(fileName);
		imageWriter.imageWriter();
		
		
		SimpleWriter writer = new SimpleWriter();
		writer.writeSillyFile();
		
		SimpleWriter formGenerator =  new SimpleWriter();
		formGenerator.writeLetter("Mr.", "Guzdial", "Decatur", "brown");
		
		SimpleWriter fileMod = new SimpleWriter();
		String file = "/home/notroot/Java/JavaII/workspace-JavaII/"
				+ "JavaII-Week-06-Notes/src/Cartoon.java";
		fileMod.modifyFile(file,  "Just Horsing Around", "What's up, Wilbur?");
		*/
	}
}