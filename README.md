# Funny JSON Explorer

Funny JSON Explorer（**FJE**），是一个JSON文件可视化的命令行界面小工具

## 类图

![fje](C:\Users\22739\Desktop\github\learning-note\c++项目\images\fje.jpg)



## 使用的设计模式及作用

使用**工厂方法**（Factory）、**抽象工厂**（Abstract Factory）、**建造者**（Builder）模式、**组合模式**（Composition），完成功能的同时，使得程序易于扩展和维护。

### FJE 类

**描述：** 该类是主类，包含主方法，使用工厂方法、抽象工厂和建造者模式来创建和运行JSON可视化工具。

- **工厂方法模式：** 确定可视化器的风格（树状或矩形）。
- **抽象工厂模式：** 创建不同的图标家族（默认或扑克风格）。
- **建造者模式：** 构建包含各种参数（如文件路径、风格和图标家族）的`FJE`实例。

`FJE` 使用 `JSONVisualizerFactory` 根据提供的参数创建一个可视化器和图标家族。

`JSONVisualizerFactory` 是一个抽象工厂，生产具体的可视化器和图标家族。



### FJEBuilder 类

**描述：** 该类使用建造者模式构建`FJE`对象，允许以方便的方式设置多个参数。

- **建造者模式：** 提供一种灵活的方式设置多个参数并构建一个`FJE`实例。

构建并返回一个具有指定参数的`FJE`实例。



### JSONVisualizerFactory 类

**描述：** 该抽象类定义了创建JSON可视化器和图标家族的方法。

- **抽象工厂模式：** 定义了创建可视化器和图标家族的抽象方法。

具体工厂如`TreeJSONVisualizerFactory`和`RectangleJSONVisualizerFactory`继承该类并实现这些方法。



### TreeJSONVisualizerFactory 类

**描述：** 该类继承了`JSONVisualizerFactory`，提供了创建树状JSON可视化器和图标家族的实现。

- **抽象工厂模式：** 实现了创建树状可视化器和图标家族的方法。

具体工厂方法创建树状可视化器（`TreeJSONVisualizer`）和特定图标家族（如`DefaultIconFamily`或`PokerFaceIconFamily`）。



### RectangleJSONVisualizerFactory 类

**描述：** 该类继承了`JSONVisualizerFactory`，提供了创建矩形JSON可视化器和图标家族的实现。

- **抽象工厂模式：** 实现了创建矩形可视化器和图标家族的方法。

具体工厂方法创建矩形可视化器（`RectangleJSONVisualizer`）和特定图标家族（如`DefaultIconFamily`或`PokerFaceIconFamily`）。



### BuildElementList 类

**描述：** 递归地构建JSON元素列表，包含键、值、层级和位置。

- **组合模式：** 处理嵌套的JSON对象，构建元素的层次结构。

在具体可视化器（如`TreeJSONVisualizer`和`RectangleJSONVisualizer`）中使用，用于构建和处理JSON元素的层级列表。



### JSONElement 类

**描述：** 表示JSON对象中的一个元素及其层级和位置。

- **组合模式：** 作为JSON对象的基本组成部分，用于构建更复杂的JSON结构。

由`BuildElementList`类生成并由具体可视化器处理和显示。



### TreeJSONVisualizer 类

**描述：** 实现了`JSONVisualizer`接口，用于树状显示JSON对象。

- **组合模式：** 处理嵌套的JSON对象，显示其层级结构。

使用`BuildElementList`生成的JSON元素列表并打印显示。



### RectangleJSONVisualizer 类

**描述：** 实现了`JSONVisualizer`接口，用于矩形显示JSON对象。

- **组合模式：** 处理嵌套的JSON对象，显示其层级结构。

使用`BuildElementList`生成的JSON元素列表并打印显示。



### IconFamily 接口及其实现类

**描述：** 定义了获取中间节点图标和叶子节点图标的方法，作为抽象工厂模式的一部分，用于创建具体的图标家族实现。

- **抽象工厂模式：** 提供不同的图标家族实现。

由具体的`JSONVisualizerFactory`子类创建，并在具体可视化器中使用。



### DefaultIconFamily 类

**描述：** 提供默认的中间节点和叶子节点图标。

- **抽象工厂模式：** `IconFamily`接口的具体实现。

由具体的`JSONVisualizerFactory`子类创建，并在具体可视化器中使用。



### PokerFaceIconFamily 类

**描述：** 提供扑克风格的中间节点和叶子节点图标。

- **抽象工厂模式：** `IconFamily`接口的具体实现。

由具体的`JSONVisualizerFactory`子类创建，并在具体可视化器中使用。





## 添加新的风格

不改变现有代码，只需添加新的抽象工厂，即可添加新的风格

只需像已有的代码一样添加类似：`TreeJSONVisualizerFactory`和`RectangleJSONVisualizerFactory`这两个抽象工厂的类形式，调用对应的`JSONVisualizer`实现和`IconFamily`实现，即可是实现添加新的风格和icon族，并显示。



## 添加新的图标族

只需要添加如下面形式的类即可实现

<img src="C:\Users\22739\Desktop\github\learning-note\c++项目\images\image-20240612123613464.png" alt="image-20240612123613464" style="zoom: 67%;" />



## 运行结果

![image-20240612130225912](C:\Users\22739\Desktop\third_fall\Software\fje\result\image-20240612130225912.png)



![image-20240612130233202](C:\Users\22739\Desktop\github\learning-note\c++项目\images\image-20240612130233202.png)



![image-20240612130254329](C:\Users\22739\Desktop\github\learning-note\c++项目\images\image-20240612130254329.png)



![image-20240612130313095](C:\Users\22739\Desktop\github\learning-note\c++项目\images\image-20240612130313095.png)





