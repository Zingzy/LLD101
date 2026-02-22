import java.util.List;
import java.util.Map;

public class OnboardingService {
    private final StudentRepository repo;
    private final StudentParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repo, OnboardingPrinter printer) {
        this.repo = repo;
        this.printer = printer;
        this.parser = new StudentParser();
        this.validator = new StudentValidator();
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        Map<String, String> fields = parser.parse(raw);
        List<String> errors = validator.validate(fields);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id,
                fields.get("name"), fields.get("email"),
                fields.get("phone"), fields.get("program"));

        repo.save(rec);
        printer.printSuccess(rec, repo.count());
    }
}
