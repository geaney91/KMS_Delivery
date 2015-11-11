public class VMoney implements VCurrencyType {

    VCurrencyType[] cash;

    public VMoney(){
        cash = new VCurrencyType[] {new VEuro(), new VSterling()};
    }

    @Override
    public void accept(VCurrencyTypeVisitor vCurrencyTypeVisitor, double d)
    {
        for (int i = 0; i < cash.length; i++)   //Iterates through array 'cash' to display output of each object on screen.
        {
            cash[i].accept(vCurrencyTypeVisitor, d);
        }
    }
}
