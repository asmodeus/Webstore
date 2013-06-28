
package beans.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import beans.entities.LoginInfo;
import entities.Customer;
import entities.Item;
import entities.Order;


@Stateful
public class DatabaseLogicBean {
    
    @PersistenceContext(unitName="customer_db") //As defined in the /WEB-INF/classes/META-INF/persistence.xml
    private EntityManager em;
    
    //Uses JPQL to get the name of the customer who will get logged in
    public LoginInfo getLogin(LoginInfo loginfo){
    	String loginName = loginfo.getLoginName();
    	String loginPassword = loginfo.getLoginPassword();
    	Query query = em.createQuery("SELECT DISTINCT l FROM LoginInfo l WHERE l.loginName = :loginName AND l.loginPassword = :loginPassword ");
    	query.setParameter("loginName", loginName);
    	query.setParameter("loginPassword", loginPassword);
    	
    	LoginInfo ret_login;  	
    	
    	try {
    		ret_login = (LoginInfo) query.getSingleResult();
    	} catch (NoResultException ex) {
    		System.err.println("No results found!");
    		return null;
    	}
    	catch (PersistenceException ex) {
    		System.err.println("Database Error!");
    		return null;
    	}
    	return ret_login;
    }
    
    //Adds an order to the database using JPA
    public boolean addOrder(LoginInfo login, List<Item> items){
    	Collection<Item> orderitems = (Collection<Item>) items;
    	Customer customer = login.getCustomer();
    	Order order = new Order();    	
    	try {
    		order.setOrderDesc("expressbud");
    		order.setCustomer(customer);
    		order.setItems(orderitems);
    		em.persist(order);
    		return true;
    	} catch (Exception ex){
    		ex.printStackTrace();
    		return false;
    	}
    	
    }
    

}
