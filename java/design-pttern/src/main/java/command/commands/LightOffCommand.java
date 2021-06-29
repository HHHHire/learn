package command.commands;

import command.recelver.Light;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/29 2:13
 * @description:
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.offLight();
    }

    @Override
    public void undo() {
        light.onLight();
    }
}
