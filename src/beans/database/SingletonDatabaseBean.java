package beans.database;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entities.Item;

@Singleton
public class SingletonDatabaseBean {

	@PersistenceContext(unitName = "customer_db")
	private EntityManager eMan;
	
	private List<Item> itemList;
	
	
	@PostConstruct //Get items from database before this class is used.
	public void getDatabaseData() {
		itemList = eMan.createQuery("select i from Item i").getResultList();
	}

	public List<Item> getItemList() {
		return itemList;
	}
	
}
