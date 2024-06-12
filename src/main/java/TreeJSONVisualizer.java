import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TreeJSONVisualizer类实现了JSONVisualizer接口，用于树状显示JSON对象。
 * 使用了组合模式来处理嵌套的JSON对象。
 */
public class TreeJSONVisualizer implements JSONVisualizer {
    private IconFamily iconFamily;

    public TreeJSONVisualizer(IconFamily iconFamily) {
        this.iconFamily = iconFamily;
    }

    @Override
    public void visualize(Map<String, Object> jsonObject) {
        List<JSONElement> elements = new ArrayList<>();
        BuildElementList tree = new BuildElementList();
        tree.buildElementList(jsonObject, 0, elements, new boolean[0]);
        printElements(elements);
    }

    /**
     * 打印JSON元素列表，显示其层级结构和位置。
     *
     * @param elements 要打印的JSON元素列表
     */
    private void printElements(List<JSONElement> elements) {
        for (JSONElement element : elements) {
            draw(element);
        }
    }

    /**
     * 绘制单个JSON元素，显示其层级结构和位置。
     *
     * @param element 要绘制的JSON元素
     */
    private void draw(JSONElement element) {
        printIndent(element.getLevel(), element.getParentHasNext());
        switch (element.getPosition()) {
            case FIRST:
            case MIDDLE:
                System.out.print("├─ ");
                break;
            case LAST:
                System.out.print("└─ ");
                break;
        }
        if (element.getValue() instanceof Map) {
            System.out.println(iconFamily.getIntermediateNodeIcon() + element.getKey());
        } else {
            System.out.println(iconFamily.getLeafNodeIcon() + element.getKey() + (element.getValue() == null ? "" : ":"+element.getValue()));
        }
    }

    /**
     * 根据缩进级别打印空格和竖线。
     *
     * @param level 缩进级别
     * @param parentHasNextParent 每个父节点是否有下一个兄弟节点的标记数组
     */
    private void printIndent(int level, boolean[] parentHasNextParent) {
        for (int i = 0; i < level; i++) {
            if (parentHasNextParent[i]) {
                System.out.print("│  ");
            } else {
                System.out.print("   ");
            }
        }
    }
}
