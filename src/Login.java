import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by 09006819 on 30/10/2015.
 */
public class Login {

    private static String currentUser = "";
    private static String currentPass = "";
    private static int currentId = 0;

    public String getCurrentUser()
    {
        return currentUser;
    }
    public String getCurrentPass()
    {
        return currentPass;
    }

    public int getCurrentId()
    {
        return currentId;
    }


    public static boolean LogUserIn() throws IOException
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter username:");
        String name = in.nextLine();
        System.out.print("Enter password:");
        String pass = in.nextLine();
        Library library = new Library();
        boolean check = false;
        ArrayList<String> localList = library.getLoginList();
        for (int i = 0; i < localList.size(); i++)
        {
            String [] details = localList.get(i).split(",");
            if (details[0].equals(name) && details[1].equals(pass))
            {
                currentUser = details[0];
                currentPass = details[1];
                currentId = Integer.parseInt(details[2]);
                check = true;
                break;
            }
            else
            {
                check = false;
            }
        }

        return check;
    }

    public static void Register() throws IOException
    {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter username:");
        String name = in.nextLine();
        System.out.print("Enter password:");
        String pass = in.nextLine();
        Library library = new Library();
        library.writeFile(name, pass);
        System.out.println("Registration successful\nPlease log in.");
    }
}
