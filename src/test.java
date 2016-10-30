public class test
{
	public static void main(String[] args)
	{
		//String message = " this is a message ";
		
		char[] message = new char[10];
		int[] text = {34,83,112,101,101,100,32,104,97,115,34};
		for (int i = 0; i < 10; i++)
		{
			int value = text[i];
			//System.out.println(value);
			message[i] = (char)value;
			System.out.print(message[i]);
		}
		
	}
}

