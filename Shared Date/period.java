import java.util.*;
import java.awt.Graphics;
import java.awt.Color;

import java.time.LocalDateTime;


class Period
{        
   private Date[] dates;
   
   private Person teacher = new Person();
   
   private ArrayList<Person> teachers = new ArrayList<Person>();
   private ArrayList<Person> students = new ArrayList<Person>();
   
   private String name;
   
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
   public Period()
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
   public Period(String newName)
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
  
   //Assigned Period
   public Period(String newName, int datesTimes[][][], ArrayList<String>[] datesTypes)
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
   
   public Period(Person newPerson, String newName)
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
  
   public Period(Person newPerson)
   {
      name = newPerson.getName();
      
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
  
   //add times
   public void addTime(int startHr, int startMin, int endHr, int endMin, String newType, int dateIndex)
   {
      dates[dateIndex].addTime(startHr, startMin, endHr, endMin, newType);
   }
   public void addTeacher(Person newTeacher)
   {
      teachers.add(newTeacher);
   }
   public void addStudent(Person newStudent)
   {
      students.add(newStudent);
   }
   
   // set things
   public void setTeacher(Person newTeacher)
   {
      teacher = newTeacher;
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
   public String getTeacherName()
   {
      return teacher.getName();
   }
   public String getTeacherNames()
   {
      String tempString = "";
      for(int i = 0; i < teachers.size(); i++)
      {
         tempString += teachers.get(i).getName();
         if(i != 0 || i != (teachers.size() - 1))
         {
            tempString += ",";
         }
         tempString += " ";
      }
      return tempString;
   }
   public String getStudentNames()
   {
      String tempString = "";
      for(int i = 0; i < students.size(); i++)
      {
         tempString += students.get(i).getName();
         if(i != 0 || i != (students.size() - 1))
         {
            tempString += ",";
         }
         tempString += " ";
      }
      return tempString;
   }
  
   public static void main(String[] args) 
   {
      
   }
   
   public ArrayList<String> comparePeriod(Person b, int index)
   {
      Date temp = new Date(b.getDate(index));
      for(int j = 0; j < dates[index].getTimesSize(); j++)
      {
         if(temp.checkTime(dates[index].getTime(j)) == "cant")
         {
            temp.changeType(j, "cant");
         }
         else if(temp.checkTime(dates[index].getTime(j)) == "unknown")
         {
            temp.changeType(j, "unknown");
         }
         else if(temp.checkTime(dates[index].getTime(j)) ==  "preferNot")
         {
            temp.changeType(j, "preferNot");
         }
         else if(temp.checkTime(dates[index].getTime(j)) == "can")
         {
            temp.changeType(j, "can");
         }
      }
    
      return temp.getTypes();
   }

}