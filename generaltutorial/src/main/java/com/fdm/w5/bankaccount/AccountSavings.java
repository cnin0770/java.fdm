package com.fdm.w5.bankaccount;

class AccountSavings extends AbsAccount {
	private double interest_rate = 0;
	private static String account_type = "Savings";

	public AccountSavings(AbsCustomer customer) {
		super(customer, account_type);
	}

	public AccountSavings(AbsCustomer customer, double init_balance) {
		super(customer, init_balance, account_type);
	}

	void setInterestRate(double rate) {
		this.interest_rate = rate;
	}

	double getInterestRate() {
		return this.interest_rate;
	}
}
