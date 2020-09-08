package com.example.customview.hilt.bean;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/9/8 0008
 * description:xxx
 * ******************************
 */
public abstract class Tools {

    String name;

    public Tools(){
        this.name = getName();
    }

    public abstract String getName();

}
