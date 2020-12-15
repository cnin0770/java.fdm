package com.fdm.w5.bankaccount;

class CustomerCompany extends AbsCustomer {
	public CustomerCompany(String name, String address, String tax_id) {
		super(name, address, tax_id);
	}

	public boolean addToAllAccounts(double amount) {
		if (super.getAccountList() == null)
			return false;
		for (AbsAccount account : super.getAccountList()) {
			account.accountDeposit(amount);
		}
		return true;
	}
}
