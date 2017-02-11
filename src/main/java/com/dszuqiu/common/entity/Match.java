package com.dszuqiu.common.entity;

public class Match{
	//页面自带的一个id，每场比赛有一个唯一id
	String trId;
	String type;
	String time;
	String hostAndConcede;
	String currentScore;
	String bigSmallAndGuest;
	String dynamicInfo;
	float hostOdds;
	String currentConcede;
	float guestOdds;
	String corner;
	float bigOdds;
	String currentBigSmall;
	float smallOdds;
	
	
	public String getTrId() {
		return trId;
	}
	public void setTrId(String trId) {
		this.trId = trId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getHostAndConcede() {
		return hostAndConcede;
	}
	public void setHostAndConcede(String hostAndConcede) {
		this.hostAndConcede = hostAndConcede;
	}
	public String getCurrentScore() {
		return currentScore;
	}
	public void setCurrentScore(String currentScore) {
		this.currentScore = currentScore;
	}
	public String getBigSmallAndGuest() {
		return bigSmallAndGuest;
	}
	public void setBigSmallAndGuest(String bigSmallAndGuest) {
		this.bigSmallAndGuest = bigSmallAndGuest;
	}
	public String getDynamicInfo() {
		return dynamicInfo;
	}
	public void setDynamicInfo(String dynamicInfo) {
		this.dynamicInfo = dynamicInfo;
	}
	public float getHostOdds() {
		return hostOdds;
	}
	public void setHostOdds(float hostOdds) {
		this.hostOdds = hostOdds;
	}
	public String getCurrentConcede() {
		return currentConcede;
	}
	public void setCurrentConcede(String currentConcede) {
		this.currentConcede = currentConcede;
	}
	public float getGuestOdds() {
		return guestOdds;
	}
	public void setGuestOdds(float guestOdds) {
		this.guestOdds = guestOdds;
	}
	public String getCorner() {
		return corner;
	}
	public void setCorner(String corner) {
		this.corner = corner;
	}
	public float getBigOdds() {
		return bigOdds;
	}
	public void setBigOdds(float bigOdds) {
		this.bigOdds = bigOdds;
	}
	public String getCurrentBigSmall() {
		return currentBigSmall;
	}
	public void setCurrentBigSmall(String currentBigSmall) {
		this.currentBigSmall = currentBigSmall;
	}
	public float getSmallOdds() {
		return smallOdds;
	}
	public void setSmallOdds(float smallOdds) {
		this.smallOdds = smallOdds;
	}
	
	@Override
	public String toString() {
		return "Match [id=" + trId + ", type=" + type + ", time=" + time + ", hostAndConcede="
				+ hostAndConcede + ", currentScore=" + currentScore
				+ ", bigSmallAndGuest=" + bigSmallAndGuest + ", dynamicInfo="
				+ dynamicInfo + ", hostOdds=" + hostOdds + ", currentConcede="
				+ currentConcede + ", guestOdds=" + guestOdds + ", corner="
				+ corner + ", bigOdds=" + bigOdds + ", currentBigSmall="
				+ currentBigSmall + ", smallOdds=" + smallOdds + "]";
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trId == null) ? 0 : trId.hashCode());
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
		Match other = (Match) obj;
		if (trId == null) {
			if (other.trId != null)
				return false;
		} else if (!trId.equals(other.trId))
			return false;
		return true;
	}
}
