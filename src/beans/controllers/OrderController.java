package beans.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import beans.database.DatabaseLogicBean;
import entities.Order;


@ManagedBean(name="orderCtrl")
@RequestScoped
public class OrderController {
	
	/** Injected Beans **/
	@EJB private DatabaseLogicBean db_logic;
	@EJB private CustomerInfoBean user_info;
	@EJB private NavigationController navi;	
	
	private List<Order> userOrders;
	
	/** Class Methods **/
	public String confirmOrder(){			
		/* 
		Set<Order> set = user_info.getOrders();
		for (Order s : set) {
			System.out.println(s.getCustomer().getFirstName());
			System.out.println(s.getOrderId());
			System.out.println(s.getOrderDesc());
			Collection<Item> col = s.getItems();
			for (Item i : col){
				System.out.println(i.getItemName());
				System.out.println(i.getItemDesc());
			}
		} 
		*/
		if(db_logic.addOrder(user_info.getUserLogin(), user_info.getUserWares())){	
			return navi.toConfirm();
		} else { 
			return navi.toError();
		}		
	
	}
	
}
