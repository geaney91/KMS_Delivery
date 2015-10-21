import java.util.*;
public class Main {
    public static void main(String[]args){

        PostFactory postFactory = new PostFactory();
        Scanner in = new Scanner(System.in);

        System.out.print("Enter type of Post: ");
        String post = in.nextLine();

        //double w = 2.3;

        Post post1 = postFactory.getPost(post);
       // post1.create();

        System.out.print("Enter weight: ");
        double w = in.nextDouble();
        in.nextLine();

        Post weightedPost = new WeightPostDecorator(post1, w);

        System.out.print("Enter country to deliver to: ");
        String c = in.nextLine();
        //weightedPost.create();

        Post countryPost = new CountryPostDecorator(weightedPost, c);
        countryPost.create();


       //post1.create();

        //System.out.print("You have selected " + post + " of " + weightedPost.getWeight() + "kg");

    }
}
