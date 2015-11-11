public class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy){
        this.strategy = strategy;
    }

    public String executeStrategy(String country){
        return strategy.DisplayDate(country);
    }
}