import java.util.*;
public class AutocompleteDriver {
    public static void main(String[] args) {
        AutocompleteProvider acp = new AutocompleteProvider();
        Scanner userInput = new Scanner(System.in);

        System.out.println("Asymmetrik AutoComplete Provider");

        int menuChoice;
        do {
            printMenu();
            menuChoice = userInput.nextInt();

            switch(menuChoice) {
                case 0:
                    break;
                case 1:
                    System.out.print("Enter the passage to use for training: ");
                    Scanner trainerInput = new Scanner(System.in);
                    String passage = trainerInput.nextLine();
                    acp.train(passage);
                    break;
                case 2:
                    System.out.print("Enter the fragment to search for candidates: ");
                    Scanner fragmentInput = new Scanner(System.in);
                    String fragment = fragmentInput.next();
                    acp.getWords(fragment);
                    printCandidates(acp.candidates);
                    break;
                default:
                    System.out.println("Invalid selection");
            }
        }
        while(menuChoice != 0);
    }

    public static void printCandidates(ArrayList<Candidate> candidates) {
        System.out.print("--> ");
        for (Candidate candidate: candidates) {
            System.out.print("\"" + candidate.getWord() +  "\" ");
            System.out.print("(" + candidate.getConfidence() + "), ");
        }
        System.out.println();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("Menu:");
        System.out.println("'0' - quit");
        System.out.println("'1' - input a training passage");
        System.out.println("'2' - input a fragment and receive a list of suggestions");

    }
}


