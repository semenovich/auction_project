package by.tc.auction.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Auction implements Serializable {
	/**
	 * 
	 */
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
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Lot getLot() {
		return lot;
	}
	
	public void setLot(Lot lot) {
		this.lot = lot;
	}
	
	public AuctionType getType() {
		return type;
	}
	
	public void setType(AuctionType type) {
		this.type = type;
	}
	
	public AuctionStatus getStatus() {
		return status;
	}
	
	public void setStatus(AuctionStatus status) {
		this.status = status;
	}
	
	public Bet getMinBet() {
		return minBet;
	}
	
	public void setMinBet(Bet minBet) {
		this.minBet = minBet;
	}
	
	public Bet getLastBet() {
		return lastBet;
	}
	
	public void setLastBet(Bet currentBet) {
		this.lastBet = currentBet;
	}
	
	public Timestamp getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	public Timestamp getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	public Timestamp getLastBetTime() {
		return lastBetTime;
	}
	
	public void setLastBetTime(Timestamp lastBetTime) {
		this.lastBetTime = lastBetTime;
	}
	
	public String getLastBetUser() {
		return lastBetUser;
	}
	
	public void setLastBetUser(String lastBetUser) {
		this.lastBetUser = lastBetUser;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastBet == null) ? 0 : lastBet.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastBetTime == null) ? 0 : lastBetTime.hashCode());
		result = prime * result + ((lastBetUser == null) ? 0 : lastBetUser.hashCode());
		result = prime * result + ((lot == null) ? 0 : lot.hashCode());
		result = prime * result + ((minBet == null) ? 0 : minBet.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Auction other = (Auction) obj;
		if (lastBet == null) {
			if (other.lastBet!= null)
				return false;
		} else if (!lastBet.equals(other.lastBet))
			return false;
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
	
	@Override
	public String toString() {
		return "Auction [id=" + id + ", lot=" + lot + ", type=" + type + ", status=" + status + ", minBet=" + minBet
				+ ", currentBet=" + lastBet + ", startTime=" + startTime + ", endTime=" + endTime + ", lastBetTime="
				+ lastBetTime + ", lastBetUser=" + lastBetUser + "]";
	}
	
}
