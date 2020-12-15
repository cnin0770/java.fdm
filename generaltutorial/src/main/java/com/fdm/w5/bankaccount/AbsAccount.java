package com.fdm.w5.bankaccount;

abstract class AbsAccount {
	private static int account_count = 1000;
	private int account_id = initAccountId();
	protected double account_balance = 0;
	AbsCustomer customer;
	private boolean deleted = false;
	String account_type;

	// /*
	AbsAccount(AbsCustomer customer, String account_type) {
		this.customer = customer;
		this.account_type = account_type;
		customer.addAccount(this);
	}

	AbsAccount(AbsCustomer customer, double init_balance, String account_type) {
		this.customer = customer;
		this.account_balance = init_balance;
		this.account_type = account_type;
		customer.addAccount(this);
	}
	// */

	public int getAccountId() {
		if (this.deleted)
			return 0;
		return account_id;
	}

	private static int initAccountId() {
		account_count += 5;
		return account_count - 5;
	}

	public double getAccountBalance() {
		if (this.deleted)
			return 0;
		return account_balance;
	}

	public AbsCustomer getCustomer() {
		if (this.deleted)
			return null;
		return this.customer;
	}

	boolean setCustomer(AbsCustomer customer) {
		if (this.deleted)
			return false;
		this.customer = customer;
		customer.addAccount(this);
		return true;
	}

	boolean accountDelete() {
		if (this.deleted)
			return false;
		this.deleted = true;
		return this.deleted;
	}

	public boolean accountDeposit(double amount) {
		if (this.deleted)
			return false;
		if (amount <= 0)
			return false;
		this.account_balance += amount;
		return true;
	}

	public boolean accountWithdraw(double amount) {
		if (this.deleted)
			return false;
		if (amount > this.account_balance)
			return false;
		this.account_balance -= amount;
		return true;
	}
	
	public String toString() {
		if (this.deleted)
			return "";
		return String.format("type: %s | id: %d | balance: %.2f", this.account_type, this.account_id, this.account_balance);
	}

	public boolean accountCorrection(double amount) {
		if (this.deleted)
			return false;
		if (amount < 0)
			return false;
		this.account_balance = amount;
		return true;
	}
}
