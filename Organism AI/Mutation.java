import greenfoot.*;
import java.util.*;
/*
 * 
 *@author Ethan Gale
 *@Version 2.0 27/10/27
 *
 */
public class Mutation{
    public static void mutate(Organism o){
        if (o.xp%10 == 0 && o.xp != 0){
            if (Greenfoot.getRandomNumber(3) == 1){
                o.reproduce();
                int chosenMutation = Greenfoot.getRandomNumber(1000);
                if (chosenMutation <= 250){
                    o.att += 2;
                }
                else if((chosenMutation > 250) && (chosenMutation <= 500)){
                    o.def += 1;
                    o.maxHealth += 5;
                }
                else if((chosenMutation > 500) && (chosenMutation <= 750)){
                    o.speed += 2;
                }
                else if((chosenMutation > 750) && (chosenMutation <= 1000)){
                    o.sight += 10;
                }
            }
        }
    }
}