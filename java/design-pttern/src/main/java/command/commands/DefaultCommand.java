package command.commands;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/29 2:15
 * @description:
 */
public class DefaultCommand implements Command {
    @Override
    public void execute() {
        System.out.println("暂未实现");
    }

    @Override
    public void undo() {
        System.out.println("暂未实现");
    }
}
