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
         }
         command = scan.next();
      }

      System.out.println("Good Bye");
   }

   public static void studentSearch(String argument, boolean bus){
      while()
   }
}