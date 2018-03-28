package by.bsu.auction.entity;

import java.util.ArrayList;

public class UsersInfo {

	public UsersInfo() {}

	private ArrayList<User> users;
	private int currentPage;
	private int pages;
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPage;
		result = prime * result + pages;
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}
	
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
	
	@Override
	public String toString() {
		return "UsersInfo [users=" + users + ", currentPage=" + currentPage + ", pages=" + pages + "]";
	}
}
