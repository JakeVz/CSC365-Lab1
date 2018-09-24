import java.util.*;
import java.io.*;
import java.lang.*;

public class SchoolSearch extends studentObj{

   public static ArrayList<studentObj> students = new ArrayList<studentObj>();

   public static void main(String[] args){
      populateStudents();
      startUp();
   }

   public static void populateStudents(){
      File studentFile = new File("students.txt");

      try (BufferedReader br = new BufferedReader(new FileReader(studentFile))){
         String line;

         while ((line = br.readLine()) != null){
            students.add(new studentObj(line));
         }
       } catch (Exception e) {
         System.out.println("Error reading in students");
         System.exit(-1);
       }
   }

   public static void startUp(){
      String options = "Here are your options:\nS[tudent]: <lastname> [B[us]]"
         + "\nT[eacher]: <lastname>\nB[us]: <number>" 
         + "\nG[rade]: <number> [[H[igh]] | [L[ow]]\nA[verage]: <number> " 
         + "I[nfo]\nQ[uit]";
      System.out.println("\n\nSchool Directory\n-------------------------");
      System.out.println(options);

      Scanner scan = new Scanner(System.in);
      String command = scan.next();
      
      while(!command.equalsIgnoreCase("Q")){
         String [] strSplit = command.split(":");
         String opition = strSplit[0];
         String argument = "";
         switch(opition){
            case "s":
            case "S":
               argument = strSplit[1];
               boolean bus = false;
               if(argument.charAt(argument.length()-1) == 'B'){
                  bus = true;
               }
               studentSearch(argument.substring(0, argument.length()-1), bus);
               break;

            case "g":
            case "G":
               argument = strSplit[1];
               boolean high = false;
               boolean low = false;
               if(argument.charAt(argument.length()-1) == 'H') high = true;
               else if(argument.charAt(argument.length() -1) == 'L') low = true;
               gradeSearch(argument, high, low);
               break;

            case "i":
            case "I":
               info();
               break;
         }
         command = scan.next();
      }

      System.out.println("Good Bye");
   }

   public static void studentSearch(String argument, boolean bus){
      for(studentObj temp : students){
         if(temp.StLastName.equalsIgnoreCase(argument) && bus == false){
            System.out.println(temp.StLastName + " " + temp.StFirstName + " " + temp.Grade + " " +
                                 temp.Classroom + " " + temp.TLastName + " " + temp.TFirstName);
         }
         else if(temp.StLastName.equalsIgnoreCase(argument) && bus == true){
            System.out.println(temp.StLastName + " " + temp.StFirstName + " " + temp.Grade + " " +
                                 temp.Classroom + " " + temp.TLastName + " " + temp.TFirstName);
         }

      }
   }

   public static void gradeSearch(String argument, boolean high, boolean low){
      int grade = Integer.parseInt(argument);
      studentObj temp = new studentObj();
      
      if(high){   
         double gpa = Integer.MIN_VALUE;
         for(studentObj student : students){
            if(student.Grade == grade && gpa < student.GPA){
               gpa = student.GPA;
               temp = student;
            }
         }
         System.out.println(temp.StLastName + " " + temp.StFirstName + " " + temp.GPA + " " + temp.TLastName + " " + temp.TFirstName + " " + temp.Bus);
      }
      else if(low){
         double gpa = Integer.MAX_VALUE;
         for(studentObj student : students){
            if(student.Grade == grade && gpa > student.GPA){
               gpa = student.GPA;
               temp = student;
            }
         }
         System.out.println(temp.StLastName + " " + temp.StFirstName + " " + temp.GPA + " " + temp.TLastName + " " + temp.TFirstName + " " + temp.Bus);
      }

      else{
         for(studentObj student : students){
            if(student.Grade == grade){
               System.out.println(student.StLastName + " " + student.StFirstName);
            }
         }
      }
   }

   public static void info(){
      for(int i = 0; i <= 6; i++){
         int count = 0;
         for(studentObj student : students){
            if(student.Grade == i) count++;
         }

         System.out.println(i + ": " + count);
      }
   }
}
