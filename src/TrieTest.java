
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Before
    public void setUp() {

    }


    @Test
    public void testInsertWordSingleChar() {
        Trie testTrie = new Trie();
        testTrie.insertWord("i");
        TrieNode child = testTrie.root.getChildAt('i');
        assertNotNull(child);
    }

    @Test
    public void testInsertOverlappingWords() {

    }
}