import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;


public class DemoTests 
{
    public void testDemoclass()
    {
        String[] args = {"Test"} ; // erzeugen input
        String out = "Test"; // erwarteter output

        //output Setzen 
        System.setOut(new PrintStream(new ByteArrayOutputStream())); // output der console 
        
        try{
            DemoConsole.main(args);
            assertEquals(out.toString(), args);
        }
        catch(Exception ex)
        {
            fail("Fehler: " +  ex.getMessage());
        }
    }
    


}

