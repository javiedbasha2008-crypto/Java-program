import java.util.ArrayList;
import java.util.List;

public class FindWords {
    public String[] findWords(String[] words) {
        // Map each letter a-z to its row number (0-indexed or 1-indexed)
        int[] charRow = new int[26];
        
        String row1 = "qwertyuiop";
        String row2 = "asdfghjkl";
        String row3 = "zxcvbnm";
        
        for (char c : row1.toCharArray()) charRow[c - 'a'] = 1;
        for (char c : row2.toCharArray()) charRow[c - 'a'] = 2;
        for (char c : row3.toCharArray()) charRow[c - 'a'] = 3;
        
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            String lower = word.toLowerCase();
            int targetRow = charRow[lower.charAt(0) - 'a'];
            boolean isValid = true;
            
            for (int i = 1; i < lower.length(); i++) {
                if (charRow[lower.charAt(i) - 'a'] != targetRow) {
                    isValid = false;
                    break;
                }
            }
            
            if (isValid) {
                result.add(word);
            }
        }
        
        return result.toArray(new String[0]);
    }
}