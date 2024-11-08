package tableservicesystem;

import java.util.Scanner;


public class Table {
    
    public void tableTransaction(){
    
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
       
        System.out.println("[******WELCOME TO TABLES******]");    
        System.out.println("1. --ADD TABLE--");
        System.out.println("2. --VIEW TABLE--");
        System.out.println("3. --UPDATE TABLE--");
        System.out.println("4. --DELETE TABLE--");
        System.out.println("5. --EXIT TABLE-- ");
        
        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        Table tb = new Table ();
        

        switch(action){
            case 1:
                tb.addTables();   
                tb.viewTables();
                break;
            case 2:       
                tb.viewTables();
                break;
            case 3:
                tb.viewTables();
                tb.updateTables();
                tb.viewTables();
                break;
            case 4:
                tb.viewTables();
                tb.deleteTables();
                tb.viewTables();    
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addTables(){
        Scanner sc = new Scanner (System.in);   
        config conf = new config(); 
        Employee em = new Employee ();
        em.viewEmployee();   
        
        System.out.print("Enter the ID of the Employee: ");
        int eid = sc.nextInt();
        
        String csql = "SELECT e_id FROM tbl_employee WHERE e_id = ?";
        while(conf.getSingleValue(csql, eid) == 0){
            System.out.print("Customer does not exist, Select Again: ");
            eid = sc.nextInt();
            
        }

        
        System.out.print("Table Capacity: ");
        String cap = sc.next();
        System.out.print("Location: ");
        String loc = sc.next();
        System.out.print("Table Status: ");
        String stat = sc.next();

        String qry = "INSERT INTO tbl_tables (e_id, t_capacity, t_location, t_status)"
                + "VALUES ( ?, ?, ?, ?)";
        conf.addRecord(qry, eid, cap, loc, stat);


    }

   public void viewTables() {
    String qry = "SELECT tbl_tables.t_id, tbl_tables.e_id, tbl_tables.t_capacity, tbl_tables.t_location, tbl_tables.t_status "
               + "FROM tbl_tables "
               + "LEFT JOIN tbl_employee ON tbl_employee.e_id = tbl_tables.e_id";

    String[] hrds = {"T_ID", "E_ID", "Table Capacity", "Location", "Status"};
    String[] clms = {"t_id", "e_id", "t_capacity", "t_location", "t_status", };
    config conf = new config();
    conf.viewRecords(qry, hrds, clms);
    }
   
    private void updateTables() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
  
        while(conf.getSingleValue("SELECT t_id FROM tbl_tables WHERE t_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Table ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.println("New Table Capacity: ");
        String ncap = sc.next();
        System.out.println("New Location: ");
        String nloc = sc.next();
        System.out.println("New Status: ");
        String nstat = sc.next();
        String qry = "UPDATE tbl_tables SET t_capacity = ?, t_location = ?, t_status = ? WHERE t_id = ?";
        
        
        conf.updateRecord(qry, ncap, nloc, nstat, id);         
        
        
    }
    
    private void deleteTables() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter the ID to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT t_id FROM tbl_tables WHERE t_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Table ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_tables WHERE t_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}


