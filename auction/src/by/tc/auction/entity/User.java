package by.tc.auction.entity;

import java.io.Serializable;

/**
 * A class is used for provide working with a user object.
 * @author semenovich
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 4306970080206320268L;
	
	private String picture;
	private String login;
	private String password;
	private String surname;
	private String name;
	private String email;
	private String phone;
	private String passportId;
	private String passportIssuedBy;
	private String country;
	private UserRole role;
	private boolean blocked;
	
	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}
	
	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * @return the passportId
	 */
	public String getPassportId() {
		return passportId;
	}
	
	/**
	 * @param passportId the passportId to set
	 */
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	
	/**
	 * @return the passportIssuedBy
	 */
	public String getPassportIssuedBy() {
		return passportIssuedBy;
	}
	
	/**
	 * @param passportIssuedBy the passportIssuedBy to set
	 */
	public void setPassportIssuedBy(String passportIssuedBy) {
		this.passportIssuedBy = passportIssuedBy;
	}
	
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}
	
	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	/**
	 * @return the blocked
	 */
	public boolean isBlocked() {
		return blocked;
	}
	
	/**
	 * @param blocked the blocked to set
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (blocked ? 1231 : 1237);
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passportId == null) ? 0 : passportId.hashCode());
		result = prime * result + ((passportIssuedBy == null) ? 0 : passportIssuedBy.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (blocked != other.blocked)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passportId == null) {
			if (other.passportId != null)
				return false;
		} else if (!passportId.equals(other.passportId))
			return false;
		if (passportIssuedBy == null) {
			if (other.passportIssuedBy != null)
				return false;
		} else if (!passportIssuedBy.equals(other.passportIssuedBy))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (role != other.role)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [picture=" + picture + ", login=" + login + ", password=" + password + ", surname=" + surname
				+ ", name=" + name + ", email=" + email + ", phone=" + phone + ", passportId=" + passportId
				+ ", passportIssuedBy=" + passportIssuedBy + ", country=" + country + ", role=" + role + ", blocked="
				+ blocked + "]";
	}
}
