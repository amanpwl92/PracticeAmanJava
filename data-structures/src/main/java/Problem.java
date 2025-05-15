import java.util.ArrayList;

public class Problem {

  public static void main(String... s) {
    String[] input = {"Hello", "Sir", "Please", "Upvote", "If", "You", "Like", "My", "Post"};
    int maxLen=10;

    ArrayList<String> output = new ArrayList<>();

    StringBuilder current = new StringBuilder(input[0]);

    for(int i=1; i< input.length; i++) {
      if (current.length() + input[i].length() < maxLen) {
        current.append("-").append(input[i]);
      } else {
        output.add(current.toString());
        current = new StringBuilder(input[i]);
      }
    }

    output.add(current.toString());

    System.out.println(output);
  }
}
