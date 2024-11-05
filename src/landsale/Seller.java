
package landsale;

import java.util.Scanner;


public class Seller {
    
    
    public void addSellerLand(){
        
          Config cf = new Config();
        Scanner sc = new Scanner(System.in);    
        
        System.out.print("Owner Name: ");
        String oname = sc.nextLine(); 
        
        System.out.print("Description: ");
        String dres = sc.nextLine(); 
        
        System.out.print("Land Type: ");
        String type = sc.nextLine(); 
        
        System.out.print("Kilometer: ");
        String kilo = sc.nextLine(); 
        
        System.out.print("Acres: ");
        String acres = sc.nextLine(); 
        
        System.out.print("Hectares: ");
        String hec = sc.nextLine();         
        
        System.out.print("Location: ");
        String location = sc.nextLine(); 
        
        System.out.print("Price: ");
        double price = sc.nextDouble();         
        sc.nextLine(); 
        
        System.out.print("Reason: ");
        String reason = sc.nextLine();         
        
        String sell = "INSERT INTO Lands(Owner_Name, Description, Land_Type, Kilometers, Acres, Hectares, Location, Price, Reason ) VALUES (?,?,?,?,?,?,?,?,?)";
        cf.addBuyer(sell, oname, dres, type, kilo, acres, hec, location, price, reason);
    }
    
    public void viewLand(){
        
        
         String qry = "SELECT * FROM Lands";
        String[] hdrs = {"Land ID ", "Owner Name ", "Description ", "Land Type", "Kilomters", "Acres", "Hectares", "Location","Price","Reason"};
        String[] clmns = {"Land_ID", "Owner_Name", "Description", "Land_Type", "Kilometers", "Acres","Hectares","Location","Price","Reason"};
        
         Config cf = new Config();
         cf.viewBuyer(qry, hdrs, clmns);
       
        
    }
    
    public void Lands(){
        
        Seller sl = new Seller();
         Config cf = new Config();
        Scanner sc = new Scanner(System.in);    
        
        String res;
        do{
            System.out.println("");
        System.out.println("1. Add Land");
        System.out.println("2. View Land");
        System.out.println("3. Update Land");
        System.out.println("4. Delete Land");
        System.out.println("5. Exit");
        
        
         int choice;
             while (true) {
             System.out.println("");
             System.out.print("Enter choice: ");
             if (sc.hasNextInt()) {
              choice = sc.nextInt();
                if (choice >= 1 && choice <= 5) {
                  break;
                } else {
                 System.out.println("Please enter a number between 1 and 5.");
                }
                    System.out.println("Invalid input. Enter a integer not a character.");
                    sc.next();
                }
            }
             
             switch(choice){
                 case 1:
                     sl.addSellerLand();
                     break;
                 case 2:
                     sl.viewLand();
                     break;
                 case 3:
                     sl.viewLand();
                     String up = "UPDATE Lands SET  Price = ?, Reason = ?  WHERE Land_ID = ? ";
                     
                   
                     
                      int landid;
                while (true) {
                System.out.print("Enter Land ID to update: ");
                if (sc.hasNextInt()) {
                    landid = sc.nextInt();
                    if (cf.getSingleValues("SELECT Land_ID  FROM Lands  WHERE Land_ID = ?", landid) != 0) {
                        break;
                    } else {
                        System.out.println("Entered Land ID doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Land ID.");
                    sc.next(); 
                }
            }
                     System.out.print("Enter new Price: ");
                     double newprice = sc.nextDouble();
                     System.out.print("Enter new Reason: ");
                     String newreason = sc.next();
                     
                     cf.updateBuyer(up, newprice, newreason, landid);
                     
                     
                     break;
                 case 4:
                     sl.viewLand();
                     String del = "DELETE FROM Lands WHERE Land_ID = ?";
                    
                      
                      int delid;
                while (true) {
                System.out.print("Enter Land ID to delete: ");
                if (sc.hasNextInt()) {
                    delid = sc.nextInt();
                    if (cf.getSingleValues("SELECT Land_ID  FROM Lands  WHERE Land_ID = ?", delid) != 0) {
                        break;
                    } else {
                        System.out.println("Entered Land ID doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Land ID.");
                    sc.next(); 
                }
            }
                     cf.deleteBuyer(del, delid);
                     break;
                 case 5:
                     System.out.println("Exit the Seller Panel");
                     break;
                 
             }
             System.out.println("");
             System.out.print("Do you want to continue on Seller panel? Yes or No: ");
             res = sc.next();
        }while(res.equalsIgnoreCase("yes"));
    }
    
    
    
    
    
    
    
}
