
public class DeluxeRoom implements PricingComponent {

    @Override
    public Money monthly() {
        return new Money(16000.0);
    }

    @Override
    public Money deposit() {
        return new Money(5000.0);
    }
}
