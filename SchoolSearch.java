import java.util.*;

public class SchoolSearch{

   public static void main(String[] args){
      startUp();
   }

   public static void startUp(){
      String opitions = "Here are your opitions:\n S[tudent]: <lastname> [B[us]]\n T[eacher]: <lastname>\n" + 
                        "B[us]: <number>\n G[rade]: <number> [[H[igh]] | [L[ow]]\n A[verage]: <number> " + 
                        "I[nfo]\n Q[uit]";
      System.out.println(opitions);
      Scanner scan = new Scanner(System.in);
      while(!scan.next().equals("Q")){
         System.out.println("Testing");
      }

      System.out.println("Good Bye");
   }
}