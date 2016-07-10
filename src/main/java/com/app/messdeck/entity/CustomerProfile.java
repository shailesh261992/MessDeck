package com.app.messdeck.entity;

import javax.persistence.Entity;

@Entity
public class CustomerProfile extends AbstractEntity {

	private double balance;
	private double credit;

	public CustomerProfile() {
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "CustomerProfile [balance=" + balance + ", credit=" + credit + "]";
	}

}
