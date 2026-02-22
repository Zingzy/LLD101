
public class LateFee implements PricingComponent {

    private final double amount;

    public LateFee(double amount) {
        this.amount = amount;
    }

    @Override
    public Money monthly() {
        return new Money(amount);
    }

    @Override
    public Money deposit() {
        return new Money(0);
    }
}
