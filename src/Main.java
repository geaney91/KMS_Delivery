import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[]args) throws IOException
    {
        Login log = new Login();
        Scanner in = new Scanner(System.in);
        Library lib = new Library();
        lib.readLoginDetails();
        lib.readTrackingDetails();
        boolean check = log.LogUserIn();
        if (check == true)
        {
            System.out.println("Logged In");
            Details();
        }
        else
        {
            while (check == false)
            {
                System.out.println("User not found. Register or Log: ");
                String start = in.nextLine();
                if(start.equalsIgnoreCase("log"))
                    check = log.LogUserIn();
                else if(start.equalsIgnoreCase("register"))
                {
                    log.Register();
                    check = log.LogUserIn();
                    //Details();
                }
                else
                    System.exit(0);
            }
            Details();
        }
    }

    public static void Details() throws IOException {
        Login log = new Login();
        Library library = new Library();
        Subject subject = new Subject();
        Date logIn = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.format(logIn);

        new ObserverPost(subject);
        subject.setState(logIn);

        PostFactory postFactory = new PostFactory();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter type of Post:(Letter, Parcel, Package) ");
        String post = in.nextLine();
        Post post1 = postFactory.getPost(post);


        System.out.print("Enter destination to deliver to: (Ireland, UK, Europe) ");
        String c = in.nextLine();
        Post countryPost = new CountryPostDecorator(post1, c);

        System.out.print("Enter weight in kg: ");
        double w = in.nextDouble();
        in.nextLine();
        Post weightedPost = new WeightPostDecorator(countryPost, w);

        System.out.print("Enter delivery type: (Standard, Express, Super)");
        String delivery  = in.nextLine();
        Post deliveryPost = new DeliveryPostDecorator(weightedPost, delivery);

        System.out.println("Postage Cost: " + deliveryPost.GetPrice());

        Context context = new Context(new DeliveryDate());
        System.out.println(context.executeStrategy(delivery, c));
        String date = context.executeStrategy(delivery, c);



        library.writeFile(log.getCurrentUser(), log.getCurrentId(), date);

    }

    /*public static boolean Login() throws IOException
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter username:");
        String name = in.nextLine();
        System.out.print("Enter password:");
        String pass = in.nextLine();
        Library library = new Library();
        boolean check = true;
        ArrayList<String> localList = library.getLoginList();
        for (int i = 0; i < localList.size(); i++)
        {
            String [] details = localList.get(i).split(",");
            if (details[0].equals(name) && details[1].equals(pass))
            {
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
        System.out.println("Registration successful");
    }*/

}
