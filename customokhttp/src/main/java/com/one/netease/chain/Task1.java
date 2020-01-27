package com.one.netease.chain;

/**
 * @author diaokaibin@gmail.com on 2020-01-27.
 */
public class Task1 extends BaseTask {

    public Task1(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {

        System.out.println(" Task1 任务节点执行了 ");

    }
}
