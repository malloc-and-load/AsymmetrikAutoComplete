/*
 * TrieNode
 *
 * Fundamental unit of the Trie data structure for Asymmetrik Autocomplete Provider.
 * Each Node stores:
  *     - the char that it represents
  *     - an array of 26 children
  *     - a "flag" signaling if this node denotes an end of a word (not necessarily a leaf node)
  *     - a "flag" signaling if this node has non-null children
  *     - a count of how many times this word has been "learned"
  *
  * Methods are only getters and setters
 */

public class TrieNode {
    // Array of 26 null nodes, one for each lowercase letter
    TrieNode[] children = new TrieNode[26];

    // The lowercase character represented by this node
    char nodeChar;

    // Denotes a node that completes a "learned" word, not necessarily a leaf node
    boolean isEndOfWord;

    // Will be set to true when any members of children array are non-null
    boolean hasChildren = false;

    // Stores the number of times the word ending at this node has been "learned"
    int usageCount;

    // Converts the raw ASCII value of the char represented by this node into the corresponding array index
    public int getCharIndex(char c) {

            return c -'a';
    }

    // Returns the TrieNode child at the specified char
    public TrieNode getChildAt(char c) {

        return this.children[getCharIndex(c)];
    }

    // Called when a node adds its first non-null child
    public void setHasChildren() {
        this.hasChildren = true;
    }

    public char getNodeChar() {
        return this.nodeChar;
    }

    public void setNodeChar(char c) {
        this.nodeChar = c;
    }

    public int getUsageCount() {
        return this.usageCount;
    }

    public void incrementUsageCount() {
        this.usageCount++;
    }
}
