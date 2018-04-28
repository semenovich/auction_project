package by.tc.auction.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class is used for provide working with info about auctions objects. 
 * A class has pages quantity and current page value. 
 * @author semenovich
 *
 */
public class AuctionsInfo implements Serializable {

	private static final long serialVersionUID = -5584217693690722456L;
	
	private ArrayList<Auction> auctions = new ArrayList<>();
	private int currentPage;
	private int pages;
	
	/**
	 * @return the auctions
	 */
	public ArrayList<Auction> getAuctions() {
		return auctions;
	}
	
	/**
	 * @param auctions the auctions to set
	 */
	public void setAuctions(ArrayList<Auction> auctions) {
		this.auctions = auctions;
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
		result = prime * result + ((auctions == null) ? 0 : auctions.hashCode());
		result = prime * result + currentPage;
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
		AuctionsInfo other = (AuctionsInfo) obj;
		if (auctions == null) {
			if (other.auctions != null)
				return false;
		} else if (!auctions.equals(other.auctions))
			return false;
		if (currentPage != other.currentPage)
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
		return "AuctionsInfo [auctions=" + auctions + ", currentPage=" + currentPage + ", pages=" + pages + "]";
	}
}
