/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package landsale;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class Buyer {
    
    public void addBuyer(){
    
    
        Config cf = new Config();
        Scanner sc = new Scanner(System.in);    
    
        System.out.print("Buyer First Name: ");
        String bname = sc.next();
        System.out.print("Buyer Last Name: ");
        String blname = sc.next();
        System.out.print("Gender: ");
        String gen = sc.next();
        System.out.print("Phone No: ");
        String pho = sc.next();
        System.out.print("Email: ");
        String ema = sc.next();
        System.out.print("Address: ");
        String add = sc.next();
        
        String buyer = "INSERT INTO Buyer(First_Name, Last_Name, Gender, Phone_No, Email, Address) VALUES (?,?,?,?,?,?)";
         cf.addBuyer(buyer, bname, blname, gen, pho, ema, add);
}
    
    public void viewBuyer(){
        
        
        
         String qry = "SELECT * FROM Buyer";
        String[] hdrs = {"Buyer ID ", "First Name", "Last Name", "Gender", "Phone_No", "Email", "Address"};
        String[] clmns = {"Buyer_ID", "First_Name", "Last_Name", "Gender", "Phone_No", "Email","Address"};
        
         Config cf = new Config();
         cf.viewBuyer(qry, hdrs, clmns);
        
    }
    
    public void Buyers(){
        
        
        Buyer by = new Buyer();
        Config cf = new Config();
        Scanner sc = new Scanner(System.in);    
        
        
        String res;
        do{
            System.out.println("");
        System.out.println("1. Add Buyer");
        System.out.println("2. View Buyer");
        System.out.println("3. Update Buyer");
        System.out.println("4. Delete Buyer");
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
                
        switch (choice){
            
            case 1:
                by.addBuyer();
                break;
            case 2:
                by.viewBuyer();
                break;
            case 3:
                by.viewBuyer();
                String update1 = "UPDATE Buyer SET Phone_No = ?, Email = ?, Address = ? WHERE Buyer_ID = ?  ";
               
                int buyid;
                while (true) {
                System.out.print("Select Buyer ID to update: ");
                if (sc.hasNextInt()) {
                    buyid = sc.nextInt();
                    if (cf.getSingleValues("SELECT Buyer_ID  FROM Buyer  WHERE Buyer_ID = ?", buyid) != 0) {
                        break;
                    } else {
                        System.out.println("Entered Buyer doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Buyer ID.");
                    sc.next(); 
                }
            }
                System.out.print("Enter new Phone No. ");
                String newphon = sc.next();
                System.out.print("Enter new Email: ");
                String newema = sc.next();
                System.out.print("Enter new Address: ");
                String newadd = sc.next();
                
                cf.updateBuyer(update1, newphon, newema, newadd);
                
                
                break;
            case 4:
                by.viewBuyer();
                String delete1 = "DELETE FROM Buyer WHERE Buyer_ID = ?";
               
                
                int buydelete;
                while (true) {
                System.out.print("Select Buyer ID to update to delete: ");
                if (sc.hasNextInt()) {
                    buydelete = sc.nextInt();
                    if (cf.getSingleValues("SELECT Buyer_ID  FROM Buyer  WHERE Buyer_ID = ?", buydelete) != 0) {
                        break;
                    } else {
                        System.out.println("Entered Buyer doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a Buyer ID.");
                    sc.next(); 
                }
            }
                cf.deleteBuyer(delete1, buydelete);
                break;
            case 5:
                System.out.println("Exit..");
                
                break;
        }
        System.out.println("");
        System.out.print("Do you want to continue? Yes or No: ");
        res = sc.next();
        }while(res.equalsIgnoreCase("yes"));
                
        
        
    }
}
