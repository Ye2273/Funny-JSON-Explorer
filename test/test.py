import graphviz

dot_graph = """
digraph UML {
    rankdir=TB;
    node [shape=record, fontsize=12];

    classFJE [label="{FJE|+ jsonFilePath: String\\l+ style: String\\l+ iconFamily: String\\l|+ FJE(String, String, String)\\l+ run() : void\\l- readFile(String) : String\\l- parseJsonObject(String) : Map<String, Object>}"];
    classFJEBuilder [label="{FJEBuilder|+ jsonFilePath: String\\l+ style: String\\l+ iconFamily: String\\l|+ setJsonFilePath(String) : FJEBuilder\\l+ setStyle(String) : FJEBuilder\\l+ setIconFamily(String) : FJEBuilder\\l+ build() : FJE}"];
    classJSONVisualizerFactory [label="{JSONVisualizerFactory|+ createVisualizer(IconFamily) : JSONVisualizer\\l+ createIconFamily(String) : IconFamily}"];
    classTreeJSONVisualizerFactory [label="{TreeJSONVisualizerFactory|+ createVisualizer(IconFamily) : JSONVisualizer\\l+ createIconFamily(String) : IconFamily}"];
    classRectangleJSONVisualizerFactory [label="{RectangleJSONVisualizerFactory|+ createVisualizer(IconFamily) : JSONVisualizer\\l+ createIconFamily(String) : IconFamily}"];
    classJSONVisualizer [label="{<<interface>> JSONVisualizer|+ visualize(Map<String, Object>) : void}"];
    classTreeJSONVisualizer [label="{TreeJSONVisualizer|+ visualize(Map<String, Object>) : void\\l+ printElements(List<JSONElement>) : void\\l+ draw(JSONElement) : void\\l+ printIndent(int, boolean[]) : void}"];
    classRectangleJSONVisualizer [label="{RectangleJSONVisualizer|+ visualize(Map<String, Object>) : void\\l+ printElements(List<JSONElement>) : void\\l+ draw(JSONElement) : void\\l+ printIndent(int, boolean[]) : void}"];
    classIconFamily [label="{<<interface>> IconFamily|+ getIntermediateNodeIcon() : String\\l+ getLeafNodeIcon() : String}"];
    classDefaultIconFamily [label="{DefaultIconFamily|+ getIntermediateNodeIcon() : String\\l+ getLeafNodeIcon() : String}"];
    classPokerFaceIconFamily [label="{PokerFaceIconFamily|+ getIntermediateNodeIcon() : String\\l+ getLeafNodeIcon() : String}"];
    classBuildElementList [label="{BuildElementList|+ buildElementList(Map<String, Object>, int, List<JSONElement>, boolean[]) : void}"];
    classJSONElement [label="{JSONElement|+ key: String\\l+ value: Object\\l+ level: int\\l+ position: Position\\l+ hasNextArray: boolean[]\\l|+ JSONElement(String, Object, int, Position, boolean[])\\l+ getKey() : String\\l+ getValue() : Object\\l+ getLevel() : int\\l+ getPosition() : Position\\l+ getParentHasNext() : boolean[]}"];
    classPosition [label="{<<enum>> Position|FIRST\\lMIDDLE\\lLAST}"];

    classFJE -> classFJEBuilder [arrowhead="open", label="creates"];
    classFJE -> classJSONVisualizerFactory [arrowhead="open"];
    classJSONVisualizerFactory -> classTreeJSONVisualizerFactory [arrowhead="open", style="dashed"];
    classJSONVisualizerFactory -> classRectangleJSONVisualizerFactory [arrowhead="open", style="dashed"];
    classJSONVisualizerFactory -> classIconFamily [arrowhead="open", style="dashed"];
    classTreeJSONVisualizerFactory -> classTreeJSONVisualizer [arrowhead="open"];
    classRectangleJSONVisualizerFactory -> classRectangleJSONVisualizer [arrowhead="open"];
    classTreeJSONVisualizer -> classJSONVisualizer [arrowhead="open", style="dashed"];
    classRectangleJSONVisualizer -> classJSONVisualizer [arrowhead="open", style="dashed"];
    classTreeJSONVisualizer -> classBuildElementList [arrowhead="open"];
    classRectangleJSONVisualizer -> classBuildElementList [arrowhead="open"];
    classTreeJSONVisualizer -> classIconFamily [arrowhead="open"];
    classRectangleJSONVisualizer -> classIconFamily [arrowhead="open"];
    classDefaultIconFamily -> classIconFamily [arrowhead="open", style="dashed"];
    classPokerFaceIconFamily -> classIconFamily [arrowhead="open", style="dashed"];
    classBuildElementList -> classJSONElement [arrowhead="open"];
    classJSONElement -> classPosition [arrowhead="open"];
}

"""

dot = graphviz.Source(dot_graph)
dot.view()