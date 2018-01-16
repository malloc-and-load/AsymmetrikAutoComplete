import java.util.*;

public class AutocompleteProvider {

    Trie myTrie = new Trie();
    List<Candidate> candidates = new ArrayList<>();

    // Trains the algorithm with the provided passage
    public void train(String passage) {
        passage = passage.toLowerCase();
        String[] words = passage.split("\\W+");

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
    public List<Candidate> getWords(String fragment) {

        fragment = fragment.toLowerCase();

        // Traverse trie down to end of fragment
        TrieNode lastNodeOfPrefix = myTrie.getNodeForEndOfFragment(fragment);

        // If entire fragment is valid path through trie

        if (lastNodeOfPrefix != null) {
                String prefix = fragment.substring(0, fragment.length()-1);
                depthFirstSearch(lastNodeOfPrefix, prefix);
            }
        // Sort the ArrayList of candidates by confidence in descending order
        Collections.sort(candidates);
        return candidates;
    }

    public void depthFirstSearch(TrieNode currentRoot, String prefix) {
        StringBuilder builder = new StringBuilder(prefix);
        depthFirstSearch(currentRoot, builder);
    }
    public void depthFirstSearch(TrieNode currentRoot, StringBuilder builder) {

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
