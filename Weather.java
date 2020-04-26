package Project_1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Keyvan Shabani on 2/15/2020.
 **/

public class Weather {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean exit = true;
       while (exit){
           System.out.println("Enter 1 or 2 or 3\n1.Display All Cities From warmest to coolest\n2.Display all rainy cities\n3.Display all cities sorted based on UVIndex\n4.Exit\nEnter your choise number:");
           String userChose = scanner.nextLine();

           ArrayList<City> information = new ArrayList<>();
           switch (userChose){
               case "1":
                   System.out.println("Please wait till getting all cities information from dark sky api(real time)");
                   try {
                       information = Network.getInstance().getCitiesInformation();
                   } catch (Exception e) {
                       System.out.println("Error On getting information");
                   }
                   System.out.println("\nAll the cities from the warmest city to the coolest city.");
                   sortArray(information,true);
                   for (City ci : information) {
                       System.out.println("State: "+ci.getState()+", Name: "+ci.getName()+", Temperature:"+ ci.getTemp());
                   }
                   break;
               case "2":
                   System.out.println("Please wait till getting all cities information from dark sky api(real time)");
                   try {
                       information =  Network.getInstance().getCitiesInformation();
                   } catch (Exception e) {
                       System.out.println("Error On getting information");
                   }
                   System.out.println("\nAll the cities where it is currently raining. ");
                   int count = 0;
                   for (City ci : information) {
                       if (ci.getIcon().equals("rain")) {
                           System.out.println("State: " + ci.getState() + ", Name: " + ci.getName());
                           count++;
                       }
                   }
                   System.out.println("number of total cities where its raining is " + count);
                   break;
               case "3":
                   System.out.println("Please wait till getting all cities information from dark sky api(real time)");
                   try {
                       information = Network.getInstance().getCitiesInformation();
                   } catch (Exception e) {
                       System.out.println("Error On getting information");
                   }
                   System.out.println("\nAll the cities from lowest UV index to highest UV index.");
                   sortArray(information,false);
                   for (City ci : information) {
                       System.out.println("State: "+ci.getState()+", Name: "+ci.getName()+", UVIndex:"+ ci.getUVIndex());
                   }
                   break;
               case "4":
                   System.exit(0);
                   break;
           }
       }
    }

    public static void sortArray(ArrayList<City> infos, Boolean tempet) {
        if (tempet){
            for (int i = 0; i < infos.size(); i++) {
            City min = infos.get(i);
            int minId = i;
            for (int j = i + 1; j < infos.size(); j++) {
                if (infos.get(j).getTemp() > min.getTemp()) {
                    min = infos.get(j);
                    minId = j;
                }
            }
            // swapping
            City temp = infos.get(i);
            infos.set(i,min);
            infos.set(minId,temp);
        }
        }else {
            for (int i = 0; i < infos.size(); i++) {
                City min = infos.get(i);
                int minId = i;
                for (int j = i + 1; j < infos.size(); j++) {
                    if (infos.get(j).getUVIndex() < min.getUVIndex()) {
                        min = infos.get(j);
                        minId = j;
                    }
                }
                // swapping
                City temp = infos.get(i);
                infos.set(i,min);
                infos.set(minId,temp);

            }
        }

    }
}
