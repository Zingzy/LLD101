public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public double discountAmount(double subtotal, int lineCount) { return 0.0; }
}
