
package question;

public class Market {

	public static void main(String[] args) {
		// Main method is given so that you can run it as a Java Application
		// and test the code yourself. You won't receive credits from what is written here.
		// As always, you will receive the actual scores from JUnit cases.
		
		User u0 = new User();
		u0.setUserInfo(0, "yigit");
		u0.newWallet("bitcoin", "btc");
		u0.newWallet("ethereum", "eth");
		u0.depositCoinToWallet("eth", 50.0);
		u0.depositCoinToWallet("btc", 3.0);
		System.out.println(u0.getUserName());
		u0.printHoldings();
	}

}

