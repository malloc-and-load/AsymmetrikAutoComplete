import java.util.*;
/*
* AutocompleteProvider
*
* Stores a Trie populated by the "learned" words and an ArrayList of Candidates
*
* Methods for training the AutocompleteProvider with new passages
* and for recursively searching the trie for candidates
*
 */

public class AutocompleteProvider {

    Trie myTrie = new Trie();
    ArrayList<Candidate> candidates = new ArrayList<>();

    // Trains the algorithm with the provided passage
    public void train(String passage) {
        passage = passage.toLowerCase();
        // Split the passage into individual words using spaces and punctuation as delimiters
        String[] words = passage.split("(['-]\\W+|[^\\w'-]\\W*)");

        // Iteratively insert each word into the trie
        for (String word : words) {
            myTrie.insertWord(word);
        }
    }

    /* Returns list of candidates ordered by confidence
     *
     * Confidence is defined as the likelihood/relevance of an individual word relative to the other words being returned by the
     * autocomplete provider.  If two words are equally likely, they have the same confidence.  If one is more likely,
     * it has a higher confidence.
    */
    public ArrayList<Candidate> getWords(String fragment) {
        candidates.clear();
        fragment = fragment.toLowerCase();
        // If fragment is empty string, return empty list of candidates
        if (fragment == "") {
            return candidates;
        }
        // Traverse trie down to end of fragment
        TrieNode lastNodeOfPrefix = myTrie.getNodeForEndOfFragment(fragment);

        // If entire fragment is valid path through trie

        if (lastNodeOfPrefix != null) {
                // Create string without last character of fragment, to circumvent duplicate printing
                // of that character with depthFirstSearch
                String prefix = fragment.substring(0, fragment.length()-1);
                depthFirstSearch(lastNodeOfPrefix, prefix);
            }
        // Sort the ArrayList of candidates by confidence in descending order
        Collections.sort(candidates);
        return candidates;
    }

    // Auxillary function to feed into recursive version of itself
    public void depthFirstSearch(TrieNode currentRoot, String prefix) {
        StringBuilder builder = new StringBuilder(prefix);
        depthFirstSearch(currentRoot, builder);
    }

    // Recursively searches the trie for ends of words and creates new Candidates if found
    public void depthFirstSearch(TrieNode currentRoot, StringBuilder builder) {

        // Each call has to pass a new StringBuilder object to allow proper backtracking
        // TODO: is there a better way to solve the bactracking problem?
        StringBuilder builder2 = new StringBuilder(builder);
        builder2.append(currentRoot.getNodeChar());

        // If it is the end of a word
        if (currentRoot.isEndOfWord) {
            // export the builder as a candidate
            Candidate candidate = new Candidate();
            candidate.word = builder2.toString();
            candidate.confidence = currentRoot.getUsageCount();
            candidates.add(candidate);
        }

        // Base Case: currentRoot has no children
        if (!currentRoot.hasChildren) {
            return;
        }
        // Recursive Case
        for (TrieNode child: currentRoot.children) {
            if (child != null) {
                depthFirstSearch(child, builder2);
            }
        }
    }
}
