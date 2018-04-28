package by.tc.auction.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class is used for working with info about lots objects.
 * A class has pages quantity and current page value. 
 * @author semenovich
 *
 */
public class LotsInfo implements Serializable {
	
	private static final long serialVersionUID = 8250921987273983941L;

	public LotsInfo() {}
	
	private ArrayList<Lot> lots;
	private int currentPage;
	private int pages;

	/**
	 * @return the lots
	 */
	public ArrayList<Lot> getLots() {
		return lots;
	}
	
	/**
	 * @param lots the lots to set
	 */
	public void setLots(ArrayList<Lot> lots) {
		this.lots = lots;
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
		result = prime * result + ((lots == null) ? 0 : lots.hashCode());
		result = prime * result + pages;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LotsInfo [lots=" + lots + ", currentPage=" + currentPage + ", pages=" + pages + "]";
	}
}
