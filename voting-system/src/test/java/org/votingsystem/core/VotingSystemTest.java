package org.votingsystem.core;

import org.junit.Test;
import org.votingsystem.model.Candidate;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class VotingSystemTest {

  @Test
  public void basicTest() {

    String[][] votes = {{"A", "B", "C"},
        {"A", "C", "D"},
        {"D", "A", "C"}};
    VotingSystem votingSystem = new VotingSystem(votes);

    votingSystem.sortByVotes();

    List<Candidate> sortedCandidates = votingSystem.getCandidatesVotes();

    assertEquals(sortedCandidates.get(0).getKey(), "A");
    assertEquals(sortedCandidates.get(1).getKey(), "D");
    assertEquals(sortedCandidates.get(2).getKey(), "C");
    assertEquals(sortedCandidates.get(3).getKey(), "B");


  }
}
