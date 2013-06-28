package beans.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import beans.database.SingletonDatabaseBean;
import entities.Item;

@ManagedBean(name="shopCtrl")
@SessionScoped
public class ShopController {
	
	/** Injected Beans **/
	@EJB private SingletonDatabaseBean items_db;
	@EJB private CustomerInfoBean user_info;
	@EJB private NavigationController navi;
	
	/** Class Objects **/
	private List<Item> items = new CopyOnWriteArrayList<Item>(); //Set as a CopyOnWriteArrayList so it can be used without concurrency problems
	private Item selectedItem;
	
	/** Class methods **/ 
	public List<Item> getCachedItems() {
		return items_db.getItemList(); 
	}
	
	public String confirmWares(){
		user_info.setUserWares(items);
		System.out.println(user_info.getUserWares());
		return navi.toOrders();
	}
	
	
	public void AddItem(){
		items.add(new Item(selectedItem));	
		String growlstr = "";
		for (Item i : items){
			growlstr +=  i.getItemName()+", ";
		}
			
		FacesMessage msg = new FacesMessage(selectedItem.getItemName()+" added", "Cart contains: "+growlstr);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void RemoveItem(){
		for (Item i : items){
			if(i.getItemId() == selectedItem.getItemId()){
				System.out.println(items.remove(i)); //For debugging purposes
				break;
			}
		}
		
		String growlstr = "";
		for (Item i : items){
			growlstr +=  i.getItemName()+", ";
		}
		FacesMessage msg = new FacesMessage(selectedItem.getItemName()+" removed", "Cart contains: "+growlstr);
		FacesContext.getCurrentInstance().addMessage(null, msg);	
	}

	/** Getters and Setters **/
	public Item getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	
}
