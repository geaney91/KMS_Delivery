import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class Library
{
    private static ArrayList<String> loginList = new ArrayList<>();
    private static ArrayList<String> trackingList = new ArrayList<>();

    public Library() {}

    public ArrayList<String> getLoginList()
    {
        return loginList;
    }
    public ArrayList<String> getTrackingList()
    {
        return trackingList;
    }

    //Write new user log in details to file
    public void writeFile(String user, String pass) throws IOException
    {
        int range = (10000);
        int id = (int)(Math.random() * range);
        PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("./src/Users.txt",true)));
        out.println(user + "," + pass + "," + id);
        out.close();
        loginList = readLoginDetails();
    }

    //Writes the details of each user that logs in to log file
    public void writeFile(String user) throws IOException
    {
        PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("./src/Log.txt",true)));
        String dt;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date loggedIn = new Date();
        dt = dateFormat.format(loggedIn);
        out.println(user + ", " +  dt);
        out.close();
    }

    //Write details to post tracking file
    public void writeFile(String user, int id, String deliveryDate) throws IOException
    {
        String dt;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date sent = new Date();
        dt = dateFormat.format(sent);
        int range = (1000);
        int letterId = (int)(Math.random() * range);
        PrintWriter out = new PrintWriter(new FileWriter("./src/PostTracking.txt",true));
        out.println(user + "," + id + IntToLetter(letterId) + "," + dt + "," + deliveryDate);
        out.close();
        trackingList = readTrackingDetails();
    }

    //Takes an int and returns characters
    public static String IntToLetter(int Int)
    {
        if (Int<27)
            return Character.toString((char)(Int+96));  //Return single char from a to z depending on the int (96 added to take us to
        else                                            //the lower case chars on ASCII table.
        {
            //Recursive calls to add more than 1 letter to the random letter sequence.
            if (Int%26==0)
                return IntToLetter((Int/26)-1)+IntToLetter((Int%26)+1);
            else
                return IntToLetter(Int/26)+IntToLetter(Int%26);
        }
    }

    public ArrayList<String> readLoginDetails() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("./src/Users.txt"));
        String line;
        while ((line = br.readLine()) != null)
        {
            loginList.add(line);
        }
        br.close();
        return loginList;
    }

    public ArrayList<String> readTrackingDetails() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("./src/PostTracking.txt"));
        String line;
        while ((line = br.readLine()) != null)
        {
            trackingList.add(line);
        }
        br.close();
        return trackingList;
    }
}
