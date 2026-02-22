import java.util.*;

public class BookingComponentFactory {

    public static List<PricingComponent> build(BookingRequest req) {
        List<PricingComponent> components = new ArrayList<>();
        components.add(roomFor(req.roomType));
        components.addAll(req.addOns);
        return components;
    }

    private static PricingComponent roomFor(int roomType) {
        return switch (roomType) {
            case LegacyRoomTypes.SINGLE -> new SingleRoom();
            case LegacyRoomTypes.DOUBLE -> new DoubleRoom();
            case LegacyRoomTypes.TRIPLE -> new TripleRoom();
            default -> new DeluxeRoom();
        };
    }
}
