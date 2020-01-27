package com.one.netease.chain2;



/**
 * @author diaokaibin@gmail.com on 2020-01-27.
 */
public class Test {
    public static void main(String[] args) {


        ChainManager chainManager = new ChainManager();

        chainManager.addTask(new Task1());
        chainManager.addTask(new Task2());
        chainManager.addTask(new Task3());
        chainManager.addTask(new Task4());

        chainManager.doRunAction("ok",chainManager);

    }
}
