import java.util.*;

public class studentObj{
   public String StLastName = "";
   public String StFirstName = "";
   public int Grade = -1;
   public int Classroom = -1;
   public int Bus = -1;
   public double GPA = -1.0;
   public String TLastName = "";
   public String TFirstName = "";

   public studentObj(String kid){
      String[] data = kid.split(",");
      StLastName = data[0];
      StFirstName = data[1];
      Grade = Integer.parseInt(data[2]);
      Classroom =  Integer.parseInt(data[3]);
      Bus =  Integer.parseInt(data[4]);
      GPA = Double.parseDouble(data[5]);
      TLastName = data[6];
      TFirstName = data[7];
/*
      for (int i = 0; i < 8; i++){
         System.out.println(data[i]);
      }
*/
   }

   public studentObj(){
      StLastName = null;
   }
/*
   public static void main(String args[]){
      studentObj test = new studentObj(args[0]);
      System.out.println(test.Grade + " || " + test.GPA);
   }
*/
}
