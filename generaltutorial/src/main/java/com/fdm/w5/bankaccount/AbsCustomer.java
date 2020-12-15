package com.fdm.w5.bankaccount;

import java.util.ArrayList;

abstract class AbsCustomer {
	private static int id_count = 2000000;
	int customer_id;
	private String name;
	private String address;
	private String tax_id;
	private ArrayList<AbsAccount> account_list = new ArrayList<AbsAccount>();
	private boolean deleted = false;

	AbsCustomer(String name, String address, String tax_id) {
		this.customer_id = initCustomerId();
		this.setName(name);
		this.setAddress(address);
		this.setTaxId(tax_id);
	}

	public int getCustomerId() {
		if (this.deleted)
			return 0;
		return this.customer_id;
	}

	public String getName() {
		if (this.deleted)
			return null;
		return name;
	}

	public boolean setName(String name) {
		if (this.deleted)
			return false;
		this.name = name;
		return true;
	}

	public String getAddress() {
		if (this.deleted)
			return null;
		return address;
	}

	public boolean setAddress(String address) {
		if (this.deleted)
			return false;
		this.address = address;
		return true;
	}

	public String getTaxId() {
		if (this.deleted)
			return null;
		return tax_id;
	}

	public boolean setTaxId(String tax_id) {
		if (this.deleted)
			return false;
		this.tax_id = tax_id;
		return true;
	}

	public ArrayList<AbsAccount> getAccountList() {
		if (this.deleted)
			return null;
		return this.account_list;
	}

	public boolean printAccountList() {
		if (this.deleted || this.account_list == null)
			return false;
		for (AbsAccount account : this.account_list)
			System.out.println(account.toString());
		return true;
	}

	public boolean getStatus() {
		return this.deleted;
	}

	boolean addAccount(AbsAccount account) {
		if (this.deleted)
			return false;
		this.account_list.add(account);
		return true;
	}

	public boolean openCurrentAccount(double amount) {
		if (this.deleted)
			return false;
		new AccountCurrent(this, amount);
		return true;
	}

	public boolean openSavingsAccount(double amount) {
		if (this.deleted)
			return false;
		new AccountSavings(this, amount);
		return true;
	}

	public boolean removeAccount(AbsAccount account) {
		if (this.deleted)
			return false;
		this.account_list.remove(account);
		return true;
	}

	private static int initCustomerId() {
		id_count += 7;
		return id_count - 7;
	}

	boolean customerDelete() {
		if (this.deleted)
			return false;
		if (this.getAccountList() != null) {
			for (AbsAccount account : this.getAccountList()) {
				account.accountDelete();
			}
		}
		this.deleted = true;
		return this.deleted;
	}
}
