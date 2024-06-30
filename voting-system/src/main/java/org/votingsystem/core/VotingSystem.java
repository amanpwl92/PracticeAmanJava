package org.votingsystem.core;

import org.votingsystem.model.Candidate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class VotingSystem {

  private String[][] votes;
  private List<Candidate> candidatesVotes;

  public VotingSystem(String[][] votes) {
    this.votes = votes;
    this.candidatesVotes = new ArrayList<>();
  }

  public String[][] getVotes() {
    return votes;
  }

  public void setVotes(String[][] votes) {
    this.votes = votes;
  }

  public List<Candidate> getCandidatesVotes() {
    return candidatesVotes;
  }

  public void setCandidatesVotes(List<Candidate> candidatesVotes) {
    this.candidatesVotes = candidatesVotes;
  }

  public void sortByVotes() {
    HashMap<String, Candidate> candidateKeyToObjectMap = new HashMap<>();
    Candidate candidate;
    String key;
    List<Integer> votesCountByPosition;
    int totalNoOfPositionsForVotes = votes[0].length;
    for (String[] vote : votes) {

      for (int i=0;i< vote.length; i++) {
        key = vote[i];
        candidate = candidateKeyToObjectMap.get(key);

        if (candidate == null) {
          candidate = new Candidate(key, totalNoOfPositionsForVotes);
          candidateKeyToObjectMap.put(key, candidate);
          candidatesVotes.add(candidate);
        }

        candidate.setWeightedVoteCount(candidate.getWeightedVoteCount() + (votes.length-i));
        votesCountByPosition = candidate.getVoteCountsByPosition();
        votesCountByPosition.set(i, votesCountByPosition.get(i) + 1);

      }
    }

    candidatesVotes.sort(new Comparator<Candidate>() {
      @Override
      public int compare(Candidate o1, Candidate o2) {
        if (o1.getWeightedVoteCount() < o2.getWeightedVoteCount()) {
          return 1;
        }

        if (o1.getWeightedVoteCount() > o2.getWeightedVoteCount()) {
          return -1;
        }

        for (int i=0; i< totalNoOfPositionsForVotes; i++) {
          if (o1.getVoteCountsByPosition().get(i) < o2.getVoteCountsByPosition().get(i)) {
            return 1;
          } else if (o1.getVoteCountsByPosition().get(i) > o2.getVoteCountsByPosition().get(i)) {
            return -1;
          }
        }

        return 0;

      }
    });
  }

}
