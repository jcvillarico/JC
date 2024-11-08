package tableservicesystem;

import java.util.Scanner;


public class Order {
    public void orderTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
       
        System.out.println("[******WELCOME TO ORDERS******]");    
        System.out.println("1. --ADD ORDERS--");
        System.out.println("2. --VIEW ORDERS--");
        System.out.println("3. --UPDATE ORDERS--");
        System.out.println("4. --DELETE ORDERS--");
        System.out.println("5. --EXIT ORDERS-- ");
        
        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        Order or = new Order ();

        switch(action){
            case 1:
                or.addOrders();
                or.viewOrders();
                break;
            case 2:       
                or.viewOrders();
                break;
            case 3:
                
                break;
            case 4:
                   
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addOrders () { 
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        Customer cr = new Customer ();
        cr.viewCustomers();
        
        System.out.print("Enter the ID of the Customer: ");
        int cid = sc.nextInt();
        
        String csql = "SELECT c_id FROM tbl_customer WHERE c_id = ?";
        while(conf.getSingleValue(csql, cid) == 0){
            System.out.print("Customer does not exist, Select Again: ");
            cid = sc.nextInt();
            
        }
         
        Table tb = new Table ();
        tb.viewTables();
        
        System.out.print("Enter the ID of the Table: ");
        int tid = sc.nextInt();
        
        String tsql = "SELECT t_id FROM tbl_tables WHERE t_id = ?";
        while(conf.getSingleValue(tsql, tid) == 0){
            System.out.print("Table does not exist, Select Again: ");
            tid = sc.nextInt();
            
        }
        
        System.out.print("Total Amount: ");
        double total = sc.nextDouble();
        
        System.out.print("Order Status: ");
        String stat = sc.next();
        
        String qry = "INSERT INTO tbl_order (c_id, t_id, o_totalamount, o_status)"
                + "VALUES (?, ?, ?, ?)";
        conf.addRecord(qry, cid, tid, total, stat);
    }
    
        public void viewOrders() {
    
    String qry = "SELECT o_id, c_name, c_status, tbl_tables.e_id, tbl_employee.e_name, t_capacity, t_location, t_status, o_totalamount, o_status FROM tbl_order "
               + "LEFT JOIN tbl_customer ON tbl_customer.c_id = tbl_order.c_id "
               + "LEFT JOIN tbl_tables ON tbl_tables.t_id = tbl_order.t_id "
               + "LEFT JOIN tbl_employee ON tbl_employee.e_id = tbl_tables.e_id";

    
    String[] hrds = {"O_ID", "Customer", "Reservation Status", "Assigned Employee ID", "Employee Name", "Table Capacity", "Location", "Table Status", "Total Amount", "Order Status"};
    
    
    String[] clms = {"o_id", "c_name", "c_status", "e_id", "e_name", "t_capacity", "t_location", "t_status", "o_totalamount", "o_status"};
    
    config conf = new config();
    conf.viewRecords(qry, hrds, clms);


  
    }
}