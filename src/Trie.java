
public class Trie {

    TrieNode root = new TrieNode();
    public void insertWord(String word) {
        word = word.toLowerCase();
        insertWord(word, root);
    }

    // Recursively inserts a string into the trie, creating new nodes as necessary
    public void insertWord(String word, TrieNode currentNode) {

        // Base Case: word is length 0
        if(word.length() == 0) {
            currentNode.isEndOfWord = true;
            currentNode.incrementUsageCount();
            return;
        }
        // Recursive Case
        else {
            // Get the first character in the input word
            char currentChar = word.charAt(0);
            TrieNode child = currentNode.getChildAt(currentChar);

            // If currentNode has no previously existing child for this character, create a new child node
            if ( child == null) {
                child = new TrieNode();
                currentNode.children[currentChar -'a'] = child;
                currentNode.setHasChildren();
            }
            child.setNodeChar(currentChar);
            // Recursive call passing word minus its first character, and the child of the node passed in the previous call
            insertWord(word.substring(1), child);
        }

    }

    public TrieNode getNodeForEndOfFragment(String fragment) {
        fragment = fragment.toLowerCase();
        return getNodeForEndOfFragment(fragment, root);
    }

    public TrieNode getNodeForEndOfFragment(String fragment, TrieNode currentNode) {
        if (fragment.length() == 0) {
            return currentNode;
        }
        char currentChar = fragment.charAt(0);
        TrieNode child = currentNode.getChildAt(currentChar);

        // If no child found at current char
        if (child == null) {
            return child;
        }

        else {
            return getNodeForEndOfFragment(fragment.substring(1), child);
        }

    }
}
