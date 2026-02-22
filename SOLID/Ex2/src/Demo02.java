
import java.util.*;

public class Demo02 {

    public static void main(String[] args) {
        CafeteriaSystem sys = new CafeteriaSystem(new FileStore());
        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> studentOrder = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );
        sys.checkout(new FixedRateTaxPolicy(5.0), new StudentDiscountPolicy(), studentOrder);

        List<OrderLine> staffSmall = List.of(
                new OrderLine("C1", 2)
        );
        sys.checkout(new FixedRateTaxPolicy(2.0), new StaffDiscountPolicy(), staffSmall);

        System.out.println("\n=== Staff Order (large) ===");
        List<OrderLine> staffLarge = List.of(
                new OrderLine("M1", 1),
                new OrderLine("C1", 1),
                new OrderLine("S1", 2)
        );
        sys.checkout(new FixedRateTaxPolicy(2.0), new StaffDiscountPolicy(), staffLarge);

        System.out.println("\n=== Guest Order ===");
        List<OrderLine> guestOrder = List.of(
                new OrderLine("S1", 3)
        );
        sys.checkout(new FixedRateTaxPolicy(8.0), new NoDiscountPolicy(), guestOrder);
    }
}
