import java.awt.Robot;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MoogPlayer
{
	Robot robot=new Robot();
	HashMap<String,String> variables=new HashMap<String,String>();
	
	public static void main(String[] args) throws Exception
	{
		new MoogPlayer();
	}
	
	public MoogPlayer() throws Exception
	{
		System.out.println("Google Moog Player version 1.1 by Oliver Ceccopieri\n");
		
		ArrayList<String> measures=new ArrayList<String>();
		Scanner keyboard=new Scanner(System.in);
		
		System.out.println("Which song file?");
		String songFile=keyboard.nextLine(); // Let user choose song file
		System.out.println("");
		
		keyboard.close();
		
		Scanner songReader=new Scanner(new File(songFile));
		
		while (songReader.hasNext())
		{
			String line=songReader.nextLine().toLowerCase().replaceAll("\\s+","");
			
			if (!line.equals("") && line.charAt(0)!='#') // Ignore comments and blank lines
			{
				if (line.charAt(0)=='$') // Treat as variable
				{
					variables.put(line.split("=")[0].substring(1),line.split("=")[1]);
				}
				else // Treat as measure
				{
					System.out.println(line);
					measures.add(line.replaceAll("~","\t"));
				}
			}
		}
		
		int speed; // Define all useful variables and set defaults
		
		if (variables.get("speed")==null) speed=100;
		else speed=Integer.parseInt(variables.get("speed"));
		
		System.out.println("\nSpeed: "+speed);
		
		songReader.close();
		
		// Begin the playing sequence
		
		robot.setAutoDelay(0);
	    robot.setAutoWaitForIdle(true);
	    
	    System.out.println("\nActivate Moog window NOW! You have 5 seconds");
	    robot.delay(5000);
	    
	    System.out.println("Playing now");
	    
	    for (int i=0;i<measures.size();i++) playMeasure(speed,measures.get(i)); // Loop through all measures
	    
	    System.out.println("Done");
	}
	
	private void playMeasure(int delay,String s)
	{
		byte[] bytes=s.getBytes();
		int heldCode=47,noteDelay=delay,stacDelay=0;
		
		for (int i=0;i<bytes.length;i++)
		{
			int code=bytes[i];
			int nextcode;
			if (i+1>=bytes.length) nextcode=47;
			else nextcode=bytes[i+1];
			
			if (code>96 && code<123) code-=32;
			if (nextcode>96 && nextcode<123) nextcode-=32;
			
			noteDelay=(delay*3)/4;
			stacDelay=delay/4;
			
			// Code for staccato
			if (nextcode==42)
			{
				noteDelay=delay/4;
				stacDelay=(delay*3)/4;
				i++;
			}
			
			// Code for holding a note
			if (code!=95 && nextcode!=95)
			{
				robot.keyPress(code);
				robot.delay(noteDelay*2);
				robot.keyRelease(code);
				robot.delay(stacDelay*2);
			}
			
			if (code!=95 && nextcode==95)
			{
				robot.keyPress(code);
				heldCode=code;
				robot.delay(noteDelay*2);
			}
			
			if (code==95 && nextcode==95)
			{
				robot.delay(noteDelay*2);
			}
			
			if (code==95 && nextcode!=95)
			{
				robot.delay(noteDelay*2);
				robot.keyRelease(heldCode);
				robot.delay(stacDelay*2);
			}
		}
	}
}
