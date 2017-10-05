import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Global Variables
 */
public class gvars
{
  static public ArrayList<Actor>  lifeForms = new ArrayList<Actor>(); //list of all the organsims in the game
  static public ArrayList<Object> food; //list of all the food
  static public ArrayList<Object> myOrgansims; //list of all friendle organisms
  static public ArrayList<Object> enemyOrganisms;

  static int startingMaxHealth = 10;
  static int startingMaxXp = 10;
  static int startingSpeed = 5;
  static int startingAttackPower = 1;
  static int startingDefensePower = 1;
  static int startingSight = 200;
  
  static int maxBuyableMaxHealth = 100;
  static int maxBuyableMaxXp = 100;
  static int maxBuyableSpeed = 10;
  static int maxBuyableAtt = 10;
  static int maxBuyableDef = 10;
  static int maxBuyableSight = 10;


}
