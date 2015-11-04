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

    public void writeFile(String user, String pass) throws IOException
    {
        int range = (10000);
        int id = (int)(Math.random() * range);
        PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("./src/Users.txt",true)));
        out.println(user + "," + pass + "," + id);
        out.close();
        loginList = readLoginDetails();
    }

    public void writeFile(String user) throws IOException
    {
        PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("./src/Log.txt",true)));
        String dt = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date sent = new Date();
        dateFormat.format(sent);
        Calendar c = Calendar.getInstance();
        c.setTime(sent);
        dt = dateFormat.format(c.getTime());
        out.println(user + ", " +  dt);
        out.close();
        loginList = readLoginDetails();
    }

    public void writeFile(String user, int id, String deliveryDate) throws IOException
    {
        String dt = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date sent = new Date();
        dateFormat.format(sent);
        Calendar c = Calendar.getInstance();
        c.setTime(sent);
        dt = dateFormat.format(c.getTime());
        int range = (259);
        int letterId = (int)(Math.random() * range);
        PrintWriter out = new PrintWriter(new FileWriter("./src/PostTracking.txt",true));
        out.println(user + "," + id + IntToLetter(letterId) + "," + dt + "," + deliveryDate);
        out.close();
        trackingList = readTrackingDetails();
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

    public ArrayList<String> readLoginDetails() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("./src/Users.txt"));
        String line = "";
        while ((line = br.readLine()) != null)
        {
            loginList.add(line);
        }
        return loginList;
    }

    public ArrayList<String> readTrackingDetails() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("./src/PostTracking.txt"));
        String line = "";
        while ((line = br.readLine()) != null)
        {
            trackingList.add(line);
        }
        return trackingList;
    }

}
