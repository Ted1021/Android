package com.study.tedkim.recyclerview;

/**
 * Created by tedkim on 2017. 3. 28..
 */

public class ListItem {

    //    int imageId;
    private String name;
    private String desc;

    public ListItem(String name, String desc){

        setName(name);
        setDesc(desc);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
