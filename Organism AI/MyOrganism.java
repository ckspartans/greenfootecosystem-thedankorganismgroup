import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Organism class for player. This will the the player specific functions.
 *
 * @author Uzair Ahmed
 * @version 0.3
 */
public class MyOrganism extends Organism
{
  //Takes in the custom variables (located in MainWorld), and sets them for the whole class to use.
  public MyOrganism(int smh, int smxp, int ss, int sa, int sd, int ssi, int am) {
    //Game Variables
    attMult = am;
    //XP Upgradeable Variables-------------------------------------------------
    maxHealth = smh; //Maximum Health
    maxXp = smxp; //Max XP Storage
    speed = ss; //Nuff Said
    att = sa; //Attack power
    def = sd; //Defensive power
    sight = ssi; //Sight

    //"Live" Variables. ----------------------------------------------------
    age = 0; //Time
    health = maxHealth-2; //Out of maxHealth
    xp = 0; //Out of maxXp
    radius = health*2;
    fill = new Color(0, 0, 0);
    border = new Color(0, 0, 255);
    threatLevel = (att*health*def)/(1+age);
    
    MainWorld world;
  }

    public void act()
    {
        drawOrganism(Color.GREEN, radius);
        AI.think(this);
    }
}
