package landsale;

import java.util.Scanner;


public class LandSale {
    
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    
       
        
        boolean exit = true;
        
        do{
            System.out.println("");
        System.out.println("1. Buyers");
        System.out.println("2. Lands");
        System.out.println("3. Land Records"); 
        System.out.println("4. Exit");
        
         int choice;
         while (true) {
             System.out.println("");
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 5.");
                    }
                } else {
                    System.out.println("Invalid input. Enter a integer not a character.");
                    sc.next();
                }
            }
        
        
        switch(choice){
            case 1:
                Buyer by = new Buyer();
               by.Buyers();
                break;
            case 2:
                Seller sl = new Seller();
                sl.Lands();
                break;
            case 3:
                LandRecords lr = new LandRecords();
                lr.Records();
                break;
            case 4:
                System.out.println("");
                System.out.println("Do you want to exit the application? Yes or No: ");
                String res = sc.next();
                if(res.equalsIgnoreCase("yes")){
                    exit = false;
                }
                break;  
            
        }
            }while(exit);

        
        
    }
    
}
