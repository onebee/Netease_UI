package com.one.netease.chian;

/**
 * @author diaokaibin@gmail.com on 2020-01-27.
 */
public class Task3 extends BaseTask{
    public Task3(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println(" Task3 任务节点执行了 ");
    }
}
