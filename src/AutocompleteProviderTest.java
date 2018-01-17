
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/*
* Unit Tests for Asymmetrik AutocompleteProvider using jUnit
*
* Still need solutions for automatically checking equivalency of Candidate lists
* and for handling non-alphabet characters.
 */

class AutocompleteProviderTest {
    AutocompleteProvider testACP;
    Trie testTrie;
    TrieNode testRoot;


    @BeforeEach
    void setUp() {

        testACP = new AutocompleteProvider();
        testTrie = testACP.myTrie;
        testRoot = testTrie.root;

    }

    @Test
    public void itShouldReturnAnEmptyListWhenFragmentIsEmpty(){
        ArrayList<Candidate> candidates = testACP.getWords("");
        ArrayList<Candidate> emptyTestList = new ArrayList<>();
        assertEquals(emptyTestList, candidates);
    }

    @Test void itShouldReturnAnEmptyListWhenFragmentCharacterPointsToNullTrieNode() {
        testTrie.insertWord("the");
        ArrayList<Candidate> candidates = testACP.getWords("thy");
        ArrayList<Candidate> emptyTestList = new ArrayList<>();
        assertEquals(emptyTestList, candidates);
    }

    @Test
    public void itShouldCorrectlyInsertSingleLetterWordIntoEmptyTrie() {
        testTrie.insertWord("t");
        TrieNode tNode = testRoot.getChildAt('t');

        assertEquals('t', tNode.getNodeChar());
        assertTrue(tNode.isEndOfWord);
        assertTrue(testRoot.hasChildren);
        assertFalse(tNode.hasChildren);
    }

    @Test
    public void itShouldCorrectlyInsertSingleWordIntoEmptyTrie() {
        testTrie.insertWord("the");

        TrieNode tNode = testRoot.getChildAt('t');
        TrieNode hNode = tNode.getChildAt('h');
        TrieNode eNode = hNode.getChildAt('e');

        assertTrue(testRoot.hasChildren);
        assertFalse(testRoot.isEndOfWord);

        assertEquals('t', tNode.getNodeChar());
        assertFalse(tNode.isEndOfWord);
        assertTrue(tNode.hasChildren);


        assertEquals('h', hNode.getNodeChar());
        assertFalse(hNode.isEndOfWord);
        assertTrue(hNode.hasChildren);

        assertEquals('e', eNode.getNodeChar());
        assertTrue(eNode.isEndOfWord);
        assertFalse(eNode.hasChildren);
    }

    @Test
    public void itShouldInsertWordsAsLowerCase() {
        testTrie.insertWord("IS");
        TrieNode iNode = testRoot.getChildAt('i');
        TrieNode sNode = iNode.getChildAt('s');

        assertNotNull(iNode);
        assertEquals('i', iNode.getNodeChar());

        assertNotNull(sNode);
        assertEquals('s', sNode.getNodeChar());
    }

    @Test
    public void itShouldBranchInsertedWordAtCommonPrefix() {
        testTrie.insertWord("The");
        testTrie.insertWord("tell");
        TrieNode tNodeFromThe = testRoot.getChildAt('t');
        TrieNode tNodeFromTell = testRoot.getChildAt('t');

        assertEquals(tNodeFromThe,tNodeFromTell);
        assertEquals('h', tNodeFromTell.getChildAt('h').getNodeChar());
        assertEquals('e', tNodeFromThe.getChildAt('e').getNodeChar());

    }

    @Test
    public void itShouldStoreEndOfWordForNonTerminalNodes() {
        testTrie.insertWord("I");
        testTrie.insertWord("is");

        TrieNode iNode = testRoot.getChildAt('i');
        TrieNode sNode = iNode.getChildAt('s');

        assertTrue(iNode.isEndOfWord);
        assertTrue(sNode.isEndOfWord);
    }

    @Test
    public void itShouldStringifyTheStringBuilderAndCreateNewCandidateWhenIsEndOFWord() {
        testTrie.insertWord("need");

        ArrayList<Candidate> candidates = testACP.getWords("nee");

        assertEquals("need", candidates.get(0).getWord());

    }

    @Test
    public void itShouldDigDownToTheEndOfTheFragment() {
        testTrie.insertWord("need");
        TrieNode nNode = testRoot.getChildAt('n');
        TrieNode eNodeFirst = nNode.getChildAt('e');
        TrieNode eNodeSecond = eNodeFirst.getChildAt('e');
        TrieNode testDigNode = testTrie.getNodeForEndOfFragment("nee");
        assertEquals(eNodeSecond, testDigNode);

    }

    @Test
    public void itShouldIncrementUsageCountAtEndOfWordEachTimeWordIsInserted() {
        testTrie.insertWord("t");
        testTrie.insertWord("t");
        testTrie.insertWord("t");
        TrieNode tNode = testRoot.getChildAt('t');
        assertEquals(3, tNode.getUsageCount());
    }

    @Test
    public void itShouldHandleNonAlphabeticalCharacters() {
       // Is there a way to use regex to preserve contractions, hyphens, abbreviations?
        fail("@TODO");

    }

    @Test
    public void itShouldReturnCandidatesThatAreNonLeafNodes() {
        testACP.train("Al, alexandra and alex all got along.");
        ArrayList<Candidate> testList = testACP.getWords("al");
        ArrayList<Candidate> expectedList = new ArrayList<>();
        // Passes through user interface, but still need to automate
        fail("Needs proper assert to check equivalency of lists");
    }
    @AfterEach
    void tearDown() {
    testACP = null;
    testTrie = null;
    testRoot = null;
    }

}