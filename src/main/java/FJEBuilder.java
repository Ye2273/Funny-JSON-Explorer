
/**
 * FJEBuilder类使用建造者模式来创建FJE对象，方便设置多个参数。
 */
public class FJEBuilder {
    private String jsonFilePath;
    private String style;
    private String iconFamily;

    public FJEBuilder setJsonFilePath(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        return this;
    }

    public FJEBuilder setStyle(String style) {
        this.style = style;
        return this;
    }

    public FJEBuilder setIconFamily(String iconFamily) {
        this.iconFamily = iconFamily;
        return this;
    }

    public FJE build() {
        return new FJE(jsonFilePath, style, iconFamily);
    }
}
