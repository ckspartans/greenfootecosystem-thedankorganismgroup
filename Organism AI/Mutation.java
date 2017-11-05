import greenfoot.*;
import java.util.*;

/**
 * Reproduce organism, then mutate it
 * 
 * @author Ethan Gale
 * @version 2.0
 */
public class Mutation{
    public static void mutate(Organism o){
        //Ethan Gale
        if (o.xp >= o.maxXp){ //reproduce only while XP is divisible by ten
            if (Greenfoot.getRandomNumber(10) == 1){ //repro chance
                o.xp = 0;o.att += 2;
                int chosenMutation = Greenfoot.getRandomNumber(1000); //choose mutation
                if (chosenMutation <= 250){ //attack
                    o.att += 2;
                }
                else if((chosenMutation > 251) && (chosenMutation <= 500)){ //tankiness
                    o.def += 2;
                    o.maxHealth += 50;
                    o.speed -=1; //the tankier you are, the slower you are
                }
                else if((chosenMutation > 501) && (chosenMutation <= 750)){ //speed
                    o.speed += 2;
                }
                else if((chosenMutation > 751) && (chosenMutation <= 1000)){ //sight range
                    o.sight += 50;
                }
                o.reproduce();
            }
        }
    }

    public static void mutate(Parasite p){
        //Ethan Gale

        if (p.xp >= p.mutateRate){
            //mutate the parasite
            int chosenMutation = Greenfoot.getRandomNumber(4);
            if (chosenMutation == 1){ //attack
                //subtract previous boost
                p.host.att -= p.attBoost;
                // increase boost
                p.attBoost += 2;
                //apply boost
                p.boost(0);
            }
            else if(chosenMutation == 2){ //tankiness
                p.host.def -= p.defBoost;
                p.host.maxHealth -= p.maxHealthBoost;
                p.defBoost += 1;
                p.maxHealthBoost += 5;
                p.boost(1);
            }
            else if(chosenMutation == 3){ //speed
                p.host.speed -= p.speedBoost;
                p.speedBoost += 2;
                p.boost(2);
            }
            else if(chosenMutation == 4){ //sight range
                p.host.speed -= p.sightBoost;
                p.sightBoost += 10;
                p.boost(3);
            }
            chosenMutation = Greenfoot.getRandomNumber(25000);
            if (chosenMutation == 69){ //super rare chance for organism to go insane
                p.insane = true;
            }
            else if (chosenMutation <= 9000){ //1/10 chance for organism to infect others
                p.organismInfections = true; 
            }
        }
    }
}
