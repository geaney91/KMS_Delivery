public class VSterling implements VCurrencyType {

    @Override
    public void accept(VCurrencyTypeVisitor vCurrencyTypeVisitor, double d)
    {
        vCurrencyTypeVisitor.calculate(this, d);
    }
}
