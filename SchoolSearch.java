import java.util.*;

public class SchoolSearch{

   ArrayList<StudentObj> students = new ArrayList<StudentObj>();

   public static void main(String[] args){
      populateStudents();
      startUp();
   }

   public static void populateStudents(){
      
   }

   public static void startUp(){
      String opitions = "Here are your opitions:\n S[tudent]: <lastname> [B[us]]\n T[eacher]: <lastname>\n" + 
                        "B[us]: <number>\n G[rade]: <number> [[H[igh]] | [L[ow]]\n A[verage]: <number> " + 
                        "I[nfo]\n Q[uit]";
      System.out.println(opitions);

      Scanner scan = new Scanner(System.in);
      String command = scan.next();
      
      while(!command.equals("Q")){
         String [] strSplit = command.split(":");
         String opition = strSplit[0];
         switch(opition){
            case "S":
               String argument = strSplit[1];
               boolean bus = false;
               if(argument.getCharAt(argument.length()-1) == 'B'){
                  bus = true;
               }
               studentSearch(argument.substring(0, arugment.length()-1), bus);
               break;

            case "G":
               String arugment = strSplit[1];
               boolean high = false;
               boolean low = false;
               if(argument.getCharAt(argument.length()-1) == 'H') high = true;
               else if(arugment.getCharAt(argument.length() -1) == 'L') low = true;
               gradeSearch(argument, high, low);
               break;
            case "I":
               info();
               break;
         }
         command = scan.next();
      }

      System.out.println("Good Bye");
   }

   public static void studentSearch(String argument, boolean bus){
      for(StudentObj temp : students){
         if(temp.StLastName.equals(arugment) && bus == false){
            System.out.println(temp.StLastName + " " + temp.StFirstName + " " + temp.Grade + " " +
                                 temp.Classroom + " " + temp.TLastName + " " + temp.TFirstName);
         }
         else if(temp.StLastName.equals(arugment) && bus == true){
            System.out.println(temp.StLastName + " " + temp.StFirstName + " " + temp.Grade + " " +
                                 temp.Classroom + " " + temp.TLastName + " " + temp.TFirstName);
         }

      }
   }

   public static void gradeSearch(String argument, boolean high, boolean low){
      int grade = Integer.parseInt(argument);
      
      if(high){
         
         long gpa = Integer.MIN_VALUE;
         StudentObj temp = new StudentObj("");
         for(StudentObj student : students){
            if(student.Grade == grade && gpa < student.GPA){
               gpa = student.GPA
               temp = student;
            }
         }
         System.out.println(temp.StLastName + " " + temp.StFirstName + " " temp.GPA + " " + temp.TLastName + " " + temp.TFirstName + " " + temp.Bus);
      }
      else if(low){
         long gpa = Integer.MAX_VALUE;
         for(StudentObj student : students){
            if(student.Grade == grade && gpa > student.GPA){
               gpa = student.GPA
               temp = student;
            }
         }
         System.out.println(temp.StLastName + " " + temp.StFirstName + " " temp.GPA + " " + temp.TLastName + " " + temp.TFirstName + " " + temp.Bus);
      }

      else{
         for(StudentObj student : students){
            if(student.Grade == grade){
               System.out.println(student.StLastName + " " + student.StFirstName);
            }
         }
      }
   }

   public static void info(){
      for(int i = 0; i <= 6; i++){
         int count = 0;
         for(StudentObj student : students){
            if(student.Grade == i) count++;
         }

         System.out.println(i + ": " + count);
      }
   }
}