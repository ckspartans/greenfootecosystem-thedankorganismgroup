import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * 
 * @author Cameron Dickie 
 * @version 0.1
 */
public class UIBack extends Actor
{
    GreenfootImage UIBack;
    public int width = 840;
    public int height = 1080;

    public UIBack() {
        UIBack = new GreenfootImage(width, height);
        UIBack.setColor(new Color(0,255,0));
        UIBack.fill();
    }

    public void act() 
    {
        // Add your action code here.
        setImage(UIBack);
    }
}
