/**
 * IconFamily接口定义了获取中间节点图标和叶子节点图标的方法。
 * 这是抽象工厂模式的一部分，用于创建具体的图标家族实现。
 */
public interface IconFamily {
    String getIntermediateNodeIcon();
    String getLeafNodeIcon();
}
