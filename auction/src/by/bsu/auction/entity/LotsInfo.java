package by.bsu.auction.entity;

import java.util.ArrayList;

public class LotsInfo {

	public LotsInfo() {}
	
	private ArrayList<Lot> lots;
	private int currentPage;
	private int pages;
	
	public ArrayList<Lot> getLots() {
		return lots;
	}
	
	public void setLots(ArrayList<Lot> lots) {
		this.lots = lots;
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
		result = prime * result + ((lots == null) ? 0 : lots.hashCode());
		result = prime * result + pages;
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
		LotsInfo other = (LotsInfo) obj;
		if (currentPage != other.currentPage)
			return false;
		if (lots == null) {
			if (other.lots != null)
				return false;
		} else if (!lots.equals(other.lots))
			return false;
		if (pages != other.pages)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "LotsInfo [lots=" + lots + ", currentPage=" + currentPage + ", pages=" + pages + "]";
	}
}
