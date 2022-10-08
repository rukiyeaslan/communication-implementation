
package question;

public class User {
	private int userID;
	private String userName;
	private Wallet wallets[];
	private int numUsedWallets;
	
	public User() {
		this.userID = -1;
		this.userName = "";
		this.wallets = new Wallet[5];  // max 5 wallets per user
		
		// initialization of individual array elements
		for(int i=0; i<wallets.length; i++) {
			this.wallets[i] = new Wallet();
		}
		
		this.numUsedWallets = 0;
	}
	
	public void newWallet(String coinName, String coinCode) {
		this.wallets[this.numUsedWallets] = new Wallet();
		this.wallets[this.numUsedWallets].setCoinName(coinName);
		this.wallets[this.numUsedWallets].setCoinCode(coinCode);
		
		this.numUsedWallets++;
	}
	
	public void setUserInfo(int userID, String userName) {
		this.userID = userID;
		this.userName = userName;
	}

	public void depositCoinToWallet(String coinCode, double amount) {
		for(Wallet w:this.wallets) {  // iterating over all wallets			
			//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

			String walletCoinCode = w.getCoinCode(); // get wallet's coin code
			
			//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
			
			if(walletCoinCode.equals(coinCode)) {  // requested wallet found
				w.depositCoins(amount);
			}
		}
	}
	
	public double getCurrentAmountFromWallet(String coinCode) {
		double currentAmount = 0;
		
		for(Wallet w:this.wallets) {  // iterating over all wallets			
			String walletCoinCode = w.getCoinCode();

			if(walletCoinCode.equals(coinCode)) {  // requested wallet found
				currentAmount = w.getCurrentAmount();
			}
		}
		
		return currentAmount;
	}
	
	public void printHoldings() {
		for(Wallet w:this.wallets) {
			if(w.getCurrentAmount() > 0)  // print unless 0 coins of that type
				System.out.println(w.getCurrentAmount() + " " + w.getCoinName());
		}
	}
	
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}	
}

