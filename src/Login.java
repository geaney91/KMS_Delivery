import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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


    public static boolean LogUserIn(Scanner in, Library library) throws IOException
    {
        System.out.print("Enter username:");
        String name = in.nextLine();
        System.out.print("Enter password:");
        String pass = in.nextLine();

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
                check = false;
        }
        return check;
    }

    public static void Register(Scanner in, Library library) throws IOException
    {
        System.out.print("Enter username:");
        String name = in.nextLine();
        System.out.print("Enter password:");
        String pass = in.nextLine();

        library.writeFile(name, pass);
        System.out.println("Registration successful\nPlease log in.");
    }
}
