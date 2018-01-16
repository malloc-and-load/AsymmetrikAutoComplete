import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieNodeTest {
    @Test
    public void testGetCharIndex() {
        TrieNode testNode = new TrieNode();
        assertEquals(testNode.getCharIndex('a'), 0);
    }

    @Test
    public void testGetChildAt() {
        TrieNode testNode = new TrieNode();
        TrieNode testChild = new TrieNode();
        testNode.children[0] = testChild;
        assertNotNull(testNode.getChildAt('a'));
    }
}