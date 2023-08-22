import java.util.*;
import java.awt.Graphics;
import java.awt.Color;

import java.time.LocalDateTime;


class Person
{        
   private Date[] dates;
  
   private String name;
   
   //private int x;
   //private int y;
  
   public String toString()
   {
      String text = name;
      for(Date a : dates)
      {
         text += "\n" + a;
      }
      return text;
   }
  
  
  
   //defualt Person
   public Person()
   {
      name = "no name";
    
      dates = new Date[Schedule.lowerBound.compare(Schedule.upperBound) + 1];
    
      for(int i = 0; i < dates.length; i++)
      {
         Date temp = new Date(Schedule.lowerBound);
         temp.addDays(i);
         dates[i] = new Date(temp);
      }
   }
   public Person(String newName)
   {
      name = newName;
    
      dates = new Date[Schedule.lowerBound.compare(Schedule.upperBound) + 1];
    
      for(int i = 0; i < dates.length; i++)
      {
         Date temp = new Date(Schedule.lowerBound);
         temp.addDays(i);
         dates[i] = new Date(temp);
      }
   }
  
   //Assigned Person
   public Person(String newName, int datesTimes[][][], ArrayList<String>[] datesTypes)
   {
      name = newName;
    
      dates = new Date[Schedule.lowerBound.compare(Schedule.upperBound) + 1];
      for(int i = 0; i < datesTimes.length; i++)
      {
         Date temp = new Date(Schedule.lowerBound);
         temp.addDays(i);
         temp.addTimes(datesTimes[i], datesTypes[i]);
         dates[i] = new Date(temp);
         
      }
      
   }
   
   public Person(Person newPerson, String newName)
   {
      name = newName;
      
      int period = Schedule.lowerBound.compare(Schedule.upperBound) + 1;
      
      Date[] tempDates = newPerson.getDates();
      dates = new Date[period];
      for(int i = 0; i < period; i++)
      {
         dates[i] = new Date(tempDates[i]);
      }
   }
  
   public Person(Person newPerson)
   {
      name = newPerson.name;
      
      int period = Schedule.lowerBound.compare(Schedule.upperBound) + 1;
      
      Date[] tempDates = newPerson.getDates();
      dates = new Date[period];
      for(int i = 0; i < period; i++)
      {
         dates[i] = new Date(tempDates[i]);
      }
   }
  
  
   //edit assignment details
   public void changeName(String taskName)
   {
      name = taskName;
   }
  
   //change type
   public void changeType(int indexDay, int indexType, String newType)
   {
      dates[indexDay].changeType(indexType, newType);
   }
  
   /*public void changePos(int X, int Y)
   {
      x = X;
      y = Y;
   }*/
  
   //add times
   public void addTime(int startHr, int startMin, int endHr, int endMin, String newType, int dateIndex)
   {
      dates[dateIndex].addTime(startHr, startMin, endHr, endMin, newType);
   }
  
  
   // get date
   public int getDay(int index)
   {
      return dates[index].getDay();
   }
   public int getMonth(int index)
   {
      return dates[index].getMonth();
   }
   public int getYear(int index)
   {
      return dates[index].getYear();
   }  
   public Date[] getDates()
   {
      return dates;
   }
   public Date getDate(int index)
   {
      return dates[index];
   }
   public String getName()
   {
      return name;
   }
  
   public static void main(String[] args) 
   {
      Date startDate = new Date(7,5,2023);
      Date endDate = new Date(7,12,2023);
      Schedule.setBounds(startDate, endDate);
      
      //Sam
      Person sam = new Person("Sam");
      sam.addTime(0,0,12,0,"can", 0);
      sam.addTime(12,0,24,0,"cant", 0);
      sam.addTime(0,0,24,0,"cant", 1);
      sam.addTime(0,0,24,0,"cant", 2);
      sam.addTime(0,0,24,0,"can", 3);
      sam.addTime(0,0,24,0,"can", 4);
      sam.addTime(0,0,24,0,"preferNot", 5);
      sam.addTime(0,0,24,0,"unknown", 6);
      sam.addTime(0,0,24,0,"unknown", 7);
      
      //silas
      Person silas = new Person("Silas");
      silas.addTime(0,0,24,0,"can", 0);
      silas.addTime(0,0,24,0,"can", 1);
      silas.addTime(0,0,24,0,"preferNot", 2);
      silas.addTime(0,0,24,0,"preferNot", 3);
      silas.addTime(0,0,24,0,"cant", 4);
      silas.addTime(0,0,24,0,"can", 5);
      silas.addTime(0,0,24,0,"unknown", 6);
      silas.addTime(0,0,24,0,"can", 7);
      
      
      Person schedule = new Person(sam, "Schedule");
      for(int i = 0; i < schedule.dates.length; i++)
      {
         schedule.dates[i].changeTypes(sam.comparePeople(silas, i));
      }
      
      System.out.println(schedule);
   }
   
   
   public void equalizeDates(Person a, Person b)
   {
      for(int i = 0; i < dates.length; i++)
      {
         Date.equalizeTimes(a.dates[i], b.dates[i]);
      }
   }
   
   
   
   // compares this person with person b at a specific date, returns a ArrayList<String> of types
   public ArrayList<String> comparePeople(Person b, int index)
   {
      equalizeDates(this, b);
      Date temp = new Date(this.dates[index]);
      for(int j = 0; j < dates[index].getTimesSize(); j++)
      {
         if(dates[index].getType(j) == "cant" || b.dates[index].getType(j) == "cant")
         {
            temp.changeType(j, "cant");
         }
         else if(dates[index].getType(j) == "unknown" || b.dates[index].getType(j) == "unknown")
         {
            temp.changeType(j, "unknown");
         }
         else if(dates[index].getType(j) == "preferNot" || b.dates[index].getType(j) == "preferNot")
         {
            temp.changeType(j, "preferNot");
         }
         else if(dates[index].getType(j) == "can" && b.dates[index].getType(j) == "can")
         {
            temp.changeType(j, "can");
         }
      }
    
      return temp.getTypes();
   }
   
   public ArrayList<String> comparePeople(Schedule a, int index)
   {
      Date temp = new Date(a.getDate(index));
      for(int j = 0; j < a.getDate(index).getTimesSize(); j++)
      {
         if(a.getDate(index).getType(j) == "cant" || dates[index].getType(j) == "cant")
         {
            temp.changeType(j, "cant");
         }
         else if(a.getDate(index).getType(j) == "unknown" || dates[index].getType(j) == "unknown")
         {
            temp.changeType(j, "unknown");
         }
         else if(a.getDate(index).getType(j) == "preferNot" || dates[index].getType(j) == "preferNot")
         {
            temp.changeType(j, "preferNot");
         }
         else if(a.getDate(index).getType(j) == "can" && dates[index].getType(j) == "can")
         {
            temp.changeType(j, "can");
         }
      }
    
      return temp.getTypes();
   }
  
  /*
  //graphics
  int taskWidth = 300;
  int taskHeight = 20;
  int checkSide = taskHeight / 2;
  
  public void drawTo(Graphics g)
  {
    //backdrop
    g.setColor(Color.BLACK);
    g.fillRect(x, y, taskWidth, taskHeight);
    
    //checkmark
    if(importance == 3)
    {
      g.setColor(Color.RED);
    }
    else if(importance == 2)
    {
      g.setColor(Color.YELLOW);
    }
    else if(importance == 1)
    {
      g.setColor(Color.BLUE);
    }
    else
    {
      g.setColor(Color.GRAY);
    }
    if(completed)
    {
      g.fillRect(x + 5, y + 5, checkSide, checkSide);
    }
    else
    {
      g.drawRect(x + 5, y + 5, checkSide, checkSide);
    }
    
    //text
    g.setColor(Color.WHITE);
    g.drawString(this.toString(), x + (checkSide * 2), y + 5 + checkSide);
    
    //multi-option button
    g.setColor(Color.BLACK);
    Button options = new Button(x + taskWidth - (checkSide * 2), y, taskHeight, taskHeight);
    options.drawTo(g);
    g.setColor(Color.GRAY);
    g.fillOval(x + taskWidth - (2 * taskHeight / 3), y + 2, checkSide / 2, checkSide / 2);
    g.fillOval(x + taskWidth - (2 * taskHeight / 3), y + 7, checkSide / 2, checkSide / 2);
    g.fillOval(x + taskWidth - (2 * taskHeight / 3), y + 12, checkSide / 2, checkSide / 2);

    
    
  }
  
  public void toolBar(Graphics g)
  {
     
  }
  
  //multi-option button
  public boolean clickOpt(int X, int Y)
  {
     if(X > x + taskWidth - (checkSide * 2) && X < x + taskWidth - (checkSide * 2) + taskHeight && Y > y && Y < y + taskHeight)
     {
        return true;
     }
     else
     {
        return false;
     }
  }
  
  // check box
  public boolean clickBox (int X, int Y)
  {
    if(X > x + 5 && X < x + checkSide + 5 && Y > y + 5 && Y < y + checkSide + 5)
    {
      completed = !completed;
      return true;
    }
    else
    {
      return false;
    }
  }
  
  //click assignment
  public boolean clicked (int X, int Y)
  {
    return !clickBox(X,Y) && (X > x && X < x + taskWidth && Y > y && Y < y + taskHeight);
  } */
}