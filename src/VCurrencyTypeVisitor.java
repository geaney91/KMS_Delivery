public interface VCurrencyTypeVisitor {

    void calculate(VEuro euro, double d);
    void calculate(VSterling sterling, double d);
}
