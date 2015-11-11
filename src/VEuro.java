/**
 * Created by 09006819 on 05/11/2015.
 */
public class VEuro implements VCurrencyType {

    @Override
    public void accept(VCurrencyTypeVisitor VCurrencyTypeVisitor, double d)
    {
        VCurrencyTypeVisitor.calculate(this, d);
    }
}
