public class LenghtofLastWord {
    public int lengthOfLastWord(String s) {
        int length = 0;
        
        // Start from the end of the string
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                length++;
            } else {
                // If we already found a word and hit a space, we are done
                if (length > 0) {
                    return length;
                }
            }
        }
        
        return length;
    }
}