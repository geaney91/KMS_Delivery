public abstract class DecoratorPost implements Post {
    protected Post decoratedPost = null;
    protected double price = 0.0;

    public DecoratorPost(Post decoratedPost)
    {
        this.decoratedPost = decoratedPost;
    }

    public double GetPrice()    //Method to calculate price as each new element is added to the post.
    {
        return price + decoratedPost.GetPrice();
    }


}
