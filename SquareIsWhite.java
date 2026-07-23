public class SquareIsWhite {
    public boolean squareIsWhite(String coordinates) {
        int col = coordinates.charAt(0) - 'a' + 1; // Convert letter to 1-8
        int row = coordinates.charAt(1) - '0';     // Convert digit char to 1-8
        
        // If sum is odd, square is white (return true)
        return (col + row) % 2 != 0;
    }
}