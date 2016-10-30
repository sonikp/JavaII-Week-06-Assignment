
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileDemo 
{
  
	public void charToFile()
	{
		
	      try 
	      {
		 //String mycontent = "This String would be written to the specified File";
	     char[] mycontent = {'A', 'B', 'C', 'D'};
	         //Specify the file name and path here
		 File file = new File("/Users/Shared/Java-Libraries/CourseCD/results/myWRITEDEMOfile.png");

		 /*
		  if (!file.exists()) 
		  {
		     file.createNewFile();
		  }
			*/
		 	FileWriter fileWrite = new FileWriter(file);
		  BufferedWriter writer = new BufferedWriter(fileWrite);
		  
		  writer.write(mycontent);
	          System.out.println("File written Successfully");

	      } catch (Exception ex) 
	      {
		   ex.printStackTrace();
		}
		finally
		{ 
		   try{
		      if(writer!=null)
		    	  writer.close();
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedWriter"+ex);
		    }
		}
	}
	
	
   public static void main(String[] args) 
   {
	   
	   WriteFileDemo write = new WriteFileDemo();
	   write.charToFile();
	   
	   
	   /*
	   BufferedWriter writer = null;
      try {
	 //String mycontent = "This String would be written to the specified File";
     char[] mycontent = {'A', 'B', 'C', 'D'};
         //Specify the file name and path here
	 File file = new File("/Users/Shared/Java-Libraries/CourseCD/results/myWRITEDEMOfile.png");

	 /* This logic will make sure that the file 
	  * gets created if it is not present at the
	  * specified location*/
	   /*
	  if (!file.exists()) {
	     file.createNewFile();
	  }

	  FileWriter fileWrite = new FileWriter(file);
	  writer = new BufferedWriter(fileWrite);
	  writer.write(mycontent);
          System.out.println("File written Successfully");

      } catch (IOException ioe) {
	   ioe.printStackTrace();
	}
	finally
	{ 
	   try{
	      if(bw!=null)
		 bw.close();
	   }catch(Exception ex){
	       System.out.println("Error in closing the BufferedWriter"+ex);
	    }
	}
	*/
   }
}