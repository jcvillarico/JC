package tableservicesystem;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Employee {
    public void eTransaction() {
    Scanner sc = new Scanner(System.in);
    String response;

    do {
        System.out.println("");
        System.out.println("[******WELCOME TO EMPLOYEE******]");
        System.out.println("");
        System.out.println("1. --ADD EMPLOYEE--");
        System.out.println("2. --VIEW EMPLOYEE--");
        System.out.println("3. --UPDATE EMPLOYEE--");
        System.out.println("4. --DELETE EMPLOYEE--");
        System.out.println("5. --EXIT EMPLOYEE--");

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

        Employee em = new Employee();

        switch (action) {
            case 1:
                em.addEmployee();
                break;
            case 2:
                em.viewEmployee();
                break;
            case 3:
                em.viewEmployee();
                em.updateEmployee();
                em.viewEmployee();
                break;
            case 4:
                em.viewEmployee();
                em.deleteEmployee();
                em.viewEmployee();
                break;
            case 5:
                System.out.println("Exiting Employee Management...");
                return; 
            default:
                System.out.println("Invalid choice! Please select a valid option (1-5).");
        }

        System.out.print("Do you want to continue? (yes/no): ");
        response = sc.next();
    } while (response.equalsIgnoreCase("yes"));

    System.out.println("Thank You, See you soon!");
}

    
    
    public void addEmployee () { 
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Employee Name: ");
        String ename = sc.nextLine();
        System.out.print("Role: ");
        String role = sc.next();
        System.out.print("Shift: ");
        String shift = sc.next();
        System.out.print("Contact Number: ");
        int contact = sc.nextInt();

        String sql = "INSERT INTO tbl_employee (e_name, e_role, e_shift, e_contact) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, ename, role, shift, contact);


    }

    public void viewEmployee() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_employee";
        String[] Headers = {"Employee_ID","Employee Name", "Role", "Shift", "Contact Number"};
        String[] Columns = {"e_id", "e_name", "e_role", "e_shift", "e_contact"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateEmployee() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid Employee ID: ");
            sc.next();
        }
            try {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT e_id FROM tbl_employee WHERE e_id = ?", id) != 0) {
                    break; 
                }
                System.out.println("Selected ID doesn't exist! Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID! Please enter a valid ID.");
                sc.nextLine(); 
            }
        }
        
        System.out.println("New Employee Name: ");
        String nename = sc.next();
        System.out.println("New Role: ");
        String nrole = sc.next();
        System.out.println("New Shift: ");
        String nshift = sc.next();
        System.out.println("New Contact Number: ");
        int ncontact = sc.nextInt();
        String qry = "UPDATE tbl_employee SET e_name = ?, e_role = ?, e_shift = ?, e_contact = ? WHERE e_id = ?";
        
        
        conf.updateRecord(qry, nename, nrole, nshift, ncontact, id);         
        
        
    }
    
    private void deleteEmployee() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        int id;
        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid Employee ID: ");
            sc.next();
        }
            try {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT e_id FROM tbl_employee WHERE e_id = ?", id) != 0) {
                    break; 
                }
                System.out.println("Selected ID doesn't exist! Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID! Please enter a valid ID.");
                sc.nextLine(); 
            }
        }
        
        String qry = "DELETE FROM tbl_employee WHERE e_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}



