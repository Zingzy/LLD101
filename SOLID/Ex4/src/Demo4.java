
import java.util.*;

public class Demo4 {

    public static void main(String[] args) {
        FakeBookingRepo repo = new FakeBookingRepo();
        HostelFeeCalculator calc = new HostelFeeCalculator(repo);

        calc.process(new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS)));

        System.out.println();
        calc.process(new BookingRequest(LegacyRoomTypes.SINGLE, List.of(AddOn.GYM)));

        System.out.println();
        calc.process(new BookingRequest(LegacyRoomTypes.TRIPLE, List.of(AddOn.MESS, AddOn.LAUNDRY, AddOn.GYM)));

        System.out.println();
        calc.process(new BookingRequest(LegacyRoomTypes.DELUXE, List.of()));

        // Stretch goal: late fee added without touching the calculator
        System.out.println();
        BookingRequest lateReq = new BookingRequest(LegacyRoomTypes.SINGLE, List.of(AddOn.MESS));
        List<PricingComponent> components = BookingComponentFactory.build(lateReq);
        components.add(new LateFee(200.0));
        FeeResult result = calc.calculate(components);
        ReceiptPrinter.print(lateReq, result.monthly, result.deposit);
        repo.save("H-LATE", lateReq, result.monthly, result.deposit);
    }
}
