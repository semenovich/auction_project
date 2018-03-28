package by.bsu.auction.entity;

import java.util.ArrayList;
import java.sql.Date;

public class Auction {
	private Integer id;
	private Lot lot;
	private AuctionType type;
	private AuctionStatus status;
	private Bet minBet = new Bet();
	private Bet currentBet = new Bet();
	private Date startTime;
	private Date endTime;
	private Date lastBetTime;
	private String lastBetUser;
	private ArrayList<User> participants = new ArrayList<>();
	
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
	
	public Bet getCurrentBet() {
		return currentBet;
	}
	
	public void setCurrentBet(Bet currentBet) {
		this.currentBet = currentBet;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Date getLastBetTime() {
		return lastBetTime;
	}
	
	public void setLastBetTime(Date lastBetTime) {
		this.lastBetTime = lastBetTime;
	}
	
	public String getLastBetUser() {
		return lastBetUser;
	}
	
	public void setLastBetUser(String lastBetUser) {
		this.lastBetUser = lastBetUser;
	}
	
	public ArrayList<User> getParticipants() {
		return participants;
	}
	
	public void setParticipants(ArrayList<User> participants) {
		this.participants = participants;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentBet == null) ? 0 : currentBet.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastBetTime == null) ? 0 : lastBetTime.hashCode());
		result = prime * result + ((lastBetUser == null) ? 0 : lastBetUser.hashCode());
		result = prime * result + ((lot == null) ? 0 : lot.hashCode());
		result = prime * result + ((minBet == null) ? 0 : minBet.hashCode());
		result = prime * result + ((participants == null) ? 0 : participants.hashCode());
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
		if (currentBet == null) {
			if (other.currentBet != null)
				return false;
		} else if (!currentBet.equals(other.currentBet))
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
		if (participants == null) {
			if (other.participants != null)
				return false;
		} else if (!participants.equals(other.participants))
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
				+ ", currentBet=" + currentBet + ", startTime=" + startTime + ", endTime=" + endTime + ", lastBetTime="
				+ lastBetTime + ", lastBetUser=" + lastBetUser + ", participants=" + participants + "]";
	}
	
}
