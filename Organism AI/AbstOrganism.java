import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
* CHANGELOG: Oct 9, 2017
*         - Added threat level variable.
*
* @author Uzair Ahmed
* @version 0.0.8
*/
public abstract class AbstOrganism extends Actor {

  //"Live" Variables
  public int age; //Time in seconds
  public int health; //Health
  public int xp; //XP - allows for RPG-like store for mutation
  public int radius; //Based off health, to visually see health
  public int threatLevel; // WE NEED TO CALCULATE THIS
  public Color playerColor; //Based off age, to visually see age.

  //XP Upgradeable Variables
  public int maxHealth; //Maximum Health
  public int maxXp; //Max XP Storage
  public int speed; //Nuff Said
  public int att; //Attck power
  public int def; //defensive power
  public int sight; //how far it can see

  public Color fill;
  public Color border;
  
  public List family;
  public List enemies ;
  public int myTeam;
  
  MainWorld world;

  public abstract void consumeFood(Food foodBeingEaten);

  public abstract void reproduce();

  public abstract void age();

  public abstract void die();

}
