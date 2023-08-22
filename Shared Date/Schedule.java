import java.util.*;
import java.awt.Graphics;
import java.awt.Color;

import java.time.LocalDateTime;


class Schedule
{     
   private Date[] scheduleDates;
   
   private String name;
   
   //Range
   public static Date lowerBound;
   public static Date upperBound;
   
   private ArrayList<Person> people = new ArrayList<Person>();
  
   // Classes
   private ArrayList<Period> classes = new ArrayList<Period>();
   
   public String toString()
   {
      String text = name;
      for(Date a : scheduleDates)
      {
         text += "\n" + a;
      }
      return text;
   }
  
   //defualt Person
   public Schedule()
   {
      name = "Untitled Schedule";

      scheduleDates = new Date[lowerBound.compare(upperBound) + 1];
    
      for(int i = 0; i < scheduleDates.length; i++)
      {
         Date temp = new Date(lowerBound);
         temp.addDays(i);
         scheduleDates[i] = new Date(temp);
      }
  }
  
  //Assigned Person
   public Schedule(String newName)
   {
      name = newName;
    
      scheduleDates = new Date[lowerBound.compare(upperBound) + 1];
    
      for(int i = 0; i < scheduleDates.length; i++)
      {
         Date temp = new Date(lowerBound);
         temp.addDays(i);
         scheduleDates[i] = new Date(temp);
      }
   }
  
   public Schedule(Person newPerson)
   {
      name = newPerson.getName();
      
      int period = lowerBound.compare(upperBound) + 1;
      
      Date[] tempDates = newPerson.getDates();
      scheduleDates = new Date[period];
      for(int i = 0; i < period; i++)
      {
         scheduleDates[i] = new Date(tempDates[i]);
      }
   }
   public Schedule(Person newPerson, String newName)
   {
      name = newName;
      
      int period = lowerBound.compare(upperBound) + 1;
      
      Date[] tempDates = newPerson.getDates();
      scheduleDates = new Date[period];
      for(int i = 0; i < period; i++)
      {
         scheduleDates[i] = new Date(tempDates[i]);
      }
   }
   
   // get private variables
   public Date[] getDates()
   {
      return scheduleDates;
   }
   public Date getDate(int index)
   {
      return scheduleDates[index];
   }
   
   public String getTypeDates(String type)
   {
      findSharedDates();
      String schedule = "";
      
      for(int j = 0; j < scheduleDates.length; j++)
      {
         String slots = "";
         int tempYear = scheduleDates[j].getYear();
         
         if(scheduleDates[j].getTimesSize() == scheduleDates[j].getTypesSize())
         {
            for(int k = 0; k < scheduleDates[j].getTimesSize(); k++)
            {
               if(scheduleDates[j].getType(k).equals(type))
               {
                  slots += scheduleDates[j].timeToString(k);
               }
            }
         }
         else
         {
            System.out.println("Error, timeSlots.size() != type.size() when printing Date " + scheduleDates[j].getMonth() + "/" + scheduleDates[j].getDay() + "/" + (tempYear % 100) + " timeSlots: " + scheduleDates[j].getTimesSize() + " typeSize: " + scheduleDates[j].getTypesSize());
            System.exit(0);
         }
         if(slots != "")
         {
            if(j != scheduleDates.length - 1)
            {
               schedule += scheduleDates[j].getMonth() + "/" + scheduleDates[j].getDay() + "/" + (tempYear % 100) + " " + slots + "\n";
            }
         }
      }
      if(schedule == "")
      {
         return "no dates with type: " + type;
      }
      return "Dates in: " + name + " with type: " + type + "\n" + schedule;
   }
   
   // returns the people and their types at certain index for dates and times
   public String getNumOfType(int index, int[][] time)
   {
      findSharedDates();
      
      ArrayList<int[][]> tempTimes = Date.equalizeTimes(time, scheduleDates[index]);
      
      int removeF = 0;
      int removeE = 0;
      for(int i = 0; i < tempTimes.size(); i++)
      {
         int tempTime[][] = tempTimes.get(i);
         if(tempTime[0][0] == time[0][0] && tempTime[0][1] == time[0][1] )
         {
            removeF = i;
         }
         if(tempTime[0][0] == time[1][0] && tempTime[0][1] == time[1][1])
         {
            removeE = i - tempTimes.size();
         }
      }
      for(int i = 0; i < removeE; i++)
      {
         int j = tempTimes.size();
         tempTimes.remove(j);
      }
      for(int i = 0; i < removeF; i++)
      {
         tempTimes.remove(0);
      }
      
      String tempType = "";
      
      String text = "";
      
      if(!people.isEmpty())
      {
         ArrayList<ArrayList<String>>[] types = new ArrayList[tempTimes.size()];
         
         for(int k = 0; k < tempTimes.size(); k++)
         {
            ArrayList<ArrayList<String>> tempTimeArray = new ArrayList<ArrayList<String>>();
            types[k] = tempTimeArray;
            for(int i = 0; i < people.size(); i++)
            {
               tempType = people.get(i).getDate(index).checkTime(tempTimes.get(k));
               boolean added = false;
               for(int j = 0; j < types[k].size(); j++)
               {
                  if(!types[k].isEmpty() && !types[k].get(j).isEmpty() && tempType.equals(types[k].get(j).get(0)))
                  {
                     types[k].get(j).add(", " + people.get(i).getName());
                     added = true;
                     break;
                  }
               }
               if(!added)
               {
                  ArrayList<String> tempArray = new ArrayList<String>();
                  types[k].add(tempArray);
                  types[k].get(types[k].size() - 1).add(tempType);
                  types[k].get(types[k].size() - 1).add(people.get(i).getName());
               }
            }
         }
         
         
         for(int k = 0; k < tempTimes.size(); k++)
         {
            text += Date.timeToString(tempTimes.get(k)) + "\n";
            for(int i = 0; i < types[k].size(); i++)
            {
               text += types[k].get(i).get(0) + ": ";
               for(int j = 1; j < types[k].get(i).size(); j++)
               {
                  text += types[k].get(i).get(j);
               }
               if(i < types[k].size() - 1)
               {
                  text += "\n";
               }
            }
            if(k < tempTimes.size() - 1)
            {
               text += "\n" + "\n";
            }
         }
         
         return text;
      }
      
      
      return "no people in list";
   }
   
   public String getPeopleNames()
   {
      String tempString = "";
      for(int i = 0; i < people.size(); i++)
      {
         tempString += people.get(i).getName();
         if(i != people.size() - 1)
         {
            tempString += ", ";
         }
      }
      return "People in Schedule: " + tempString;
   }
   
   public void setSchedule(Person newPerson)
   {
      int period = lowerBound.compare(upperBound) + 1;
      
      Date[] tempDates = newPerson.getDates();
      scheduleDates = new Date[period];
      for(int i = 0; i < period; i++)
      {
         scheduleDates[i] = new Date(tempDates[i]);
      }
   }
   public void setSchedule(Person newPerson, String newName)
   {
      name = newName;
      int period = lowerBound.compare(upperBound) + 1;
      
      Date[] tempDates = newPerson.getDates();
      scheduleDates = new Date[period];
      for(int i = 0; i < period; i++)
      {
         scheduleDates[i] = new Date(tempDates[i]);
      }
   }
   public void setSchedule(Period newPeriod)
   {
      name = newPeriod.getName();
      int period = lowerBound.compare(upperBound) + 1;
      
      Date[] tempDates = newPeriod.getDates();
      scheduleDates = new Date[period];
      for(int i = 0; i < period; i++)
      {
         scheduleDates[i] = new Date(tempDates[i]);
      }
   }
  
   public void addPerson(Person newPerson)
   {
      people.add(newPerson);
   }
   public void addPeriod(Period newPeriod)
   {
      classes.add(newPeriod);
   }
  
   // set bounds, returns # of days between lower and upper (inclusive)
   public static int setBounds(Date lower, Date upper)
   {
      lowerBound = lower;
      upperBound = upper;
      return lower.compare(upper) + 1;
   }
  
   public static void main(String[] args) 
   {
      Date startDate = new Date(7,5,2023);
      Date endDate = new Date(7,12,2023);
      setBounds(startDate, endDate);
      
      // classes
      Period math = new Period("math");
      math.addTime(8,30,10,10,"can", 0);
      math.addTime(8,30,10,10,"can", 2);
      math.addTime(8,30,10,10,"can", 6);
      
      //Sam
      Person sam = new Person("Sam");
      sam.addTime(0,0,12,0,"can", 0);
      sam.addTime(12,0,24,0,"cant", 0);
      sam.addTime(0,0,24,0,"cant", 1);
      sam.addTime(0,0,24,0,"can", 2);
      sam.addTime(0,0,24,0,"can", 3);
      sam.addTime(0,0,24,0,"can", 4);
      sam.addTime(0,0,24,0,"preferNot", 5);
      sam.addTime(0,0,24,0,"can", 6);
      sam.addTime(0,0,24,0,"can", 7);
      
      //Silas
      Person silas = new Person("Silas");
      silas.addTime(9,0,16,0,"can", 0);
      silas.addTime(0,0,24,0,"can", 1);
      silas.addTime(0,0,24,0,"preferNot", 2);
      silas.addTime(0,0,24,0,"preferNot", 3);
      silas.addTime(0,0,24,0,"cant", 4);
      silas.addTime(0,0,24,0,"can", 5);
      silas.addTime(0,0,24,0,"unknown", 6);
      silas.addTime(0,0,24,0,"can", 7);
      
      //Matthew
      Person matthew = new Person("Matthew");
      matthew.addTime(0,0,12,0,"preferNot", 0);
      matthew.addTime(0,0,24,0,"can", 1);
      matthew.addTime(0,0,24,0,"can", 2);
      matthew.addTime(0,0,24,0,"can", 3);
      matthew.addTime(0,0,24,0,"can", 4);
      matthew.addTime(0,0,24,0,"cant", 5);
      matthew.addTime(0,0,24,0,"can", 6);
      matthew.addTime(0,0,24,0,"can", 7);
      
      Schedule schedule = new Schedule("Schedule");
      
      schedule.addPerson(sam);
      schedule.addPerson(silas);
      schedule.addPerson(matthew);
      
      schedule.addPeriod(math);
      schedule.findPeriod(math, true);
      
      System.out.println("possible math teachers are: " + math.getTeacherNames());
      
      System.out.println("");
      
      schedule.findSharedDates();
      
      System.out.println(schedule);
      
      System.out.println("");
      
      System.out.println(schedule.getTypeDates("can"));
      
      System.out.println("");
      
      int[][] timeNum = {{9, 0}, {12, 0}};
      System.out.println(schedule.getNumOfType(0, timeNum));
      
      System.out.println("");
      
      System.out.println(schedule.getPeopleNames());

   }
   
   public void equalizePeople()
   {
      for(int k = 0; k < 2; k++)
      {
         for(int i = 1; i < people.size(); i++)
         {
            for(int j = 0; j < people.get(i).getDates().length; j++)
            {
               Date.equalizeTimes(people.get(0).getDate(j), people.get(i).getDate(j));
            }
         }
      }
   } 
     
   public void findSharedDates()
   {
      equalizePeople();
      if(people.size() != 0)
      {
         setSchedule(people.get(0));
         for(int i = 1; i < people.size(); i++)
         {
            for(int j = 0; j < scheduleDates.length; j++)
            {
               scheduleDates[j].changeTypes(people.get(i).comparePeople(this, j));
            }
         }
      }
      else
      {
         System.out.println("schedule is empty");
      }
   }
   public void findPeriod(Period subject, boolean isTeacher)
   {
      equalizePeople();
      if(people.size() != 0)
      {
         for(int i = 0; i < people.size(); i++)
         {
            boolean didBreak = false;
            for(int j = 0; j < subject.getDates().length; j++)
            {
               for(int k = 0; k < subject.getDate(j).getTimesSize(); k++)
               {
                  if(!subject.comparePeriod(people.get(i), j).get(k).equals("can"))
                  {
                     didBreak = true;
                     break;
                  }
               }
               if(j == (subject.getDates().length - 1) && !didBreak)
               {
                  if(isTeacher)
                  {
                     subject.addTeacher(people.get(i));
                  }
                  else
                  {
                     subject.addStudent(people.get(i));

                  }
               }
            }
         }
      }
      else
      {
         System.out.println("no people in schedule");
      }
   }
   
   // sets teacher to each class that works, returns any problems
   public String findClassSchedule()
   {
      return "";
   }
}