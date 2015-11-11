public class DecoratorWeightPost extends DecoratorPost {

   public DecoratorWeightPost(Post countryPost, double w)
   {
       super(countryPost);

       //The addition to the price is whatever the weight is divided by 2.
       this.price = w/2;
   }
}
