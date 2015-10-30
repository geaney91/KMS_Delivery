import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

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
            Options();
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
                    //Options();
                }
                else
                    System.exit(0);
            }
            Options();
        }
    }

    public static void Options() throws IOException {
        Scanner in = new Scanner(System.in);
        Login log = new Login();
        Library library = new Library();
        Subject subject = new Subject();
        Date logIn = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.format(logIn);

        new ObserverPost(subject);
        subject.setState(logIn);

        System.out.println("\nWould you like to create post to send or track an item you have previously created: (create or track)");
        String choice = in.nextLine();
        if (choice.toLowerCase().equals("create"))
        {
            CreatePost(in, library, log);
        }
        else if (choice.toLowerCase().equals("track"))
        {

        }

    }

    public static void CreatePost(Scanner in, Library library, Login log) throws IOException
    {
        PostFactory postFactory = new PostFactory();
        System.out.print("Enter type of Post:(Letter, Parcel, Package) ");
        String post = in.nextLine();
        if (post.equalsIgnoreCase("letter") || post.equalsIgnoreCase("parcel") || post.equalsIgnoreCase("package"))
        {
            Post post1 = postFactory.getPost(post);
            System.out.print("Enter destination to deliver to: (Ireland, UK, Europe) ");
            String c = in.nextLine();
            if (c.equalsIgnoreCase("ireland") || c.equalsIgnoreCase("uk") || c.equalsIgnoreCase("europe"))
            {
                Post countryPost = new CountryPostDecorator(post1, c);
                System.out.print("Enter weight in kg: (e.g 1.2) ");
                String ws = in.nextLine();
                if (ws.matches("-?\\d+(\\.\\d+)?") || ws.matches("\\d+"))
                {
                    double w = Double.parseDouble(ws);
                    Post weightedPost = new WeightPostDecorator(countryPost, w);

                    System.out.print("Enter delivery type: (Standard, Express, Super)");
                    String delivery  = in.nextLine();
                    if (delivery.equalsIgnoreCase("standard") || delivery.equalsIgnoreCase("express") || delivery.equalsIgnoreCase("super"))
                    {
                        Post deliveryPost = new DeliveryPostDecorator(weightedPost, delivery);

                        System.out.println("Postage Cost: " + deliveryPost.GetPrice());

                        System.out.println("Would you like to send this post: (y, n)");
                        String send = in.nextLine();
                        if (send.equalsIgnoreCase("y"))
                        {
                            Context context = new Context(new DeliveryDate());
                            System.out.println(context.executeStrategy(delivery, c));
                            String date = context.executeStrategy(delivery, c);
                            library.writeFile(log.getCurrentUser(), log.getCurrentId(), date);
                            System.out.println("Post sent!");
                        }
                        else if (send.equalsIgnoreCase("n"))
                        {
                            System.out.println("Post not sent");
                            System.exit(0);
                        }
                        else
                        {
                            System.out.println("Please enter a valid input i.e 'y' or 'n'");
                            CreatePost(in, library, log);
                        }
                    }
                    else
                    {
                        System.out.println("Invalid delivery method. Please enter a valid input");
                        CreatePost(in, library, log);
                    }
                }
                else
                {
                    System.out.println("Invalid weight. Please enter a valid input");
                    CreatePost(in, library, log);
                }
            }
            else
            {
                System.out.println("Invalid destination. Please enter a valid input");
                CreatePost(in, library, log);
            }
        }
        else
        {
            System.out.println("Invalid type of post. Please enter a valid input");
            CreatePost(in, library, log);
        }

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
