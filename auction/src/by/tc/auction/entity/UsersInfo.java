package by.tc.auction.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class is used for provide working with info about users objects. 
 * A class has pages quantity and current page value. 
 * @author semenovich
 *
 */
public class UsersInfo implements Serializable {

	private static final long serialVersionUID = 5511586564443974191L;

	public UsersInfo() {}

	private ArrayList<User> users;
	private int currentPage;
	private int pages;

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}
	
	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
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
		result = prime * result + currentPage;
		result = prime * result + pages;
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		UsersInfo other = (UsersInfo) obj;
		if (currentPage != other.currentPage)
			return false;
		if (pages != other.pages)
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsersInfo [users=" + users + ", currentPage=" + currentPage + ", pages=" + pages + "]";
	}
}
