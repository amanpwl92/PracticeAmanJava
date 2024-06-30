package org.votingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Candidate {

  private int weightedVoteCount;
  private List<Integer> voteCountsByPosition;

  private String key;

  public Candidate(String key, int totalNoOfPositionsForVotes) {
    this.key = key;
    this.weightedVoteCount = 0;
    this.voteCountsByPosition = new ArrayList<>();
    for (int i=0;i<totalNoOfPositionsForVotes; i++) {
      voteCountsByPosition.add(0);
    }
  }

  public String getKey() {
    return key;
  }

  public int getWeightedVoteCount() {
    return weightedVoteCount;
  }

  public void setWeightedVoteCount(int weightedVoteCount) {
    this.weightedVoteCount = weightedVoteCount;
  }

  public List<Integer> getVoteCountsByPosition() {
    return voteCountsByPosition;
  }

  public void setVoteCountsByPosition(List<Integer> voteCountsByPosition) {
    this.voteCountsByPosition = voteCountsByPosition;
  }
}
