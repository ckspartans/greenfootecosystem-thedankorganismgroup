import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
* CHANGELOG: Sept 30, 2017
*       - Changed energy to xp. XP will now be skillpoints that you earn in
*  order to mutate, and reproduce.
*
*       - Added CustomValues section in which the player can change constants
*   for a custom game, these settings can be edited through the UI.
*
*       - Added upgradeableable constants section. These include speed,
*  attack, defense, max health, and max xp.
*
*       - Removed repro_age/repro_energy. Age will be replaced by xp units,
*  once player has gained a set amount of xp, player can reproduce. Energy
*  required will now be a flat 35% of max xp storage.
*
*       - Mutation_rate, and trophic level are now irrelevant
*
* @author Uzair Ahmed
* @version 0.0.5
*/
public abstract class AbstOrganism extends Actor {
  static public ArrayList<Actor> lifeforms = new ArrayList<Actor>(); //list of all the organsims in the game
  public ArrayList<Object> prey; //list of all that the types of organism can feed on
  public ArrayList<Object> predators; //list of all the types of organsims that the organism can be eaten by

  public GreenfootImage[] imgs;

  /* CustomValues. Feel free to edit these as you wish:
  *  In fact, these variables can be put in a settings menu to be
  *  changed using sliders and whatnot for a custom game. */
  public int startingMaxHealth;
  public int startingMaxXp;
  //public int startingSpeed;
  public int startingAttackPower;
  public int startingDefensePower;
  public int maxBuyableMaxHealth;
  public int maxBuyableMaxXp;
  public int maxBuyableSpeed;
  public int maxBuyableAtt;
  public int maxBuyableDef;

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

  MainWorld world;

  public abstract void moveAround();

  //public abstract void follow();

  public abstract void consumeFood();

  public abstract void consumePlayer();

  public abstract void shrink(int damageTaken);

  public abstract void grow(int damageGiven);

  public abstract void reproduce();

  public abstract void age();

  public abstract void die();

}
