package com.one.netease.chain;

/**
 * @author diaokaibin@gmail.com on 2020-01-27.
 */
public abstract class BaseTask {


    //判断当前任务节点有没有能力执行
    private boolean isTask;

    public BaseTask(boolean isTask) {
        this.isTask = isTask;
    }

    // 执行下一个节点
    private BaseTask nextTask;

    // 添加下一个节点任务
    public void addNextTask(BaseTask nextTask) {
        this.nextTask = nextTask;
    }

    // 让子节点任务去完成的
    public abstract void doAction();

    public void action() {
        if (isTask) {
            doAction();// 执行子节点 链条断
        } else {
            //继续执行下一个任务节点
            if (nextTask != null) {
                nextTask.action();
            }
        }

    }
}
