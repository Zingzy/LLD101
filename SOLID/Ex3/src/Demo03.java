import java.util.List;

public class Demo03 {
    public static void main(String[] args) {
        RuleInput config = new RuleInput();
        List<EligibilityRule> rules = List.of(
                new DisciplinaryFlagRule(),
                new CgrRule(config.minCgr),
                new AttendanceRule(config.minAttendance),
                new CreditsRule(config.minCredits)
        );
        EligibilityEngine engine = new EligibilityEngine(rules, new FakeEligibilityStore());

        StudentProfile ryan = new StudentProfile("24BCS1001", "Ryan", 8.10, 72, 18, LegacyFlags.NONE);
        engine.runAndPrint(ryan);

        System.out.println();
        StudentProfile drake = new StudentProfile("24BCS1002", "Drake", 8.90, 80, 22, LegacyFlags.NONE);
        engine.runAndPrint(drake);

        System.out.println();
        StudentProfile spider = new StudentProfile("24BCS1003", "Spider", 9.00, 85, 24, LegacyFlags.WARNING);
        engine.runAndPrint(spider);

        System.out.println();
        StudentProfile klerk = new StudentProfile("24BCS1004", "Klerk", 7.50, 78, 21, LegacyFlags.NONE);
        engine.runAndPrint(klerk);
    }
}
