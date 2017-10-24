import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AbstParasite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AbstParasite extends Actor
{
    public int siphonRate; //amount of energy it steals
    public int power; //the strength of the parasite (determines what it can infect)
    public int mutateRate; //speed of which it mutates
    
    MainWorld world;
    public abstract void reproduce();

    public abstract void mutate();

    public abstract void siphonEnergy();

    public abstract void die();
}
