import java.util.*;
import java.lang.Math;

class Date 
{
   private int day;
   private int month;
   private int year;
  
   private ArrayList<String> type = new ArrayList<String> ();
  
   private ArrayList<int[][]> timeSlots = new ArrayList<int[][]> ();
   private int[][] times = new int[2][2]; //[0 start/1 end][0 Hr/1 Min]
  
   //defualt Assignment
   public Date()
   {
      day = 1;
      month = 1;
      year = 1;
     
      if(timeSlots.size() != type.size())
      {
         int tempYear = year;
         System.out.println("Caution, timeSlots.size() != type.size() for Date() " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
      }
   }
  
   //Assigned Assignment
   public Date(int dateMonth, int dateDay, int dateYear)
   {
      day = dateDay;
      month = dateMonth;
      year = dateYear;
     
      if(timeSlots.size() != type.size())
      {
         int tempYear = year;
         System.out.println("Caution, timeSlots.size() != type.size() for Date(int, int, int) " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
      }
   }
   
   public Date(int dateMonth, int dateDay, int dateYear, int[][] dateTimes, ArrayList<String> dateTypes)
   {
      day = dateDay;
      month = dateMonth;
      year = dateYear;
     
      type = new ArrayList<String>(dateTypes);
      
      // int [startHr/startMin/endHr/endMin][#]
      for(int i = 0; i < dateTimes[0].length; i++)
      {
         times = new int[2][2];
         times[0][0] = dateTimes[0][i];
         times[0][1] = dateTimes[1][i];
         times[1][0] = dateTimes[2][i];
         times[1][1] = dateTimes[3][i];
      
         timeSlots.add(times);
      }
      
      if(timeSlots.size() != type.size())
      {
         int tempYear = year;
         System.out.println("Caution, timeSlots.size() != type.size() for Date(int, int, int, int[][], ArrayList<String>) " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
      }
   }
  
   public Date(Date newDate)
   {
      day = newDate.day;
      month = newDate.month;
      year = newDate.year;
      
      type = new ArrayList<String> (newDate.type);
      
      timeSlots = new ArrayList<int[][]> (newDate.timeSlots);
      
      if(timeSlots.size() != type.size())
      {
         int tempYear = year;
         System.out.println("Caution, timeSlots.size() != type.size() for Date(Date) " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
      }
   }
  
  
  
   public String toString()
   {
      int tempYear = year;
      String slots = "";
      
      if(timeSlots.size() == type.size())
      {
         for(int i = 0; i < timeSlots.size(); i++)
         {
            slots += timeToString(i);
         }
      }
      else
      {
         System.out.println("Error, timeSlots.size() != type.size() when printing Date " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
         System.exit(0);
      }
      return month + "/" + day + "/" + (tempYear % 100) + " " + slots;
   }
   
   public String timeToString(int index)
   {
      int[][] tempTimes = new int[2][2];
      String slots = "";
      tempTimes = timeSlots.get(index);
      if(tempTimes[0][1] < 10)
      {
         if(tempTimes[1][1] < 10)
         {
            slots += tempTimes[0][0] + ":0" + tempTimes[0][1] + "-" + tempTimes[1][0] + ":0" + tempTimes[1][1] + " " + type.get(index) + " ";
         }
         else
         {
            slots += tempTimes[0][0] + ":0" + tempTimes[0][1] + "-" + tempTimes[1][0] + ":" + tempTimes[1][1] + " " + type.get(index) + " ";
         }
      }
      else if(tempTimes[1][1] < 10)
      {
         slots += tempTimes[0][0] + ":" + tempTimes[0][1] + "-" + tempTimes[1][0] + ":0" + tempTimes[1][1] + " " + type.get(index) + " ";
      }
      else
      {
         slots += tempTimes[0][0] + ":" + tempTimes[0][1] + "-" + tempTimes[1][0] + ":" + tempTimes[1][1] + " " + type.get(index) + " ";
      }
      return slots;
   }
   public static String timeToString(int[][] timeArray)
   {
      int[][] tempTimes = new int[2][2];
      String slots = "";
      tempTimes = timeArray;
      if(tempTimes[0][1] < 10)
      {
         if(tempTimes[1][1] < 10)
         {
            slots += tempTimes[0][0] + ":0" + tempTimes[0][1] + "-" + tempTimes[1][0] + ":0" + tempTimes[1][1];
         }
         else
         {
            slots += tempTimes[0][0] + ":0" + tempTimes[0][1] + "-" + tempTimes[1][0] + ":" + tempTimes[1][1];
         }
      }
      else if(tempTimes[1][1] < 10)
      {
         slots += tempTimes[0][0] + ":" + tempTimes[0][1] + "-" + tempTimes[1][0] + ":0" + tempTimes[1][1];
      }
      else
      {
         slots += tempTimes[0][0] + ":" + tempTimes[0][1] + "-" + tempTimes[1][0] + ":" + tempTimes[1][1];
      }
      return slots;
   }

  
   public void addTime(int startHr, int startMin, int endHr, int endMin, String newType)
   {
      times = new int[2][2];
      times[0][0] = startHr;
      times[0][1] = startMin;
      times[1][0] = endHr;
      times[1][1] = endMin;
      
      timeSlots.add(times);
      type.add(newType); 
   }
   public void addTime(int index, int startHr, int startMin, int endHr, int endMin, String newType)
   {
      times = new int[2][2];
      times[0][0] = startHr;
      times[0][1] = startMin;
      times[1][0] = endHr;
      times[1][1] = endMin;
      
      timeSlots.add(index, times);
      type.add(index, newType); 
   }
   public void addTime(int[][] newTime, String newType)
   {
      timeSlots.add(newTime);
      type.add(newType);
   }
   public void addTime(int index, int[][] newTime, String newType)
   {
      timeSlots.add(index, newTime);
      type.add(index, newType);
   }
   
   
   //add multiple times
   public void addTimes(int[][] newTimes, ArrayList<String> newTypes)
   {
      type = new ArrayList<String>(newTypes);
     
      // int [startHr/startMin/endHr/endMin][#]
      if(newTimes.length == 4)
      {
         for(int i = 0; i < newTimes[0].length; i++)
         {
            times = new int[2][2];
            times[0][0] = newTimes[0][i];
            times[0][1] = newTimes[1][i];
            times[1][0] = newTimes[2][i];
            times[1][1] = newTimes[3][i];
            
            timeSlots.add(times);
         }
      }
      else
      {
         System.out.println("newTimes array is inputed incorrectly, it looks like this: ");
         for(int i = 0 ; i < newTimes.length; i++)
         {
            for(int j = 0 ; j < newTimes[0].length; j++)
            {
               System.out.print(newTimes[i][j] + " ");
            }
            System.out.println("");
         }
      }
      if(timeSlots.size() != type.size())
      {
         int tempYear = year;
         System.out.println("Caution, timeSlots.size() != type.size() when method addTimes(int[][], ArrayList<String>) was called " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
      }
   }
   
   public void removeTime(int index)
   {
      timeSlots.remove(index);
      type.remove(index);
   }
   
   // removes dup and returns number of times deleted
   public int removeDupTimes(int[][] dupTime, String newType)
   {
      ArrayList<Integer> duplicateIndexs = new ArrayList<Integer> ();
      for(int i = timeSlots.size() - 1; i >= 0; i--)
      {
         int[][] tempTime = timeSlots.get(i);
         if(tempTime[0][0] == dupTime[0][0] && tempTime[0][1] == dupTime[0][1] && tempTime[1][0] == dupTime[1][0] && tempTime[1][1] == dupTime[1][1] && type.get(i).equals(newType))
         {
            duplicateIndexs.add(i);
         }
      }
      for(int i = 1; i < duplicateIndexs.size(); i++)
      {
         removeTime(duplicateIndexs.get(i));
      }
      if(timeSlots.size() != type.size())
      {
         int tempYear = year;
         System.out.println("Caution, timeSlots.size() != type.size() after removeDupTimes " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
      }
      return duplicateIndexs.size() - 1;
   }
   
   public void replaceTime(int index, int[][] newTimes, String newType)
   {
      removeTime(index);
      addTime(index, newTimes, newType);
      if(timeSlots.size() != type.size())
      {
         int tempYear = year;
         System.out.println("Caution, timeSlots.size() != type.size() after replaceTime " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
      }
   }
   
   //edit assignment details
   public void changeDay(int newDay)
   {
      day = newDay;
   }
   public void changeMonth(int newMonth)
   {
      month = newMonth;
   }
   public void changeYear(int newYear)
   {
      year = newYear;
   }
   public void changeType(int index, String newType)
   {
      if(type.get(index) == null)
      {
         System.out.println("changeType index is out of bounds, type is size: " + type.size() + " index: " + index);
         System.exit(0);
      }
      else
      {
         type.set(index, newType);
      }
   }
   public void changeTypes(ArrayList<String> newTypes)
   {
      type = new ArrayList<String> (newTypes);
   }
   public void setDate(int newMonth, int newDay, int newYear)
   {
      day = newDay;
      month = newMonth;
      year = newYear;
   }
  
  
  
   // return values
  
   public int getDay()
   {
      return day;
   }
   public int getMonth()
   {
      return month;
   }
   public int getYear()
   {
      return year;
   }
   public String getType(int index)
   {
      if(index < type.size())
      {
         return type.get(index);
      }
      else
      {
         int tempYear = year;
         System.out.println("IndexOutOfBoundsException for date " + month + "/" + day + "/" + (tempYear % 100) + " when method getType() was called, Index: " + index + ", Size: " + type.size());
         type.get(index);
      }
      return "unknown";
   }
   public ArrayList<String> getTypes()
   {
      return type;
   }
   public ArrayList<int[][]> getTimes()
   {
      return timeSlots;
   }
   public int[][] getTime(int index)
   {
      return timeSlots.get(index);
   }
   public int getTimesSize()
   {
      return timeSlots.size();
   }
   public int getTypesSize()
   {
      return type.size();
   }
  
  
  
  
  
  public void addDays(int daysAdded)
  {
    day += daysAdded;
    while(day > 31)
    {
       if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10)
       {
         if(day > 31)
         {
            day -= 31;
            month++;
         }
       }
       else if(month == 4 || month == 6 || month == 9 || month == 11)
       {
         if(day > 30)
         {
            day -= 30;
            month++;
         }
       }
       else if(month == 2)
       {
         if(year % 4.0 != 0)
         {
            if(day > 28)
            {
               day -= 28;
               month++;
            }
         }
         else
         {
            if(day > 29)
            {
               day -= 29;
               month++;
            }
         }
       }
       else if(month == 12)
       {
         if(day > 31)
         {
            day -= 31;
            month = 1;
            year++;
         }
       }
    }
    if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10)
    {
      if(day > 31)
      {
         day -= 31;
         month++;
      }
    }
    else if(month == 4 || month == 6 || month == 9 || month == 11)
    {
      if(day > 30)
      {
         day -= 30;
         month++;
      }
    }
    else if(month == 2)
    {
      if(year % 4.0 != 0)
      {
         if(day > 28)
         {
            day -= 28;
            month++;
         }
      }
      else
      {
         if(day > 29)
         {
            day -= 29;
            month++;
         }
      }
    }
    else if(month == 12)
    {
      if(day > 31)
      {
         day -= 31;
         month = 1;
         year++;
      }
    }
  }
  
  public void addWeeks(int weeksAdded)
  {
     addDays(7 * weeksAdded);
  }
  
  public void addMonths(int monthsAdded)
  {
    month += monthsAdded;
    
    int tempMonth = month;
    if(tempMonth > 12)
    {
       year += tempMonth / 12;
    }
    month %= 12;
    
    if(month == 4 || month == 6 || month == 9 || month == 11)
    {
      if(day > 30)
      {
         day = 30;
      }
    }
    else if(month == 2)
    {
     if(year % 4.0 != 0)
     {
         if(day > 28)
         {
            day = 28;
         }
      }
      else
      {
         if(day > 29)
         {
            day = 29;
         }
      }
    }
  }
  public void addYears(int yearsAdded)
  {
    year += yearsAdded;
  }
  
  // for 1 =< a =< 12 and 1 =< b =< 12
  public int daysMonth(Date a, Date b)
  {
     int[] monthDays = new int[12];
     int monthDiff = b.month - a.month;
     int days = 0;
     
     if((a.year % 4.0) != 0)
     {
        monthDays[0] = 31;
        monthDays[1] = 28;
        monthDays[2] = 31;
        monthDays[3] = 30;
        monthDays[4] = 31;
        monthDays[5] = 30;
        monthDays[6] = 31;
        monthDays[7] = 31;
        monthDays[8] = 30;
        monthDays[9] = 31;
        monthDays[10] = 30;
        monthDays[11] = 31;
     }
     else
     {
        monthDays[0] = 31;
        monthDays[1] = 29;
        monthDays[2] = 31;
        monthDays[3] = 30;
        monthDays[4] = 31;
        monthDays[5] = 30;
        monthDays[6] = 31;
        monthDays[7] = 31;
        monthDays[8] = 30;
        monthDays[9] = 31;
        monthDays[10] = 30;
        monthDays[11] = 31;
     }
     if(a.month < b.month)
     {
        for(int i = a.month; i < b.month; i++)
        {
           days += monthDays[i-1];
        }
     }
     else if(b.month < a.month)
     {
        for(int i = b.month; i < a.month; i++)
        {
           days -= monthDays[i-1];
        }
     }
     return days;
  }
  
  
  
  // check if date equals each other
   public int equals(Date b)
   {
      if(Integer.compare(year,b.year) == 0)
      {
         if(Integer.compare(month,b.month) == 0)
         {
            return Integer.compare(day,b.day) * -1;
         }
         else
         {
            return Integer.compare(month,b.month) * -1;
         }
      }
      else
      {
         return Integer.compare(year,b.year) * -1;
      }
   }
  
  
   //returns # days from a and b
   public int compare(Date b)
   {
      int yearComp = b.year - this.year;
      int dayComp = b.day - this.day;
     
      int difference = 0;
     
      //years
      int endWeird = 0;
      if(this.year < b.year)
      {
         if((this.year % 4) == 0 && (yearComp % 4) != 0)
         {
            endWeird++;
         }
      }
      else if(this.year > b.year)
      {
         if((b.year % 4)== 0 && (yearComp % 4) != 0)
         {
            endWeird--;
         }
      }
      int weirdYr = endWeird + (yearComp / 4);
      int normYr = yearComp - weirdYr;
     
      difference += (normYr * 365) + (weirdYr * 366);
     
      //month

      difference += daysMonth(this, b);
     
      difference += dayComp;
     
      return difference;
   }
  
  
   public static void main(String[] args) 
   {
      Date test1 = new Date(1,19,2023);
      Date test2 = new Date(2,19,2023);
      
      test1.addTime(1, 01, 2, 05, "can");
      test1.addTime(2, 05, 3, 07, "cant");
      test1.addTime(3, 07, 4, 02, "preferNot");
      test1.addTime(6, 06, 10, 06, "cant");
      
      test2.addTime(1, 01, 3, 07, "cant");
      test2.addTime(3, 07, 4, 01, "can");
      test2.addTime(4, 01, 5, 06, "can");
      test2.addTime(6, 06, 10, 06, "can");
      
      System.out.println(test1);
      System.out.println(test2);
      
      test1.equalizeTimes(test1, test2);
      
      System.out.println(test1);
      System.out.println(test2);
      
      int[][] test = test1.getTimes().get(2);
   }
   
   public static boolean equalizeLoop(Date a, Date b, int index)
   {
      if(a.timeSlots.size() < b.timeSlots.size())
      {
         if(index < a.timeSlots.size())
         {
            equalizeDateTimes(a, index, b, index);
            return true;
         }
         else if(index < b.timeSlots.size())
         {
            equalizeDateTimes(a, a.timeSlots.size() - 1, b, index);
            return true;
         }
      }
      else
      {
         if(index < b.timeSlots.size())
         {
            equalizeDateTimes(a, index, b, index);
            return true;
         }
         else if(index < a.timeSlots.size())
         {
            equalizeDateTimes(a, index, b, b.timeSlots.size() - 1);
            
            return true;
         }
      }
      return false;
   }
   
   //does equalizeDateTimes but for all times
   public static void equalizeTimes(Date a, Date b)
   {
      int i = 0;
      while(equalizeLoop(a, b, i))
      {
         i++;
      }
      
      if(a.getTimesSize() != a.getTypesSize())
      {
         int tempYear = a.year;
         System.out.println("Caution, timeSlots.size() != type.size() after equalizeTimes was called for date a" + a.month + "/" + a.day + "/" + (tempYear % 100) + " timeSlots: " + a.getTimesSize() + " typeSize: " + a.getTypesSize());
      }
      if(b.getTimesSize() != b.getTypesSize())
      {
         int tempYear = b.year;
         System.out.println("Caution, timeSlots.size() != type.size() after equalizeTimes was called for date b" + b.month + "/" + b.day + "/" + (tempYear % 100) + " timeSlots: " + b.getTimesSize() + " typeSize: " + b.getTypesSize());
      }
   }
   
   public static ArrayList<int[][]> equalizeTimes(int[][] interval, Date dB)
   {
      Date a = new Date();
      a.addTime(interval, "unknown");
      int i = 0;
      while(equalizeLoop(a, dB, i))
      {
         i++;
      }
      return a.timeSlots;
   }
   
   //given two times, splits as needed to create the same intervals, returns number of time slots added
   public static int equalizeDateTimes(Date dA, int indexA, Date dB, int indexB)
   {
      int[][] a = dA.timeSlots.get(indexA);
      int[][] b = dB.timeSlots.get(indexB);
      
      // a start is == b start
      if(a[0][0] == b[0][0] && a[0][1] == b[0][1])
      {
         if(a[1][0] < b[1][0] || (a[1][0] == b[1][0] && a[1][1] < b[1][1])) // a end is < b end
         {
            int[][] temp1 = {{a[0][0], a[0][1]}, {a[1][0], a[1][1]}};
            int[][] temp2 = {{a[1][0], a[1][1]}, {b[1][0], b[1][1]}};
            
            String tempTypeA = dA.type.get(indexA);
            String tempTypeB = dB.type.get(indexB);
            
            dA.replaceTime(indexA, temp2, dA.checkTime(temp2));
            dA.addTime(indexA, temp1, tempTypeA);
            
            dB.replaceTime(indexB, temp2, tempTypeB);
            dB.addTime(indexB, temp1, tempTypeB);
            
            dA.removeDupTimes(temp2, dA.checkTime(temp2));
            dA.removeDupTimes(temp1, tempTypeA);
            dB.removeDupTimes(temp2, tempTypeB);
            dB.removeDupTimes(temp1, tempTypeB);
            
            return 1;
         }
         else if(a[1][0] > b[1][0] || (a[1][0] == b[1][0] && a[1][1] > b[1][1]))
         {
            int[][] temp1 = {{a[0][0], a[0][1]}, {b[1][0], b[1][1]}};
            int[][] temp2 = {{b[1][0], b[1][1]}, {a[1][0], a[1][1]}};
            
            String tempTypeA = dA.type.get(indexA);
            String tempTypeB = dB.type.get(indexB);
            
            dA.replaceTime(indexA, temp2, tempTypeA);
            dA.addTime(indexA, temp1, tempTypeA);
            
            dB.replaceTime(indexB, temp2, dB.checkTime(temp2));
            dB.addTime(indexB, temp1, tempTypeB);
            
            dA.removeDupTimes(temp2, tempTypeA);
            dA.removeDupTimes(temp1, tempTypeA);
            dB.removeDupTimes(temp2, dB.checkTime(temp2));
            dB.removeDupTimes(temp1, tempTypeB);
            
            return 1;
         }
         else
         {
            return 0;
         }
      }// a start is < b start
      else if(a[0][0] < b[0][0] || (a[0][0] == b[0][0] && a[0][1] < b[0][1])) 
      {
         if(a[1][0] < b[1][0] || (a[1][0] == b[1][0] && a[1][1] < b[1][1]))// a end is < b end
         {
            if(a[1][0] < b[0][0] || (a[1][0] == b[0][0] && a[1][1] <= b[0][1]))// a end < b start
            {
               int[][] temp1 = {{a[0][0], a[0][1]}, {a[1][0], a[1][1]}};
               int[][] temp2 = {{b[0][0], b[0][1]}, {b[1][0], b[1][1]}};
               
               String tempTypeA = dA.type.get(indexA);
               String tempTypeB = dB.type.get(indexB);
               
               dA.replaceTime(indexA, temp2, dA.checkTime(temp2));
               dA.addTime(indexA, temp1, tempTypeA);
               
               dB.replaceTime(indexB, temp2, tempTypeB);
               dB.addTime(indexB, temp1, dB.checkTime(temp1));
               
               dA.removeDupTimes(temp2, dA.checkTime(temp2));
               dA.removeDupTimes(temp1, tempTypeA);
               dB.removeDupTimes(temp2, tempTypeB);
               dB.removeDupTimes(temp1, dB.checkTime(temp1));
            
               return 1;
            }
            else
            {
               int[][] temp1 = {{a[0][0], a[0][1]}, {b[0][0], b[0][1]}};
               int[][] temp2 = {{b[0][0], b[0][1]}, {a[1][0], a[1][1]}};
               int[][] temp3 = {{a[1][0], a[1][1]}, {b[1][0], b[1][1]}};
               
               String tempTypeA = dA.type.get(indexA);
               String tempTypeB = dB.type.get(indexB);
               
               dA.replaceTime(indexA, temp3, dA.checkTime(temp3));
               dA.addTime(indexA, temp2, tempTypeA);
               dA.addTime(indexA, temp1, tempTypeA);
               
               dB.replaceTime(indexB, temp3, tempTypeB);
               dB.addTime(indexB, temp2, tempTypeB);
               dB.addTime(indexB, temp1, dB.checkTime(temp1));
               
               dA.removeDupTimes(temp3, dA.checkTime(temp3));
               dA.removeDupTimes(temp2, tempTypeA);
               dA.removeDupTimes(temp1, tempTypeA);
               dB.removeDupTimes(temp3, tempTypeB);
               dB.removeDupTimes(temp2, tempTypeB);
               dB.removeDupTimes(temp1, dB.checkTime(temp1));
               
               return 2;
            }
            
         }
         else if(a[1][0] > b[1][0] || (a[1][0] == b[1][0] && a[1][1] > b[1][1]))
         {
            int[][] temp1 = {{a[0][0], a[0][1]}, {b[0][0], b[0][1]}};
            int[][] temp2 = {{b[0][0], b[0][1]}, {b[1][0], b[1][1]}};
            int[][] temp3 = {{b[1][0], b[1][1]}, {a[1][0], a[1][1]}};
            
            String tempTypeA = dA.type.get(indexA);
            String tempTypeB = dB.type.get(indexB);
            
            dA.replaceTime(indexA, temp3, tempTypeA);
            dA.addTime(indexA, temp2, tempTypeA);
            dA.addTime(indexA, temp1, tempTypeA);
            
            dB.replaceTime(indexB, temp3, dB.checkTime(temp3));
            dB.addTime(indexB, temp2, tempTypeB);
            dB.addTime(indexB, temp1, dB.checkTime(temp1));
            
            dA.removeDupTimes(temp3, tempTypeA);
            dA.removeDupTimes(temp2, tempTypeA);
            dA.removeDupTimes(temp1, tempTypeA);
            dB.removeDupTimes(temp3, dB.checkTime(temp1));
            dB.removeDupTimes(temp2, tempTypeB);
            dB.removeDupTimes(temp1, dB.checkTime(temp1));
               
            return 2;
         }
         else
         {
            int[][] temp1 = {{a[0][0], a[0][1]}, {b[0][0], b[0][1]}};
            int[][] temp2 = {{b[0][0], b[0][1]}, {b[1][0], b[1][1]}};
            
            String tempTypeA = dA.type.get(indexA);
            String tempTypeB = dB.type.get(indexB);
            
            dA.replaceTime(indexA, temp2, tempTypeA);
            dA.addTime(indexA, temp1, tempTypeA);
            
            dB.replaceTime(indexB, temp2, tempTypeB);
            dB.addTime(indexB, temp1, dB.checkTime(temp1));
            
            dA.removeDupTimes(temp2, tempTypeA);
            dA.removeDupTimes(temp1, tempTypeA);
            dB.removeDupTimes(temp2, tempTypeB);
            dB.removeDupTimes(temp1, dB.checkTime(temp1));
               
            return 1;
         }
      }// a start is > b start
      else if(a[0][0] > b[0][0] || (a[0][0] == b[0][0] && a[0][1] > b[0][1]))
      {
         if(a[1][0] > b[1][0] || (a[1][0] == b[1][0] && a[1][1] > b[1][1])) // a end > b end
         {
            if(a[0][0] > b[1][0] || (a[0][0] == b[1][0] && a[0][1] >= b[1][1])) // a start > b end
            {
               int[][] temp1 = {{b[0][0], b[0][1]}, {b[1][0], b[1][1]}};
               int[][] temp2 = {{a[0][0], a[0][1]}, {a[1][0], a[1][1]}};
               String tempTypeA = dA.type.get(indexA);
               String tempTypeB = dB.type.get(indexB);
               
               dA.replaceTime(indexA, temp2, tempTypeA);
               dA.addTime(indexA, temp1, dA.checkTime(temp1));
               
               dB.replaceTime(indexB, temp2, dB.checkTime(temp2));
               dB.addTime(indexB, temp1, tempTypeB);
               
               dA.removeDupTimes(temp2, tempTypeA);
               dA.removeDupTimes(temp1, dA.checkTime(temp1));
               dB.removeDupTimes(temp2, dB.checkTime(temp2));
               dB.removeDupTimes(temp1, tempTypeB);
            
               return 1;
            }
            else
            {
               int[][] temp1 = {{b[0][0], b[0][1]}, {a[0][0], a[0][1]}};
               int[][] temp2 = {{a[0][0], a[0][1]}, {b[1][0], b[1][1]}};
               int[][] temp3 = {{b[1][0], b[1][1]}, {a[1][0], a[1][1]}};
               
               String tempTypeA = dA.type.get(indexA);
               String tempTypeB = dB.type.get(indexB);
               
               dA.replaceTime(indexA, temp3, tempTypeA);
               dA.addTime(indexA, temp2, tempTypeA);
               dA.addTime(indexA, temp1, dA.checkTime(temp1));
               
               dB.replaceTime(indexB, temp3, dB.checkTime(temp3));
               dB.addTime(indexB, temp2, tempTypeB);
               dB.addTime(indexB, temp1, tempTypeB);
               
               dA.removeDupTimes(temp3, tempTypeA);
               dA.removeDupTimes(temp2, tempTypeA);
               dA.removeDupTimes(temp1, dA.checkTime(temp1));
               dB.removeDupTimes(temp3, dB.checkTime(temp1));
               dB.removeDupTimes(temp2, tempTypeB);
               dB.removeDupTimes(temp1, tempTypeB);
               
               return 2;
            }
         }
         else if (a[1][0] == b[1][0] && a[1][1] == b[1][1])
         {
            int[][] temp1 = {{b[0][0], b[0][1]}, {a[0][0], a[0][1]}};
            int[][] temp2 = {{a[0][0], a[0][1]}, {b[1][0], b[1][1]}};
            
            String tempTypeA = dA.type.get(indexA);
            String tempTypeB = dB.type.get(indexB);
            
            dA.replaceTime(indexA, temp2, tempTypeA);
            dA.addTime(indexA, temp1, dA.checkTime(temp1));
            
            dB.replaceTime(indexB, temp2, tempTypeB);
            dB.addTime(indexB, temp1, tempTypeB);
            
            dA.removeDupTimes(temp2, tempTypeA);
            dA.removeDupTimes(temp1, dA.checkTime(temp1));
            dB.removeDupTimes(temp2, tempTypeB);
            dB.removeDupTimes(temp1, tempTypeB);
               
            return 1;
         }
      }
      return 0;
   }
   
   //Check if "time" is within a time in timeSlots, then returns either the type if there is a time, or returns "unknown"
   public String checkTime(int[][] time)
   {
      int[][] tempTime;
      for(int i = 0; i < timeSlots.size(); i++)
      {
         tempTime = timeSlots.get(i);
         if(( (time[0][0] > tempTime[0][0]) || (time[0][0] == tempTime[0][0] && time[0][1] >= tempTime[0][1])) && ( (time[1][0] < tempTime[1][0]) || (time[1][0] == tempTime[1][0] && time[1][1] <= tempTime[1][1])))
         {
            if(timeSlots.size() != type.size())
            {
               int tempYear = year;
               System.out.println("Caution, timeSlots.size() != type.size() after checkTime was called " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
            }
            return type.get(i);
         }
         else if(time[0][0] > tempTime[0][0] && time[0][0] < tempTime[1][0])
         {
            int[][] tempTempTime = timeSlots.get(i);
            String tempType = type.get(i);
            int j = 1;
            boolean didBreak = false;
            while(time[1][0] >= tempTempTime[0][0] && time[1][1] >= tempTempTime[0][1] && (i + j) < timeSlots.size())
            {
               if(!tempType.equals(type.get(i + j)))
               {
                  didBreak = true;
                  break;
               }
               j++;
               if((i + j) < timeSlots.size())
               {
                  tempTempTime = timeSlots.get(i + j);
               }
            }
            if(!didBreak)
            {
               return type.get(i);
            }
         }
      }
      if(timeSlots.size() != type.size())
      {
         int tempYear = year;
         System.out.println("Caution, timeSlots.size() != type.size() after checkTime was called " + month + "/" + day + "/" + (tempYear % 100) + " timeSlots: " + timeSlots.size() + " typeSize: " + type.size());
      }
      return "unknown";
   }
}