/*
	There are four key principles associated with object-oriented programming:
		1) abstraction
		2) encapsulation
		3) inheritance
		4) polymorphism

	This Date class illustrates principles 1 and 2

	1) abstraction: Abstraction in object-oriented programming allows us to focus only on the desired 
		properties and behaviors of the objects and discard what is unimportant or irrelevant. It also 
		allows us the abiity to hide implementation details from agents that are using our classes. We
		will only expose the "interface"
	
	2) encapsulation: The object-oriented principle of encapsulation is the notion that we should hide 
		the contents of a class, except what is absolutely necessary to expose. Hence, we will restrict 
		the access to our class as much as we can, so that a user can change the class properties and 
		behaviors only from methods provided by the class. This also allows us to easily perform
		'domain validation'.

		marking something 'private' hides it (denies access)
		marking something 'public' exposes it (permits access)

	domain: The set of all possible values of a variable.

	Date Domains:
	=============
	1) Days {1 - 31} with some exceptions
	2) Months {1 - 12}
	3) Years {4-digit numbers}
 */

 import java.util.HashMap;
 import java.util.Map.Entry;
 
 public class Date{
 
	 // ================== //
	 // INSTANCE VARIABLES //
	 // ================== //
	 
	 private int month	= 1;
	 private int day		= 1;
	 private int year	= 1000;
	 private DateFormat format;
 
	 // =============== //
	 // CLASS VARIABLES //
	 // =============== //
 
	 private static final HashMap<Integer, String> months = new HashMap<Integer, String>();
 
	 static{
		 months.put(1,"January");
		 months.put(2,"February");
		 months.put(3,"March");
		 months.put(4,"April");
		 months.put(5,"May");
		 months.put(6,"June");
		 months.put(7,"July");
		 months.put(8,"August");
		 months.put(9,"September");
		 months.put(10,"October");
		 months.put(11,"November");
		 months.put(12,"December");
	 };
 
	 private static int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
 
	 // ================ //
	 // PUBLIC CONSTANTS //
	 // ================ //
 
	 public static enum DateFormat{LONG_DATE, SHORT_DATE};
 
	 // =================== //
	 // CONSTRUCTOR METHODS //
	 // =================== //
 
	 /**
	  * The no argument constructor which uses default values of
	  * 1/1/1000 and LONG_DATE formatting
	  */
	 public Date() {
		 this.month	= 1;
		 this.day	= 1;
		 this.year	= 1000;
		 this.format = DateFormat.LONG_DATE;
	 }
 
	 /**
	  * Overloaded constructor to set all fields.
	  * Arguments will be domain validated
	  * 
	  * @param month
	  * @param day
	  * @param year
	  */ 
	 public Date(int month, int day, int year){
		 this.setYear(year);
		 this.setMonth(month);
		 this.setDay(day);
		 this.format = DateFormat.LONG_DATE;
	 }
 
	 /**
	  * Copy constructor
	  * 
	  * @param d Date object to copy
	  */
	 public Date(Date d){
		 this.month	= d.month;
		 this.day	= d.day;
		 this.year	= d.year;
		 this.format = d.format;
	 }
 
	 // ================ //
	 // INSTANCE METHODS //
	 // ================ //
 
	 public void setMonth(int month){
		 if(month >= 1 && month <= 12)
			 this.month = month;
	 }
 
	 public void setMonth(String month){
		 if(!months.containsValue(month))return;
		 for (Entry<Integer, String> month_mapping : months.entrySet())
			 if (month_mapping.getValue().equals(month))
				 this.month = month_mapping.getKey();
	 }
 
	 public int getMonth(){
		 return month;
	 }
 
	 public String getMonthName(){
		 return months.get(month);
	 }
 
	 public void setDay(int _day){
		 if(_day < 1 || _day > 31) return;
		 if(_day <= days[ this.month - 1 ])
			 this.day = _day;
		 else if(this.month == 2 && isLeapYear(this.year) && _day <= 29)
			 this.day = _day;
		 else this.day = 1;
	 }
 
	 public int getDay(){
		 return day;
	 }
 
	 public void setYear(int year){
		 if(year >= 1000 && year <= 9999)
			 this.year = year;
	 }
 
	 public int getYear(){
		 return year;
	 }
 
	 public void setFormat(DateFormat format){
		 this.format = format;
	 }
 
	 public int compareTo(Date dateArg){
		 if(	this.equals( dateArg ) )
			 return 0;
		 if(	this.year  < dateArg.year ) return -1;
		 if( this.year  > dateArg.year )	return  1;
		 if( this.month < dateArg.month) return -1;
		 if( this.month > dateArg.month) return  1;
		 if( this.day   < dateArg.day ) 	return -1;
										 return  1;
	 }
 
	 public String toString(){
		 if(format == DateFormat.SHORT_DATE)
			 return month + "/" + day + "/" + year;
		 else 
			 return getMonthName() + " " + day + ", " + year;
	 }
 
	 public boolean equals(Date d){
		 if(this == d) return true;
		 if(d == null) return false;
		 return	this.day	== d.day	&&
				 this.month	== d.month	&&
				 this.year	== d.year;
	 }
 
	 private boolean isLeapYear(int y){
		 return (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
	 }
 }
 