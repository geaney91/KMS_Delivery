/**
 * Created by 09006819 on 05/11/2015.
 */
public interface CurrencyType {
    public void accept(CurrencyTypeVisitor currencyTypeVisitor, double d);
}
