import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BuildElementList类递归地构建JSON元素列表，包含键、值、层级和位置。
 * 使用了组合模式来处理嵌套的JSON对象。
 */
public class BuildElementList {
    /**
     * 递归地构建JSON元素列表，包含键、值、层级和位置。
     *
     * @param jsonObject 要处理的JSON对象
     * @param level 当前嵌套层级
     * @param elements 用于存储JSON元素的列表
     * @param parentHasNextParent 每个父节点是否有下一个兄弟节点的标记数组
     */
    public void buildElementList(Map<String, Object> jsonObject, int level, List<JSONElement> elements, boolean[] parentHasNextParent) {
        Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
        Iterator<Map.Entry<String, Object>> it = entrySet.iterator();
        int size = entrySet.size();
        int count = 0;
        boolean[] hasNextArray = new boolean[level + 1];
        System.arraycopy(parentHasNextParent, 0, hasNextArray, 0, level);

        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            JSONElement.Position position;
            hasNextArray[level] = it.hasNext();

            if (count == 0) {
                position = JSONElement.Position.FIRST;
            } else {
                position = JSONElement.Position.MIDDLE;
            }
            if (count == size - 1) {
                position = JSONElement.Position.LAST;
            }
            elements.add(new JSONElement(key, value, level, position, hasNextArray.clone()));
            if (value instanceof Map) {
                buildElementList((Map<String, Object>) value, level + 1, elements, hasNextArray);
            }
            count++;
        }
    }
}
