package com.xpecya.xds;

abstract class AbstractUndirectedGraph<T> implements UndirectedGraph<T> {

    /**
     * 转换一个图的底层结构
     * @param anotherGraph 另一个图
     */
    AbstractUndirectedGraph(Graph<T> anotherGraph) {}

    /**
     * 不含任何参数的构造函数
     */
    AbstractUndirectedGraph() {}
}
