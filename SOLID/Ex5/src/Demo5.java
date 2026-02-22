
public class Demo5 {

    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();
        Exporter xml = new XmlExporter();

        System.out.println("PDF: " + describe(pdf.export(req)));
        System.out.println("CSV: " + describe(csv.export(req)));
        System.out.println("JSON: " + describe(json.export(req)));
        System.out.println("XML: " + describe(xml.export(req)));   

        System.out.println();
        ExportRequest noBody = new ExportRequest("Empty", null);
        System.out.println("PDF: " + describe(pdf.export(noBody)));
        System.out.println("CSV: " + describe(csv.export(noBody)));
        System.out.println("JSON: " + describe(json.export(noBody)));
        System.out.println("XML: " + describe(xml.export(noBody)));     
    }

    private static String describe(ExportResult r) {
        return "OK type=" + r.contentType + " bytes=" + r.bytes.length;
    }
}
