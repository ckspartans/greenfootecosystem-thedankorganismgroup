/*import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


 * Pretty much the same as MyOrganism, this is being used for easy differenciation
 * between Enemy and Friendly
 *
 * @author (your name)
 * @version (a version number or a date)


public class EnemyOrganism extends AbstOrganism {
  protected int speed = 5;
  protected int organismX; //X location (can be protected
  protected int organismY;//Y location (can be protected

  public EnemyOrganism() {

    // Custom Variables
    int startingMaxHealth = 10;
    int startingMaxXp = 10;
    int startingSpeed = 5;
    int startingAttackPower = 1;
    int startingDefensePower = 1;
    int maxBuyableMaxHealth = 100;
    int maxBuyableMaxXp = 100;
    int maxBuyableSpeed = 10;
    int maxBuyableAtt = 10;
    int maxBuyableDef = 10;

    //XP Upgradeable Variables
    int maxHealth = startingMaxHealth; //Maximum Health
    int maxXp = startingMaxXp; //Max XP Storage
    int speed = startingSpeed; //Nuff Said
    int att = startingAttackPower; //Attack power
    int def = startingDefensePower; //Defensive power

    //"Current" Variables. (These are live variables that are constantly changing.
    int age = 0; //Time
    int health = startingMaxHealth; //Out of maxHealth
    int xp = 0; //Out of maxXp
    int radius = health; //Same as value from health
    Color enemyFill = new Color(0, 0, 255); //Same as value mapped to 255.
    Color enemyBorder = new Color(0, 0, 255);

    MainWorld world;
  }

  public void act() {

    move();
    consumeFood();
    consumePlayer();
    reproduce();
    age();

  }

  protected void move() {
    if (Greenfoot.isKeyDown("w")) {
      organismY = -speed;
    } else if (Greenfoot.isKeyDown("s")) {
      organismY = speed;
    } else {
      organismY = 0;
    }
    if (Greenfoot.isKeyDown("a")) {
      organismX = -speed;
    } else if (Greenfoot.isKeyDown("d")) {
      organismX = speed;
    } else {
      organismX = 0;
    }
    setLocation(getX() + organismX, getY() + organismY);
  }

  public void consumeFood() {
    if (getOneIntersectingObject(Food.class) != null) {
      //world.foodEaten ++;
      //world.removeObject(); //Try and find a way to remove the object it touches
      //call grow (or add it to a temp health variable) to increase health
      //add xp
    }

  }

  public void consumePlayer() {
    if (getOneIntersectingObject(TestOrganism.class) != null) { //Change testOrganism to enemyOrganism
      //do stuff to return damage int to enemy and to self.
      //add xp
    }

  }

  public void shrink(int damageTaken) {
    //takes damagetaken and applys it to health and radius
  }

  public void grow(int damageGiven) {
    //takes damageGiven and applys it to health and radius
  }

  public void age() {
    //start a timer in act() and use that value to edit age value, and color
  }

  public void reproduce() {
    //create new myOrganism object and do magic
    //remove xp
    //drop in size
  }

  public void die() {
    //Remove the object.
    //disperse as food througout the world
  }
}
*/
