package beans.entities;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import entities.Customer;

/*
 * 
 * This bean is used to get login parameters from the login view.
 * 
 */
@Named("loginBean")
@RequestScoped
@Entity
@Table(name = "LOGIN_INFO")
public class LoginInfo implements Serializable {
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOGIN_INFO_ID")
	private Long loginInfoId;

	@Column(name = "LOGIN_NAME")
	private String loginName;

	@Column(name = "PASSWORD")
	private String loginPassword;

	@OneToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	public Long getLoginInfoId() {
		return loginInfoId;
	}

	public void setLoginInfoId(Long loginInfoId) {
		this.loginInfoId = loginInfoId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
