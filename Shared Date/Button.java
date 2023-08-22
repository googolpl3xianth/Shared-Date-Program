import java.awt.Graphics;
import java.awt.Color;

public class Button
{
   private int x;
   private int y;
   private int width;
   private int height;
   
   public Button()
   {
      x = 0;
      y = 0;
      width = 0;
      height = 0;
   }
   
   public Button(int X, int Y, int buttonWidth, int buttonHeight)
   {
      x = X;
      y = Y;
      width = buttonWidth;
      height = buttonHeight;
   }
   
   public void drawTo(Graphics g)
   {
      g.fillRect(x, y, width, height);
   }
   
   public boolean clicked (int X, int Y)
   {
      return X > x && X < x + width && Y > y && Y < y + height;
   }
}