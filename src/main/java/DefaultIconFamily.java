/**
 * DefaultIconFamily类实现了IconFamily接口，提供默认的中间节点和叶子节点图标。
 * 使用了抽象工厂模式。
 */
public class DefaultIconFamily implements IconFamily {
    @Override
    public String getIntermediateNodeIcon() {
        return "";
    }

    @Override
    public String getLeafNodeIcon() {
        return "";
    }
}
