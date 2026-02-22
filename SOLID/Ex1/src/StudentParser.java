import java.util.LinkedHashMap;
import java.util.Map;

public class StudentParser {
    public Map<String, String> parse(String raw) {

        Map<String, String> kv = new LinkedHashMap<>();

        for (String part : raw.split(";")) {
            String[] t = part.split("=", 2);
            if (t.length == 2) kv.put(t[0].trim(), t[1].trim());
        }
        return kv;
    }
}
