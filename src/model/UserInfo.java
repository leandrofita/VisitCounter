package model;

import java.util.Optional;

public class UserInfo {
	
	private Optional<Long> visitCount;
	
	public UserInfo(Optional<Long> visitCount) {
		this.visitCount = visitCount;
	}
	
	public Optional<Long> getVisitCount() {
		return visitCount;
	}

}
