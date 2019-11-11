package com.xpecya.xds;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 邻接链表构成的无向图
 * @param <T> 结点数据类型
 */
public class AdjacencyListUndirectedGraph<T> extends AbstractUndirectedGraph<T> {

    /**
     * 构造一个默认的邻接链表无向图
     */
    public AdjacencyListUndirectedGraph() {
        super();
    }

    /**
     * 转换一个图的底层结构
     * @param anotherGraph 另一个图
     */
    public AdjacencyListUndirectedGraph(Graph<T> anotherGraph) {
        super(anotherGraph);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
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

    @Override
    public Spliterator<Edge<T>> edgeSpliterator() {
        return null;
    }

    @Override
    public void foreach(Consumer<? super Edge<T>> edgeConsumer) {

    }
}
