/**
 * Created by 09006819 on 05/11/2015.
 */
public class Euro implements CurrencyType {

    @Override
    public void accept(CurrencyTypeVisitor currencyTypeVisitor, double d)
    {
        currencyTypeVisitor.calculate(this, d);
    }
}
