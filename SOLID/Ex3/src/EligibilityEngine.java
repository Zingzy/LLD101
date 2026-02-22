import java.util.*;

public class EligibilityEngine {
    private final List<EligibilityRule> rules;
    private final FakeEligibilityStore store;

    public EligibilityEngine(List<EligibilityRule> rules, FakeEligibilityStore store) {
        this.rules = rules;
        this.store = store;
    }

    public void runAndPrint(StudentProfile s) {
        EligibilityResult result = evaluate(s);
        new ReportPrinter().print(s, result);
        store.save(s.rollNo, result.status);
    }

    public EligibilityResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        for (EligibilityRule rule : rules) {
            rule.check(s).ifPresent(reasons::add);
        }
        return new EligibilityResult(reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE", reasons);
    }
}
