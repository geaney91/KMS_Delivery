/**
 * Created by 09006819 on 05/11/2015.
 */
public class VCurrencyTypeDisplayVisitor implements VCurrencyTypeVisitor {

    @Override
    public void calculate(VEuro VEuro, double d)
    {
        System.out.println("Postage Cost in VEuro: " + d);
    }

    @Override
    public void calculate(VSterling VSterling, double d)
    {
        System.out.println("Postage Cost in VSterling: " + (d * 0.71));
    }
}
