
public class SingleRoom implements PricingComponent {

    @Override
    public Money monthly() {
        return new Money(14000.0);
    }

    @Override
    public Money deposit() {
        return new Money(5000.0);
    }
}
