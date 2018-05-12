import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
/**
 * Created by Alena_Adm on 05.10.2017.
 */
public class AhoCorasickTest {

    @Test
    public void testDeleteAllSpaces_NewLine() {
        String str = "a\n";
        String answer = "a";
        String result = AhoCorasick.deleteAllSpaces(str);
        assertEquals(answer, result);
    }
    @Test
    public void testDeleteAllSpaces_NoNewLine() {
        String str = "abc";
        String answer = "abc";
        String result = AhoCorasick.deleteAllSpaces(str);
        assertEquals(answer, result);
    }
	
    @Test
    public void testCreateNew_HasChildren()
    {
        boolean answer = false;
        AhoCorasick a = new AhoCorasick(10);
        boolean result = a.createNew(1, 0, 'a', 2);
        assertEquals(answer, result);
    }

    @Test
    public void testCreateNew_HasNoChildren()
    {
        boolean answer = true;
        AhoCorasick a = new AhoCorasick(10);
        boolean result = a.createNew(-1, 0, 'a', 2);
        assertEquals(answer, result);
    }

    @Test
    public void testTransition_NoTransitionHasChildren()
    {
        String str = "a";
        List<String> patterns = new ArrayList<String>();
        patterns.add("a");
        List<Integer> res = AhoCorasick.find(str, patterns);
        int answer = 0;
        int result = Integer.valueOf(res.get(0));
        assertEquals(answer, result);
    }

    @Test
    public void testTransition_AlreadyHasThisLetter()
    {
        String str = "ababa";
        List<String> patterns = new ArrayList<String>();
        patterns.add("ab");
        patterns.add("b");
        List<Integer> res = AhoCorasick.find(str, patterns);
        int answer = 1;
        int result = Integer.valueOf(res.get(0));
        assertEquals(answer, result);
    }

    @Test
    public void testTransition_DoesntHaveChildren_TheFirstOne()
    {
        String str = "abc";
        List<String> patterns = new ArrayList<String>();
        patterns.add("a");
        List<Integer> res = AhoCorasick.find(str, patterns);
        int answer = 0;
        int result = Integer.valueOf(res.get(0));
        assertEquals(answer, result);
    }

    @Test
    public void testTransition_DoesntHaveChildren_NotTheFirstOne()
    {
        String str = "abc";
        List<String> patterns = new ArrayList<String>();
        patterns.add("b");
        List<Integer> res = AhoCorasick.find(str, patterns);
        int answer = 1;
        int result = Integer.valueOf(res.get(0));
        assertEquals(answer, result);
    }

    @Test
    public void testSuffLink_HasAParent()
    {
        String str = "abc";
        List<String> patterns = new ArrayList<String>();
        patterns.add("ab");
        List<Integer> res = AhoCorasick.find(str, patterns);
        int answer = 1;
        int result = Integer.valueOf(res.get(0));
        assertEquals(answer, result);
    }

    @Test
    public void testSuffLink_DoesntHaveAParent()
    {
        String str = "ab";
        List<String> patterns = new ArrayList<String>();
        patterns.add("a");
        List<Integer> res = AhoCorasick.find(str, patterns);
        int answer = 0;
        int result = Integer.valueOf(res.get(0));
        assertEquals(answer, result);
    }
}