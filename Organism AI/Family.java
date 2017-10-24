import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Family Class
 *
 * @author Uzair Ahmed
 * @version 0.1
 */
public class Family extends Actor
{
    public List familyList = new ArrayList();
    public Organism alpha;
    public Organism targetEnemy;
    public Boolean familyAttackMode = false;

    public Family() {

    }

    public void addOrganism(Organism o){
        familyList.add(o);
        alpha = o;
    }

    public void remOrganism(Organism o){
        familyList.remove(o);
    }

    public void setAlpha(){
      int tempAge=0;
      for (int i = 0; i < familyList.size(); i++){
        Organism tempOrganism = (Organism)familyList.get(i);
        if (tempOrganism.age > tempAge){
          tempAge = tempOrganism.age;
          alpha = tempOrganism;
          alpha.myColor = Color.WHITE;
        }
      }
    }
    
    public int getGroupPower(){
        //This gets all the attack power of the organisms in the group
        //needs uzair's code
        int groupAtt = 0;

        for (int i = 0;i < (familyList.size()); i++){
            Organism tempOrg = (Organism)familyList.get(i);
            groupAtt += tempOrg.att;
        }

        return groupAtt;
    }
    
    public int getAvgGroupPower(){
        int avgGroupAtt = 0;
        for (int i = 0;i < (familyList.size()); i++){
            Organism tempOrg = (Organism)familyList.get(i);
            avgGroupAtt += tempOrg.att;
        }
        return (int) avgGroupAtt/familyList.size();
    }

    public void checkIfAttacking(){ //checks if anyone in the group is attacking someone
        for (int i = 0; i > familyList.size(); i++){ 
            Organism tempOrg;
            tempOrg = (Organism)familyList.get(i);
            if (tempOrg.attackMode == true){ //if anyone in your family is attaccking someone
                Organism enemy = tempOrg.chosenEnemy;

                targetEnemy = enemy; //using their chosenEnemy value, which enemy is it?
                familyAttackMode = true; //set your own attackmode to true
                int tatic = tempOrg.attackTatic; //finds which tatic that organism is using
                //attack(o, enemy, tatic); //attack that enemy
            }
        }
    }
    
    public void act()
    {
        setAlpha();
    }
}
