# AsymmetrikAutoComplete
Learns the words typed by the user over time and then determines a ranked list of autocomplete candidates given a word fragment. Each candidate is given a confidence, which is the likelihood/relevance of an individual word relative to the other words being returned by the autocomplete provider.

## Usage
```
java AsymmetrikAutoCompleteDriver
Asymmetrik AutoComplete Provider

Menu:
'0' - quit
'1' - input a training passage
'2' - input a fragment and receive a list of suggestions
1
Enter the passage to use for training: 
Al, Alex, and Alexandra all got along, all along.

Menu:
'0' - quit
'1' - input a training passage
'2' - input a fragment and receive a list of suggestions
2
Enter the fragment to search for candidates: 
al
--> "all" (2), "along" (2), "al" (1), "alex" (1), "alexandra" (1), 
```
## Interface Specification

Candidate
    String getWord() : returns the autocomplete candidate
    Integer getConfidence() : returns the confidence* for the candidate

AutocompleteProvider
    List<Candidate> getWords(String fragment) : returns list of candidates ordered by confidence*
    void train(String passage) : trains the algorithm with the provided passage

## Design Notes
The specification requires the following: "Due to the deployment environment for this algorithm, efficiency is critical. The data structure utilized by your algorithm should be optimized for space and time."

Therefore, the data structure used is a Trie in which each node has an array of 26 child nodes (one for each lowercase letter, all initialized to null),  a boolean denoting whether the node has any non-null children a boolean denoting whether the node is the end of a word, and an integer denoting how many times that word has been seen in training passages.

This data structure will allow for space economy, O(n) lookup, and use of recursive algorthims.


