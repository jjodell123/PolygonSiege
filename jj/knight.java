package jj;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class knight
{
    int tankX;
    double tankValX,tankValY;
    int tankY;
    int adjacent,opposite;
    boolean placed=false;
    int health=300;
    Rectangle tank;
    public knight()
    {
        tank=new Rectangle (tankX,tankY,15,15);
    }

    public void tankPlacement(int xx, int yy)
    {
        tankX=xx;
        tankY=yy;
        placed=true;
       // System.out.println("hi");

    }
    //412
    //131
    public void search(int nearX,int nearY)
    {
        if(placed)
        {
            adjacent=tankX-nearX;
            opposite=tankY-nearY;
            for(double x=0.0000;x<1;x+=0.00001)
            {
                if((5.5>=(adjacent*x)*(adjacent*x)+(opposite*x)*(opposite*x)) && (2.5<=(adjacent*x)*(adjacent*x)+(opposite*x)*(opposite*x)))
                {
                    tankValX=(adjacent*x);
                    tankValY=(opposite*x);
                }
            }
        }
    }

    public int getTankX()
    {
        return tankX;
    }

    public int getTankY()
    {
        return tankY;
    }

    public void move()
    {
        tankX+=(tankValX*-1);
        tankY+=(tankValY*-1);
        tank.setLocation(tankX,tankY);
    }

    public Rectangle getTank()
    {
        return tank;
    }

    public void harm(int damage)
    {
        health-=damage;
        if(health<=0)
        {
            tankX=90000000;
            tankY=90000000;
            tank.setLocation(tankX,tankY);
        }
        //System.out.println(health);
        
    }

    public void drawTank(Graphics2D g,Graphics2D g2)
    {
        g.setColor(Color.red);
        g.drawOval(tankX,tankY,15,15);
        g.fillRect(tankX-3,tankY-6,(health/8),3);
        //System.out.println(health);
    }
}