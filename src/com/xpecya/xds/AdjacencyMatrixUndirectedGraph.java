package com.xpecya.xds;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 邻接矩阵表示的无向图
 * @param <T> 结点数据类型
 */
public class AdjacencyMatrixUndirectedGraph<T> extends AbstractUndirectedGraph<T> {

    /**
     * 构造一个默认的邻接矩阵无向图
     */
    public AdjacencyMatrixUndirectedGraph() {

    }

    /**
     * 转换一个图的底层结构
     * @param anotherGraph 另一个图
     */
    public AdjacencyMatrixUndirectedGraph(Graph<T> anotherGraph) {
        super(anotherGraph);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public boolean contains(T vertice) {
        return false;
    }

    @Override
    public boolean contains(T start, T end) {
        return false;
    }

    @Override
    public boolean isConnect(T start, T end) {
        return false;
    }

    @Override
    public void add(T vertice) {

    }

    @Override
    public void add(T start, T end) {

    }

    @Override
    public void remove(T vertice) {

    }

    @Override
    public void removeEdge(T start, T end) {

    }

    @Override
    public Iterator<Edge<T>> edgeIterator() {
        return null;
    }
}
