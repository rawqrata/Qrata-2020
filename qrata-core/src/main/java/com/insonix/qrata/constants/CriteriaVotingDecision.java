package com.insonix.qrata.constants;

public enum CriteriaVotingDecision {
	AGREE((short) 1), DISAGREE((short) 0);
	
	private short voting;

	CriteriaVotingDecision(short voting){
		this.voting = voting;
	}
	
	public short getVoting() {
		return voting;
	}
}
