import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
* CHANGELOG: Oct 5, 2017
*         - Deleted custom variables.
*
* @author Uzair Ahmed
* @version 0.0.6
*/
public abstract class AbstOrganism extends Actor {

  //"Current" Variables
  public int age; //Time in seconds
  public int health; //Health
  public int xp; //XP - allows for RPG-like store for mutation
  public int radius; //Based off health, to visually see health
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

  MainWorld world;

  public abstract void moveAround();

  public abstract void consumeFood();

  public abstract void consumePlayer();

  public abstract void shrink(int damageTaken);

  public abstract void grow(int damageGiven);

  public abstract void reproduce();

  public abstract void age();

  public abstract void die();

}
