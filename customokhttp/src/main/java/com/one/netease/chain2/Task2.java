package com.one.netease.chain2;

/**
 * @author diaokaibin@gmail.com on 2020-01-27.
 */
public class Task2 implements IBaseTask {
    @Override
    public void doRunAction(String isTask, IBaseTask iBaseTask) {
        if ("no".equals(isTask)) {

            System.out.println("拦截器 任务节点二 处理了 ");
            return;
        } else {
            // 继续执行下一个链条任务节点
            iBaseTask.doRunAction(isTask, iBaseTask);
        }
    }
}
