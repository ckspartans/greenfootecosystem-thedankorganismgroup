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
        if (o.xp >= o.maxXp){ //reproduce only while XP is divisible by ten
            o.xp = 0;
            if (Greenfoot.getRandomNumber(10) == 1){ //repro chance
                o.reproduce();
            }
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

    public static void mutate(Parasite p){
        if (p.xp >= p.mutateRate){
            //mutate the parasite
            int chosenMutation = Greenfoot.getRandomNumber(4);
            if (chosenMutation == 1){ //attack
                p.attBoost += 2;
            }
            else if(chosenMutation == 2){ //tankiness
                p.defBoost += 1;
                p.maxHealthBoost += 5;
            }
            else if(chosenMutation == 3){ //speed
                p.speedBoost += 2;
            }
            else if(chosenMutation == 4){ //sight range
                p.sightBoost += 10;
            }
            chosenMutation = Greenfoot.getRandomNumber(10000);
            if (chosenMutation == 69){

            }
        }
    }
}	