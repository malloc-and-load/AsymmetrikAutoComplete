/*
* Candidate
*
* Fundamental unit for suggested results.
* Stores the suggested word and the confidence (number of times the word has been "learned")
* Implements the Comparable interface and overrides compareTo() for sorting Candidates in descending order by confidence
 */

public class Candidate implements Comparable<Candidate> {
    String word;
    int confidence;

    Candidate() {}

    Candidate(String word, int confidence) {
        this.word = word;
        this.confidence = confidence;
    }
    public String getWord() {
        return this.word;
    }

    public int getConfidence() {
        return this.confidence;
    }

    // Compares this candidate with the specified candidate, based on confidence
    // Returns a negative integer, zero, or a positive integer as this candidate's confidence is greater than, equal to,
    // or less than the specified candidate's confidence.
    // Used for sort() in AutoCompleteProvider
    @Override
    public int compareTo(Candidate other) {
        return other.getConfidence() - this.getConfidence();
    }
}
