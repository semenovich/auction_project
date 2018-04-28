package by.tc.auction.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A class used for provide way to work with an auction object.
 * @author semenovich
 *
 */
public class Auction implements Serializable {

	private static final long serialVersionUID = -6577364844657624226L;
	
	private Integer id;
	private Lot lot;
	private AuctionType type;
	private AuctionStatus status;
	private Bet minBet = new Bet();
	private Bet lastBet = new Bet();
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp lastBetTime;
	private String lastBetUser;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the lot
	 */
	public Lot getLot() {
		return lot;
	}
	/**
	 * @param lot the lot to set
	 */
	public void setLot(Lot lot) {
		this.lot = lot;
	}
	
	/**
	 * @return the type
	 */
	public AuctionType getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(AuctionType type) {
		this.type = type;
	}
	
	/**
	 * @return the status
	 */
	public AuctionStatus getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(AuctionStatus status) {
		this.status = status;
	}
	
	/**
	 * @return the minBet
	 */
	public Bet getMinBet() {
		return minBet;
	}
	
	/**
	 * @param minBet the minBet to set
	 */
	public void setMinBet(Bet minBet) {
		this.minBet = minBet;
	}
	
	/**
	 * @return the lastBet
	 */
	public Bet getLastBet() {
		return lastBet;
	}
	
	/**
	 * @param lastBet the lastBet to set
	 */
	public void setLastBet(Bet lastBet) {
		this.lastBet = lastBet;
	}
	
	/**
	 * @return the startTime
	 */
	public Timestamp getStartTime() {
		return startTime;
	}
	
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * @return the endTime
	 */
	public Timestamp getEndTime() {
		return endTime;
	}
	
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @return the lastBetTime
	 */
	public Timestamp getLastBetTime() {
		return lastBetTime;
	}
	
	/**
	 * @param lastBetTime the lastBetTime to set
	 */
	public void setLastBetTime(Timestamp lastBetTime) {
		this.lastBetTime = lastBetTime;
	}
	
	/**
	 * @return the lastBetUser
	 */
	public String getLastBetUser() {
		return lastBetUser;
	}
	
	/**
	 * @param lastBetUser the lastBetUser to set
	 */
	public void setLastBetUser(String lastBetUser) {
		this.lastBetUser = lastBetUser;
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
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastBet == null) ? 0 : lastBet.hashCode());
		result = prime * result + ((lastBetTime == null) ? 0 : lastBetTime.hashCode());
		result = prime * result + ((lastBetUser == null) ? 0 : lastBetUser.hashCode());
		result = prime * result + ((lot == null) ? 0 : lot.hashCode());
		result = prime * result + ((minBet == null) ? 0 : minBet.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Auction other = (Auction) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastBet == null) {
			if (other.lastBet != null)
				return false;
		} else if (!lastBet.equals(other.lastBet))
			return false;
		if (lastBetTime == null) {
			if (other.lastBetTime != null)
				return false;
		} else if (!lastBetTime.equals(other.lastBetTime))
			return false;
		if (lastBetUser == null) {
			if (other.lastBetUser != null)
				return false;
		} else if (!lastBetUser.equals(other.lastBetUser))
			return false;
		if (lot == null) {
			if (other.lot != null)
				return false;
		} else if (!lot.equals(other.lot))
			return false;
		if (minBet == null) {
			if (other.minBet != null)
				return false;
		} else if (!minBet.equals(other.minBet))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Auction [id=" + id + ", lot=" + lot + ", type=" + type + ", status=" + status + ", minBet=" + minBet
				+ ", lastBet=" + lastBet + ", startTime=" + startTime + ", endTime=" + endTime + ", lastBetTime="
				+ lastBetTime + ", lastBetUser=" + lastBetUser + "]";
	}
}
