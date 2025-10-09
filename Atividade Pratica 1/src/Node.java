import java.util.ArrayList;

public class Node {
    private final float id;
    private final String label;

    public Node(float id, String label) {
        this.id = id;
        this.label = label;
    }

    public float getId() {
        return this.id;
    }
    public String getLabel() {
        return this.label;
    }
}
