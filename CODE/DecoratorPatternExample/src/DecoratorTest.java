public class DecoratorTest {

    public static void main(String[] args) {

        Notifier n =
            new SlackNotifierDecorator(
                new SMSNotifierDecorator(
                    new EmailNotifier()));

        n.send();
    }
}