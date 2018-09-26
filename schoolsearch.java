import java.util.*;
import java.io.*;
import java.lang.*;

public class schoolsearch extends studentObj{

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
         + "\nI[nfo]\nQ[uit]";
      System.out.println("\n\nSchool Directory\n-------------------------");
      System.out.println(options);

      Scanner scan = new Scanner(System.in);
      String command = scan.nextLine();

      while(true){
         String [] strSplit = command.split(":");
         char opition = strSplit[0].charAt(0);
         String argument = "";
         
         switch(opition){
            case 's':
            case 'S':
               argument = strSplit[1];
               argument = argument.trim();
               String [] busOpitions = argument.split(" ");

               if(busOpitions.length == 2 && busOpitions[1].charAt(0) == 'B'){
                  studentSearch(busOpitions[0].trim(), true);
                  
               }
               else{
                  studentSearch(argument.substring(0, argument.length()), false);

               }
               break;

            case 'g':
            case 'G':
               argument = strSplit[1].trim();
               boolean high = false;
               boolean low = false;
               String[] gradeOpitions = argument.split(" ");

               if(gradeOpitions.length == 2 && gradeOpitions[1].charAt(0) == 'H'){
                  gradeSearch(gradeOpitions[0], true, false);
               }
               else if(gradeOpitions.length == 2 && gradeOpitions[1].charAt(0) == 'L'){
                  gradeSearch(gradeOpitions[0],false,true);
               }
               else gradeSearch(argument, high, low);
               break;

            case 'i':
            case 'I':
               info();
               break;

            case 'a':
            case 'A':
               argument = strSplit[1].trim();
               String grade = argument.replaceAll(" ", "");
               average(grade);
               break;

            case 'b':
            case 'B':
               argument = strSplit[1].trim();
               String busNum = argument.replaceAll(" ", "");
               bus(busNum);
               break;

            case 't':
            case 'T':
               argument = strSplit[1].trim();
               String teacher = argument.replaceAll(" ", "");
               teacher(teacher);
               break;

            case 'q':
            case 'Q':
               System.out.println("Good Bye");
               return;

            default:
               System.out.println("Not a valid opition");
               break;
         }

         System.out.println("\n\nSchool Directory\n-------------------------");
         System.out.println(options);
         command = scan.nextLine();
      }
   }

   public static void studentSearch(String argument, boolean bus){
      if (!bus) System.out.println("\nStudent Search:");
      else System.out.println("\nStudent Search With Bus:");
      for(studentObj temp : students){
         if(temp.StLastName.equalsIgnoreCase(argument) && !bus){
            System.out.println(temp.StLastName + "," + temp.StFirstName + "," + temp.Grade + "," +
                                 temp.Classroom + "," + temp.TLastName + "," + temp.TFirstName);
         }
         else if(temp.StLastName.equalsIgnoreCase(argument) && bus){
            System.out.println(temp.StLastName + "," + temp.StFirstName + "," + temp.Bus);
         }

      }
   }

   public static void gradeSearch(String argument, boolean high, boolean low){
      System.out.println("\nGrade Search:");
      try{
         int grade = Integer.parseInt(argument);
         studentObj temp = new studentObj();
         //TODO: Account for in high and low if there are no students in a grade such as with 5
         if(high){   
            double gpa = Integer.MIN_VALUE;
            for(studentObj student : students){
               if(student.Grade == grade && gpa < student.GPA){
                  gpa = student.GPA;
                  temp = student;
               }
            }
            System.out.println(temp.StLastName + "," + temp.StFirstName + "," + temp.GPA + "," + temp.TLastName + "," + temp.TFirstName + "," + temp.Bus);
         }
         else if(low){
            double gpa = Integer.MAX_VALUE;
            for(studentObj student : students){
               if(student.Grade == grade && gpa > student.GPA){
                  gpa = student.GPA;
                  temp = student;
               }
            }
            System.out.println(temp.StLastName + "," + temp.StFirstName + "," + temp.GPA + "," + temp.TLastName + "," + temp.TFirstName + "," + temp.Bus);
         }

         else{
            for(studentObj student : students){
               if(student.Grade == grade){
                  System.out.println(student.StLastName + "," + student.StFirstName);
               }
            }
         }
      }
      catch(Exception ex){
         System.out.println("Please enter a valid number");
      }
   }

   public static void info(){
      System.out.println("\nInformation:");
      System.out.println("Grade | # Of Students");
      for(int i = 0; i <= 6; i++){
         int count = 0;
         for(studentObj student : students){
            if(student.Grade == i) count++;
         }

         System.out.println(i + ": " + count);
      }
   }

   public static void teacher(String teacher){
      System.out.println("\nTeacher Search:");
      for(studentObj temp : students){
         if (temp.TLastName.equalsIgnoreCase(teacher)){
            System.out.println(temp.StLastName + ", " + temp.StFirstName);
         }
      }
   }

   public static void bus(String bus){
      System.out.println("\nBus Search:");
      try{
         int busNum = Integer.parseInt(bus);

         for(studentObj temp : students){
            if (temp.Bus == busNum){
               System.out.println(temp.StLastName + ", " + temp.StFirstName + ", " + temp.Grade + ", " + temp.Classroom);
            }
         }
      }
      catch(Exception ex){
         System.out.println("Enter a valid integer");
      }
   }

   public static void average(String grade){
      try{
         int gradeNum = Integer.parseInt(grade);
      double GPATot = 0.0;
      int numStudents = 0;

      for(studentObj temp : students){
         if (temp.Grade == gradeNum){
            GPATot += temp.GPA;
            numStudents++;
         }
      }
      if (numStudents != 0) System.out.println("\nAverage GPA for grade "+ grade + " is: " + (double)GPATot/numStudents);
      }
      catch(Exception ex){
         System.out.println("Enter a valid number");
      }
      
   }

}
