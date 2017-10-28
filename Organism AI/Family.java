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
        setAlpha();
    }
}
