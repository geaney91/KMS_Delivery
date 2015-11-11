/**
 * Created by 09006819 on 05/11/2015.
 */
public class VMoney implements VCurrencyType {

    VCurrencyType[] cash;

    public VMoney(){
        cash = new VCurrencyType[] {new VEuro(), new VSterling()};
    }

    @Override
    public void accept(VCurrencyTypeVisitor VCurrencyTypeVisitor, double d)
    {
        for (int i = 0; i < cash.length; i++)
        {
            cash[i].accept(VCurrencyTypeVisitor, d);
        }
        //VCurrencyTypeVisitor.calculate(this, d);
    }
}
