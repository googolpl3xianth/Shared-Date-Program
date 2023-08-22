/* This is a basic main method to be used with graphical applications.
   It can technically go in the same class as the Panel class, but we've
   separated it here for clarity.
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*; 

public class DateDisplay
{ 
   public static void main(String[] args)
   {  
      // Initialize the window
      JFrame frame = new JFrame();
       
      // Text field
      
      // Set the width and height of the window in pixels
      frame.setSize(1650,1080);
   
      // Make the program end when the window is closed
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
      // Initialize the drawing canvas
       
      FindDate panel = new FindDate();
       
      // Add the drawing canvas to the window
      frame.add((FindDate)panel);
       
      // Show the window when we are done with all of our initialization
      frame.setVisible(true);
      
      /*
      panel = switchRoom(((Room1)panel).mainLoop() , panel, frame);
      
      while (true)
      {
         panel = switchRoom(panel.mainLoop(), panel, frame);
      }*/
   }
  /*
   public static Room switchRoom(int room, Room newPanel, JFrame newFrame)
   {
      newFrame.remove((JPanel)newPanel);
      
      // set panel to the new room
      if (room == 1)
      {
         newPanel = new Room1();
      }
      
      if (room == 2)
      {
         newPanel = new Room2();
      }
      
      if (room == 3)
      {
         newPanel = new Room3();
      }
      
      if (room == 4)
      {
         newPanel = new Room4();
      }
      if (room == 5)
      {
         newPanel = new Room5();
      }
      if (room == 6)
      {
         newPanel = new Room6();
      }
      if (room == 7)
      {
         newPanel = new Room7();
      }
      if (room == 0)
      {
         newPanel = new RoomX();
      }
      
      newFrame.add((JPanel)newPanel);
      
      newFrame.setVisible(false);
      newFrame.setVisible(true);
      
      return newPanel;
   }*/
}