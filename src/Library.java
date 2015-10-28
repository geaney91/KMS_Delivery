import java.io.*;
import java.util.*;
public class Library
{
    public Library()
    {
    }

    Scanner in = new Scanner(System.in);
    public void readFile() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("Login.txt"));
        String list;
        String contents="";
        while((list = br.readLine()) != null){
            contents+= list;
            //String contents = br.readLine();
            // return contents;
        }
       // return contents;
    }

    public void writeFile(String ID,String Spass,String name,String password) throws IOException
    {
        PrintWriter out = new PrintWriter(new FileWriter("Login.txt",true));
        out.println("Student,"+ ID + ","+Spass+",");
        out.close();
    }


}