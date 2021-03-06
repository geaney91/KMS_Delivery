import com.sun.istack.internal.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class Main {

    private static int count = 0;
    public static void main(String[]args) throws IOException
    {
        Login log = new Login();
        Scanner in = new Scanner(System.in);
        Library lib = new Library();
        lib.readLoginDetails(); //Reads in the login details from Users.txt into an arrayList.
        lib.readTrackingDetails();  //Reads in the tracking details from PostTracking.txt into an arrayList.
        boolean check = log.LogUserIn(in, lib);    //Calls method to check if login was successful and returns true if it was.
        while (!check)  //Enters this loop if user enters incorrect/unknown login details.
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


    //Called on successful login.
    public static void Options(Scanner in, Library lib, Login log) throws IOException {

        ObserverSubject observerSubject = new ObserverSubject();
        Date logIn = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.format(logIn);

        //Starts thread for constant checking of delivery status.
        DeliveryStatusCheckThread ds1 = new DeliveryStatusCheckThread();
        ds1.start();

        if (count == 0)     //Ensures that the log file is only written to when the user logs in initially.
        {
            ILogger logger = new ILogger();
            IDispatcher.register(logger);   //Registers logging interceptor with the dispatcher.
            IConcreteFramework cf = new IConcreteFramework();
            cf.event(); //Event is triggered.
            count++;
        }

        System.out.println("\nWould you like to create post to send or track an item you have previously created: (create or track)");
        String choice = in.nextLine();
        if (choice.toLowerCase().equals("create"))  //Calls CreatePost method.
        {
            CreatePost(in, lib, log);
        }
        else if (choice.toLowerCase().equals("track"))  //User can view current delivery status of their post.
        {
            new ObserverPost(observerSubject);  //Gets delivery status of all post using observer design pattern.
            observerSubject.setState(logIn);
            int check = ReturnToMenu(in);
            if (check == 0) //If user has chosen to return to the menu.
                Options(in, lib, log);
            else if (check == 1)    //If user has chosen to exit.
                System.exit(0);
            else    //If invalid entry.
            {
                System.out.println("Invalid entry. System will exit.");
                System.exit(0);
            }
        }
        else
        {
            System.out.println("Invalid entry");
            Options(in, lib, log);
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
            Post post1 = postFactory.getPost(post); //Sends string to post factory which creates an object of the correct type.
            System.out.print("Enter destination to deliver to: (Ireland, UK, Europe) ");
            String c = in.nextLine();
            if (c.equalsIgnoreCase("ireland") || c.equalsIgnoreCase("uk") || c.equalsIgnoreCase("europe"))
            {
                Post countryPost = new DecoratorCountryPost(post1, c);  //Decorates the object with chosen destination. Price is updated.
                System.out.print("Enter weight in kg: (e.g 1.2) ");
                String ws = in.nextLine();
                if (ws.matches("-?\\d+(\\.\\d+)?") || ws.matches("\\d+"))
                {
                    double w = Double.parseDouble(ws);
                    Post weightedPost = new DecoratorWeightPost(countryPost, w);    //Decorates object with weight. Price is updated.

                    System.out.print("Enter delivery type: (Standard, Express, Super)");
                    String delivery  = in.nextLine();
                    if (delivery.equalsIgnoreCase("standard") || delivery.equalsIgnoreCase("express") || delivery.equalsIgnoreCase("super"))
                    {
                        Post deliveryPost = new DecoratorDeliveryPost(weightedPost, delivery);  //Decorates object with delivery type. Price is updated.
                        VCurrencyType currency = new VMoney();
                        currency.accept(new VCurrencyTypeDisplayVisitor(), deliveryPost.GetPrice());

                        boolean done = true;
                        while (done) {
                            System.out.print("Would you like to send this post: (y, n)");
                            String send = in.nextLine();
                            if (send.equalsIgnoreCase("y")) //Chosen to send post.
                            {
                                if (delivery.equalsIgnoreCase("standard")) {
                                    Display(library, log, c, in, new StrategyDeliveryDateStandard());   //Calls method with correct strategy type.
                                } else if (delivery.equalsIgnoreCase("express")) {
                                    Display(library, log, c, in, new StrategyDeliveryDateExpress());    //Calls method with correct strategy type.
                                } else if (delivery.equalsIgnoreCase("super")) {
                                    Display(library, log, c, in, new StrategyDeliveryDateSuper());  //Calls method with correct strategy type.
                                }
                                done = false;
                            } else if (send.equalsIgnoreCase("n"))    //Chosen not to send post.
                            {
                                System.out.println("Post not sent");
                                int check = ReturnToMenu(in);
                                if (check == 0)
                                    Options(in, library, log);
                                else if (check == 1)
                                    System.exit(0);
                                else {
                                    System.out.println("Invalid entry. System will exit.");
                                    System.exit(0);
                                }
                                done = false;
                            } else {
                                System.out.println("Please enter a valid input i.e 'y' or 'n'");
                                done = true;
                            }
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

    //Uses strategy to get the delivery date and write the details to file.
    public static void Display(Library library, Login log, String c, Scanner in, Strategy s) throws IOException
    {
        StrategyContext strategyContext = new StrategyContext(s);   //Creates new strategy context of the required type.
        String date = strategyContext.executeStrategy(c);   //Uses strategy to get the delivery date.
        System.out.println("Delivery date: " + date);
        library.writeFile(log.getCurrentUser(), log.getCurrentId(), date);  //Sends details to writeFile method to be written to PostTracking file.
        System.out.println("Post sent!");
        int check = ReturnToMenu(in);
        if (check == 0)
            Options(in, library, log);
        else if (check == 1)
        {
            System.out.println("System will exit.");
            System.exit(0);
        }
        else
        {
            System.out.println("Invalid Entry. System will exit.");
            System.exit(0);
        }

    }

    public static int ReturnToMenu(Scanner in)
    {

        int check;
        System.out.println("Return to menu: (y or n)");
        String result = in.nextLine();
        if (result.equalsIgnoreCase("y"))
            check = 0;
        else if (result.equalsIgnoreCase("n"))
            check = 1;
        else
            check = 2;

        return check;
    }
}
