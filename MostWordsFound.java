public class MostWordsFound{
    public int mostWordsFound(String[] sentences) {
        int maxWords = 0;
        
        for (String sentence : sentences) {
            int spaces = 0;
            for (int i = 0; i < sentence.length(); i++) {
                if (sentence.charAt(i) == ' ') {
                    spaces++;
                }
            }
            // Number of words is spaces + 1
            maxWords = Math.max(maxWords, spaces + 1);
        }
        
        return maxWords;
    }
}