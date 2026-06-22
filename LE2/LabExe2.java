import java.util.Scanner;
public class Main 
{
   public static void main(String[] args) 
   {
      Employee e1 = new Employee();
      e1.setEmpNum(101);
      e1.setEmpName("Ali");
      System.out.println("Employee Number: " + e1.getEmpNum());
      System.out.println("Employee Name: " + e1.getEmpName());
      
      Book b1 = new Book("Java 101", "John Doe");
      b1.display();
      
      new User();
      new User();
      System.out.println("Total Users: " + User.getUserCount());
   
      Employee e = new Employee();
      e.setSalary(5000);
      System.out.println("Salary: " + e.getSalary());
   
   }
}

class Employee 
{
   // TODO: Add fields
   private int employeeNum;
   private String employeeName;
    
   // TODO: Make salary private
   private double salary;

   // TODO: Add setter and getter methods
   ////setter employee number
   public void setEmpNum(int employeeNum) 
   {
      this.employeeNum = employeeNum;
   }

   ////getter employee number
   public int getEmpNum() 
   {
      return employeeNum;
   }

   ////setter employee name
   public void setEmpName(String employeeName) 
   {
      this.employeeName = employeeName;
   }

   ////getter employee name 
   public String getEmpName() 
   {
      return employeeName;
   }
    
   // TODO: Write setSalary() and getSalary() methods
   public void setSalary(double s)
   {
      this.salary = s; 
   }
   
   public double getSalary()
   {
      return salary;
   }
}


class Car 
{
   // TODO: Declare two private instance variables (brand and year)
   private String brand;
   private int year;

   // TODO: Create setter for brand
   public void setBrand(String brand) 
   {
      this.brand = brand;
   }

   // TODO: Create getter for year    
   public int getYear() 
   {
      return year;
   }
}


class Book 
{
   private String title;
   private String author;

   // TODO: Add a constructor 
   public Book(String t, String a) 
   {
      title = t;
      author = a;
   }
    
   public void display() 
   {
      System.out.println("Title: " + title);
      System.out.println("Author: " + author);
   }
}


class Student 
{
   private String name;
   private int age;

   // TODO: Implement setter for name
   public void setName(String name) 
   {
      this.name = name;
   }

   // TODO: Implement getter for age
   public int getAge() 
   {
      return age;
   }
}

class Message 
{
   // TODO: Write a method display() that prints "Hello!"
   public void display() 
   {
      System.out.println("Hello!");
   }
    
   // TODO: Overload display(String msg) to print "Message: msg"
   public void display(String msg) 
   {
      System.out.println("Message: " + msg);
   }
}

class User 
{
   // TODO: Declare a static counter variable
   private static int counter = 0;

   public User() 
   {
      // TODO: Increment counter
      counter++;
   }

   public static int getUserCount() 
   {
      // TODO: Return counter
      return counter; 
   }
}
