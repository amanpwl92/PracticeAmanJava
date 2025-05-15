import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


// Aman Notes : //
// A node is storing count, and multiple keys corresponding to that count.
// Right side of node will either have a node with higher count or tail.
// Left side of node will either have a node with lesser count or head.
// Map is storing node corresponding to a key. In case there are 2 unique keys with same count lets say 2, then we
// have single node with count 2 and those 2 keys in the set. Map will be pointing to same node for both keys.

class AllOne {
  class Node {
    int count;

//    String key;
    Set<String> keys = new HashSet<>(); //  this will have keys corresponding to a count at a moment
    // Aman Notes: //
    // left will have lesser count and right will have higher count. Thats why during inc we are doing insertRight
    // on current node, where as in dec we are doing insert right on current node's left i.e new node will come left
    // of current node
    Node l, r;
    Node(String key, int count) {
//      this.key = key;
      this.keys.add(key);
      this.count = count;
    }
    void insertRight(Node node) {
      node.l = this;
      node.r = this.r;
      this.r.l = node;
      this.r = node;
    }
    void remove() {
      this.l.r = this.r;
      this.r.l = this.l;
    }
  }

  Map<String, Node> map = new HashMap<>();

  // head and tail are static nodes and all other nodes will be present b.w them
  // If no node is b.w them , means we dont have any node and min and max will be ""
  Node head = new Node("", -1), tail = new Node("", -1);
  /** Initialize your data structure here. */
  public AllOne() {
    head.r = tail;
    tail.l = head;
  }

  /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
  public void inc(String key) {
    Node node = head;       // if new key point to the head
    if (map.containsKey(key))
      node = map.get(key); // not new key, get from map
    node.keys.remove(key); // remove current key from key
    int count = node==head? 1: node.count+1; // count + 1
    if (node.r.count != count) // inert a right node if its right node 's count is not equals to count+1
      node.insertRight(new Node(key, count));
    node.r.keys.add(key);     // put this key into its right node 's keys set
    map.put(key, node.r);     // map the key to the node with count+1
    if (node != head && node.keys.isEmpty()) // empty node which should be removed
      node.remove();
  }

  /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
  public void dec(String key) {
    if (!map.containsKey(key)) return;
    Node node = map.get(key);   // get current node from map
    node.keys.remove(key);      // remove key from current node's keys set
    if (node.count > 1) {       // find the count - 1 node
      if (node.l.count != node.count-1) // inert a left node if its left node's count is not equals to count-1
        node.l.insertRight(new Node(key, node.count-1));
      node.l.keys.add(key);   // put this key into its left node 's keys set
      map.put(key, node.l);   // map the key to the node with count-1
    } else // remove the key if its count is 1
      map.remove(key);
    if (node.keys.isEmpty())  // empty node which should be removed
      node.remove();
  }

  /** Returns one of the keys with maximal value. */
  public String getMaxKey() {
    if (tail.l == head) return "";
    return tail.l.keys.iterator().next();
  }

  /** Returns one of the keys with Minimal value. */
  public String getMinKey() {
    if (head.r == tail) return "";
    return head.r.keys.iterator().next();
  }


  public static void main(String... s) {
    AllOne obj = new AllOne();
    obj.inc("a");
    obj.inc("b");
    obj.inc("b");
    obj.inc("c");
    obj.inc("c");
    obj.inc("c");
    obj.dec("b");
    obj.dec("b");
    System.out.println(obj.getMinKey());
    obj.dec("a");
    System.out.println(obj.getMaxKey());
    System.out.println(obj.getMinKey());

//    obj.inc("hello");
//    obj.inc("hello");
//    System.out.println(obj.getMaxKey());
//    System.out.println(obj.getMinKey());
//    obj.inc("leet");
//    System.out.println(obj.getMaxKey());
//    System.out.println(obj.getMinKey());


  }
}

