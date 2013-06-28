package beans.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import beans.entities.LoginInfo;
import entities.Customer;
import entities.Item;
import entities.Order;

@Named("info")
@Stateless
@SessionScoped
public class CustomerInfoBean {
	
	/** Class Objects **/
	private LoginInfo userLogin; //Set by LoginController.login() method
	private List<Item> userWares; //Set by ShopController.confirmWares() method
	

	/** Getters and Setters **/
	public LoginInfo getUserLogin() {
		return userLogin;
	}
	
	public void setUserLogin(LoginInfo userLogin) {
		this.userLogin = userLogin;
	}
	
	public List<Item> getUserWares() {
		return userWares;
	}
	
	public void setUserWares(List<Item> userWares) {
		this.userWares = userWares;
	}

	public Customer getCustomer(){
		return this.getUserLogin().getCustomer();
	}

	//This method converts a set into a List
	public List<Order> getOrders(){
		Set<Order> orders = this.getUserLogin().getCustomer().getOrders();
		List<Order> list = new ArrayList<Order>();
		for (Order o : orders){
			list.add(o);
		}
		return list;
	}

	
}
