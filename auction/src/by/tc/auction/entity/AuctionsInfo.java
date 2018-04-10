package by.tc.auction.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class AuctionsInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5584217693690722456L;
	
	public AuctionsInfo() {}

	private ArrayList<Auction> auctions;
	private int currentPage;
	private int pages;
	public ArrayList<Auction> getAuctions() {
		return auctions;
	}
	public void setAuctions(ArrayList<Auction> auctions) {
		this.auctions = auctions;
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
		result = prime * result + ((auctions == null) ? 0 : auctions.hashCode());
		result = prime * result + currentPage;
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
	
	@Override
	public String toString() {
		return "AuctionsInfo [auctions=" + auctions + ", currentPage=" + currentPage + ", pages=" + pages + "]";
	}
}
