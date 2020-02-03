package com.one.netease.event_sample.bean;

/**
 * @author diaokaibin@gmail.com on 2020-02-03.
 */
public class EventBean {

    public String name;


    public EventBean(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
