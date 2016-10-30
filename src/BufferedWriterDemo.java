

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

public class BufferedWriterDemo 
{
   
	public static void main(String[] args) throws IOException 
   {
      
      StringWriter sw = null;
      BufferedWriter bw = null;
         
      try{
         // create string writer
         sw = new StringWriter();
         
         //create buffered writer
         bw = new BufferedWriter(sw);
         
         // create character buffer
         char[] cbuf = {'A', 'B', 'C', 'D'};
         
         // append character to the writer
         bw.write(cbuf);
                  
         // flush the characters to the intended writer
         bw.flush();
         
         // print string buffer from string writer
         System.out.println(sw.getBuffer());
   
      }catch(IOException e){
         // if I/O error occurs
         e.printStackTrace();
      }finally{
         // releases any system resources associated with the stream
         if(sw!=null)
            sw.close();
         if(bw!=null)
            bw.close();
      }
   }
}
