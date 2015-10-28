public abstract class PostDecorator extends Post {
    protected Post decoratedPost = null;
    protected double price = 0.0;

    public PostDecorator(Post decoratedPost)
    {
        this.decoratedPost = decoratedPost;
    }

    public void create()
    {
        decoratedPost.create();
    }

    public double GetPrice()
    {
        return price + decoratedPost.GetPrice();
    }


}
