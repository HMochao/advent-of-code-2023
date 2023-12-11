public class GhostNode {

    private String label;
    private String left;
    private String right;

    public GhostNode(String label, String left, String right) {
        this.label = label;
        this.left = left;
        this.right = right;
    }

    public String getDirection(char d) {
        if(d == 'L') {
            return left;
        } else {
            return right;
        }
    }
}
