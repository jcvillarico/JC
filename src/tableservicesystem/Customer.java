package tableservicesystem;

import java.util.Scanner;


public class Customer {
    
    public void customerTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
       
        System.out.println("[******WELCOME CUSTOMER******]");    
        System.out.println("1. --ADD CUSTOMER--");
        System.out.println("2. --VIEW CUSTOMER--");
        System.out.println("3. --UPDATE CUSTOMER--");
        System.out.println("4. --DELETE CUSTOMER--");
        System.out.println("5. --EXIT CUSTOMER-- ");
        
        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        Customer cr = new Customer ();

        switch(action){
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
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addCustomers () { 
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Contact Number: ");
        String cont = sc.next();
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
        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
  
        while(conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Customer ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.println("New Customer Name: ");
        String nname = sc.next();
        System.out.println("New Contact Number: ");
        String ncont = sc.next();
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
        System.out.println("Enter the ID  to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Customer ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_customer WHERE c_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}

