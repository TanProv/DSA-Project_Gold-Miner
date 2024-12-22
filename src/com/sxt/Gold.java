package com.sxt;

import java.awt.Toolkit;

public class Gold extends Object{

    boolean flag = false;
    Gold(){
        this.x = (int)(Math.random()*700);
        this.y = (int)(Math.random()*550+300);
        this.width=60;
        this.height=60;
        this.flag = false;
        this.m = 60;
        this.count = 4;
        this.type = 1;
        this.img = Toolkit.getDefaultToolkit().getImage("img/gold2.gif");

    }
    
}

class GoldMini extends Gold{
    GoldMini(){

        this.width = 36;
        this.height = 36;
        this.m = 30;
        this.count = 2;
        this.img = Toolkit.getDefaultToolkit().getImage("img/gold1.gif");
    }

}
class GoldPlus extends Gold{
    GoldPlus(){
        this.x = (int)(Math.random()*650);
        this.width = 77;
        this.height = 77;
        this.m = 80;
        this.count = 8;
        this.img = Toolkit.getDefaultToolkit().getImage("img/gold0.gif");
    }

}