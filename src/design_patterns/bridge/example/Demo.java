package design_patterns.bridge.example;

import design_patterns.bridge.example.devices.Device;
import design_patterns.bridge.example.devices.Radio;
import design_patterns.bridge.example.devices.Tv;
import design_patterns.bridge.example.remotes.AdvancedRemote;
import design_patterns.bridge.example.remotes.BasicRemote;

public class Demo {
    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}
