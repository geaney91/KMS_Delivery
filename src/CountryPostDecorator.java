/**
 * Created by 12154008 on 20/10/2015.
 */
public class CountryPostDecorator extends PostDecorator {

    private String country = "";
    public CountryPostDecorator(Post decoratedPost, String country){
        super(decoratedPost);
        this.country = country;
    }

    public String getCountry()
    {
        return this.country;
    }

    @Override
    public void create(){
        decoratedPost.create();
        setPostCountry(this.country);
    }

    public void setPostCountry(String c)
    {
        System.out.print(c);
    }
}
