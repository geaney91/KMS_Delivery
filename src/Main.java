import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[]args) throws IOException
    {

        PostFactory postFactory = new PostFactory();
        Scanner in = new Scanner(System.in);


        if (Login() == true) {
            System.out.println("Logged In");

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
            //weightedPost.create();

            System.out.print("Enter delivery type: (Standard, Express, Super)");
            String delivery  = in.nextLine();

            Post deliveryPost = new DeliveryPostDecorator(weightedPost, delivery);

            System.out.println("Postage Cost: " + deliveryPost.GetPrice());

            Context context = new Context(new DeliveryDate());
            System.out.println(context.executeStrategy(delivery, c));

            Subject subject = new Subject();
            new ObserverPost(subject);

            //subject.setState(true);


            // vb.compareTo(array[x])

            //post1.create();
            // System.out.print("You have selected " + post + " of " + weightedPost.getWeight() + "kg");
        }
        else
            System.out.println("No Log in today");

    }

    public static boolean Login() throws IOException
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter username:");
        String name = in.nextLine();
        System.out.print("Enter password:");
        String pass = in.nextLine();

        BufferedReader br = new BufferedReader(new FileReader("./src/Users.txt"));
        String line = "";
        int count = 0;
        boolean check = true;
        while ((line = br.readLine()) != null)
        {
            String [] details = line.split(",");
            if (details[0].equals(name) && details[1].equals(pass))
            {
                check = true;
                break;
            }
            else
                check = false;
        }

        return check;

    }

}
