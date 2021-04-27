package lab4;

public class Time2 {
   private int hour; // 0 - 23
   private int minute; // 0 - 59
   private int second; // 0 - 59


   //无参构造函数
   //把时间初始化为00:00:00
   public Time2() 
   {                                             
      this(0, 0, 0);
   } 

   //有参构造函数——hour
   public Time2(int hour) 
   {                                               
      this(hour, 0, 0);
   } 

   //有参构造函数——hour minute
   public Time2(int hour, int minute) 
   {
      this(hour, minute, 0);
   } 

   //有参构造函数——hour minute second
   public Time2(int hour, int minute, int second) 
   {                    
      if (hour < 0 || hour >= 24) 
      {
         throw new IllegalArgumentException("hour must be 0-23"); //抛出异常信息
      } 

      if (minute < 0 || minute >= 60) {
         throw new IllegalArgumentException("minute must be 0-59"); //抛出异常信息
      } 

      if (second < 0 || second >= 60) {
         throw new IllegalArgumentException("second must be 0-59"); //抛出异常信息
      } 

      this.hour = hour;
      this.minute = minute; 
      this.second = second; 
   } 

   // Time2 constructor: another Time2 object supplied           
   public Time2(Time2 time) 
   {                                   
      // invoke constructor with three arguments
      this(time.getHour(), time.getMinute(), time.getSecond());
   } 

   // Set Methods
   // set a new time value using universal time;  
   // validate the data
   public void setTime(int hour, int minute, int second) 
   {
      if (hour < 0 || hour >= 24) 
      {
         throw new IllegalArgumentException("hour must be 0-23");
      } 

      if (minute < 0 || minute >= 60) 
      {
         throw new IllegalArgumentException("minute must be 0-59");
      } 

      if (second < 0 || second >= 60) 
      {
         throw new IllegalArgumentException("second must be 0-59");
      } 

      this.hour = hour;
      this.minute = minute; 
      this.second = second; 
   } 

   // validate and set hour 
   public void setHour(int hour) 
   {
      if (hour < 0 || hour >= 24) 
      {
         throw new IllegalArgumentException("hour must be 0-23");
      }

      this.hour = hour;
   } 

   // validate and set minute 
   public void setMinute(int minute) 
   {
      if (minute < 0 || minute >= 60) 
      {
         throw new IllegalArgumentException("minute must be 0-59");
      }

      this.minute = minute; 
   } 

   // validate and set second 
   public void setSecond(int second) 
   {
      if (second < 0 || second >= 60) 
      {
         throw new IllegalArgumentException("second must be 0-59");
      }

      this.second = second;
   } 

   // Get Methods         
   // get hour value      
   public int getHour() 
   {
	   return hour;
}

   // get minute value      
   public int getMinute() 
   {
	   return minute;
   } 

   // get second value      
   public int getSecond() 
   {
	   return second;
}   

   // convert to String in universal-time format (HH:MM:SS)
   public String toUniversalString() 
   {
      return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
   } 

   // convert to String in standard-time format (H:MM:SS AM or PM)
   public String toString() 
   {
      return String.format("%d:%02d:%02d %s", 
         ((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
         getMinute(), getSecond(), (getHour() < 12 ? "AM" : "PM"));
   }
   
   public void incrementSecond()
   {
	   if(this.second<59)
	   {
		   this.second++;
	   }
	   else
	   {
		   this.second = 0;
		   incrementMinute();
	   }
   }
   
   public void incrementMinute()
   {
	   if(this.minute<59)
	   {
		   this.minute++;
	   }
	   else
	   {
		   this.minute = 0;
		   incrementHour();
	   }
   }
   
   public void incrementHour()
   {
	   if(this.hour<23)
		   this.hour++;
	   else
		   this.hour = 0;
   }
} 
