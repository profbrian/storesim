package edu.marist.cmpt220l.storesim;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by brian on 11/16/14.
 */
public class Main {

    public static void main(String[] args)
    {
        if(args.length != 4)
        {
            System.out.println("Incorrect number of arguments. Proper form is: java com.marist.cmpt220l.storesim.Main <numCycles> <numRegisters> <maxNewCustomersPerCycle> <maxRegisterTime>");
            return;
        }

        int numCycles = Integer.parseInt(args[0]);
        int numRegisters = Integer.parseInt(args[1]);
        int maxNewCustomersPerCycle = Integer.parseInt(args[2]);
        int maxRegisterTime = Integer.parseInt(args[3]);

        ArrayList<Register> registers = new ArrayList<Register>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Line line = new Line();
        Random random = new Random();

        for(int i=0; i < numRegisters; i++)
            registers.add(new Register());

        System.out.println("Registers: " + numRegisters + "    MCPC: " + maxNewCustomersPerCycle + "    Cycles: " + numCycles + "    MRT: " + maxRegisterTime);
        for(int i=0; i < numCycles; i++)
        {
            //Add any new customers for this cycle
            int numNewCustomers = random.nextInt(maxNewCustomersPerCycle);
            System.out.println("Cycle: " + (i+1) +", New Customers: " + numNewCustomers);
            for(int j=0; j < numNewCustomers; j++) {
                Customer customer = new Customer();
                customers.add(customer);
                line.addCustomer(customer);
            }

            //update the registers, and bring in a new customer if the line isn't
            for(int j=0; j < registers.size(); j++) {
                Register register = registers.get(j);

                if(register.update()) {
                    System.out.println("Customer at register " + (j+1) + " Done");
                    register.removeCustomer();
                    customers.remove(register.getCustomer());
                }

                if(!register.hasCustomer() && line.hasCustomers()) {
                    System.out.println("New customer at register " + (j+1));
                    register.setCustomer(line.getNextCustomer(), random.nextInt(maxRegisterTime));
                }
            }

            //update the customers in line and at the register
            for(int j=0; j < customers.size(); j++)
                customers.get(j).update();

            line.printLine();
            for(int j=0; j < registers.size(); j++) {
                if(registers.get(j).getCustomer() == null)
                    System.out.println("Register " + (j + 1) + ": (Empty)");
                else
                    System.out.println("Register " + (j + 1) + ": " + registers.get(j).getTimeRemaining());
            }
            System.out.println("\n");
        }
    }
}
