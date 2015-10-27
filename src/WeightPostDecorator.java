import java.util.*;
public class WeightPostDecorator extends PostDecorator {

    private double weight = 0.0;
   public WeightPostDecorator(Post post1, double w) {
       super(post1);
       this.weight = w;
   }

    @Override
    public void create(){
        decoratedPost.create();
        //setPostWeight(/*decoratedPost*/this.weight);
    }

    //public void setPostWeight(/*Post decoratedPost*/double w){
       // weight = w;
    //}

    public String toString()
    {
        String s = String.valueOf(this.weight);
        return s;
    }
}
