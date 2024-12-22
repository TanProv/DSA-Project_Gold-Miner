package com.sxt;

import java.awt.*;

public class Object {
    int x;
    int y;

    int width;
    int height;

    Image img;

    boolean flag;

    int m;

    int count;

    int type;

    void paintSelf(Graphics g){
        g.drawImage(img, x, y, null);
    }

    public int getWidth(){
        return width;
    }

    public Rectangle getRec(){
        return new Rectangle(x, y, width, height);
    }
}
