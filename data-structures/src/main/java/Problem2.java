import java.util.ArrayList;
import java.util.Arrays;

public class Problem2 {
  public static void main(String... s) {
    String[] input = {"The day began as still as the", "night abruptly lighted with", "brilliant flame"};
    ArrayList<String> list = new ArrayList<>();
    int exactLen = 24, len, rem, slots = 0, replaceAll = 0;

    ArrayList<String> output = new ArrayList<>();
    String temp;

    for (int i = 0; i< input.length; i++) {
      list.addAll(Arrays.stream(input[i].split(" ")).toList());
    }

    StringBuilder current = new StringBuilder(list.get(0));

    for(int i=1; i< list.size(); i++) {
      if (current.length() + list.get(i).length() < exactLen) {
        current.append("-").append(list.get(i));
        slots++;
      } else {

        if(slots > 0) {
          len = current.length();
          rem = exactLen - len;

          replaceAll = rem / slots;
          rem = rem % slots;

          if (replaceAll > 0) {
            temp = "-";
            while (replaceAll > 0) {
              temp = temp + "-";
              replaceAll--;
            }

            current = new StringBuilder(current.toString().replaceAll("-", temp));
          }

//          if (rem > 0) {
//
//          }

        }

        output.add(current.toString());
        current = new StringBuilder(list.get(i));
        slots=0;
      }
    }

    output.add(current.toString());

    System.out.println(output);


  }
}
