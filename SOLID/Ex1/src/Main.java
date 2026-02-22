import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        FakeDb db = new FakeDb();
        StudentValidator validator = new StudentValidator(Set.of("CSE", "AI", "SWE"));
        OnboardingService svc = new OnboardingService(db, new OnboardingPrinter(), validator);

        svc.registerFromRawInput("name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE");
        System.out.println();
        svc.registerFromRawInput("name=;email=not-an-email;phone=abc;program=XYZ");

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}
