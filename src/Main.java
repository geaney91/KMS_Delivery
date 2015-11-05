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
        lib.readLoginDetails(); //Reads in the login details from Users.txt into an arrayList.
        lib.readTrackingDetails();  //Reads in the tracking details from PostTracking.txt into an arrayList.
        boolean check = log.LogUserIn(in, lib);    //Calls method to check if login was successful and returns true if it was.
        while (check == false)  //Enters this loop if user enters incorrect/unknown login details.
        {
            System.out.println("User not found. Register or Log: ");
            String start = in.nextLine();
            if (start.equalsIgnoreCase("log"))
                check = log.LogUserIn(in, lib);    //Recalls the login method to allow user to try to log in again.
            else if (start.equalsIgnoreCase("register"))
            {
                log.Register(in, lib); //Calls the register method to allow a user to register their details.
                check = log.LogUserIn(in, lib);    //The user must log in after registering.
            }
            else
                System.exit(0);
        }
        System.out.println("Logged In");    //Once log in details are correct and recognised the user can enter the application.
        Options(in, lib, log);
    }

    public static void Threader(Subject subject, Date logIn)
    {
        DeliveryStatusCheck ds1 = new DeliveryStatusCheck(subject,logIn);

        //t1.start();

        //ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);


        //schedule to run after sometime
        //System.out.println("Current Time = "+new Date());
        //change for loop to if statement to check if a change occurs if not recheck track
        while (true) {


            //Thread t1 = new Thread(ds1);
            ds1.start();
            //scheduledThreadPool.schedule(t1, 10, TimeUnit.SECONDS);
        }
    }

    //Called on successful login.
    public static void Options(Scanner in, Library lib, Login log) throws IOException {

        Subject subject = new Subject();
        Date logIn = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.format(logIn);

        DeliveryStatusCheck ds1 = new DeliveryStatusCheck(subject,logIn);
        ds1.start();
        //Threader(subject, logIn);

        Logger logger = new Logger();
        Dispatcher.register(logger);
        ConcreteFramework cf = new ConcreteFramework();
        cf.event();

        System.out.println("\nWould you like to create post to send or track an item you have previously created: (create or track)");
        String choice = in.nextLine();
        if (choice.toLowerCase().equals("create"))  //Calls CreatePost method.
        {
            CreatePost(in, lib, log);
        }
        else if (choice.toLowerCase().equals("track"))  //Updates current date in Subject to display post delivery status to user.
        {
            new ObserverPost(subject);
            subject.setState(logIn);
        }

    }

    //Called when user enters 'create'
    public static void CreatePost(Scanner in, Library library, Login log) throws IOException
    {
        PostFactory postFactory = new PostFactory();
        System.out.print("Enter type of Post:(Letter, Parcel, Package) ");
        String post = in.nextLine();
        if (post.equalsIgnoreCase("letter") || post.equalsIgnoreCase("parcel") || post.equalsIgnoreCase("package"))
        {
            Post post1 = postFactory.getPost(post); //Sends string to post factory which creates an object of that type
            System.out.print("Enter destination to deliver to: (Ireland, UK, Europe) ");
            String c = in.nextLine();
            if (c.equalsIgnoreCase("ireland") || c.equalsIgnoreCase("uk") || c.equalsIgnoreCase("europe"))
            {
                Post countryPost = new CountryPostDecorator(post1, c);  //Decorates the object with chosen destination. Price is updated.
                System.out.print("Enter weight in kg: (e.g 1.2) ");
                String ws = in.nextLine();
                if (ws.matches("-?\\d+(\\.\\d+)?") || ws.matches("\\d+"))
                {
                    double w = Double.parseDouble(ws);
                    Post weightedPost = new WeightPostDecorator(countryPost, w);    //Decorates object with weight. Price is updated.

                    System.out.print("Enter delivery type: (Standard, Express, Super)");
                    String delivery  = in.nextLine();
                    if (delivery.equalsIgnoreCase("standard") || delivery.equalsIgnoreCase("express") || delivery.equalsIgnoreCase("super"))
                    {
                        Post deliveryPost = new DeliveryPostDecorator(weightedPost, delivery);  //Decorates object with delivery type. Price is updated.

                        //System.out.println("Postage Cost: " + deliveryPost.GetPrice()); //Gets the total postage cost.
                        CurrencyType currency = new Money();
                        currency.accept(new CurrencyTypeDisplayVisitor(), deliveryPost.GetPrice());

                        System.out.println("Would you like to send this post: (y, n)");
                        String send = in.nextLine();
                        if (send.equalsIgnoreCase("y"))
                        {
                            Context context = new Context(new DeliveryDate());
                            System.out.println("Delivery date: " + context.executeStrategy(delivery, c));
                            String date = context.executeStrategy(delivery, c);
                            library.writeFile(log.getCurrentUser(), log.getCurrentId(), date);  //Sends details to writeFile method to be written to PostTracking file.
                            System.out.println("Post sent!");
                            System.exit(0);
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

}
