package tableservicesystem;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Customer {
    
    public void customerTransaction() {
    Scanner sc = new Scanner(System.in);
    String response;

    do {
        System.out.println("");
        System.out.println("[******WELCOME CUSTOMER******]");
        System.out.println("");
        System.out.println("1. --ADD CUSTOMER--");
        System.out.println("2. --VIEW CUSTOMER--");
        System.out.println("3. --UPDATE CUSTOMER--");
        System.out.println("4. --DELETE CUSTOMER--");
        System.out.println("5. --EXIT CUSTOMER--");

        int action = -1;
        
        while (true) {
            System.out.print("Enter Action: ");
            if (sc.hasNextInt()) {
                action = sc.nextInt();
                break;
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                sc.next(); 
            }
        }

        Customer cr = new Customer();

        switch (action) {
            case 1:
                cr.addCustomers();
                break;
            case 2:
                cr.viewCustomers();
                break;
            case 3:
                cr.viewCustomers();
                cr.updateCustomers();
                cr.viewCustomers();
                break;
            case 4:
                cr.viewCustomers();
                cr.deleteCustomers();
                cr.viewCustomers();
                break;
            case 5:
                System.out.println("Exiting Customer Management...");
                return; 
            default:
                System.out.println("Invalid choice! Please select a valid option (1-5).");
        }

        System.out.print("Do you want to continue? (yes/no): ");
        response = sc.next();
    } while (response.equalsIgnoreCase("yes"));

    System.out.println("Thank You, See you soon!");
}

    
    
    public void addCustomers () { 
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Contact Number: ");
        int cont = sc.nextInt();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.print("Resservation Status: ");
        String stat = sc.next();

        String sql = "INSERT INTO tbl_customer (c_name, c_contact, c_email, c_status) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, name, cont, email, stat);


    }

    public void viewCustomers() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_customer";
        String[] Headers = {"Customers_ID","Customer Name", "Contact Number", "Email", "Status"};
        String[] Columns = {"c_id", "c_name", "c_contact", "c_email", "c_status"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateCustomers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid Customer ID: ");
            sc.next();
        }
            try {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", id) != 0) {
                    break; 
                }
                System.out.println("Selected ID doesn't exist! Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID! Please enter a valid ID.");
                sc.nextLine(); 
            }
        }
        
        System.out.println("New Customer Name: ");
        String nname = sc.next();
        System.out.println("New Contact Number: ");
        int ncont = sc.nextInt();
        System.out.println("New Email: ");
        String nemail = sc.next();
        System.out.println("New Reservation Status: ");
        String nstat = sc.next();
        String qry = "UPDATE tbl_customer SET c_name = ?, c_contact = ?, c_email = ?, c_status = ? WHERE c_id = ?";
        
        
        conf.updateRecord(qry, nname, ncont, nemail, nstat, id);         
        
        
    }
    
    private void deleteCustomers() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        int id;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid Customer ID: ");
            sc.next();
        }
            try {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", id) != 0) {
                    break; 
                }
                System.out.println("Selected ID doesn't exist! Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID! Please enter a valid ID.");
                sc.nextLine(); 
            }
        }
        
        String qry = "DELETE FROM tbl_customer WHERE c_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}

