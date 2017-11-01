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
        if (o.xp%10 == 0){ //reproduce only while XP is divisible by ten
            if (Greenfoot.getRandomNumber(50) == 1){ //repro chance
                o.reproduce();
                int chosenMutation = Greenfoot.getRandomNumber(1000); //choose mutation
                if (chosenMutation <= 250){ //attack
                    o.att += 2;
                }
                else if((chosenMutation > 251) && (chosenMutation <= 500)){ //tankiness
                    o.def += 2;
                    o.maxHealth += 5;
                    o.speed -=1; //the tankier you are, the slower you are
                }
                else if((chosenMutation > 501) && (chosenMutation <= 750)){ //speed
                    o.speed += 2;
                }
                else if((chosenMutation > 751) && (chosenMutation <= 1000)){ //sight range
                    o.sight += 10;
                }
            }
        }
    }
    
    public static void mutate(Parasite p){
        
    }
        
    
}