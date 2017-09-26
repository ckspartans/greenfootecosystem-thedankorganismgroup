import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Basic brainstorming of Abstract Class for any organism.
 * @author Uzair A, Laura B, and Cameron D
 * @version 0.0.1
 */
public abstract class AbstOrganism extends Actor
{
    int age;        	//Time since entry into world
    int hunger;		//As size increases, hunger increases. if no food is eaten before hunger time, organism drops by 1 class. 
    int size;		//Calculates class, based of value from 0-100
    int speed;		//As size increases, organism moves slower.
    int children;		//The number of children it can produce
    int orgClass;	//Class system based on size
    boolean isAlive;
    
    public abstract void beginTimer();
    
    public abstract void spawn(int type);
    
    public abstract void reproduce(int size, int children); //Divides the current
    
    public abstract void consume(int sizePredator, int sizePrey, int agePredator, int agePrey, int time);
    //{
        //healthDisplaced = time * 1/(agePredator) * agePrey*10
        //sizePredator += healthDisplaced
        //sizePrey -= healthDisplaced
    //}
    
    public abstract void starve(int size); //Drop in size based on hunger
}
