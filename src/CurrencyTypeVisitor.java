
public interface CurrencyTypeVisitor {
    //public void calculate(Money currency);
    public void calculate(Euro euro, double d);
    public void calculate(Sterling sterling, double d);
}
