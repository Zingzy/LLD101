
import java.util.*;

public class HostelFeeCalculator {

    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
    }

    public FeeResult calculate(List<PricingComponent> components) {
        Money monthly = new Money(0);
        Money deposit = new Money(0);
        for (PricingComponent c : components) {
            monthly = monthly.plus(c.monthly());
            deposit = deposit.plus(c.deposit());
        }
        return new FeeResult(monthly, deposit);
    }

    public void process(BookingRequest req) {
        List<PricingComponent> components = BookingComponentFactory.build(req);
        FeeResult result = calculate(components);
        ReceiptPrinter.print(req, result.monthly, result.deposit);
        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, result.monthly, result.deposit);
    }
}
