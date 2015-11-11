public class VCurrencyTypeDisplayVisitor implements VCurrencyTypeVisitor {

    @Override
    public void calculate(VEuro euro, double d) //Calculate method for euro
    {
        System.out.print("Postage Cost in euro: ");
        System.out.printf("%.2f", d);
        System.out.println();
    }

    @Override
    public void calculate(VSterling sterling, double d) //Calculate method for sterling
    {
        System.out.print("Postage Cost in sterling: ");
        System.out.printf("%.2f", (d * 0.71));
        System.out.println();
    }
}
