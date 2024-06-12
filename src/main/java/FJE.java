import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * FJE类是主类，包含主方法，使用工厂模式和建造者模式来创建和运行JSON可视化工具。
 */
public class FJE {
    private String jsonFilePath;
    private String style;
    private String iconFamily;

    public FJE(String jsonFilePath, String style, String iconFamily) {
        this.jsonFilePath = jsonFilePath;
        this.style = style;
        this.iconFamily = iconFamily;
    }

    public void run() {
        String jsonContent = readFile(jsonFilePath);
        Map<String, Object> jsonObject = parseJsonObject(jsonContent);
        JSONVisualizerFactory factory;
        if (style.equals("tree")) {
            factory = new TreeJSONVisualizerFactory();
        } else {
            factory = new RectangleJSONVisualizerFactory();
        }

        IconFamily iconFamilyObj = factory.createIconFamily(iconFamily);
        JSONVisualizer visualizer = factory.createVisualizer(iconFamilyObj);
        visualizer.visualize(jsonObject);
    }

    private Map<String, Object> parseJsonObject(String content) {
        Map<String, Object> map = new LinkedHashMap<>();
        int braceCount = 0;
        int start = 0;
        boolean parsingKey = true;
        String currentKey = null;

        for (int i = 0; i <= content.length(); i++) {
            char c = i < content.length() ? content.charAt(i) : ',';
            if (c == '{') braceCount++;
            if (c == '}') braceCount--;
            if (braceCount == 0 && (c == ':' || c == ',' || i == content.length())) {
                if (parsingKey) {
                    currentKey = content.substring(start, i).trim().replaceAll("\"", "");
                    parsingKey = false;
                    start = i + 1;
                } else {
                    String value = content.substring(start, i).trim().replaceAll("\"", "");
                    if (value.equals("null")) {
                        map.put(currentKey, null);
                    } else if (value.startsWith("{") && value.endsWith("}")) {
                        map.put(currentKey, parseJsonObject(value.substring(1, value.length() - 1)));
                    } else {
                        map.put(currentKey, value);
                    }
                    parsingKey = true;
                    start = i + 1;
                }
            }
        }
        return map;
    }
    private String readFile(String filePath) {
        try {
            String jsonConent = new String(Files.readAllBytes(Paths.get(filePath)));
            jsonConent = jsonConent.trim( );
            if (jsonConent.startsWith("{") && jsonConent.endsWith("}")) {
                jsonConent = jsonConent.substring(1, jsonConent.length() - 1).trim();
            }
            return jsonConent;
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public static void main(String[] args) {
        String jsonFile = args[1];
//        String jsonFile = "src/main/resources/test.json";
        String style = args[3];
//        String style = "tree";
        String iconFamily = "";
        if (args.length == 6) {
            iconFamily = args[5];
        }
//        String iconFamily = "poke";

        FJE fje = new FJEBuilder()
                .setJsonFilePath(jsonFile)
                .setStyle(style)
                .setIconFamily(iconFamily)
                .build();

        fje.run();
    }
}
