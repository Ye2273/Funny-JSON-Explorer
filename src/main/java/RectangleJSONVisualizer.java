import java.util.*;

/**
 * RectangleJSONVisualizer类实现了JSONVisualizer接口，用于矩形显示JSON对象。
 * 使用了组合模式来处理嵌套的JSON对象。
 */
public class RectangleJSONVisualizer implements JSONVisualizer {
    private IconFamily iconFamily;
    private int elementSize;
    private Map<String, Integer> resultMap = new LinkedHashMap<>();
    private int resultSize;

    public RectangleJSONVisualizer(IconFamily iconFamily) {
        this.iconFamily = iconFamily;
    }

    @Override
    public void visualize(Map<String, Object> jsonObject) {
        List<JSONElement> elements = new ArrayList<>();
        BuildElementList rectangle = new BuildElementList();
        rectangle.buildElementList(jsonObject, 0, elements, new boolean[0]);
        this.elementSize = elements.size();
        printElements(elements);
    }

    /**
     * 打印JSON元素列表，显示其层级结构和位置。
     *
     * @param elements 要打印的JSON元素列表
     */
    private void printElements(List<JSONElement> elements) {
        int maxResultSize = 0;
        int count = 0;
        for(int i = 0; i < elements.size(); i++) {
            JSONElement element = elements.get(i);

            count = makeResultMap(element, i);
            maxResultSize = Math.max(maxResultSize, count);
        }
        draw(maxResultSize);

    }

    private void draw (int maxResultSize){
        int count = 0;
        int len = maxResultSize + 4;
        for(Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            for(int i = 0; i < len - value; i++) {
                key += "─";
            }
            if(count == 0){
                key += "┐";
            } else if(count == elementSize - 1) {
                key += "┘";
            } else {
                key += "┤";
            }
            count++;
            System.out.println(key);
        }
    }
    /**
     * 绘制单个JSON元素，显示其层级结构和位置。
     *
     * @param element 要绘制的JSON元素
     */
    private int makeResultMap(JSONElement element, int idx) {

        String result = printIndent(element.getLevel(), element.getParentHasNext(), idx);
        switch (element.getPosition()) {
            case FIRST:
                if (element.getLevel() > 0){
//                    System.out.print("├─");
                    result += "├─";
                } else {
//                    System.out.print("┌─");
                    result += "┌─";
                }

                break;
            case LAST:
            case MIDDLE:
                if (idx != elementSize - 1){
//                    System.out.print("├─");
                    result += "├─";
                }
                break;
        }
        if (element.getValue() instanceof Map) {
            result += (iconFamily.getIntermediateNodeIcon() + element.getKey());
//            System.out.println(iconFamily.getIntermediateNodeIcon() + element.getKey());
        } else {
            result += (iconFamily.getLeafNodeIcon() + element.getKey() + (element.getValue() == null ? "" : ": " + element.getValue()));
//            System.out.println(iconFamily.getLeafNodeIcon() + element.getKey() + (element.getValue() == null ? "" : ": " + element.getValue()));
        }
        resultSize = result.length();
        resultMap.put(result, resultSize);
        return resultSize;
    }

    /**
     * 根据缩进级别打印空格和竖线。
     *
     * @param level 缩进级别
     * @param parentHasNextParent 每个父节点是否有下一个兄弟节点的标记数组
     */
    private String printIndent(int level, boolean[] parentHasNextParent, int idx) {
        String result = "";
        if (idx == elementSize - 1) {
            result += "└──";
            for (int i = 0; i < level; i++) {
                result += "─┴─";
            }
            return result;
        }
        for (int i = 0; i < level; i++) {
            result += "│   ";
        }
        return result;
    }

}

