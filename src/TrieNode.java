public class TrieNode {
    // Array of 26 nodes, one for each lowercase letter
    TrieNode[] children = new TrieNode[26];
    char nodeChar;
    boolean isEndOfWord;
    boolean hasChildren = false;
    int usageCount;


    public int getCharIndex(char c) {

        return c -'a';
    }

    // Returns the TrieNode child at the specified char
    public TrieNode getChildAt(char c) {

        return this.children[getCharIndex(c)];
    }

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
