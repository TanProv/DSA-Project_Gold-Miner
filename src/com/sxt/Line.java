package com.sxt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Line {
    

    int x= 305;
    int y= 150;

    int endx= 500 ;
    int endy= 500;

    double length = 80 ;
    double MIN_length = 100;
    double Max_length = 700;
    double  n = 0.5 ;

    int dir = 1;

    int state;

    Image hook = Toolkit.getDefaultToolkit().getImage("img/hook.png");

    GameWindow frame ;

    Line(GameWindow frame){this.frame = frame ;}

    void logic(){
        for(Object obj: this.frame.objectList)
        if(endx > obj.x && endx < obj.x + obj.width 
        && endy > obj.y && endy < obj.y + obj.height)
        {
          state = 3 ;

          obj.flag=true;
        }
    }

    void lines(Graphics g){
        endx = (int) (x + length*Math.cos(n* Math.PI));
        endy = (int) (y + length*Math.sin(n* Math.PI));
        g.setColor(Color.RED);
        g.drawLine(x-1, y, endx-1, endy);
        g.drawLine(x, y, endx, endy);
        g.drawLine(x+1, y, endx+1, endy);
        g.drawImage(hook, endx-36, endy-2, null);
    }
    


    void paintSelf(Graphics g){

        logic();


        switch (state) {
            case 0:
            if(n < 0.1){ dir = 1;}
        else if (n > 0.9){dir = -1;}
        n = n + 0.005*dir;
        lines(g);
                
                break;
            case 1:
            if(length< Max_length){
                length= length+5;
        lines(g);

            } else  {state=2;}
            break;
           
            case 2:
            if(length>MIN_length){
                length= length-5;
        lines(g);
            } else {
                state=0;
            }
            break;
           
            case 3:
            int m = 1;
            if(length>MIN_length){
                length= length-5;
        lines(g);
        for(Object obj:this.frame.objectList){
            if(obj.flag){
                m = obj.m;
                obj.x=endx-obj.getWidth()/2;
            obj.y=endy;
            if(length<=MIN_length){
                obj.x = -150;
                obj.y = -150;
                obj.flag = false;
                Bg.waterflag = false;
                Bg.count+=obj.count;
                state=0;
            }
            if(Bg.waterflag){
                if(obj.type==1){
                    m=1;
                }
                if(obj.type==2){
                obj.x = -150;
                obj.y = -150;
                obj.flag = false;
                Bg.waterflag = false;
                state=2;
                }
            }
        }
            
    }
        
}
try{
    Thread.sleep(m);
} catch(InterruptedException e){
    e.printStackTrace();
}
            break;
            
            
            default:
            
        }
        
    }
    void reGame(){
        n=0;
        length = 100;
        
    }
}