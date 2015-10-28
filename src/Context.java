import java.util.Date;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public Date executeStrategy(String deliveryType, String country){
        return strategy.DisplayDate(deliveryType, country);
    }
}