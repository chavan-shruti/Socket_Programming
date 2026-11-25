import java.net.*;
import java.io.*;

class Serverside
{
    public static void main(String a[])
    {
        try
        {
            ServerSocket ssobj = new ServerSocket(5100);
            System.out.println("Server is running at port 5100...");
            
            Socket sobj = ssobj.accept(); 
            System.out.println("Client request gets accepted successfully!");
            
            PrintStream pobj = new PrintStream(sobj.getOutputStream(), true);
            BufferedReader bobj1 = new BufferedReader(new InputStreamReader(sobj.getInputStream()));
            BufferedReader bobj2 = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Marvellous server is running...");
            String str1 = null, str2 = null;
            
            while(true)
            {
                str1 = bobj1.readLine();
                if(str1 == null || str1.equalsIgnoreCase("end"))
                {
                    System.out.println("Client disconnected.");
                    break;
                }
                
                System.out.println("Client says: " + str1);
                
                System.out.println("Enter msg for client:");
                str2 = bobj2.readLine();
                if(str2 == null)
                {
                    break;
                }
                pobj.println(str2);
            }
            
            pobj.close();
            bobj1.close();
            bobj2.close();
            sobj.close();
            ssobj.close();
        }
        catch(Exception eobj)
        {
            System.out.println("Server error:");
            eobj.printStackTrace();
        }
    }
}