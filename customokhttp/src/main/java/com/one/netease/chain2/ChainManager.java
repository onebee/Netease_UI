package com.one.netease.chain2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diaokaibin@gmail.com on 2020-01-27.
 */
public class ChainManager implements IBaseTask {

    private List<IBaseTask> mIBaseTasks = new ArrayList<>();

    public void addTask(IBaseTask iBaseTask) {
        mIBaseTasks.add(iBaseTask);
    }


    private int index;


    @Override
    public void doRunAction(String isTask, IBaseTask iBaseTask) {

        if (mIBaseTasks.isEmpty()) {
            // 抛出异常
            return;
        }
        if (index >= mIBaseTasks.size()) {
            return;
        }

        IBaseTask iBaseTaskResult = mIBaseTasks.get(index);  // t1
        index++;

        iBaseTaskResult.doRunAction(isTask, iBaseTask);
    }
}
