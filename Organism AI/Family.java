import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Family Class
 *
 * @author Uzair Ahmed
 * @version 1.0
 */
public class Family extends Actor
{
    //Creates a list of all family organisms
    public List familyList = new ArrayList();

    //create a summed health value
    public double familyHealth;
    public Organism targetEnemy;
    public Boolean familyAttackMode = false;
    public Color color;
    
    public Family(Color c){
        color = c;
    }

    public void addOrganism(Organism o){
        //Uzair Ahmed
        //Adds an organism to the family list

        //Adds to list
        familyList.add(o);
    }

    public void remOrganism(Organism o){
        //Uzair Ahmed
        //Removes the organism from the family list

        //removes self from the familyList
        familyList.remove(o);
    }

    public int getAvgGroupPower(){
        //Uzair Ahmed
        //Gets the average group att for enemychoosing purposes

        int avgGroupAtt = 0;
        //go through everyone in the family
        for (int i = 0; i < (familyList.size()); i++){
            Organism tempOrg = (Organism)familyList.get(i);
            //add their att to the average
            avgGroupAtt += tempOrg.att;
        }
        //return the final average
        return (int) avgGroupAtt/familyList.size();
    }
}
