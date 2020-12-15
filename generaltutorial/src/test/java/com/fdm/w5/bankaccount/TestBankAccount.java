package com.fdm.w5.bankaccount;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBankAccount {

	// @Test
	public void test() {
		fail("Not yet implemented");
	}

	static CustomerPersonal my_cus1 = new CustomerPersonal("John Ab", "white house", "25725");
	static CustomerCompany my_cus2 = new CustomerCompany("John Bull", "white house", "25725");
	static CustomerPersonal my_cus3 = new CustomerPersonal("John Carl", "white house", "25725");
	static CustomerPersonal my_cus4 = new CustomerPersonal("John Doe", "white house", "25725");
	static CustomerPersonal my_cus5 = new CustomerPersonal("John Ed", "white house", "25725");

//	 @Test
	public void prsn_acc() {
		System.out.println(my_cus1.getCustomerId());
		System.out.println(my_cus1.getAddress());
		System.out.println(my_cus1.customerDelete());
		System.out.println(my_cus1.getName());
		my_cus1.openSavingsAccount(265);
		AccountSavings my_acc1 = new AccountSavings(my_cus1, 1000);
		my_cus1.printAccountList();
//		my_acc1.accountDeposit(7000);
//		my_cus1.resetAccounts();
//		my_cus1.printAccountList();
	}

	@Test
	public void comp_acc() {
		AccountSavings my_acc2 = new AccountSavings(my_cus2, 1000);
		my_cus2.openCurrentAccount(265);
		my_acc2.getCustomer().printAccountList();
		my_cus2.addToAllAccounts(50);
		my_acc2.getCustomer().printAccountList();
	}

}
