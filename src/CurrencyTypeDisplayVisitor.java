/**
 * Created by 09006819 on 05/11/2015.
 */
public class CurrencyTypeDisplayVisitor implements CurrencyTypeVisitor {

    @Override
    public void calculate(Euro euro, double d)
    {
        System.out.println("Postage Cost in Euro: " + d);
    }

    @Override
    public void calculate(Sterling sterling, double d)
    {
        System.out.println("Postage Cost in Sterling: " + (d * 0.71));
    }
}
