package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {

    static int state;


    List<Object> objectList = new ArrayList<>();
    
    Bg bg = new Bg();
    Line line = new Line(this);
    
    
    
    public GameWindow() {
        
        boolean isPlace = true;
        
        for (int i = 0; i < 11; i++) {
            double random = Math.random();
            Gold gold;
            if (random < 0.3) {
                gold = new GoldMini();
            } else if (random < 0.7) {
                gold = new Gold();
            } else {
                gold = new GoldPlus();
            }
            
            isPlace = true;  
            for (Object obj : objectList) {
                if (gold.getRec().intersects(obj.getRec())) {
                    isPlace = false;
                    break; 
                }
            }
            if (isPlace) {
                objectList.add(gold);
            } else {
                i--; 
            }
        }

        for (int i = 0; i < 5; i++) {
            Rock rock = new Rock();
            isPlace = true; 
            for (Object obj : objectList) {
                if (rock.getRec().intersects(obj.getRec())) {
                    isPlace = false;
                    break;  
                }
            }
            if (isPlace) {
                objectList.add(rock);
            } else {
                i--;  
            }
        }
    }

    Image offScreenImage;

    public void launch() {
        this.setVisible(true);
        this.setSize(620, 800);
        this.setLocationRelativeTo(null);
        this.setTitle("Gold Miner");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (state) {
                    case 0:
                    if(e.getButton()==3){
                        state=1;
                        bg.startTime = System.currentTimeMillis();
                    }
                        break;
                        case 1:     
                        if (e.getButton() == 1 && line.state == 0) {    
                            line.state = 1;
                        }
                        if(e.getButton() == 3 && line.state==3 && Bg.waternumber>0){
                            Bg.waterflag = true;
                            Bg.waternumber--;
                        }
                        break;
                    case 2:
                    if(e.getButton()==1){
                        bg.shop=true;
                    }
                    if(e.getButton()==3){
                        state=1;
                        bg.startTime = System.currentTimeMillis();
                    }
                    break;
                    case 3:
                    case 4:
                    if(e.getButton()==1){
                        state=0;
                        bg.reGame();
                        line.reGame();
                    }
                    break;
                
                    default:
                }

            }
        });

        while (true) {
            repaint();
            nextLevel();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void nextLevel(){
        if(bg.gameTime() && state==1){
            if(Bg.count>=bg.goal){
                if(Bg.level==5){state=4;}
                else{ 
                    state=2;
                Bg.level++;   
                }

            } else {state = 3;}
            dispose();
            GameWindow gameWindow1 = new GameWindow();
            gameWindow1.launch();
        }

    }

    @Override
    public void paint(Graphics g) {
        offScreenImage = this.createImage(620, 800);
        Graphics gImage = offScreenImage.getGraphics();

        bg.paintSelf(gImage);
        if(state==1){
            for (Object obj : objectList) {
                obj.paintSelf(gImage);
            }
            line.paintSelf(gImage);
        }

        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.launch();
    }
}
