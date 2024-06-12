/**
 * PokerFaceIconFamily类实现了IconFamily接口，提供扑克风格的中间节点和叶子节点图标。
 * 使用了抽象工厂模式。
 */
public class PokerFaceIconFamily implements IconFamily {
    @Override
    public String getIntermediateNodeIcon() {
        return "♢";
    }

    @Override
    public String getLeafNodeIcon() {
        return "♤";
    }
}
