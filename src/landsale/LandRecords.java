
package landsale;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class LandRecords {
    
    
    public void addRecords(){
        Config cf = new Config();

         Scanner sc = new Scanner(System.in);    
        System.out.println(" - Buyer List - ");
         Buyer by = new Buyer();
         by.viewBuyer();
         System.out.println(" - Land List - ");
         Seller sl = new Seller();
         sl.viewLand();
         
    
         int buyerid;
                while (true) {
                System.out.print("Enter Buyer ID: ");
                if (sc.hasNextInt()) {
                    buyerid = sc.nextInt();
                    if (cf.getSingleValues("SELECT Buyer_ID  FROM Buyer  WHERE Buyer_ID = ?", buyerid) != 0) {
                        break;
                    } else {
                        System.out.println("Entered Buyer doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Buyer ID.");
                    sc.next(); 
                }
            }
               
        
         int landid;
                while (true) {
                System.out.print("Enter Land ID: ");
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
                
         System.out.print("Payment Method: ");
         String meth  = sc.next();
         
         System.out.print("Enter Cash: ");
         int cash = sc.nextInt();
         
         String price = "SELECT Price FROM Lands WHERE Land_ID  =?";
         double price1 = cf.getSingleValues(price, landid);
         
         while(cash < price1 ){
             System.out.print("Not enough Cash, Try Again: ");
             cash = sc.nextInt();
         }
         
         double change  = cash - price1;
         
         System.out.println("---------------------------------");
         System.out.printf("Total Change is: %.2f\n", change);
         System.out.println("---------------------------------");
            
         LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
         
         String status = "On Transaction";
         
         String land = "INSERT INTO LandRecords(Buyer_ID, Land_ID, Payment_Method, Price, Cash, Change, Date_of_Transaction, Status ) VALUES (?,?,?,?,?,?,?,?)";
         
         cf.addBuyer(land, buyerid, landid, meth, price1, cash, change, date, status);
    }
    
    public void viewLandRecords(){
        
      String view = "SELECT LandRecords.LandRecord_ID, "
                + "Buyer.First_Name, Buyer.Last_Name, "
                + "Lands.Owner_Name, Lands.Land_Type, Lands.Location, Lands.Price, LandRecords.Status "
                + "FROM LandRecords "
                + "LEFT JOIN Buyer ON Buyer.Buyer_ID = LandRecords.Buyer_ID "
                + "LEFT JOIN Lands ON Lands.Land_ID = LandRecords.Land_ID";       
      
        String[] header = {"Land Record ID", "First Name", "Last Name", "Owner Name", "Land Type", "Location", "Price", "Status"};
        String[] column = {"LandRecord_ID", "First_Name", "Last_Name", "Owner_Name", "Land_Type", "Location","Price", "Status"};
           Config cf = new Config();
           cf.viewBuyer(view, header, column);
        
    }
    
    public void Records(){
        
        
        
        LandRecords lr = new LandRecords();
        
            Config cf = new Config();
           Scanner sc = new Scanner(System.in);    
          String res;
        do{
            System.out.println("");
        System.out.println("1. Add a Land Record");
        System.out.println("2. View Land Record");
        System.out.println("3. Update a Record");
        System.out.println("4. Delete a Record ");
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
                lr.addRecords();
                break;
            case 2:
                lr.viewLandRecords();
                break;
            case 3:
                String up = "UPDATE LandRecords SET Status = ? WHERE LandRecord_ID = ? ";
               
                
         int idrecord;
                while (true) {
                System.out.print("Enter Record ID to update: ");
                if (sc.hasNextInt()) {
                    idrecord = sc.nextInt();
                    if (cf.getSingleValues("SELECT LandRecord_ID  FROM LandRecords  WHERE LandRecord_ID = ?", idrecord) != 0) {
                        break;
                    } else {
                        System.out.println("Entered Land Record ID doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Land Record ID.");
                    sc.next(); 
                }
            }
                
                
                System.out.print("Enter new Status: ");
                String newstats = sc.next();
                
                
                cf.updateBuyer(up, newstats, idrecord);
                break;
            case 4:
                String del = "DELETE FROM LandRecords WHERE LandRecord_ID = ? ";
                
                
                int delete;
                while (true) {
                System.out.print("Enter Record ID to delete: ");
                if (sc.hasNextInt()) {
                    delete = sc.nextInt();
                    if (cf.getSingleValues("SELECT LandRecord_ID  FROM LandRecords  WHERE LandRecord_ID = ?", delete) != 0) {
                        break;
                    } else {
                        System.out.println("Entered Land Record ID doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Land Record ID.");
                    sc.next(); 
                }
            }
                
                cf.deleteBuyer(del, delete);
                break;
            case 5:
                
                System.out.println("Exit");
                break;
            
        }
        System.out.println("");
        System.out.print("Do you want to continue? Yes or No: ");
        res = sc.next();
    }while(res.equalsIgnoreCase("yes"));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
