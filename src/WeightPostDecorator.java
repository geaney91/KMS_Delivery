import java.util.*;
public class WeightPostDecorator extends PostDecorator {

   public WeightPostDecorator(Post countryPost, double w) {
       super(countryPost);
       this.price = w/2;
   }

    @Override
    public void create(){
        decoratedPost.create();

        //setPostWeight(/*decoratedPost*/this.weight);
    }

    //public void setPostWeight(/*Post decoratedPost*/double w){
       // weight = w;
    //}

    //public String toString()
    //{
     //   String s = String.valueOf(this.weight);
     //   return s;
    //}
}
