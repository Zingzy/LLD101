public class PushSender extends NotificationSender {
    public PushSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        System.out.println("PUSH -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("push sent");
    }
}
