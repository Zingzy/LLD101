import java.nio.charset.StandardCharsets;

public class XmlExporter extends Exporter {

    @Override
    protected ExportResult doExport(ExportRequest req) {
        String xml = "<report><title>" + escape(req.title) + "</title><body>" + escape(req.body) + "</body></report>";
        return new ExportResult("application/xml", xml.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
