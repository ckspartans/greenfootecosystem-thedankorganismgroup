import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 * Needs lots of clean-up.
 * 
 * @author Uzair Ahmed
 * @0.2
 */

public class Algae extends AbstOrganism
{
    Color fill = new Color(200, 200,200);
    Color edge = new Color(0,255,0);
    public Algae(){
        ArrayList <AbstOrganism> lifeforms; //list of all the organsims in the game
        ArrayList <Object> prey;            //list of all that the types of organism can feed on
        ArrayList <Object> predators;       //list of all the types of organsims that the organism can be eaten by 
        
        age = 0;
        health = 1.0;
        trophicLevel = 0;
        energy = 10;
        size = 10;
        speed = 5;
        
        repro_age = 0;
        repro_energy = 0;
        mutation_rate = 0;
        att = 0;
        def = 0;

        MainWorld world;
        //lifeForms = new lifeForm (this);

        
    }
    
    public void act() 
    {
        if (world == null){world = (MainWorld) getWorld();}
        
        move();
        feed();
        grow();
        reproduce();
        age();

    }    
    
    public void move(){
        
    }

    public void feed(){
        
    }
    
    public void grow(){
        size = (int)(0.2*energy+5);
    }
    
    public void reproduce(){
        
    }
    public int age(){
        return 0;
    }  
    public void die(){
        
    }
    
    public void mutate(){
        
    }
}
