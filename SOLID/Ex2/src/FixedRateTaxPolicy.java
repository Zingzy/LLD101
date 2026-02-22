public class FixedRateTaxPolicy implements TaxPolicy {
    private final double rate;

    public FixedRateTaxPolicy(double rate) { this.rate = rate; }

    @Override
    public double taxPercent() { return rate; }
}
