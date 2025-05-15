import java.util.Arrays;

public class Starter {

  public static void main(String... s) {

    String[] words = {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};

    for(int i=0; i< words.length; i++) {
      words[i] = sort(words[i]);
    }

    System.out.println(find(words, "ctay"));
    System.out.println(find(words, "bcanihjsrrrferet"));
    System.out.println(find(words, "tbaykkjlga"));
    System.out.println(find(words, "bbbblkkjbaby"));
    System.out.println(find(words, "dad"));
    System.out.println(find(words, "breadmaking"));
    System.out.println(find(words, "dadaa"));

  }

  /*
  abby

  acty
   */

  public static String sort(String word) {
    char[] c = word.toCharArray();
    Arrays.sort(c);
    return new String(c);
  }

  public static String find(String[] arr, String note) {

    note = sort(note);

    int i,j;

    for(String word : arr) {
      if(word.length() > note.length()){
        continue;
      }

      i=0; j=0;

      while(i<word.length() && j<note.length()) {
        if(word.charAt(i) == note.charAt(j)) {
          i++;
          j++;
        } else if (word.charAt(i) < note.charAt(j)) {
          break;
        } else {
          j++;
        }

      }

      if(i==word.length()) {
        return "yes";
      }

    }

    return "-";

  }
}


//A -> some files, C -> files
//
//
//B -> some files

//

/*

Imagine we have a system that stores files, and these files can be grouped into collections.
We are interested in knowing where our resources are being taken up.
For this system we would like to generate a report that lists:
The total size of all files stored; and
The top N collections (by file size) where N can be a user-defined value (max to min)


entities ->
  File -> unique Id, name, extension, size, file location

  Colletction -> colleciton id, size, list<Files> -> implement Comparable (sort basis size of any collection)



  Collection -> map (collection id, Collection Object)

  totalSize -> pre computed any operation create file, remove file happening

operations ->

//  create file (file id, collection id)

  add file()

  remove file (file id)

  list of -> <collection id, file id, file size>

  list add (1, 1, 10)
  1, 2, 3

  2, 4, 20

  getTotalSize()
    return size

  getTopNDirectories


    coll 1 -> 10, coll 2- 20, coll 3 -> 20, coll 4 -> 30 top 2

    30, 20


    return duplicates as well

    or

    if duplicates, then return most recently used


 */
