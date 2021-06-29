package command.commands;

import command.recelver.Light;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/29 2:05
 * @description:
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.onLight();
    }

    @Override
    public void undo() {
        light.offLight();
    }


}
