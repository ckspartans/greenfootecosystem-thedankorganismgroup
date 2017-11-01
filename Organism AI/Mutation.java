import greenfoot.*;
import java.util.*;
/*
 * 
 *@author Ethan Gale
 *@Version 3.2 01/11/17
 *
 */
public class Mutation{
    public static void mutate(Organism o){ //mutate the organism
        if (o.xp%10 == 0){ //reproduce only while XP is divisible by ten
            if (Greenfoot.getRandomNumber(50) == 1){ //repro chance
                o.reproduce();
                int chosenMutation = Greenfoot.getRandomNumber(8); //choose mutation
                if (chosenMutation == 0){ //mutate more attack
                    o.att += 2;
                }
                else if(chosenMutation == 1){ //mutate more tankiness
                    o.def += 2;
                    o.maxHealth += 5;
                    o.speed -=1; //the tankier you are, the slower you are
                }
                else if(chosenMutation == 2){ //mutate more speed
                    o.speed += 2;
                }
                else if(chosenMutation == 3){ //mutate more sight range
                    o.sight += 10;
                }
                chosenMutation = Greenfoot.getRandomNumber(49);
                if (chosenMutation == 0){ //mutate more parasite resistance
                   o.parasiteResistance += 1; 
                }
                chosenMutation = Greenfoot.getRandomNumber(25000); //set up rare mutation(s)
                if (chosenMutation == 0){ //REEEEEEE mode
                    o.att = 10;
                    o.speed = 15;
                    o.sight += 50;
                    o.parasiteResistance = 10;
                    o.maxHealth = 3;
                    //big stats, lowered health so it can rampage but it's killable
                }
            }
            }
           }
    public static void mutate(Parasite p){
        if (p.xp >= p.mutateRate){
            //mutate the parasite
            int chosenMutation = Greenfoot.getRandomNumber(3);
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
            else if(chosenMutation == 0){ //sight range
                p.sightBoost += 10;
            }
            chosenMutation = Greenfoot.getRandomNumber(10000);
            if (chosenMutation == 666){ //super rare, organism becomes insane and starts eating everything (uberabies)
                p.insane = true;
            }
            else if(chosenMutation >= 9000){ //1/10 chance for organism 
                p.organismInfections = true;    
            }
        }
    }
}	