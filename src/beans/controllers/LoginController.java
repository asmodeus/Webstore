package beans.controllers;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.database.DatabaseLogicBean;
import beans.entities.LoginInfo;

@Named("loginCtrl")
@RequestScoped
public class LoginController {

	/** Injected Beans **/
	@EJB private DatabaseLogicBean db_logic; //Contains all the methods for the database calls. This bean is called frequently by the controllers
	@EJB private CustomerInfoBean info_bean; //Class that contains all the current user data (orders, logininfo) as soon as the person is logged in. 
	@EJB private NavigationController navi; //Standard navigation controller for navigating around the site
	
	@Inject private LoginInfo userParams;
	
	/** Class methods **/
	public String login() {
		LoginInfo login = db_logic.getLogin(userParams);
		
		userParams.setLoginName(null);
		userParams.setLoginPassword(null);
		if (null == login) {
			return null;
		} else {
			info_bean.setUserLogin(login);
			System.out.println(info_bean.getUserLogin());
			return navi.toShop();
		}
		
	}


}
