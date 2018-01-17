/*
 * Trie
 *
 * The main data structure for the Asymmetrik Autocomplete Provider.
 * Recursive methods to insert a word, one node for each character,
 * and to trace through the tree to find the node at the of a specified fragment.
 */

public class Trie {

    TrieNode root = new TrieNode();

    // Auxillary function that feeds into recursive version of itself
    public void insertWord(String word) {
        word = word.toLowerCase();
        insertWord(word, root);
    }

    // Recursively inserts a string into the trie, creating new nodes as necessary
    public void insertWord(String word, TrieNode currentNode) {

        // Base Case: no characters left in word
        if (word.length() == 0) {
            // Mark the end node as an end of word and increment the usage count
            currentNode.isEndOfWord = true;
            currentNode.incrementUsageCount();
            return;
        }
        // Recursive Case
        else {
            // Get the first character in the input word
            char currentChar = word.charAt(0);
            // If currentChar is not a letter, getChildAt() will throw an IndexOutOfBoundsError
            try {
                TrieNode child = currentNode.getChildAt(currentChar);

                // If currentNode has no previously existing child for this character, create a new child node
                if (child == null) {
                    child = new TrieNode();
                    currentNode.children[currentChar - 'a'] = child;
                    currentNode.setHasChildren();
                }
                child.setNodeChar(currentChar);
                // Recursive call passing word minus its first character, and the child of the node passed in the previous call
                insertWord(word.substring(1), child);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Training passage can not contain words that have non-alphabetical characters");
            }
        }
    }

    // Auxillary function that feeds into recursive version of itself
    public TrieNode getNodeForEndOfFragment(String fragment) {
        // Convert fragment to lowercase, so each node requires only 26 array slots for children
        fragment = fragment.toLowerCase();
        return getNodeForEndOfFragment(fragment, root);
    }

    // Recursively traces trie for fragment
    // Returns TrieNode for last character in fragment
    // Returns a null node if fragment is not a valid path through the trie
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
            // Recursively call with fragment minus the first character and child node
            return getNodeForEndOfFragment(fragment.substring(1), child);
        }
    }
}
