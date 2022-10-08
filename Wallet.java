
package question;

public class Wallet {
	private String walletID;
	private String coinName;
	private String coinCode;

	private double currentAmount;


	public Wallet() {
		this.walletID = this.generateRandomWalletID();  // initialization of walletID with a random string
		this.coinName = "";
		this.coinCode = "";
		this.currentAmount = 0;
	}

	/**
	 * 
	 * @param amount the amount of coin to be added to currentAmount
	 * @return currentAmount the newly increased currentAmount
	 */
	public double depositCoins(double amount) {
		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
        this.currentAmount += amount;

		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
		
		return this.currentAmount;
	}

	/**
	 * Subtracts amount from current holdings. If there is not enough coins, withdraws all.
	 * 
	 * @param amount the amount of coin to be withdrawn from the wallet
	 * @return withdrawnAmount
	 */
	public double withdrawCoins(double amount) {
		double withdrawnAmount = 0;
		
		if(amount > this.currentAmount) {  // requested more than current amount
			withdrawnAmount = this.currentAmount;
			this.currentAmount = 0;
		}
		else {
			withdrawnAmount = amount;
			this.currentAmount -= amount;
		}
	
		return withdrawnAmount;
	}

	public String generateRandomWalletID() {
		int sz = 32;  // length of the id
		String randomID = "";

		// taken from https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijklmnopqrstuvxyz";

		for (int i=0; i<sz; i++) {

			// generate a random number between 0 and sz
			int index = (int)(alphaNumericString.length() * Math.random());
			randomID += alphaNumericString.charAt(index);
		}

		return randomID;
	}

	/**
	 * @return the walletID
	 */
	public String getWalletID() {
		return walletID;
	}

	/**
	 * @return the coinName
	 */
	public String getCoinName() {
		return coinName;
	}

	/**
	 * @return the coinCode
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/**
	 * @return the currentAmount
	 */
	public double getCurrentAmount() {
		return currentAmount;
	}

	/**
	 * @param coinName the coinName to set
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	/**
	 * @param coinCode the coinCode to set
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	/**
	 * @param currentAmount the currentAmount to set
	 */
	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}


}

