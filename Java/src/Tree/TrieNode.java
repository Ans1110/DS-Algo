package Tree;

public class TrieNode<V> {
    V val = null;
    TrieNode<V>[] children = new TrieNode[256];
}
