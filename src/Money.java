/**
 * Created by 09006819 on 05/11/2015.
 */
public class Money implements CurrencyType{

    CurrencyType[] cash;

    public Money(){
        cash = new CurrencyType[] {new Euro(), new Sterling()};
    }

    @Override
    public void accept(CurrencyTypeVisitor currencyTypeVisitor, double d)
    {
        for (int i = 0; i < cash.length; i++)
        {
            cash[i].accept(currencyTypeVisitor, d);
        }
        //currencyTypeVisitor.calculate(this, d);
    }
}
