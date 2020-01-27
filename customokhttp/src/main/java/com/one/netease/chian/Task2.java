package com.one.netease.chian;

/**
 * @author diaokaibin@gmail.com on 2020-01-27.
 */
public class Task2 extends BaseTask {
    public Task2(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println(" Task2 任务节点执行了 ");
    }
}
