package com.one.netease.chain2;

/**
 * @author diaokaibin@gmail.com on 2020-01-27.
 */
public interface IBaseTask {

    /**
     *
     * @param isTask   节点是否有能力执行
     * @param iBaseTask 下一个节点
     */
    void doRunAction(String isTask,IBaseTask iBaseTask);


}
