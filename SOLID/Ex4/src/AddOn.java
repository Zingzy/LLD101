
public enum AddOn implements PricingComponent {
    MESS(1000.0), LAUNDRY(500.0), GYM(300.0);

    private final double fee;

    AddOn(double fee) {
        this.fee = fee;
    }

    @Override
    public Money monthly() {
        return new Money(fee);
    }

    @Override
    public Money deposit() {
        return new Money(0);
    }
}
