/**
 * JSONVisualizerFactory是一个抽象工厂类，定义了创建JSON可视化器和图标家族的方法。
 * 这是抽象工厂模式的实现，用于生成不同风格的可视化器和图标家族。
 */
public abstract class JSONVisualizerFactory {
    public abstract JSONVisualizer createVisualizer(IconFamily iconFamily);
    public abstract IconFamily createIconFamily(String iconFamilyName);
}
