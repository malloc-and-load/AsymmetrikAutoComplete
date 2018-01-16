public class Candidate implements Comparable<Candidate> {
    String word;
    int confidence;

    Candidate() {

    };

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

    @Override
    public int compareTo(Candidate other) {
        return other.getConfidence() - this.getConfidence();
    }
}
