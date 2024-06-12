/**
 * JSONElement类表示JSON对象中的一个元素及其层级和位置。
 * 使用了组合模式。
 */
public class JSONElement {
    private String key;
    private Object value;
    private int level;
    private Position position;
    private boolean[] hasNextArray;

    public enum Position {
        FIRST, MIDDLE, LAST
    }

    public JSONElement(String key, Object value, int level, Position position, boolean[] hasNextArray) {
        this.key = key;
        this.value = value;
        this.level = level;
        this.position = position;
        this.hasNextArray = hasNextArray;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public int getLevel() {
        return level;
    }

    public Position getPosition() {
        return position;
    }

    public boolean[] getParentHasNext() {
        return hasNextArray;
    }
}
