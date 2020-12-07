package jj;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class gladiator
{
    int gladX;
    double gladValX,gladValY;
    int gladY;
    int adjacent,opposite;
    boolean placed=false;
    int health=42;
    Rectangle glad;
    public gladiator()
    {
        glad=new Rectangle (gladX,gladY,15,15);
    }

    public void gladPlacement(int xx, int yy)
    {
        gladX=xx;
        gladY=yy;
        placed=true;
       // System.out.println("hi");

    }
    //412
    //131
    public void search(int nearX,int nearY)
    {
        if(placed)
        {
            adjacent=gladX-nearX;
            opposite=gladY-nearY;
            for(double x=0.0000;x<1;x+=0.00001)
            {
                if((26.5>=(adjacent*x)*(adjacent*x)+(opposite*x)*(opposite*x)) && (23.5<=(adjacent*x)*(adjacent*x)+(opposite*x)*(opposite*x)))
                {
                	
                    gladValX=(adjacent*x);
                    gladValY=(opposite*x);
                }
            }
        }
    }

    public int getGladX()
    {
        return gladX;
    }

    public int getGladY()
    {
        return gladY;
    }

    public void move()
    {
        gladX+=(gladValX*-1);
        gladY+=(gladValY*-1);
        glad.setLocation(gladX,gladY);
        /*if (placed)
        {
        gladX+=(adjacent/(adjacent+opposite))*-gladM;
        gladY+=(opposite/(adjacent+opposite))*-gladM;
        }*/
    }

    public Rectangle getGlad()
    {
        return glad;
    }

    public void harm(int damage)
    {
        health-=damage;
        if(health<=0)
        {
            gladX=90000000;
            gladY=90000000;
            glad.setLocation(gladX,gladY);
        }
        //System.out.println(health);
        
    }

    public void drawGlad(Graphics2D g,Graphics2D g2)
    {
        g.drawOval(gladX,gladY,15,15);
        g.fillRect(gladX-3,gladY-6,(health/2),3);
        //System.out.println(health);
    }
}