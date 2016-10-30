
	 

	import javax.imageio.ImageIO;

	import java.io.File;

	import java.io.IOException;

	import java.awt.image.BufferedImage;

	 
	
	 

	public class ImageIOExample 
	{   

		private String fileName;
		public ImageIOExample()
		{}
		public ImageIOExample(String fileName)
		{
			this.fileName = fileName;
		}
		

	    public static void main( String[] args )
	    {
	    	
	    	File picture = new File("/Users/Shared/Java-Libraries/CourseCD/mediasources/gokart.png");
	    	ImageIOExample image = new ImageIOExample();
	    	image.imageIoWrite(picture);
	    	//imageWrite()
	    	

	    }

	     
	    
	    public void imageWrite()
	    {
	    	try 
			{
				
				BufferedImage img = null;
			
				
			
			img = ImageIO.read(new File("gokart.png"));
		    File outputfile = new File("saved.png");
		    ImageIO.write(img, "png", outputfile);
			} catch (Exception ex) 
			{
				ex.printStackTrace();
			} 
			
	    }
	    
	    

	    public void imageIoWrite(File initialImage) 	//File fileName
	    {

	    	//File f = new File(fileName);  
	    	BufferedImage bImage = null;

	         try {

	             //File initialImage = new File("/Users/Shared/Java-Libraries/CourseCD/mediasources/gokart.png");
	        	 //File initialImage = fileName;
	        	 
	             bImage = ImageIO.read(initialImage);

	             ImageIO.write(bImage, "png", new File("/Users/Shared/Java-Libraries/CourseCD/results/image.png"));



	 

	         } catch (IOException e) {

	               System.out.println("Exception occured :" + e.getMessage());

	         }

	         System.out.println("Images were written succesfully.");

	    }

	 

	}