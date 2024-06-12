/**
 * TreeJSONVisualizerFactory类继承了JSONVisualizerFactory，提供了创建树状JSON可视化器和图标家族的实现。
 * 使用了抽象工厂模式。
 */
public class TreeJSONVisualizerFactory extends JSONVisualizerFactory {
    @Override
    public JSONVisualizer createVisualizer(IconFamily iconFamily) {
        return new TreeJSONVisualizer(iconFamily);
    }

    @Override
    public IconFamily createIconFamily(String iconFamilyName) {
        if (iconFamilyName.equals("poker")) {
            return new PokerFaceIconFamily();
        } else {
            return new DefaultIconFamily();
        }
    }
}
