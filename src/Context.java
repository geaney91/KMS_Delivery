import java.util.Date;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public String executeStrategy(String country){
        return strategy.DisplayDate(country);
    }
}