import java.util.*;
public class AutocompleteDriver {
    public static void main(String[] args) {
        AutocompleteProvider acp = new AutocompleteProvider();
        acp.train("The third thing that I need to tell you is that this thing doesn't think thoroughly.");
        List<Candidate> candidates = acp.getWords("t");
        for (Candidate candidate: candidates) {
            System.out.print(candidate.getWord());
            System.out.print(" ");
            System.out.print("(");
            System.out.print(candidate.getConfidence());
            System.out.println(")");
        }

    }
}
