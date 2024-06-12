/**
 * RectangleJSONVisualizerFactory类继承了JSONVisualizerFactory，提供了创建矩形JSON可视化器和图标家族的实现。
 * 使用了抽象工厂模式。
 */
public class RectangleJSONVisualizerFactory extends JSONVisualizerFactory {
    @Override
    public JSONVisualizer createVisualizer(IconFamily iconFamily) {
        return new RectangleJSONVisualizer(iconFamily);
    }

    @Override
    public IconFamily createIconFamily(String iconFamilyName) {
        if (iconFamilyName.equals("poker")) {
            return new PokerFaceIconFamily();
        } else if (iconFamilyName.equals("music")) {
            return new MusicIconFamily();
        }else {
            return new DefaultIconFamily();
        }
    }
}
