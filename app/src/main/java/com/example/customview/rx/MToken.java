package com.example.customview.rx;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/8/20 0020
 * description:xxx
 * ******************************
 */
public class MToken {

    private String token="";

    private static final MToken ourInstance = new MToken();

    static MToken getInstance() {
        return ourInstance;
    }

    private MToken() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
