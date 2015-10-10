package com.lzb.oa.commons;

/**
 * Created by lvzhenbin on 2015/10/10.
 */
public enum Period {
    P9("9:30~11:30", 1), P13("13:30~15:30", 2), P15("15:30~17:30",
            3), P16("18:30~20:30", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Period(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getString(int index) {
        for (Period c : Period.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
