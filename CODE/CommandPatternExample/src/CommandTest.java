public class CommandTest {

    public static void main(String[] args) {

        Light light =
            new Light();

        RemoteControl rc =
            new RemoteControl();

        rc.setCommand(
            new LightOnCommand(light));
        rc.pressButton();

        rc.setCommand(
            new LightOffCommand(light));
        rc.pressButton();
    }
}