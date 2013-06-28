package beans.controllers;

import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named("naviCtrl")
@Stateless
@RequestScoped
public class NavigationController {
			
	/** Class methods **/ 
	public String toLogin(){
		return "login?faces-redirect=true";
	}
	
	public String toShop(){
		return "shop?faces-redirect=true";
	}
	
	public String toOrders(){
		return "orders?faces-redirect=true";
	}
	
	public String toConfirm(){
		return "confirm?faces-redirect=true";
	}
	
	public String toError(){
		return "error.xhtml";
	}
	
}
