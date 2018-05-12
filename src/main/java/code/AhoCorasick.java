package code;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AhoCorasick {
    static final int ALPHABET_SIZE = 26;
    Node[] nodes;
    int nodeCount;
    private static class Node {
        int parent;
        char charFromParent;
        int suffLink = -1;
        int[] children = new int[ALPHABET_SIZE];
        int[] transitions = new int[ALPHABET_SIZE];
        boolean leaf;
        {
            Arrays.fill(children, -1);
            Arrays.fill(transitions, -1);
        }
    }
    public AhoCorasick(int maxNodes) {
        nodes = new Node[maxNodes];
        nodes[0] = new Node();
        nodes[0].suffLink = 0;
        nodes[0].parent = -1;
        nodeCount = 1;
    }
    public boolean createNew(int amountOfChildren, int cur, char ch, int c)
    {
        boolean created = false;
        if (amountOfChildren == -1)
        {
            nodes[nodeCount] = new Node();
            nodes[nodeCount].parent = cur;
            nodes[nodeCount].charFromParent = ch;
            nodes[cur].children[c] = nodeCount++;
            created = true;
        }
        return created;
    }

    public void addString(String s) {
        int cur = 0;
        for (char ch : s.toCharArray())
        {
            int c = ch - 'a';
            createNew(nodes[cur].children[c], cur, ch, c);
            cur = nodes[cur].children[c];
        }
        nodes[cur].leaf = true;
    }

    public int suffLink(int nodeIndex)
    {
        Node node = nodes[nodeIndex];
        if (node.parent == 0)
            node.suffLink = 0;
        else
            node.suffLink = transition(suffLink(node.parent), node.charFromParent);
        return node.suffLink;
    }

    private int transition(int nodeIndex, char ch) {
        int c = ch - 'a';
        Node node = nodes[nodeIndex];
        if (node.transitions[c] == -1)
            if (node.children[c] != -1)
                node.transitions[c] = node.children[c];
            else
            if (nodeIndex == 0)
                node.transitions[c] = 0;
            else
                node.transitions[c] = transition(suffLink(nodeIndex), ch);
       return node.transitions[c];
    }

    public static String deleteAllSpaces(String s)
    {
        String new_str ="";
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++)
        {
            if (chars[i] != '\n' && chars[i] != '\t')
                new_str+=chars[i];
        }
        return new_str;
    }

    public static List<Integer> find(String string, List<String> listOfPatterns)
    {
        List<Integer> positions;
        String new_str = deleteAllSpaces(string);
        positions = find_pattern(new_str, listOfPatterns);
        return positions;
    }

    private static List<Integer> find_pattern(String string, List<String> listOfPatterns)
    {
        List<Integer> positions = new ArrayList<Integer>();
        AhoCorasick ahoCorasick = new AhoCorasick(100);
        for (String pattern : listOfPatterns) {
            ahoCorasick.addString(pattern);
        }
        int node = 0;
        for (int i = 0; i < string.length(); i++) {
            node = ahoCorasick.transition(node, string.charAt(i));
            if (ahoCorasick.nodes[node].leaf)
                positions.add(i);
        }
        return positions;
    }

    public static void main(String[] args)
    {
    	
    }
}
