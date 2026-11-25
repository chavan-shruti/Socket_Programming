import java.net.*;
import java.io.*;

class ChatClientX
{
    public static void main(String a[])
    {
        try
        {
            Socket sobj = new Socket("localhost", 5100);
            System.out.println("Client gets connected with server successfully!");
            
            PrintStream pobj = new PrintStream(sobj.getOutputStream(), true);
            BufferedReader bobj1 = new BufferedReader(new InputStreamReader(sobj.getInputStream()));
            BufferedReader bobj2 = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Marvellous client is running...");
            String str1 = null, str2 = null;
            
            while(true)
            {
                System.out.println("Enter Msg For Server:");
                str1 = bobj2.readLine();
                
                if(str1 == null || str1.equalsIgnoreCase("end"))
                {
                    pobj.println("end");
                    break;
                }
                
                pobj.println(str1); // sends msg to server
                
                str2 = bobj1.readLine(); // waits for the msg from server
                if(str2 == null)
                {
                    System.out.println("Server disconnected.");
                    break;
                }
                
                System.out.println("Server says: " + str2);
            }
            
            pobj.close();
            bobj1.close();
            bobj2.close();
            sobj.close();
        }
        catch(Exception eobj)
        {
            System.out.println("Client error:");
            eobj.printStackTrace();
        }
    }
}