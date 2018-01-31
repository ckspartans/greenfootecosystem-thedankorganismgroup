import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Family Class
 *
 * @author Uzair Ahmed
 * @version 0.7
 */
public class Family extends Actor
{
    //Creates a list of all family organisms
    public List familyList = new ArrayList();
    //Creates an alpha
    public Organism alpha;
    //create a summed health value
    public double familyHealth;
    public Organism targetEnemy;
    public Boolean familyAttackMode = false;

    public Family() {
        //
    }

    //Adds an organism to the family list
    public void addOrganism(Organism o){
        //Uzair Ahmed

        //Adds to list
        familyList.add(o);
        //sets alpha to the current organism
        alpha = o;
    }

    //Removes the organism from the family list
    public void remOrganism(Organism o){
        //Uzair Ahmed

        //removes self from the familyList
        familyList.remove(o);
    }

    //Sets the alpha (leader) of the family
    public void setAlpha(){
        //Uzair Ahmed

        //creates a temporary age
        int tempAge=0;
        //goes through each organism in the familyList
        for (int i = 0; i < familyList.size(); i++){
            //creates a temporary Organism
            Organism tempOrganism = (Organism)familyList.get(i);
            //checks if the age of the current organism is greater than the max
            if (tempOrganism.age > tempAge){
                //Sets the new max to the organisms age
                tempAge = tempOrganism.age;
                //sets the new alpha to the current organism
                alpha = tempOrganism;
            }
        }
    }

    public int getAvgGroupPower(){
        int avgGroupAtt = 0;
        for (int i = 0;i < (familyList.size()); i++){
            Organism tempOrg = (Organism)familyList.get(i);
            avgGroupAtt += tempOrg.att;
        }
        return (int) avgGroupAtt/familyList.size();
    }

    public void act()
    {
        //runs the setAlpha method
        setAlpha();
    }
}

