/**
 * Created by 09006819 on 05/11/2015.
 */
public interface CurrencyTypeVisitor {
    //public void calculate(Money currency);
    public void calculate(Euro euro, double d);
    public void calculate(Sterling sterling, double d);
}
