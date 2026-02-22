
import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final InvoiceFormatter formatter = new InvoiceFormatter();
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store) {
        this.store = store;
    }

    public void addToMenu(MenuItem item) {
        menu.put(item.id, item);
    }

    public void checkout(TaxPolicy taxPolicy, DiscountPolicy discountPolicy, List<OrderLine> orderLines) {
        String invId = "INV-" + (++invoiceSeq);

        List<InvoiceLine> lines = new ArrayList<>();
        double subtotal = 0.0;

        for (OrderLine ol : orderLines) {
            MenuItem item = menu.get(ol.itemId);
            double lineTotal = item.price * ol.qty;
            subtotal += lineTotal;
            lines.add(new InvoiceLine(item.name, ol.qty, lineTotal));
        }

        double taxPct = taxPolicy.taxPercent();
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.discountAmount(subtotal, orderLines.size());
        double total = subtotal + tax - discount;

        Invoice invoice = new Invoice(invId, lines, subtotal, taxPct, tax, discount, total);
        String formatted = formatter.format(invoice);
        System.out.print(formatted);

        store.save(invId, formatted);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
