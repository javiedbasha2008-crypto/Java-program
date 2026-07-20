 public class Isnumber{
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExponent = false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '+' || c == '-') {
                // A sign is only valid at the very beginning or right after an exponent 'e'/'E'
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') {
                // A dot is invalid if we've already seen an exponent or another dot
                if (seenExponent || seenDot) {
                    return false;
                }
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                // An exponent is invalid if we've already seen one, or if no digits came before it
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                seenDigit = false; // Reset to ensure there is an integer after the exponent
            } else {
                // Any other character is invalid
                return false;
            }
        }
        
        // The string is only valid if we ended with a valid digit sequence
        return seenDigit;
    }
}