
public class CountryPostDecorator extends PostDecorator {

    public CountryPostDecorator(Post post1, String country){
        super(post1);
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



    @Override
    public void create(){
        decoratedPost.create();

    }


    //public String toString()
    //{
        //return this.country + decoratedPost;

    //}
}
