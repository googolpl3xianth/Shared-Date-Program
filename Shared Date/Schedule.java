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
   public static Date getLower()
   {
      return lowerBound;
   }
   public static Date getUpper()
   {
      return upperBound;
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
   
   public void equalizePeople()
   {
      for(int i = 1; i < people.size(); i++)
      {
         for(int j = 0; j < people.get(i).getDates().length; j++)
         {
            Date.equalizeTimes(people.get(0).getDate(j), people.get(i).getDate(j));
         }
      }
      for(int i = 1; i < people.size() - 1; i++)
      {
         for(int j = 0; j < people.get(i).getDates().length; j++)
         {
            Date.equalizeTimes(people.get(0).getDate(j), people.get(i).getDate(j));
         }
      }
   } 
   
   public int containsPerson(ArrayList<Person> listPeople, Person person) // returns index of person in listPeople, returns -1 if there is no person in listPeople
   {
      for(int i = 0; i < listPeople.size(); i++)
      {
         if(listPeople.get(i).equals(person))
         {
            return i;
         }
      }
      return -1;
   }
   
   public static void main(String[] args) ////////////////////////////////////////////////////////////main////////////////////////////////////////////////////////////////////
   {
      Date startDate = new Date(8,28,2023);
      Date endDate = new Date(5,24,2024);
      setBounds(startDate, endDate);
      
      Schedule schedule = new Schedule("Schedule");
      
      // classes
      Period mathMiddle = new Period("5th-8th Virtual Math Class");
      for(int i = 0; i < getLower().compare(getUpper()) + 1; i += 7)
      {
         for(int j = i; j < i + 4; j++)
         {
            mathMiddle.addTime(9,00,9,55,"can", j);
            mathMiddle.addTime(10,00,10,55,"can", j);
            mathMiddle.addTime(11,00,11,55,"can", j);
            mathMiddle.addTime(1,00,1,55,"can", j);
         }
      }
      
      Period mathHigh = new Period("9th-12th Virtual Math Class");
      for(int i = 0; i < getLower().compare(getUpper()) + 1; i += 7)
      {
         for(int j = i; j < i + 4; j++)
         {
            mathHigh.addTime(9,00,9,55,"can", j);
            mathHigh.addTime(10,00,10,55,"can", j);
            mathHigh.addTime(1,00,1,55,"can", j);
            mathHigh.addTime(2,00,2,55,"can", j);
         }
      }
      
      schedule.addPeriod(mathMiddle);
      schedule.addPeriod(mathHigh);
      
      //Sam
      Person sam = new Person("Sam");
      
      for(int i = 0; i < getLower().compare(getUpper()) + 1; i += 7)
      {
         for(int j = i; j < i + 7 && j < getLower().compare(getUpper()) + 1; j++)
         {
            sam.addTime(0,0,24,0,"can", j);
         }
      }
      
      //Silas
      Person silas = new Person("Silas");
      
      for(int i = 0; i < getLower().compare(getUpper()) + 1; i += 7)
      {
         for(int j = i; j < i + 5 && j < getLower().compare(getUpper()) + 1; j++)
         {
            silas.addTime(8,30,14,35,"cant", j);
            silas.addTime(15,0,24,0,"can", j);
         }
         for(int j = i + 5; j < i + 7 && j < getLower().compare(getUpper()) + 1; j++)
         {
            silas.addTime(0,0,24,0,"can", j);
         }
      }
      
      schedule.addPerson(sam);
      schedule.addPerson(silas);
      schedule.findSharedDates();
      
      System.out.println(schedule.findClassSchedule());
      
      System.out.println(mathMiddle);
      System.out.println();
      
      System.out.println(schedule.getTeacherNames());
      //System.out.println(schedule.findPeriod(mathMiddle, true));
      //int[][] tempTime = {{15,0},{24,0}};
      //System.out.println(schedule.getNumOfType(0, tempTime));
   }
   
   ////////////////////////////////////////////////////////////////////////////////////////////////// Useful methods to be used in main///////////////////////////////////////////////
   // set bounds, returns # of days between lower and upper (inclusive)
   public static int setBounds(Date lower, Date upper)
   {
      lowerBound = lower;
      upperBound = upper;
      return lower.compare(upper) + 1;
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
   
   public String getTypeDates(String type) // searches people for dates with type, returns date with time
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
         
         int tempYear = scheduleDates[index].getYear();
         return scheduleDates[index].getMonth() + "/" + scheduleDates[index].getDay() + "/" + (tempYear % 100) + "\n" + text;
      }
      
      
      return "no people in list";
   }
   
   public String getPeopleNames() // returns the name of each Person in people
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
   
   public String getTeacherNames() // returns the name of each teacher for each class
   {
      String tempString = "";
      for(int i = 0; i < classes.size(); i++)
      {
         tempString += "Class: " + classes.get(i).getName() + " Teacher: " + classes.get(i).getTeacherName() + "\n";
      }
      return tempString;
   }
   
   // compares each person in people if they fit period subject and adds them to the period if they fit, returns a string list of people added
   public String findPeriod(Period subject, boolean isTeacher)
   {
      equalizePeople();
      
      String peopleAdded = "";
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
                     peopleAdded += subject.getTeacherName(subject.getTeachers().size() - 1) + " ";
                  }
                  else
                  {
                     subject.addStudent(people.get(i));
                     peopleAdded += subject.getStudentName(subject.getStudents().size() - 1) + " ";
                  }
               }
            }
         }
      }
      else
      {
         return "no people in schedule";
      }
      return "People added: " + peopleAdded;
   }
   
   // sets teacher to each class that works, returns any problems
   public String findClassSchedule()
   {
      ArrayList<Person> tempPeople = new ArrayList<Person> (people);
      ArrayList<Person> tempPerson = new ArrayList<Person>();
      int tempInt;
      for(Period p : classes)
      {
         p.clearTeacher();
         findPeriod(p, true);
         tempInt = containsPerson(tempPeople, p.getTeacher(0));
         if(p.getTeachers().size() == 1 && tempInt != -1)
         {
            tempPeople.remove(tempInt);
            p.setTeacher(0);
         }
      }
      for(int i = 0; i < classes.size(); i++)
      {
         for(Period p : classes)
         {
            tempPerson = p.findSharedPeople(tempPeople, people);
            if(p.getTeacher() != null && tempPerson.size() == 1)
            {
               tempPeople.remove(containsPerson(tempPeople, tempPerson.get(0)));
               p.setTeacher(tempPerson.get(0));
            }
         }
      }
      String tempString = "";
      
      for(int i = 0; i < tempPeople.size(); i++)
      {
         tempString += tempPeople.get(i).getName() + " ";
      }
      return "People without a class: " + tempString;
   }
}