package com.fdm.w5.bankaccount;

class AccountCurrent extends AbsAccount {
	int cheque_id = 1;
	private static String account_type = "Current";

	public AccountCurrent(AbsCustomer customer) {
		super(customer, account_type);
	}

	public AccountCurrent(AbsCustomer customer, double init_balance) {
		super(customer, init_balance, account_type);
	}

	@Override
	public boolean accountWithdraw(double amount) {
		this.account_balance -= amount;
		return true;
	}

	@Override
	public boolean accountCorrection(double amount) {
		this.account_balance = amount;
		return true;
	}

	String issueCheque() {
		int current_cheque = this.cheque_id;
		++this.cheque_id;
		return this.getAccountId() + "BCK" + current_cheque;
	}

	String nextCheque() {
		return this.getAccountId() + "BCK" + Integer.toString(this.cheque_id + 1);
	}
}
