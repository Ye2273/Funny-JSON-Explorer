import java.util.Map;
/**
 * JSONVisualizer接口定义了一个方法用于可视化JSON对象。
 * 这是工厂方法模式的一部分，工厂方法将返回实现该接口的具体类。
 */
public interface JSONVisualizer {
    void visualize(Map<String, Object> jsonObject);
}
