
public class Euro implements CurrencyType {

    @Override
    public void accept(CurrencyTypeVisitor currencyTypeVisitor, double d)
    {
        currencyTypeVisitor.calculate(this, d);
    }
}
