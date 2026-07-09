public class SlackNotifierDecorator
        extends NotifierDecorator {

    public SlackNotifierDecorator(
            Notifier notifier) {
        super(notifier);
    }

    public void send() {
        notifier.send();
        System.out.println("Slack Message Sent");
    }
}