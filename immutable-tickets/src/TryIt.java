import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;
import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        IncidentTicket assigned  = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);

        System.out.println("\nAfter assign + escalate (new instances):");
        System.out.println("  original  : " + t);
        System.out.println("  assigned  : " + assigned);
        System.out.println("  escalated : " + escalated);

        // Demonstrate external mutation via leaked list reference
        List<String> tags = t.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nTag mutation succeeded (BAD)");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTag mutation blocked (GOOD): " + t.getTags());
        }
    }
}
