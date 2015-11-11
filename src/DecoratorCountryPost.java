public class DecoratorCountryPost extends DecoratorPost {

    public DecoratorCountryPost(Post post1, String country){
        super(post1);

        //Sets price depending on destination chosen.
        if (country.equalsIgnoreCase("ireland"))
        {
            this.price = 1;
        }
        else if (country.equalsIgnoreCase("uk"))
        {
            this.price = 2;
        }
        else if (country.equalsIgnoreCase("europe"))
        {
            this.price = 3;
        }
    }
}
