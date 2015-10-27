/**
 * Created by 12154008 on 20/10/2015.
 */
public class CountryPostDecorator extends PostDecorator {

    private String country = "";
    public CountryPostDecorator(Post weightedPost, String country){
        super(weightedPost);
        this.country = country;
    }



    @Override
    public void create(){
        decoratedPost.create();
    }


    public String toString()
    {
        return this.country + decoratedPost;

    }
}
