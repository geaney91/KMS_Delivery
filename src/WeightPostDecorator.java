import java.util.*;
public class WeightPostDecorator extends PostDecorator {

    private double weight = 0.0;
   public WeightPostDecorator(Post decoratedPost, double w){
       super(decoratedPost);
       this.weight = w;
   }

    public double getWeight()
    {
        return this.weight;
    }

    @Override
    public void create(){
        decoratedPost.create();
        setPostWeight(/*decoratedPost*/this.weight);
    }

    public void setPostWeight(/*Post decoratedPost*/double w){
        System.out.print(w);
    }
}
