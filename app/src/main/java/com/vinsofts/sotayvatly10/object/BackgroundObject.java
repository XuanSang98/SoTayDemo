package com.vinsofts.sotayvatly10.object;

/**
 * Created by Administrator on 22/04/2016.
 */
public class BackgroundObject {
    private int icon;
    private int checkChoose;

    public BackgroundObject(int icon, int checkChoose) {
        this.icon = icon;
        this.checkChoose = checkChoose;
    }

    public int getCheckChoose() {
        return checkChoose;
    }

    public void setCheckChoose(int checkChoose) {
        this.checkChoose = checkChoose;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
