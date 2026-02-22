public class RuleInput {
    public final double minCgr;
    public final int minAttendance;
    public final int minCredits;

    public RuleInput() {
        this(8.0, 75, 20);
    }

    public RuleInput(double minCgr, int minAttendance, int minCredits) {
        this.minCgr = minCgr;
        this.minAttendance = minAttendance;
        this.minCredits = minCredits;
    }
}
