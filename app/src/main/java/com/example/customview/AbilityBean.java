package com.example.customview;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class AbilityBean {

    private String desc;
    private float value;
    private float totalValue;
    private int icon;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getValue() {
        return value;
    }

    public AbilityBean(String desc, float value, float totalValue, int icon) {
        this.desc = desc;
        this.value = value;
        this.totalValue = totalValue;
        this.icon = icon;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }
}
