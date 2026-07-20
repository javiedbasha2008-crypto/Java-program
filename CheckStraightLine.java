public class CheckStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        // Base case: 2 points always form a straight line
        if (coordinates.length <= 2) {
            return true;
        }
        
        // Get the differences using the first two points
        int x0 = coordinates[0][0], y0 = coordinates[0][1];
        int x1 = coordinates[1][0], y1 = coordinates[1][1];
        
        int dx = x1 - x0;
        int dy = y1 - y0;
        
        // Check all other points against the initial slope
        for (int i = 2; i < coordinates.length; i++) {
            int currX = coordinates[i][0];
            int currY = coordinates[i][1];
            
            // Cross-multiplication check
            if (dy * (currX - x0) != dx * (currY - y0)) {
                return false;
            }
        }
        
        return true;
    }
}