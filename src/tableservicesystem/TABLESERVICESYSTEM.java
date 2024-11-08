package tableservicesystem;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Scanner;


public class TABLESERVICESYSTEM {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);    
        boolean exit = true;
        do{
        System.out.println("*** WELCOME TO TABLE SERVICE SYSTEM ***");
        System.out.println("");
        System.out.println("1. CUSTOMER");
        System.out.println("2. TABLE");
        System.out.println("3. EMPLOYEE");
        System.out.println("4. ORDER");
        System.out.println("5. EXIT");
        
        System.out.print("Enter Action: ");
        int act = sc.nextInt();
        
        switch(act){
            case 1:
                Customer cr = new Customer ();
                cr.customerTransaction();
            break;
            
            case 2:
                Table tb = new Table ();
                tb.tableTransaction();
            break;
            
            case 3:
                Employee em = new Employee ();
                em.eTransaction();
            break;
            case 4:
                Order or = new Order ();
                or.orderTransaction();
            break;
            case 5:
                System.out.println("are you sure??? (yes/no): ");
                String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                           exit = false;
                }
            break;   
            
        }
        }while (exit);
        System.out.print("Sunod napud!");               
    
    }} 