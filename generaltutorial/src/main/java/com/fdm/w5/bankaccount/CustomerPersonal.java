package com.fdm.w5.bankaccount;

class CustomerPersonal extends AbsCustomer {
	public CustomerPersonal(String name, String address, String tax_id) {
		super(name, address, tax_id);
	}

	boolean resetAccounts() {
		if (super.getAccountList() == null)
			return false;
		for (AbsAccount account : super.getAccountList()) {
			account.accountCorrection(0);
		}
		return true;
	}
}
