package jj;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Driver extends JFrame implements Runnable, KeyListener,MouseListener
{
    //basics
	//int drawY=0;
    Container con = getContentPane();
    Thread t = new Thread(this);
    int troop[]=new int[10];
    int selected=0;
    Rectangle placer;
    boolean placing=false;
   // String levelQuestion=JOptionPane.showInputDialog(null,"What level would you like to start at?");
   
    int level=1;//(Integer.parseInt(levelQuestion));
    int tx[]=new int[42],ty[]=new int[28];
    Rectangle troopSelected[]=new Rectangle [10];
    //glad
    String gladQuestion=JOptionPane.showInputDialog(null,"How many Gladiators do you want?");
    int gladNumberMax=Integer.parseInt(gladQuestion); 
    int gladNumber,pastGladNumber[]=new int[gladNumberMax];
    int gladPlacerX, gladPlacerY;
    // gladiator glad;
    gladiator gladNum[]=new gladiator[gladNumberMax];
    int pastGladX[]=new int[gladNumberMax];
    int pastGladY[]=new int[gladNumberMax];
    boolean gladFight[]=new boolean[gladNumberMax];
    int gladCount[]=new int[gladNumberMax];
    double gladDamage=6.0;
    int closeGladX[][]=new int [3][4];
    int closeGladY[][]=new int [3][4];

    String tankQuestion=JOptionPane.showInputDialog(null,"How many tanks do you want?");
    int tankNumberMax=Integer.parseInt(tankQuestion); 
    int tankNumber,pastTankNumber[]=new int[tankNumberMax];
    int tankPlacerX, tankPlacerY;
    knight tank;
    knight tankNum[]=new knight[tankNumberMax];
    int pastTankX[]=new int[tankNumberMax];
    int pastTankY[]=new int[tankNumberMax];
    boolean tankFight[]=new boolean[tankNumberMax];
    int tankCount[]=new int[tankNumberMax];
    double tankDamage=3.0;
    int closeTankX[][]=new int [3][4];
    int closeTankY[][]=new int [3][4];

    int closeTroopX[][]=new int[3][4];
    int closeTroopY[][]=new int[3][4];

    boolean defenseFight[][]=new boolean [3][4];

    //level 1
    int turret1x[]=new int [4];
    int turret1y[]=new int [4];
    Rectangle turret1[]=new Rectangle [4];
    Rectangle turret1p[]=new Rectangle [4];
    int turret1health[]=new int [4];
    int turret1healthX[]=new int [4];
    int turret1healthY[]=new int [4];
    double tuHealth[]=new double [4];
    int tuuHealth[]=new int [4];
    Rectangle turretRange[]=new Rectangle[4];
    int turretShotX[]=new int [4];
    int turretShotY[]=new int [4];
    double turretXval[]=new double[4];
    double turretYval[]=new double[4];
    Rectangle turretShot[]=new Rectangle[4];
    int turretCount[]=new int[4];
    boolean tuCount[]=new boolean[4];
    int turretDamage=42;
    int tuShotCount[]=new int[4];

    int tower1x[]=new int[4];
    int tower1y[]=new int[4];
    Rectangle tower1[]=new Rectangle [4];
    Rectangle tower1p[]=new Rectangle [4];
    int tower1health[]=new int[4];
    int tower1healthX[]=new int[4];
    int tower1healthY[]=new int[4];
    double toHealth[]=new double [4];
    int tooHealth[]=new int [4];
    Rectangle towerRange[]=new Rectangle[4];
    int towerShotX[]=new int [4];
    int towerShotY[]=new int [4];
    double towerXval[]=new double[4];
    double towerYval[]=new double[4];
    Rectangle towerShot[]=new Rectangle[4];
    int towerCount[]=new int[4];
    boolean toCount[]=new boolean[4];
    int towerDamage=10;
    int toShotCount[]=new int[4];

    int rocket1x[]=new int[4];
    int rocket1y[]=new int[4];
    Rectangle rocket1[]=new Rectangle [4];
    Rectangle rocket1p[]=new Rectangle [4];
    int rocket1health[]=new int[4];
    int rocket1healthX[]=new int[4];
    int rocket1healthY[]=new int[4];
    double roHealth[]=new double [4];
    int rooHealth[]=new int [4];
    Rectangle rocketRange[]=new Rectangle[4];
    int rocketShotX[]=new int [4];
    int rocketShotY[]=new int [4];
    double rocketXval[]=new double[4];
    double rocketYval[]=new double[4];
    Rectangle rocketShot[]=new Rectangle[4];
    int rocketCount[]=new int[4];
    boolean roCount[]=new boolean[4];
    int rocketDamage=5;
    int roShotCount[]=new int[4];
    boolean explode[]=new boolean[4];
    int explosionX[]=new int[4];
    int explosionY[]=new int[4];
    Rectangle explosion[]=new Rectangle [4];
    int explodeCount[]=new int[4];

    int house1x[]=new int[4];
    int house1y[]=new int[4];
    Rectangle house1[]=new Rectangle [4];
    Rectangle house1p[]=new Rectangle [4];
    int house1health[]=new int[4];
    int house1healthX[]=new int[4];
    int house1healthY[]=new int[4];
    double h1Health[]=new double [4];
    int h11Health[]=new int [4];

    int house2x[]=new int[4];
    int house2y[]=new int[4];
    Rectangle house2[]=new Rectangle [4];
    Rectangle house2p[]=new Rectangle [4];
    int house2health[]=new int[4];
    int house2healthX[]=new int[4];
    int house2healthY[]=new int[4];
    double h2Health[]=new double [4];
    int h22Health[]=new int [4];

    Rectangle tile1[][]=new Rectangle[42][28];
    int closestX[][]=new int [5][4];
    int closestY[][]=new int [5][4];
    int closeGx[]=new int[gladNumberMax],closeGy[]=new int[gladNumberMax];
    int closeTx[]=new int[tankNumberMax],closeTy[]=new int[tankNumberMax];
    int choosenGlad[][]=new int[3][4];
    int choosenTank[][]=new int[3][4];
    int destroyed=0;
    boolean playGame=false;
    boolean homeScreen=true;
    int playCount=0;
    int easyD=100,mediumD=150,hardD=400;
    int difficulty;
    int divisor=2;
    int which[]=new int[3];
    int difficultyCount=0;

    Rectangle play,easy,medium,hard,instructions,back;
    Image Hexagon, homeScreenPic,Pentagon,Triangle,explodePic,instructionsPic;
    boolean instructionsPage;
    public Driver ()
    {
        JOptionPane.showMessageDialog(null,"Change troops by clicking the bowes at the bottom.");
        Hexagon=Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/hexagon.png"));
        Hexagon=Hexagon.getScaledInstance(39, 39, 1);
        Pentagon=Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/pentagon.png"));
        Pentagon=Pentagon.getScaledInstance(39, 39, 1);
        Triangle=Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/triangle.png"));
        Triangle=Triangle.getScaledInstance(39, 39, 1);
        explodePic=Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/explosion2.png"));
        explodePic=explodePic.getScaledInstance(15, 15, 1);
        homeScreenPic=Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/HomePage.png"));
        homeScreenPic=homeScreenPic.getScaledInstance(822, 700, 1);
        instructionsPic=Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/instructions.png"));
        instructionsPic=instructionsPic.getScaledInstance(822, 700, 1);
        play=new Rectangle(171,500,480,157);
        easy=new Rectangle(50,50,50,50);
        medium=new Rectangle(125,50,50,50);
        hard=new Rectangle(200,50,50,50);
        instructions=new Rectangle(571,50,200,50);
        back=new Rectangle(50,50,100,50);
        for(int y=0;y<closestX.length;y++)
        {
            for(int x=0;x<closestX[y].length;x++)
            {
                closestX[y][x]=10000;
                closestY[y][x]=10000;
            }
        }
        for(int x=0;x<tx.length;x++)
        {
            tx[x]=(x*19)+36;
        }
        for(int x=0;x<ty.length;x++)
        {
            ty[x] =(x*19)+13;
        }
        con.setLayout(new FlowLayout());
        addMouseListener(this);
        addKeyListener(this);
        placer=new Rectangle(13,36,797,531);
        // glad=new gladiator();
        gladNumber=gladNumberMax;
        tankNumber=tankNumberMax;

        for(int x=0;x<gladNumber;x++)
        {
            gladNum[x]=new gladiator();
        }

        for(int x=0;x<tankNumber;x++)
        {
            tankNum[x]=new knight();
        }

        for(int x=0;x<gladNumber;x++)
        {
            gladFight[x]=false;
        }

        for(int x=0;x<tankNumber;x++)
        {
            tankFight[x]=false;
        }
        troopSelected[0]=new Rectangle (13,586,75,100);
        troopSelected[1]=new Rectangle (93,586,75,100);
        troopSelected[2]=new Rectangle (173,586,75,100);
        troopSelected[3]=new Rectangle (253,586,75,100);
        troopSelected[4]=new Rectangle (333,586,75,100);
        troopSelected[5]=new Rectangle (413,586,75,100);
        troopSelected[6]=new Rectangle (493,586,75,100);
        troopSelected[7]=new Rectangle (573,586,75,100);
        troopSelected[8]=new Rectangle (653,586,75,100);
        troopSelected[9]=new Rectangle (733,586,75,100);

        //level 1
        if(level==1)
        {
            turret1x[0]=412;
            turret1y[0]=169;
            turret1x[1]=526;
            turret1y[1]=283;
            turret1x[2]=412;
            turret1y[2]=397;
            turret1x[3]=298;
            turret1y[3]=283;
            for(int x=0; x<turret1x.length;x++)
            {
                turret1[x]=new Rectangle(turret1x[x]-19,turret1y[x]-19,75,75);
                turret1p[x]=new Rectangle(turret1x[x]-19,turret1y[x]-19,75,75);
                turret1health[x]=100;
                turret1healthX[x]=turret1x[x]-6;
                turret1healthY[x]=turret1y[x]-10;
                turretCount[x]=0;
                explosionX[x]=-30;
                explosionY[x]=-30;
                explosion[x]=new Rectangle(explosionX[x],explosionY[x],15,15);
                //tuHealth[x]=50.0;
            }

            tower1x[0]=355;
            tower1y[0]=226;
            tower1x[1]=469;
            tower1y[1]=226;
            tower1x[2]=469;
            tower1y[2]=340;
            tower1x[3]=355;
            tower1y[3]=340;
            for(int x=0; x<tower1x.length;x++)
            {
                tower1[x]=new Rectangle(tower1x[x]-19,tower1y[x]-19,75,75);
                tower1p[x]=new Rectangle(tower1x[x]-19,tower1y[x]-19,75,75);
                tower1health[x]=100;
                tower1healthX[x]=tower1x[x]-6;
                tower1healthY[x]=tower1y[x]-10;
                towerCount[x]=0;
            }
            rocket1x[0]=412;
            rocket1y[0]=245;
            rocket1x[1]=450;
            rocket1y[1]=283;
            rocket1x[2]=412;
            rocket1y[2]=321;
            rocket1x[3]=374;
            rocket1y[3]=283;
            for(int x=0; x<rocket1x.length;x++)
            {
                rocket1[x]=new Rectangle(rocket1x[x]-19,rocket1y[x]-19,75,75);
                rocket1p[x]=new Rectangle(rocket1x[x]-19,rocket1y[x]-19,75,75);
                rocket1health[x]=100;
                rocket1healthX[x]=rocket1x[x]-6;
                rocket1healthY[x]=rocket1y[x]-10;
                rocketCount[x]=0;
            }

            house1x[0]=298;
            house1y[0]=226;
            house1x[1]=526;
            house1y[1]=226;
            house1x[2]=526;
            house1y[2]=340;
            house1x[3]=298;
            house1y[3]=340;
            for(int x=0; x<house1x.length;x++)
            {
                house1[x]=new Rectangle(house1x[x]-19,house1y[x]-19,75,75);
                house1p[x]=new Rectangle(house1x[x]-19,house1y[x]-19,75,75);
                house1health[x]=100;
                house1healthX[x]=house1x[x]-6;
                house1healthY[x]=house1y[x]-10;
            }

            house2x[0]=355;
            house2y[0]=169;
            house2x[1]=469;
            house2y[1]=169;
            house2x[2]=469;
            house2y[2]=397;
            house2x[3]=355;
            house2y[3]=397;
            for(int x=0; x<house2x.length;x++)
            {
                house2[x]=new Rectangle(house2x[x]-19,house2y[x]-19,75,75);
                house2p[x]=new Rectangle(house2x[x]-19,house2y[x]-19,75,75);
                house2health[x]=100;
                house2healthX[x]=house2x[x]-6;
                house2healthY[x]=house2y[x]-10;
            }

        }
        else
        {
            /*turret1x[0]=9000;
            turret1y[0]=900;
            turret1[2]=new Rectangle(4000-19,4000-19,75,75);
            turret1[0]=new Rectangle(turret1x[0]-7000,turret1y[0]-7000,55,55);*/
        }
        for (int x=0;x<tile1.length;x++)
        {
            for(int y=0;y<tile1[x].length;y++)
            {
                tile1[x][y]=new Rectangle(tx[x],ty[y],19,19);
            }
        }
        for(int y=0;y<closestX.length;y++)
        {
            for(int x=0;x<closestX[y].length;x++)
            {
                if(y==0)
                {
                    closestX[y][x]=turret1x[x]+19;
                    closestY[y][x]=turret1y[x]+19;
                    turretShotX[x]=closestX[y][x];
                    turretShotY[x]=closestY[y][x];
                    turretShot[x]=new Rectangle(turretShotX[x],turretShotY[x],4,4);
                    turretRange[x]=new Rectangle(closestX[y][x]-76,closestY[y][x]-76,152,152);
                }
                if(y==1)
                {
                    closestX[y][x]=tower1x[x]+19;
                    closestY[y][x]=tower1y[x]+19;
                    towerShotX[x]=closestX[y][x];
                    towerShotY[x]=closestY[y][x];
                    towerShot[x]=new Rectangle(towerShotX[x],towerShotY[x],4,4);
                    towerRange[x]=new Rectangle(closestX[y][x]-95,closestY[y][x]-95,190,190);
                }
                if(y==2)
                {
                    closestX[y][x]=rocket1x[x]+19;
                    closestY[y][x]=rocket1y[x]+19;
                    rocketShotX[x]=closestX[y][x];
                    rocketShotY[x]=closestY[y][x];
                    rocketShot[x]=new Rectangle(rocketShotX[x],rocketShotY[x],4,4);
                    rocketRange[x]=new Rectangle(closestX[y][x]-76,closestY[y][x]-76,152,152);
                }
                if(y==3)
                {
                    closestX[y][x]=house1x[x]+19;
                    closestY[y][x]=house1y[x]+19;
                }
                if(y==4)
                {
                    closestX[y][x]=house2x[x]+19;
                    closestY[y][x]=house2y[x]+19;
                }
            }
        }
        t.start();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void keyPressed(KeyEvent j)
    {
        if(j.getKeyCode()==32)
        {
            //  System.out.println(turret1health[0]);
            //System.out.println(closeGladX[0][1]+" "+closeGladY[0][1]);
            //System.out.println(closeGladX[0][0]);
            //System.out.println(turret1[0]);
            // System.out.println(turret1health[0]);
            //System.out.println(tuHealth[0]);
            //System.out.println(1.5%1);
        }
    }

    public void keyTyped(KeyEvent j)
    {}

    public void keyReleased(KeyEvent j)
    {
    }

    public void mouseExited(MouseEvent j)
    {}

    public void mouseClicked(MouseEvent j)
    {
    	//drawY=j.getY();
        /*if(turretRange[0].contains(j.getPoint()))
        {
        System.out.println("yay");
        }*/
    }

    public void mouseEntered(MouseEvent j)
    {}

    public void mouseReleased(MouseEvent j)
    {
        if(homeScreen)
        {
            if(play.contains(j.getPoint()))
            {
                playCount+=1;
            }
            if(easy.contains(j.getPoint()))
            {
                difficultyCount=1;
            }
            if(medium.contains(j.getPoint()))
            {
                difficultyCount=2;
            }
            if(hard.contains(j.getPoint()))
            {
                difficultyCount=3;
            }
            if(instructions.contains(j.getPoint()))
            {
                instructionsPage=true;
                homeScreen=false;
            }
        }
        if(instructionsPage)
        {
            if(back.contains(j.getPoint()))
            {
                homeScreen=true;
                instructionsPage=false;
            }
        }
        if(playGame)
        {
            if(selected==0 && (placer.contains(j.getPoint())) && gladNumber>=1 && 
            (!(turret1p[0].contains(j.getPoint()))) && (!(turret1p[1].contains(j.getPoint())))
            && (!(turret1p[2].contains(j.getPoint()))) && (!(turret1p[3].contains(j.getPoint())))
            && (!(tower1p[0].contains(j.getPoint()))) && (!(tower1p[1].contains(j.getPoint())))
            && (!(tower1p[2].contains(j.getPoint()))) && (!(tower1p[3].contains(j.getPoint())))
            && (!(rocket1p[0].contains(j.getPoint()))) && (!(rocket1p[1].contains(j.getPoint())))
            && (!(rocket1p[2].contains(j.getPoint()))) && (!(rocket1p[3].contains(j.getPoint())))
            && (!(house1p[0].contains(j.getPoint()))) && (!(house1p[1].contains(j.getPoint())))
            && (!(house1p[2].contains(j.getPoint()))) && (!(house1p[3].contains(j.getPoint())))
            && (!(house2p[0].contains(j.getPoint()))) && (!(house2p[1].contains(j.getPoint())))
            && (!(house2p[2].contains(j.getPoint()))) && (!(house2p[3].contains(j.getPoint()))))
            {
                for(int x=0;x<tx.length;x++)
                {
                    if(j.getX()<(((x+1)*19)+13) && j.getX()>=(((x)*19)+13))
                    {
                        gladPlacerX=((x*19)+15);
                    }
                }
                for(int x=0;x<ty.length;x++)
                {
                    if(j.getY()<(((x+1)*19)+36) && j.getY()>=(((x)*19)+36))
                    {
                        gladPlacerY=((x*19)+38);
                    }
                }
                pastGladX[gladNumber-1]=gladPlacerX;
                pastGladY[gladNumber-1]=gladPlacerY;
                gladNum[gladNumber-1].gladPlacement(gladPlacerX,gladPlacerY);
                pastGladNumber[gladNumber-1]=1;
                gladNumber-=1;
            }
            if(selected==0 && (placer.contains(j.getPoint())) && gladNumber<1 && 
            (!(turret1p[0].contains(j.getPoint()))) && (!(turret1p[1].contains(j.getPoint())))
            && (!(turret1p[2].contains(j.getPoint()))) && (!(turret1p[3].contains(j.getPoint())))
            && (!(tower1p[0].contains(j.getPoint()))) && (!(tower1p[1].contains(j.getPoint())))
            && (!(tower1p[2].contains(j.getPoint()))) && (!(tower1p[3].contains(j.getPoint())))
            && (!(rocket1p[0].contains(j.getPoint()))) && (!(rocket1p[1].contains(j.getPoint())))
            && (!(rocket1p[2].contains(j.getPoint()))) && (!(rocket1p[3].contains(j.getPoint())))
            && (!(house1p[0].contains(j.getPoint()))) && (!(house1p[1].contains(j.getPoint())))
            && (!(house1p[2].contains(j.getPoint()))) && (!(house1p[3].contains(j.getPoint())))
            && (!(house2p[0].contains(j.getPoint()))) && (!(house2p[1].contains(j.getPoint())))
            && (!(house2p[2].contains(j.getPoint()))) && (!(house2p[3].contains(j.getPoint()))))
            {
                JOptionPane.showMessageDialog(null, "no more Glads");
            }
            if(selected==1 && (placer.contains(j.getPoint())) && tankNumber<1 && 
            (!(turret1p[0].contains(j.getPoint()))) && (!(turret1p[1].contains(j.getPoint())))
            && (!(turret1p[2].contains(j.getPoint()))) && (!(turret1p[3].contains(j.getPoint())))
            && (!(tower1p[0].contains(j.getPoint()))) && (!(tower1p[1].contains(j.getPoint())))
            && (!(tower1p[2].contains(j.getPoint()))) && (!(tower1p[3].contains(j.getPoint())))
            && (!(rocket1p[0].contains(j.getPoint()))) && (!(rocket1p[1].contains(j.getPoint())))
            && (!(rocket1p[2].contains(j.getPoint()))) && (!(rocket1p[3].contains(j.getPoint())))
            && (!(house1p[0].contains(j.getPoint()))) && (!(house1p[1].contains(j.getPoint())))
            && (!(house1p[2].contains(j.getPoint()))) && (!(house1p[3].contains(j.getPoint())))
            && (!(house2p[0].contains(j.getPoint()))) && (!(house2p[1].contains(j.getPoint())))
            && (!(house2p[2].contains(j.getPoint()))) && (!(house2p[3].contains(j.getPoint()))))
            {
                JOptionPane.showMessageDialog(null, "no more tanks");
            }

            if(selected==1 && (placer.contains(j.getPoint())) && tankNumber>=1 && 
            (!(turret1p[0].contains(j.getPoint()))) && (!(turret1p[1].contains(j.getPoint())))
            && (!(turret1p[2].contains(j.getPoint()))) && (!(turret1p[3].contains(j.getPoint())))
            && (!(tower1p[0].contains(j.getPoint()))) && (!(tower1p[1].contains(j.getPoint())))
            && (!(tower1p[2].contains(j.getPoint()))) && (!(tower1p[3].contains(j.getPoint())))
            && (!(rocket1p[0].contains(j.getPoint()))) && (!(rocket1p[1].contains(j.getPoint())))
            && (!(rocket1p[2].contains(j.getPoint()))) && (!(rocket1p[3].contains(j.getPoint())))
            && (!(house1p[0].contains(j.getPoint()))) && (!(house1p[1].contains(j.getPoint())))
            && (!(house1p[2].contains(j.getPoint()))) && (!(house1p[3].contains(j.getPoint())))
            && (!(house2p[0].contains(j.getPoint()))) && (!(house2p[1].contains(j.getPoint())))
            && (!(house2p[2].contains(j.getPoint()))) && (!(house2p[3].contains(j.getPoint()))))
            {
                for(int x=0;x<tx.length;x++)
                {
                    if(j.getX()<(((x+1)*19)+13) && j.getX()>=(((x)*19)+13))
                    {
                        tankPlacerX=((x*19)+15);
                    }
                }
                for(int x=0;x<ty.length;x++)
                {
                    if(j.getY()<(((x+1)*19)+36) && j.getY()>=(((x)*19)+36))
                    {
                        tankPlacerY=((x*19)+38);
                    }
                }
                pastTankX[tankNumber-1]=tankPlacerX;
                pastTankY[tankNumber-1]=tankPlacerY;
                tankNum[tankNumber-1].tankPlacement(tankPlacerX,tankPlacerY);
                pastTankNumber[tankNumber-1]=1;
                tankNumber-=1;
            }

            for(int x=0;x<troopSelected.length;x++)
            {
                if(troopSelected[x].contains(j.getPoint()))
                {
                    selected=x;
                }

            }
        }
    }

    public void mousePressed(MouseEvent j)
    {

    }

    public void run()
    {
        try{
            while(true)
            {
                t.sleep(40);
                if(homeScreen)
                {
                    if(playCount==1)
                    {
                        homeScreen=false;
                        playGame=true;
                    }
                    if(difficultyCount==1)
                    {
                        difficulty=100;
                        divisor=2;
                        for(int x=0;x<turret1health.length;x++)
                        {
                            turret1health[x]=difficulty;
                            tower1health[x]=difficulty;
                            rocket1health[x]=difficulty;
                            house1health[x]=difficulty;
                            house2health[x]=difficulty;
                        }
                    }
                    if(difficultyCount==2)
                    {
                        difficulty=150;
                        divisor=3;
                        for(int x=0;x<turret1health.length;x++)
                        {
                            turret1health[x]=difficulty;
                            tower1health[x]=difficulty;
                            rocket1health[x]=difficulty;
                            house1health[x]=difficulty;
                            house2health[x]=difficulty;
                        }
                    }
                    if(difficultyCount==3)
                    {
                        difficulty=200;
                        divisor=4;
                        for(int x=0;x<turret1health.length;x++)
                        {
                            turret1health[x]=difficulty;
                            tower1health[x]=difficulty;
                            rocket1health[x]=difficulty;
                            house1health[x]=difficulty;
                            house2health[x]=difficulty;
                        }
                    }
                }
                if(playGame)
                {
                    for(int xx=0;xx<gladNum.length;xx++)
                    {
                        int  adj=0,hyp=0,opp=0;
                        int close=2100000000;
                        // closeX=closeY=8000;

                        for (int y=0;y<closestX.length;y++)
                        {
                            for(int x=0;x<closestX[y].length;x++)
                            {
                                adj=gladNum[xx].getGladX()-closestX[y][x];

                                if(adj<0)
                                {
                                    adj*= (-1);
                                }
                                opp=gladNum[xx].getGladY()-closestY[y][x];
                                if(opp<0)
                                {
                                    opp*= (-1);
                                }
                                hyp=((adj)*(adj))+((opp)*(opp));

                                if(hyp<close)
                                {
                                    close=hyp;
                                    closeGx[xx]=closestX[y][x];
                                    closeGy[xx]=closestY[y][x];

                                }

                            }
                        }
                    }
                    for(int xx=0;xx<tankNum.length;xx++)
                    {
                        int  adj=0,hyp=0,opp=0;
                        int close=2100000000;
                        // closeX=closeY=8000;

                        for (int y=0;y<closestX.length;y++)
                        {
                            for(int x=0;x<closestX[y].length;x++)
                            {
                                adj=tankNum[xx].getTankX()-closestX[y][x];

                                if(adj<0)
                                {
                                    adj*= (-1);
                                }
                                opp=tankNum[xx].getTankY()-closestY[y][x];
                                if(opp<0)
                                {
                                    opp*= (-1);
                                }
                                hyp=((adj)*(adj))+((opp)*(opp));

                                if(hyp<close)
                                {
                                    close=hyp;
                                    closeTx[xx]=closestX[y][x];
                                    closeTy[xx]=closestY[y][x];

                                }

                            }
                        }
                    }
                    for(int y=0;y<turret1.length;y++)
                    {
                        for(int x=0;x<gladNum.length;x++)
                        {

                            if(turret1[y].contains(gladNum[x].getGlad()) || tower1[y].contains(gladNum[x].getGlad())
                            || house1[y].contains(gladNum[x].getGlad()) || rocket1[y].contains(gladNum[x].getGlad())
                            || house2[y].contains(gladNum[x].getGlad()))
                            {
                                gladFight[x]=true;
                            }
                            else
                            {
                                //  gladFight[x]=false;
                                //may slow down
                            }

                            //later add an else if for if the turret is dead
                        }
                    }
                    for(int y=0;y<turret1.length;y++)
                    {
                        for(int x=0;x<tankNum.length;x++)
                        {

                            if(turret1[y].contains(tankNum[x].getTank()) || tower1[y].contains(tankNum[x].getTank())
                            || house1[y].contains(tankNum[x].getTank()) || rocket1[y].contains(tankNum[x].getTank())
                            || house2[y].contains(tankNum[x].getTank()))
                            {
                                tankFight[x]=true;

                            }
                            else
                            {
                                //  gladFight[x]=false;
                                //may slow down
                            }

                            //later add an else if for if the turret is dead
                        }
                    }
                    for(int y=0;y<defenseFight.length;y++)
                    {
                        for(int x=0;x<defenseFight[y].length;x++)
                        {
                            for(int xx=0;xx<gladNum.length;xx++)
                            {
                                if(y==0)
                                {
                                    if((turretRange[x].contains(gladNum[xx].getGlad())) || (turretRange[x].intersects(gladNum[xx].getGlad())))
                                    {
                                        defenseFight[y][x]=true;

                                        //JOptionPane.showMessageDialog(null, y+" "+x);
                                    }
                                }
                                if(y==1)
                                {
                                    if((towerRange[x].contains(gladNum[xx].getGlad())) || (towerRange[x].intersects(gladNum[xx].getGlad())))
                                    {
                                        defenseFight[y][x]=true;
                                        //JOptionPane.showMessageDialog(null, y+" "+x);
                                    }
                                }
                                if(y==2)
                                {
                                    if((rocketRange[x].contains(gladNum[xx].getGlad())) || (rocketRange[x].intersects(gladNum[xx].getGlad())))
                                    {
                                        defenseFight[y][x]=true;
                                        //System.out.println("hi");
                                        //JOptionPane.showMessageDialog(null, y+" "+x);
                                    }
                                }
                                if(y==0)
                                {
                                    if((turretRange[x].contains(tankNum[xx].getTank())) || (turretRange[x].intersects(tankNum[xx].getTank())))
                                    {
                                        defenseFight[y][x]=true;
                                        //JOptionPane.showMessageDialog(null, y+" "+x);
                                    }
                                }
                                if(y==1)
                                {
                                    if((towerRange[x].contains(tankNum[xx].getTank())) || (towerRange[x].intersects(tankNum[xx].getTank())))
                                    {
                                        defenseFight[y][x]=true;
                                        //JOptionPane.showMessageDialog(null, y+" "+x);
                                    }
                                }
                                if(y==2)
                                {
                                    if((rocketRange[x].contains(tankNum[xx].getTank())) || (rocketRange[x].intersects(tankNum[xx].getTank())))
                                    {
                                        defenseFight[y][x]=true;
                                        //System.out.println("hi");
                                        //JOptionPane.showMessageDialog(null, y+" "+x);
                                    }
                                }
                            }
                        }
                    }
                    for(int y=0;y<defenseFight.length;y++)
                    {
                        for(int x=0;x<defenseFight[y].length;x++)
                        {
                            int adj=0,opp=0,hyp=0;
                            int close=2000000000;
                            for(int xx=0;xx<gladNum.length;xx++)
                            {
                                int gladX=(gladNum[xx].getGladX())+7;
                                int gladY=(gladNum[xx].getGladY())+7;
                                int tankX=(tankNum[xx].getTankX())+7;
                                int tankY=(tankNum[xx].getTankY())+7;
                                if(y==0)
                                {
                                    if(defenseFight[y][x] && gladX<1000 && turret1x[x]<1000)
                                    {
                                        adj=(closestX[y][x]-gladX);
                                        if(adj<0)
                                        {
                                            adj*= (-1);
                                        }
                                        opp=(closestY[y][x]-gladY);
                                        if(opp<0)
                                        {
                                            opp*= (-1);
                                        }
                                        hyp=((adj)*(adj))+((opp)*(opp));
                                        if(hyp<close)
                                        {
                                            close=hyp;
                                            closeGladX[y][x]=gladX;
                                            closeGladY[y][x]=gladY;
                                            which[y]=0;
                                            choosenGlad[y][x]=xx;
                                            //System.out.println(close+" "+closeGladX[y][x]+" "+closeGladY[y][x]+" "+xx);
                                        }
                                    }
                                }
                                if(y==1)
                                {
                                    if(defenseFight[y][x] && gladX<1000 && tower1x[x]<1000)
                                    {
                                        adj=(closestX[y][x]-gladX);
                                        if(adj<0)
                                        {
                                            adj*= (-1);
                                        }
                                        opp=(closestY[y][x]-gladY);
                                        if(opp<0)
                                        {
                                            opp*= (-1);
                                        }
                                        hyp=((adj)*(adj))+((opp)*(opp));
                                        if(hyp<close)
                                        {
                                            close=hyp;
                                            closeGladX[y][x]=gladX;
                                            closeGladY[y][x]=gladY;
                                            which[y]=0;
                                            choosenGlad[y][x]=xx;
                                            //System.out.println(close+" "+closeGladX[y][x]+" "+closeGladY[y][x]+" "+xx);
                                        }
                                    }
                                }
                                if(y==2)
                                {
                                    if(defenseFight[y][x] && gladX<1000 && rocket1x[x]<1000)
                                    {
                                        adj=(closestX[y][x]-gladX);
                                        if(adj<0)
                                        {
                                            adj*= (-1);
                                        }
                                        opp=(closestY[y][x]-gladY);
                                        if(opp<0)
                                        {
                                            opp*= (-1);
                                        }
                                        hyp=((adj)*(adj))+((opp)*(opp));
                                        if(hyp<close)
                                        {
                                            close=hyp;
                                            closeGladX[y][x]=gladX;
                                            closeGladY[y][x]=gladY;
                                            which[y]=0;
                                            choosenGlad[y][x]=xx;
                                            //System.out.println(close+" "+closeGladX[y][x]+" "+closeGladY[y][x]+" "+xx);
                                        }
                                    }
                                }
                                if(y==0)
                                {
                                    if(defenseFight[y][x] && tankX<1000 && turret1x[x]<1000)
                                    {
                                        adj=(closestX[y][x]-tankX);
                                        if(adj<0)
                                        {
                                            adj*= (-1);
                                        }
                                        opp=(closestY[y][x]-tankY);
                                        if(opp<0)
                                        {
                                            opp*= (-1);
                                        }
                                        hyp=((adj)*(adj))+((opp)*(opp));
                                        if(hyp<close)
                                        {
                                            close=hyp;
                                            closeTankX[y][x]=tankX;
                                            closeTankY[y][x]=tankY;
                                            which[y]=1;
                                            choosenTank[y][x]=xx;
                                            //System.out.println(close+" "+closeGladX[y][x]+" "+closeGladY[y][x]+" "+xx);
                                        }
                                    }
                                }
                                if(y==1)
                                {
                                    if(defenseFight[y][x] && tankX<1000 && tower1x[x]<1000)
                                    {
                                        adj=(closestX[y][x]-tankX);
                                        if(adj<0)
                                        {
                                            adj*= (-1);
                                        }
                                        opp=(closestY[y][x]-tankY);
                                        if(opp<0)
                                        {
                                            opp*= (-1);
                                        }
                                        hyp=((adj)*(adj))+((opp)*(opp));
                                        if(hyp<close)
                                        {
                                            close=hyp;
                                            closeTankX[y][x]=tankX;
                                            closeTankY[y][x]=tankY;
                                            which[y]=1;
                                            choosenTank[y][x]=xx;
                                            //System.out.println(close+" "+closeGladX[y][x]+" "+closeGladY[y][x]+" "+xx);
                                        }
                                    }
                                }
                                if(y==2)
                                {
                                    if(defenseFight[y][x] && tankX<1000 && rocket1x[x]<1000)
                                    {
                                        adj=(closestX[y][x]-tankX);
                                        if(adj<0)
                                        {
                                            adj*= (-1);
                                        }
                                        opp=(closestY[y][x]-tankY);
                                        if(opp<0)
                                        {
                                            opp*= (-1);
                                        }
                                        hyp=((adj)*(adj))+((opp)*(opp));
                                        if(hyp<close)
                                        {
                                            close=hyp;
                                            closeTankX[y][x]=tankX;
                                            closeTankY[y][x]=tankY;
                                            which[y]=1;
                                            choosenTank[y][x]=xx;
                                            //System.out.println(close+" "+closeGladX[y][x]+" "+closeGladY[y][x]+" "+xx);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for(int y=0;y<defenseFight.length;y++)
                    {
                        for(int x=0;x<defenseFight[y].length;x++)
                        {
                            if(defenseFight[y][x] && tuCount[x]==false)
                            {
                                if(which[y]==0)
                                {
                                    int adj=0,opp=0;
                                    // adj=(closeGladX[y][x]-closestX[y][x]);
                                    //opp=(closeGladY[y][x]-closestY[y][x]);
                                    adj=(closeGladX[y][x]-turretShotX[x]);
                                    opp=(closeGladY[y][x]-turretShotY[x]);

                                    if(y==0)
                                    {
                                        for(double xx=0.0000;xx<1;xx+=0.00001)
                                        {
                                            /*if((122.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (119.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                            turretXval[x]=(adj*xx);
                                            turretYval[x]=(opp*xx);

                                            }*/
                                            if((82.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (79.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                                turretXval[x]=(adj*xx);
                                                turretYval[x]=(opp*xx);

                                            }
                                        }
                                        turretShotX[x]+=turretXval[x];
                                        turretShotY[x]+=turretYval[x];
                                        tuShotCount[x]++;
                                        turretShot[x].setLocation(turretShotX[x],turretShotY[x]);
                                        if(tuShotCount[x]==10)
                                        {
                                            tuCount[x]=true;
                                            tuShotCount[x]=0;
                                            turretShotX[x]=closestX[y][x];
                                            turretShotY[x]=closestY[y][x];
                                            gladNum[choosenGlad[y][x]].harm(turretDamage);
                                            defenseFight[y][x]=false;
                                        }

                                        if((turretShot[x].intersects(gladNum[choosenGlad[y][x]].getGlad())) || (gladNum[choosenGlad[y][x]].getGlad().contains(turretShot[x])))
                                        {
                                            //System.out.println("hi");
                                            gladNum[choosenGlad[y][x]].harm(turretDamage);
                                            turretShotX[x]=closestX[y][x];
                                            turretShotY[x]=closestY[y][x];
                                            tuCount[x]=true;
                                            defenseFight[y][x]=false;
                                            tuShotCount[x]=0;
                                            //gladNum[choosenGlad[y][x]].
                                            //System.out.println(choosenGlad[y][x]);

                                        }
                                        //System.out.println("hi");
                                    }
                                }
                                if(which[y]==1)
                                {
                                    int adj=0,opp=0;
                                    // adj=(closeGladX[y][x]-closestX[y][x]);
                                    //opp=(closeGladY[y][x]-closestY[y][x]);
                                    adj=(closeTankX[y][x]-turretShotX[x]);
                                    opp=(closeTankY[y][x]-turretShotY[x]);

                                    if(y==0)
                                    {
                                        for(double xx=0.0000;xx<1;xx+=0.00001)
                                        {
                                            /*if((122.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (119.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                            turretXval[x]=(adj*xx);
                                            turretYval[x]=(opp*xx);

                                            }*/
                                            if((82.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (79.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                                turretXval[x]=(adj*xx);
                                                turretYval[x]=(opp*xx);

                                            }
                                        }
                                        turretShotX[x]+=turretXval[x];
                                        turretShotY[x]+=turretYval[x];
                                        tuShotCount[x]++;
                                        turretShot[x].setLocation(turretShotX[x],turretShotY[x]);
                                        if(tuShotCount[x]==10)
                                        {
                                            tuCount[x]=true;
                                            tuShotCount[x]=0;
                                            turretShotX[x]=closestX[y][x];
                                            turretShotY[x]=closestY[y][x];
                                            tankNum[choosenTank[y][x]].harm(turretDamage);
                                            defenseFight[y][x]=false;
                                        }

                                        if((turretShot[x].intersects(tankNum[choosenTank[y][x]].getTank())) || (tankNum[choosenTank[y][x]].getTank().contains(turretShot[x])))
                                        {
                                            //System.out.println("hi");
                                            tankNum[choosenTank[y][x]].harm(turretDamage);
                                            turretShotX[x]=closestX[y][x];
                                            turretShotY[x]=closestY[y][x];
                                            tuCount[x]=true;
                                            defenseFight[y][x]=false;
                                            tuShotCount[x]=0;
                                            //gladNum[choosenGlad[y][x]].
                                            //System.out.println(choosenGlad[y][x]);

                                        }
                                        //System.out.println("hi");
                                    }
                                }

                            }
                            if(defenseFight[y][x] && toCount[x]==false)
                            {
                                if(which[y]==0)
                                {
                                    int adj=0,opp=0;
                                    // adj=(closeGladX[y][x]-closestX[y][x]);
                                    //opp=(closeGladY[y][x]-closestY[y][x]);
                                    adj=(closeGladX[y][x]-towerShotX[x]);
                                    opp=(closeGladY[y][x]-towerShotY[x]);

                                    if(y==1)
                                    {
                                        for(double xx=0.0000;xx<1;xx+=0.00001)
                                        {
                                            /*if((122.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (119.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                            turretXval[x]=(adj*xx);
                                            turretYval[x]=(opp*xx);

                                            }*/
                                            if((197.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (194.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                                towerXval[x]=(adj*xx);
                                                towerYval[x]=(opp*xx);

                                            }
                                        }
                                        towerShotX[x]+=towerXval[x];
                                        towerShotY[x]+=towerYval[x];
                                        toShotCount[x]++;
                                        towerShot[x].setLocation(towerShotX[x],towerShotY[x]);
                                        if(toShotCount[x]==10)
                                        {
                                            toCount[x]=true;
                                            toShotCount[x]=0;
                                            towerShotX[x]=closestX[y][x];
                                            towerShotY[x]=closestY[y][x];
                                            gladNum[choosenGlad[y][x]].harm(towerDamage);
                                            defenseFight[y][x]=false;
                                        }

                                        if((towerShot[x].intersects(gladNum[choosenGlad[y][x]].getGlad())) || (gladNum[choosenGlad[y][x]].getGlad().contains(towerShot[x])))
                                        {
                                            //System.out.println("hi");
                                            gladNum[choosenGlad[y][x]].harm(towerDamage);
                                            towerShotX[x]=closestX[y][x];
                                            towerShotY[x]=closestY[y][x];
                                            toCount[x]=true;
                                            defenseFight[y][x]=false;
                                            toShotCount[x]=0;
                                        }
                                    }
                                }
                                if(which[y]==1)
                                {
                                    int adj=0,opp=0;
                                    // adj=(closeGladX[y][x]-closestX[y][x]);
                                    //opp=(closeGladY[y][x]-closestY[y][x]);
                                    adj=(closeTankX[y][x]-towerShotX[x]);
                                    opp=(closeTankY[y][x]-towerShotY[x]);

                                    if(y==1)
                                    {
                                        for(double xx=0.0000;xx<1;xx+=0.00001)
                                        {
                                            /*if((122.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (119.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                            turretXval[x]=(adj*xx);
                                            turretYval[x]=(opp*xx);

                                            }*/
                                            if((197.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (194.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                                towerXval[x]=(adj*xx);
                                                towerYval[x]=(opp*xx);

                                            }
                                        }
                                        towerShotX[x]+=towerXval[x];
                                        towerShotY[x]+=towerYval[x];
                                        toShotCount[x]++;
                                        towerShot[x].setLocation(towerShotX[x],towerShotY[x]);
                                        if(toShotCount[x]==10)
                                        {
                                            toCount[x]=true;
                                            toShotCount[x]=0;
                                            towerShotX[x]=closestX[y][x];
                                            towerShotY[x]=closestY[y][x];
                                            tankNum[choosenTank[y][x]].harm(towerDamage);
                                            defenseFight[y][x]=false;
                                        }

                                        if((towerShot[x].intersects(tankNum[choosenTank[y][x]].getTank())) || (tankNum[choosenTank[y][x]].getTank().contains(towerShot[x])))
                                        {
                                            //System.out.println("hi");
                                            tankNum[choosenTank[y][x]].harm(towerDamage);
                                            towerShotX[x]=closestX[y][x];
                                            towerShotY[x]=closestY[y][x];
                                            toCount[x]=true;
                                            defenseFight[y][x]=false;
                                            toShotCount[x]=0;
                                        }
                                    }
                                }
                            }
                            if(defenseFight[y][x] && roCount[x]==false)
                            {
                                if(which[y]==0)
                                {
                                    int adj=0,opp=0;
                                    adj=(closeGladX[y][x]-rocketShotX[x]);
                                    opp=(closeGladY[y][x]-rocketShotY[x]);

                                    if(y==2)
                                    {
                                        for(double xx=0.0000;xx<1;xx+=0.00001)
                                        {
                                            if((82.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (79.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                                rocketXval[x]=(adj*xx);
                                                rocketYval[x]=(opp*xx);

                                            }
                                        }
                                        rocketShotX[x]+=rocketXval[x];
                                        rocketShotY[x]+=rocketYval[x];
                                        roShotCount[x]++;
                                        rocketShot[x].setLocation(rocketShotX[x],rocketShotY[x]);
                                        if(roShotCount[x]==10)
                                        {
                                            roCount[x]=true;
                                            roShotCount[x]=0;
                                            rocketShotX[x]=closestX[y][x];
                                            rocketShotY[x]=closestY[y][x];
                                            gladNum[choosenGlad[y][x]].harm(rocketDamage);
                                            defenseFight[y][x]=false;
                                        }
                                        if((rocketShot[x].intersects(gladNum[choosenGlad[y][x]].getGlad())) || (gladNum[choosenGlad[y][x]].getGlad().contains(rocketShot[x])))
                                        {
                                            //System.out.println("hi");
                                            explosionX[x]=gladNum[choosenGlad[y][x]].getGladX();
                                            explosionY[x]=gladNum[choosenGlad[y][x]].getGladY();
                                            explosion[x].setLocation(explosionX[x],explosionY[x]);
                                            explode[x]=true;
                                            gladNum[choosenGlad[y][x]].harm(rocketDamage);
                                            rocketShotX[x]=closestX[y][x];
                                            rocketShotY[x]=closestY[y][x];
                                            roCount[x]=true;
                                            defenseFight[y][x]=false;
                                            roShotCount[x]=0;
                                            //gladNum[choosenGlad[y][x]].
                                            //System.out.println(choosenGlad[y][x]);

                                        }
                                        //System.out.println("hi");
                                    }
                                }
                                if(which[y]==1)
                                {
                                    int adj=0,opp=0;
                                    adj=(closeTankX[y][x]-rocketShotX[x]);
                                    opp=(closeTankY[y][x]-rocketShotY[x]);

                                    if(y==2)
                                    {
                                        for(double xx=0.0000;xx<1;xx+=0.00001)
                                        {
                                            if((82.5>=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)) && (79.5<=(adj*xx)*(adj*xx)+(opp*xx)*(opp*xx)))
                                            {
                                                rocketXval[x]=(adj*xx);
                                                rocketYval[x]=(opp*xx);

                                            }
                                        }
                                        rocketShotX[x]+=rocketXval[x];
                                        rocketShotY[x]+=rocketYval[x];
                                        roShotCount[x]++;
                                        rocketShot[x].setLocation(rocketShotX[x],rocketShotY[x]);
                                        if(roShotCount[x]==10)
                                        {
                                            roCount[x]=true;
                                            roShotCount[x]=0;
                                            rocketShotX[x]=closestX[y][x];
                                            rocketShotY[x]=closestY[y][x];
                                            tankNum[choosenTank[y][x]].harm(rocketDamage);
                                            defenseFight[y][x]=false;
                                        }
                                        if((rocketShot[x].intersects(tankNum[choosenTank[y][x]].getTank())) || (tankNum[choosenTank[y][x]].getTank().contains(rocketShot[x])))
                                        {
                                            //System.out.println("hi");
                                            explosionX[x]=tankNum[choosenTank[y][x]].getTankX();
                                            explosionY[x]=tankNum[choosenTank[y][x]].getTankY();
                                            explosion[x].setLocation(explosionX[x],explosionY[x]);
                                            explode[x]=true;
                                            tankNum[choosenTank[y][x]].harm(rocketDamage);
                                            rocketShotX[x]=closestX[y][x];
                                            rocketShotY[x]=closestY[y][x];
                                            roCount[x]=true;
                                            defenseFight[y][x]=false;
                                            roShotCount[x]=0;
                                            //gladNum[choosenGlad[y][x]].
                                            //System.out.println(choosenGlad[y][x]);

                                        }
                                        //System.out.println("hi");
                                    }
                                }
                            }
                        }
                    }
                    for(int x=0;x<gladNum.length;x++)
                    {
                        for(int y=0;y<explosion.length;y++)
                        {
                            if(gladNum[x].getGlad().intersects(explosion[y]))
                            {
                                gladNum[x].harm(rocketDamage);
                            }
                        }
                    }
                    for(int x=0;x<tankNum.length;x++)
                    {
                        for(int y=0;y<explosion.length;y++)
                        {
                            if(tankNum[x].getTank().intersects(explosion[y]))
                            {
                                tankNum[x].harm(rocketDamage);
                            }
                        }
                    }
                    for(int x=0;x<explosion.length;x++)
                    {
                        if(explode[x])
                        {

                            explodeCount[x]++;
                            if(explodeCount[x]==2)
                            {
                                explosionX[x]=-10;
                                explosion[x].setLocation(explosionX[x], explosionY[x]);
                                explode[x]=false;
                                explodeCount[x]=0;
                            }
                        }
                    }
                    for(int x=0;x<tuCount.length;x++)
                    {
                        if(tuCount[x])
                        {
                            turretCount[x]++;
                            if(turretCount[x]==25)
                            {
                                tuCount[x]=false;
                                turretCount[x]=0;
                            }
                        }
                        if(toCount[x])
                        {
                            toCount[x]=false;
                            /*towerCount[x]++;
                            if(towerCount[x]==4)
                            {
                            toCount[x]=false;
                            towerCount[x]=0;
                            }*/
                        }
                        if(roCount[x])
                        {
                            rocketCount[x]++;
                            if(rocketCount[x]==20)
                            {
                                roCount[x]=false;
                                rocketCount[x]=0;
                            }
                        }
                    }
                    for(int x=0;x<ty.length;x++)
                    {
                        ty[x]=(x*19)+36;
                    }
                    for(int x=0;x<tx.length;x++)
                    {
                        tx[x]=(x*19)+13;
                    }
                    for(int x=0;x<gladNum.length;x++)
                    {
                        gladNum[x].search(closeGx[x],closeGy[x]);
                        if(!(gladFight[x]))
                        {
                            gladNum[x].move();
                        }
                    }
                    for(int x=0;x<tankNum.length;x++)
                    {
                        tankNum[x].search(closeTx[x],closeTy[x]);
                        if(!(tankFight[x]))
                        {
                            tankNum[x].move();
                        }
                    }
                    for(int x=0;x<turret1.length;x++)
                    {
                        for(int xx=0;xx<gladNum.length;xx++)
                        {
                            if(turret1[x].contains(gladNum[xx].getGlad()))
                            {
                                gladCount[xx]++;
                            }
                            if(gladCount[xx]==30)
                            {
                                gladCount[xx]=0;
                                turret1health[x]-=gladDamage;
                                tuHealth[x]=(turret1health[x])/divisor;
                                // tuHealth[x]-=;
                                tuuHealth[x]=(int)tuHealth[x];
                            }
                            if(tower1[x].contains(gladNum[xx].getGlad()))
                            {
                                gladCount[xx]++;
                            }
                            if(gladCount[xx]==30)
                            {
                                gladCount[xx]=0;
                                tower1health[x]-=gladDamage;
                                toHealth[x]=(tower1health[x])/divisor;
                                tooHealth[x]=(int)toHealth[x];
                            }
                            if(rocket1[x].contains(gladNum[xx].getGlad()))
                            {
                                gladCount[xx]++;
                            }
                            if(gladCount[xx]==30)
                            {
                                gladCount[xx]=0;
                                rocket1health[x]-=gladDamage;
                                roHealth[x]=(rocket1health[x])/divisor;
                                // tuHealth[x]-=;
                                rooHealth[x]=(int)roHealth[x];
                            }
                            if(house1[x].contains(gladNum[xx].getGlad()))
                            {
                                gladCount[xx]++;
                            }
                            if(gladCount[xx]==30)
                            {
                                gladCount[xx]=0;
                                house1health[x]-=gladDamage;
                                h1Health[x]=(house1health[x])/divisor;
                                h11Health[x]=(int)h1Health[x];
                            }
                            if(house2[x].contains(gladNum[xx].getGlad()))
                            {
                                gladCount[xx]++;
                            }
                            if(gladCount[xx]==30)
                            {
                                gladCount[xx]=0;
                                house2health[x]-=gladDamage;
                                h2Health[x]=(house2health[x])/divisor;
                                h22Health[x]=(int)h2Health[x];
                            }
                        }
                    }
                    for(int x=0;x<turret1.length;x++)
                    {
                        for(int xx=0;xx<tankNum.length;xx++)
                        {
                            if(turret1[x].contains(tankNum[xx].getTank()))
                            {
                                tankCount[xx]++;
                            }
                            if(tankCount[xx]==30)
                            {
                                tankCount[xx]=0;
                                turret1health[x]-=tankDamage;
                                tuHealth[x]=(turret1health[x])/divisor;
                                // tuHealth[x]-=;
                                tuuHealth[x]=(int)tuHealth[x];
                            }
                            if(tower1[x].contains(tankNum[xx].getTank()))
                            {
                                tankCount[xx]++;
                            }
                            if(tankCount[xx]==30)
                            {
                                tankCount[xx]=0;
                                tower1health[x]-=tankDamage;
                                toHealth[x]=(tower1health[x])/divisor;
                                tooHealth[x]=(int)toHealth[x];
                            }
                            if(rocket1[x].contains(tankNum[xx].getTank()))
                            {
                                tankCount[xx]++;
                            }
                            if(tankCount[xx]==30)
                            {
                                tankCount[xx]=0;
                                rocket1health[x]-=tankDamage;
                                roHealth[x]=(rocket1health[x])/divisor;
                                // tuHealth[x]-=;
                                rooHealth[x]=(int)roHealth[x];
                            }
                            if(house1[x].contains(tankNum[xx].getTank()))
                            {
                                tankCount[xx]++;
                            }
                            if(tankCount[xx]==30)
                            {
                                tankCount[xx]=0;
                                house1health[x]-=tankDamage;
                                h1Health[x]=(house1health[x])/divisor;
                                h11Health[x]=(int)h1Health[x];
                            }
                            if(house2[x].contains(tankNum[xx].getTank()))
                            {
                                tankCount[xx]++;
                            }
                            if(tankCount[xx]==30)
                            {
                                tankCount[xx]=0;
                                house2health[x]-=tankDamage;
                                h2Health[x]=(house2health[x])/divisor;
                                h22Health[x]=(int)h2Health[x];
                            }
                        }
                    }
                    for(int xx=0;xx<gladNum.length;xx++)
                    {
                        for(int x=0;x<turret1.length;x++)
                        {
                            if(turret1health[x]<=0)
                            {
                                if(turret1[x].contains(gladNum[xx].getGlad()))
                                {
                                    gladFight[xx]=false;
                                }
                                if(turret1[x].contains(tankNum[xx].getTank()))
                                {
                                    tankFight[xx]=false;
                                }
                            }
                            if(tower1health[x]<=0)
                            {
                                if(tower1[x].contains(gladNum[xx].getGlad()))
                                {
                                    gladFight[xx]=false;
                                }
                                if(tower1[x].contains(tankNum[xx].getTank()))
                                {
                                    tankFight[xx]=false;
                                }
                            }
                            if(rocket1health[x]<=0)
                            {
                                if(rocket1[x].contains(gladNum[xx].getGlad()))
                                {
                                    gladFight[xx]=false;
                                }
                                if(rocket1[x].contains(tankNum[xx].getTank()))
                                {
                                    tankFight[xx]=false;
                                }
                            }
                            if(house1health[x]<=0)
                            {
                                if(house1[x].contains(gladNum[xx].getGlad()))
                                {
                                    gladFight[xx]=false;
                                }
                                if(house1[x].contains(tankNum[xx].getTank()))
                                {
                                    tankFight[xx]=false;
                                }
                            }
                            if(house2health[x]<=0)
                            {
                                if(house2[x].contains(gladNum[xx].getGlad()))
                                {
                                    gladFight[xx]=false;
                                }
                                if(house2[x].contains(tankNum[xx].getTank()))
                                {
                                    tankFight[xx]=false;
                                }
                            }
                        }
                    }
                    for (int y=0;y<closestX.length;y++)
                    {
                        for(int x=0;x<closestX[y].length;x++)
                        {
                            //for(int xx=0;xx<gladNum.length;xx++)
                            {
                                if(y==0)
                                {
                                    if(turret1health[x]<=0)
                                    {
                                        turret1x[x]=6000;
                                        turret1[x].setLocation(turret1x[x],turret1y[x]);
                                        turretShotX[x]=6000;
                                        closestX[y][x]=6000;
                                        //destroyed++;
                                    }
                                }
                                if(y==1)
                                {
                                    if(tower1health[x]<=0)
                                    {
                                        tower1x[x]=6000;
                                        tower1[x].setLocation(tower1x[x],tower1y[x]);
                                        towerShotX[x]=6000;
                                        closestX[y][x]=6000;
                                        //destroyed++;
                                    }
                                }
                                if(y==2)
                                {
                                    if(rocket1health[x]<=0)
                                    {
                                        rocket1x[x]=6000;
                                        rocket1[x].setLocation(rocket1x[x],rocket1y[x]);
                                        rocketShotX[x]=6000;
                                        closestX[y][x]=6000;
                                        //destroyed++;
                                    }
                                }
                                if(y==3)
                                {
                                    if(house1health[x]<=0)
                                    {
                                        house1x[x]=6000;
                                        house1[x].setLocation(house1x[x],house1y[x]);
                                        closestX[y][x]=6000;
                                        //destroyed++;
                                    }
                                }
                                if(y==4)
                                {
                                    if(house2health[x]<=0)
                                    {
                                        house2x[x]=6000;
                                        house2[x].setLocation(house2x[x],house2y[x]);
                                        closestX[y][x]=6000;
                                        // destroyed++;
                                    }
                                }
                            }
                        }
                    }
                    if(turret1x[0]>1000 && turret1x[1]>1000 && turret1x[2]>1000 && turret1x[3]>1000
                    && tower1x[0]>1000 && tower1x[1]>1000 && tower1x[2]>1000 && tower1x[3]>1000
                    && rocket1x[0]>1000 && rocket1x[1]>1000 && rocket1x[2]>1000 && rocket1x[3]>1000
                    && house1x[0]>1000 && house1x[1]>1000 && house1x[2]>1000 && house1x[3]>1000
                    && house2x[0]>1000 && house2x[1]>1000 && house2x[2]>1000 && house2x[3]>1000)
                    {
                        playGame=false;
                        JOptionPane.showMessageDialog(null, "~YOU WIN!~");
                        turret1x[0]= -40000;

                    }
                }
                repaint();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void paint(Graphics gr)
    {
        Image i=createImage(getSize().width, getSize().height);
        Graphics2D gr2 = (Graphics2D)i.getGraphics();
        Graphics2D gr3 = (Graphics2D)i.getGraphics();
        if(homeScreen)
        {
            gr2.drawImage(homeScreenPic,0,0,this);
            gr2.setColor(Color.white);
            gr2.drawRect(171,500,480,157);
            gr2.drawRect(50,50,50,50);
            gr2.drawRect(125,50,50,50);
            gr2.drawRect(200,50,50,50);
            gr2.drawRect(571,50,200,50);
            if(difficultyCount==1)
            {
                gr2.setColor(Color.black);
                gr2.drawRect(48,48,54,54);
                gr2.drawRect(49,49,52,52);
                gr2.drawRect(50,50,50,50);
            }
            if(difficultyCount==2)
            {
                gr2.setColor(Color.black);
                gr2.drawRect(123,48,54,54);
                gr2.drawRect(124,49,52,52);
                gr2.drawRect(125,50,50,50);
            }
            if(difficultyCount==3)
            {
                gr2.setColor(Color.black);
                gr2.drawRect(198,48,54,54);
                gr2.drawRect(199,49,52,52);
                gr2.drawRect(200,50,50,50);
            }
        }
        if(instructionsPage)
        {
            gr2.drawImage(instructionsPic,0,0,this);
            gr2.drawRect(50,50,100,50);
        }
        if(playGame)
        {
            gr2.setColor(Color.green);
            gr2.fillRect(13,36,797,531);
            gr2.setColor(Color.black);
            gr2.drawRect(13,580,75,100);
            gr2.drawRect(93,580,75,100);
            gr2.drawRect(173,580,75,100);
            gr2.drawRect(253,580,75,100);
            gr2.drawRect(333,580,75,100);
            gr2.drawRect(413,580,75,100);
            gr2.drawRect(493,580,75,100);
            gr2.drawRect(573,580,75,100);
            gr2.drawRect(653,580,75,100);
            gr2.drawRect(733,580,75,100);
            if(selected==0)
            {
                gr2.fillRect(8,575,5,110);
                gr2.fillRect(88,575,5,110);
                gr2.fillRect(8,575,85,5);
                gr2.fillRect(8,680,85,5);
            }
            if(selected==1)
            {
                gr2.fillRect(88,575,5,110);
                gr2.fillRect(168,575,5,110);
                gr2.fillRect(88,575,85,5);
                gr2.fillRect(88,680,85,5);
            }
            if(selected==2)
            {
                gr2.fillRect(168,575,5,110);
                gr2.fillRect(248,575,5,110);
                gr2.fillRect(168,575,85,5);
                gr2.fillRect(168,680,85,5);
            }
            if(selected==3)
            {
                gr2.fillRect(248,575,5,110);
                gr2.fillRect(328,575,5,110);
                gr2.fillRect(248,575,85,5);
                gr2.fillRect(248,680,85,5);
            }
            if(selected==4)
            {
                gr2.fillRect(328,575,5,110);
                gr2.fillRect(408,575,5,110);
                gr2.fillRect(328,575,85,5);
                gr2.fillRect(328,680,85,5);
            }
            if(selected==5)
            {
                gr2.fillRect(408,575,5,110);
                gr2.fillRect(488,575,5,110);
                gr2.fillRect(408,575,85,5);
                gr2.fillRect(408,680,85,5);
            }
            if(selected==6)
            {
                gr2.fillRect(488,575,5,110);
                gr2.fillRect(568,575,5,110);
                gr2.fillRect(488,575,85,5);
                gr2.fillRect(488,680,85,5);
            }
            if(selected==7)
            {
                gr2.fillRect(568,575,5,110);
                gr2.fillRect(648,575,5,110);
                gr2.fillRect(568,575,85,5);
                gr2.fillRect(568,680,85,5);
            }
            if(selected==8)
            {
                gr2.fillRect(648,575,5,110);
                gr2.fillRect(728,575,5,110);
                gr2.fillRect(648,575,85,5);
                gr2.fillRect(648,680,85,5);
            }
            if(selected==9)
            {
                gr2.fillRect(728,575,5,110);
                gr2.fillRect(808,575,5,110);
                gr2.fillRect(728,575,85,5);
                gr2.fillRect(728,680,85,5);
            }

            for(int x=0;x<28;x++)
            {
                int y=(x*19)+36;
                gr2.drawLine(13,y,810,y);
            }
            for(int x=0;x<42;x++)
            {
                int y=(x*19)+13;
                gr2.drawLine(y,36,y,567);
            }
            gr2.drawRect(13,36,797,531);

            for(int x=0;x<turret1x.length;x++)
            {
                gr2.setColor(Color.red);
                gr2.fillRect(turret1x[x],turret1y[x],39,39);
                gr2.fillRect(turretShotX[x],turretShotY[x],4,4);
                gr2.setColor(Color.black);
                gr2.drawRect(turretShotX[x],turretShotY[x],4,4);
            }

            for(int x=0;x<tower1x.length;x++)
            {

                // gr2.fillRect(tower1x[x],tower1y[x],39,39);
                gr2.drawImage(Pentagon,tower1x[x],tower1y[x],this);
                gr2.setColor(Color.blue);
                gr2.fillRect(towerShotX[x],towerShotY[x],4,4);
                gr2.setColor(Color.black);
                gr2.drawRect(towerShotX[x],towerShotY[x],4,4);
            }

            for(int x=0;x<rocket1x.length;x++)
            {

                // gr2.fillRect(rocket1x[x],rocket1y[x],39,39);
                gr2.drawImage(Hexagon,rocket1x[x],rocket1y[x],this);
                gr2.setColor(Color.magenta);
                gr2.fillRect(rocketShotX[x],rocketShotY[x],4,4);
                gr2.setColor(Color.black);
                gr2.drawRect(rocketShotX[x],rocketShotY[x],4,4);
            }
            gr2.setColor(Color.orange);
            for(int x=0;x<house1x.length;x++)
            {
                gr2.drawImage(Triangle,house1x[x],house1y[x],this);
                gr2.drawImage(Triangle,house2x[x],house2y[x],this);
                //gr2.fillRect(house1x[x],house1y[x],39,39);
                //gr2.fillRect(house2x[x],house2y[x],39,39);
            }
            gr2.setColor(Color.black);
            for(int x=0;x<turret1x.length;x++)
            {
                gr2.fillRect(turret1healthX[x],turret1healthY[x],((tuuHealth[x])),5);
            }
            for(int x=0;x<tower1x.length;x++)
            {
                gr2.fillRect(tower1healthX[x],tower1healthY[x],((tooHealth[x])),5);
            }
            for(int x=0;x<rocket1x.length;x++)
            {
                gr2.fillRect(rocket1healthX[x],rocket1healthY[x],((rooHealth[x])),5);
            }
            for(int x=0;x<house1x.length;x++)
            {
                gr2.fillRect(house1healthX[x],house1healthY[x],((h11Health[x])),5);
            }
            for(int x=0;x<house2x.length;x++)
            {
                gr2.fillRect(house2healthX[x],house2healthY[x],((h22Health[x])),5);
            }
            for(int x=0; x<gladNum.length;x++)
            {
                if(pastGladNumber[x]==1)
                {
                    gladNum[x].drawGlad(gr2,gr3);
                }
            }

            for(int x=0; x<tankNum.length;x++)
            {
                if(pastTankNumber[x]==1)
                {
                    tankNum[x].drawTank(gr2,gr3);
                }
            }
            gr2.setColor(Color.black);
            for(int x=0;x<explosion.length;x++)
            {
                gr2.drawImage(explodePic,explosionX[x],explosionY[x],this);
                // gr2.fillRect(explosionX[x],explosionY[x],15,15);
            }
        }
        //gr2.drawLine(0, drawY, 2000, drawY);
        gr2.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public static void main(String[] args)
    {
        Driver frame = new Driver ();
        frame.setSize(822, 700);
        frame.setVisible(true);
    }

    public void update(Graphics g)
    {
        paint(g);
    } 

}