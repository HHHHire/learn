package command;

import command.commands.LightOffCommand;
import command.commands.LightOnCommand;
import command.recelver.Light;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/29 2:22
 * @description:
 */
public class CommandTest {
    public static void main(String[] args) {
        Light livingRoom = new Light("客厅");
        Light bedRoom = new Light("卧室");
        RemoteControl remoteControl = new RemoteControl();
        LightOnCommand lightOnCommand = new LightOnCommand(livingRoom);
        LightOffCommand lightOffCommand = new LightOffCommand(livingRoom);
        remoteControl.setCommand(0, lightOnCommand, lightOffCommand);
        remoteControl.buttonOnPushed(0);
        remoteControl.buttonOffPushed(0);
        remoteControl.undoButton();
    }
}
