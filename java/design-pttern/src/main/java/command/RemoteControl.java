package command;

import command.commands.Command;
import command.commands.DefaultCommand;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/29 2:14
 * @description:
 */
public class RemoteControl {
    private Command[] commandOns;
    private Command[] commandOffs;
    private Command undoCommand;

    public RemoteControl() {
        commandOns = new Command[7];
        commandOffs = new Command[7];

        for (int i = 0; i < 7; i++) {
            commandOns[i] = new DefaultCommand();
            commandOffs[i] = new DefaultCommand();
        }
        undoCommand = new DefaultCommand();
    }

    public void setCommand(int index, Command commandOn, Command commandOff) {
        commandOns[index] = commandOn;
        commandOffs[index] = commandOff;
    }

    public void buttonOnPushed(int index) {
        commandOns[index].execute();
        undoCommand = commandOns[index];
    }

    public void buttonOffPushed(int index) {
        commandOffs[index].execute();
        undoCommand = commandOffs[index];
    }

    public void undoButton() {
        undoCommand.undo();
    }
}
