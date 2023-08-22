import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.*;

public class FindDate extends JPanel implements MouseListener
{
   ArrayList<Person> list = new ArrayList<Person> ();
   
   Button sortButton = new Button(1000, 20, 20, 20);
   Button addTask = new Button(799, 20, 20, 20);
   
   public FindDate()
   {
      /*addPerson(4, 21, 2023, 0, "Calc Test", "got to study", "Calc", 0, 0, false);
      addPerson(4, 18, 2023, 2, "College essay", "need to study vocab", "Misc", 0, 20, false);
      addPerson(4, 18, 2023, 3, "Mock Exam", "hyperlink", "Calc BC", 0, 40, false);*/
      
      addMouseListener(this);
      setFocusable(true);
   }
   
   public void addPerson()
   {
      Person task = new Person();
      list.add(task);
   }
   
   public void addPerson(int taskMonth, int taskDay, int taskYear, int taskImportance, String taskName, String taskDescription, String taskSubject, int X, int Y, boolean status)
   {
      Person task = new Person(taskMonth, taskDay, taskYear, taskImportance, taskName, taskDescription, taskSubject, X, Y, status);
      list.add(task);
   }
   
   
   // Sort List
   public void swap(ArrayList<Person> arrList, int i, int j) 
   {
     Person temp = new Person(arrList.get(i));
     arrList.set(i, arrList.get(j));
     arrList.set(j, temp);
   }

   public int partition(ArrayList<Person> arrList, int down, int up) 
   {
     int pivot = arrList.get(up).getOrder();
     int i = down - 1;
     for (int j = down; j < up; j++) 
     {
        if (arrList.get(j).getOrder() <= pivot) 
        {
             i++;

             swap(arrList, i, j);
         }
     }
    
     swap(arrList, i+1, up);
    
     return i++;
     }
  
   public void sort(ArrayList<Person> arrList, int down, int up)
   {
     if (down < up)
     {
       // pi = partition index
       int pi = partition(arrList, down, up);
       sort(arrList, down, pi - 1);
       sort(arrList, pi + 1, up);
     }
     int k = 0;
     for(Person i : list)
     {
        i.changePos(0, (list.size() - k - 1) * 20);
        k += 1;
     }
   }
   
   
   //print graphics
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      
      // Background
      g.setColor(Color.GRAY);
      g.fillRect(0,0,1650,1080);
      
      for(Person i : list)
      {
         i.drawTo(g);
      }
      
      g.setColor(Color.BLACK);
      sortButton.drawTo(g);
      g.setColor(Color.RED);
      addTask.drawTo(g);
   }
  
   public int mainLoop()
   {
      while(true)
      { 
         
      }
   }
  
  
   
   public void mousePressed(MouseEvent e)
   {
      // getButton tells us which button was clicked.
      int button = e.getButton();
       
      // BUTTON1 = left click
      if(button == MouseEvent.BUTTON1)
      {
         int x = e.getX();
         int y = e.getY();
         
         if(sortButton.clicked(x,y))
         {
            sort(list, 0, list.size() - 1);
            System.out.println("sorted");
         }
         else if(addTask.clicked(x,y))
         {
            addPerson();
            sort(list, 0, list.size() - 1);
         }
         else
         {
            for(Person i : list)
            {
               if(i.clickBox(x,y))
               {
               
               }
            }
         }
      }
      repaint();
   }
   public void mouseReleased(MouseEvent e)
   {
   }
   public void mouseClicked(MouseEvent e)
   {
   }  
   public void mouseEntered(MouseEvent e)
   { 
      int x = e.getX();
      int y = e.getY();
   } 
   public void mouseExited(MouseEvent e)
   { 
   } 
}

interface Room
{
  public int mainLoop();
}
