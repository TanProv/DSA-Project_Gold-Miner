package com.sxt;

import java.awt.*; 

public class Bg {
    static int level = 1;
    int goal = level * 10;
    static int count = 0 ;
    static int waternumber = 3;
    static boolean waterflag = false;
    long startTime ;
    long endTime ; 
    int price = (int) (Math.random()*10);
    boolean shop = false;


    Image bg =Toolkit.getDefaultToolkit().getImage("img/bg0.png");
    Image bg1 =Toolkit.getDefaultToolkit().getImage("img/bg1.png");
    Image cha =Toolkit.getDefaultToolkit().getImage("img/cha.png");
    Image water =Toolkit.getDefaultToolkit().getImage("img/water.png");

    void paintSelf(Graphics g){
        g.drawImage(bg, 0, 0, null);
        g.drawImage(bg1, 0, 0, null);
        switch (GameWindow.state) {
            case 0:
            drawWord(g, 80,"START",  260, 100);
                break;
            case 1:
            g.drawImage(cha,230,36, null);
            drawWord(g, 30,"Point: " +count ,  30, 150);
    
            g.drawImage(water, 460, 40, null);
            drawWord(g, 30,"*" +waternumber ,  510, 70);
    
            drawWord(g, 30,"Level: " +level,  30, 60);
    
            drawWord(g, 30,"Goal: " +goal ,  30, 110);

            endTime = System.currentTimeMillis();
            long tim =  20 - (endTime - startTime) / 1000;
            drawWord(g, 30, "Time: "+(tim>0?tim:0), 450, 120);
                break;
            case 2:
            g.drawImage(water, 290, 400, null);
            drawWord(g, 30,"Cost: " +price ,  150 , 500);
            drawWord(g, 30,"Do you want to buy?" ,  150, 550);
            if(shop){
                count = count - price;
                waternumber++;
                shop = false;
                GameWindow.state=1;
                startTime = System.currentTimeMillis();
            }
                break;
            case 3:
            drawWord(g, 80, "YOU LOSE", 225, 350);
            drawWord(g, 30,"Point:" +count ,  250, 400);
                break;
            case 4:
            drawWord(g, 80, "YOU WIN", 235, 350);
            drawWord(g, 30,"Point:" +count ,  250, 400);
                break;
            default:
                break;
        }
       

    }

    boolean gameTime(){
        long tim = (endTime - startTime)/1000;
        if(tim>20){
            return true;
        }
        return false;

    }

    void reGame(){
    level = 1;
    goal = level * 10;
    count = 0 ;
    waternumber = 3;
    waterflag = false;
    }


    public static void drawWord(Graphics g,int size,String str, int x, int y ){
        g.setColor(Color.BLACK);
        g.setFont(new Font("bold", Font.BOLD, 30) );
        g.drawString(str, x, y);

    }
}
 