package command.commands;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/29 2:06
 * @description:
 */
public interface Command {
    void execute();

    /**
     * 撤销操作
     */
    void undo();
}
