package svlt;

public class Acct {
	String acctNo;
	String acctName;
	double balance;
	String acctStatus;
	
	public Acct() {
	
		// TODO Auto-generated constructor stub
	}
	
	public Acct(String acctNo, String acctName, double balance, String acctStatus) {

		this.acctNo = acctNo;
		this.acctName = acctName;
		this.balance = balance;
		this.acctStatus = acctStatus;
	}
	
	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAcctStatus() {
		return acctStatus;
	}

	public void setAcctStatus(String acctStatus) {
		this.acctStatus = acctStatus;
	}

	@Override
	public String toString() {
		return "Acct [acctNo=" + acctNo + ", acctName=" + acctName + ", balance=" + balance + ", acctStatus="
				+ acctStatus + "]";
	}
	
	

}
