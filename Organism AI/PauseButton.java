import greenfoot.*;
/**
@author Cameron Dickie
@version 0.0.6
*/
public class PauseButton extends AbstButton {
    
  GreenfootImage img;
  public PauseButton() {
      img = new GreenfootImage(100,100);
      img.setColor(new Color(220,220,220));
      img.fill();
   }
  public void OnMouseOver() {
    //change the shading of the button

  }
  public void OnClick() {
    //enters the world of PauseScreen and stops running the act method of world
  }
  public void act() {
      draw();
  }
  public void draw() {
      setImage(img);
  }
}
