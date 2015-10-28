import java.io.*;
import java.util.*;
public class Library
{

    public Library()
    {
    }

    Scanner in = new Scanner(System.in);

    public void writeFile(String user, String pass) throws IOException
    {
        int range = (10000);
        int id = (int)(Math.random() * range);
        PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("./src/Users.txt",true)));
        out.println(user + "," + pass + "," + id);
        out.close();
    }

    public void writeFile(String user, int id, Date deliveryDate) throws IOException
    {
        Date sent = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(sent);
        int range = (259);
        int letterId = (int)(Math.random() * range);
        PrintWriter out = new PrintWriter(new FileWriter("./src/PostTracking.txt",true));
        out.println(user + "," + id + IntToLetter(letterId) + "," + sent + "," + deliveryDate);
        out.close();
    }

    public static String IntToLetter(int Int)
    {
        if (Int<27){
            return Character.toString((char)(Int+96));
        } else {
            if (Int%26==0) {
                return IntToLetter((Int/26)-1)+IntToLetter((Int%26)+1);
            } else {
                return IntToLetter(Int/26)+IntToLetter(Int%26);
            }
        }

        }

}
