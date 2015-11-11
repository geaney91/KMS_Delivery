/**
 * Created by 09006819 on 05/11/2015.
 */
public interface VCurrencyTypeVisitor {
    //public void calculate(VMoney currency);
    public void calculate(VEuro VEuro, double d);
    public void calculate(VSterling VSterling, double d);
}
