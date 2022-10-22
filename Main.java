
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

	
		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		
		
        reader.nextLine();
        
        String[] twoList = new String[N];
        for (int i=0; i<N;i++) {
    	   String input = reader.nextLine();
    	  
    	   twoList[i] = input;
       	
       }
        
       
        int id1 = 0;
        for(int i=0; i<N;i++) {
        	String input = twoList[i];
        	String[] lst = input.split(" ");
        	int num = Integer.parseInt(lst[0]);
        
        	if (num == 2) {   //Input 2 : Creating a new Operator
        		
        		Operator operator = new Operator(id1, Double.parseDouble(lst[1]), Double.parseDouble(lst[2]), Double.parseDouble(lst[3]), Integer.parseInt(lst[4]));
        	    operators[id1] = operator;
        	    id1++;
        	}
        }
       id1=0;
        int id = 0;
        for(int i=0; i<N;i++) {
        	
        	String input = twoList[i];
        	String[] lst = input.split(" ");
        	int num = Integer.parseInt(lst[0]);
        	
        	
        	if ((int) num ==1) {    //Input 1 : Creating a new Customer
        		
        		Customer customer = new Customer(id,lst[1], Integer.parseInt(lst[2]), operators[Integer.parseInt(lst[3])], Double.parseDouble(lst[4]));
        	    customers[id] = customer;
        	    id+=1;
        	}
        	
        	}
        id = 0;
       
      
        for(int i=0; i<N;i++) {
        	//String input = reader.nextLine();
        	String input = twoList[i];
        	String[] lst = input.split(" ");
        	int num = Integer.parseInt(lst[0]);
        	
        	
        	if (num == 3) {   //Input 3 : A customer talks to another customer
        		int payer = Integer.parseInt(lst[1]); //customer id of the caller and who pays for it
        		int calledOne = Integer.parseInt(lst[2]);
        		int time = Integer.parseInt(lst[3]);
  
        		
        		double talkingCost = customers[payer].getOperator().calculateTalkingCost(time, customers[payer]); //calculates the cost of call for caller
       		    
        		if (customers[payer].getBill().check(talkingCost) || customers[payer] == customers[calledOne]) { 
        	    	continue;	
        		}
        		
        		customers[payer].getOperator().operatorTotalTalk(time);
        		customers[payer].talk(time, customers[calledOne]);   //adds time the totalTime the caller talks
        	    customers[payer].getBill().add(talkingCost);
        	    
        	    customers[calledOne].getOperator().totalTime += time;  //since talking time isn't added to called operator in calculateTalkingCost fct, I add it manually
        	    customers[calledOne].totalTime += time;                  //adding time to the called ones total time too.      		 
        	}
        	
            	
        	else if (num == 4) {  //Input 4 : A customer sends message to another customer
        		int sender = Integer.parseInt(lst[1]); //id of the one who sends message
        		int receiver = Integer.parseInt(lst[2]);  //id of the customer who gets the message
        	    int quantity = Integer.parseInt(lst[3]);  //nof messages sent
        	    
        	    double messageCost = customers[sender].getOperator().calculateMessageCost(quantity, customers[sender], customers[receiver]);  //the cost of sending message
        	    
        	    if (customers[sender].getBill().check(messageCost) || customers[sender] == customers[receiver]) {
        	    	continue;
        	    	}
        	    customers[sender].getOperator().operatorTotalMessage(quantity);
        	    customers[sender].message(quantity, customers[receiver]);  //adds the quantity of message sent by sender.
        	    customers[sender].getBill().add(messageCost);       //adds the messageCost to the debts of sender.
        	   

        	}
        	
               	    
        	    else if (num == 5) {
        		int user = Integer.parseInt(lst[1]);   //id of the customer who uses the network
        		double amount = Double.parseDouble(lst[2]);  //the amount network is used.
        		
        		double networkCost = customers[user].getOperator().calculateNetworkCost(amount); //the cost for using 'amount' MB of the net
        		
        		if (customers[user].getBill().check(networkCost)) {	
        			continue;
        		} 
       		    
        		customers[user].getOperator().operatorTotalNetwork(amount);
        		customers[user].connection(amount);             //to add the used net to customers totalNetwork
        		customers[user].getBill().add(networkCost);          //adds the cost of net usage to the currentDebt of the customer
        		
        		 		
        	}
        
                	
        	    else if (num == 6) {
        		int user = Integer.parseInt(lst[1]);            //id of the customer who pays her bills
        		double amount = Double.parseDouble(lst[2]);       //amount that customer pays
        		
        		                // pays the indicated amount, also increases moneyPaid by given amount
        	    
        		
                if (customers[user].getBill().getCurrentDebt()<amount ) {   //if a customer pays more than her debts, sets the debt to zero
                	customers[user].getBill().pay(customers[user].getBill().getCurrentDebt());
                	customers[user].getBill().setCurrentDebt(0.00);
        	    }
                else
                	customers[user].getBill().pay(amount);        // pays the indicated amount, also increases moneyPaid by given amount
        	}
        	
                	
        	    else if (num == 7) {
        		int user = Integer.parseInt(lst[1]);              //id of the customer who wants to change her operator
        		int operator2 = Integer.parseInt(lst[2]);         //id of the operator that customer wants to use
        		
        		customers[user].setOperator(operators[operator2]);
        	
        	}
        	
        	
        	    else if (num == 8) {
        		int user = Integer.parseInt(lst[1]);             //id of the user who wants to change her limitingAmount
        		double newAmount = Double.parseDouble(lst[2]);     //updated limitingAmount
        		if (newAmount >= customers[user].getBill().getCurrentDebt())
        		     customers[user].getBill().changeTheLimit(newAmount);
        	}
                
        }
           

      //WRTING TO FILE   
        
    	//Output1

        for (int j=0; j< operators.length; j++)	{
    		String line = "Operator " + j +" : "  + operators[j].totalTime + " " + operators[j].totalMessage + " " +  String.format("%.2f", operators[j].totalNetwork);
    		outstream1.println(line);
    	}
    	
        //output2
    	for (int j=0; j<customers.length;j++) {
    		String line = "Customer " + j +" : " + String.format("%.2f", customers[j].getBill().moneyPaid) + " " + String.format("%.2f", customers[j].getBill().getCurrentDebt());
    		outstream1.println(line);
    	}
    	
    	//output3
    	int mostTime = 0;
		String name3= " ";
    	for (int i=0; i<customers.length;i++) {     //finds the customer who talked the most and her name   		
    		if (customers[i].totalTime > mostTime || (name3 ==" ")) {   //without second part if totalTime=0, writing name as " "
    			
    			name3 = customers[i].name;
    			mostTime = customers[i].totalTime;
    		}   		
    	}
    	String line3 = name3 +" : " + mostTime;
    	outstream1.println(line3);
    	
    	//output4
    	int mostMessage = 0;
    	String name4 = " ";
    	for (int i=0; i<customers.length;i++) {     //finds the customer who talked the most and her name   		
    		if (customers[i].totalMessage > mostMessage || (name4 ==" ")) {
    			
    			name4 = customers[i].name;
    			mostMessage = customers[i].totalMessage;
    		}   		
    	}
    	String line4 = name4 + " : "+ mostMessage;
    	outstream1.println(line4);
    	
    	//output5
    	double mostNetwork = 0;
    	String name5 = " ";
    	for (int i=0; i<customers.length;i++) {     //finds the customer who talked the most and her name   		
    		if (customers[i].totalNetwork > mostNetwork || (name5 ==" ")) {   
    			
    			name5 = customers[i].name;
    			mostNetwork = customers[i].totalNetwork;
    		}   		
    	}
    	String line5 = name5 + " : " + String.format("%.2f", mostNetwork);
    	outstream1.println(line5);
    	
    	outstream1.close();
        reader.close();      


	
	} 
}

