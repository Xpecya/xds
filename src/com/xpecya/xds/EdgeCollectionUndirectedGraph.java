package com.xpecya.xds;

import java.util.*;
import java.util.function.Consumer;

/**
 * 由边的集合表示的无向图
 * 可以接受null参数，但是只能有一个null
 * 所有null结点视作同一个结点
 * 由于底层只维护了边的列表
 * 因此对边的遍历性能很好，但是对点的遍历性能相对差一点
 * 不推荐使用
 * @param <T> 结点数据类型
 */
public class EdgeCollectionUndirectedGraph<T> extends AbstractUndirectedGraph<T> {

    /**
     * 边的集合
     * 暂时用hashSet表示
     * 未来会改用自己的数据结构自举
     */
    private Collection<Edge<T>> edgeSet = new HashSet<>();

    /**
     * 构造一个默认的边列表无向图
     */
    public EdgeCollectionUndirectedGraph() {}

    /**
     * 转换一个图的底层结构
     * 对于EdgeListUndirectedGraph，我们只关心其他图的边
     * @param anotherGraph 另一张图
     */
    public EdgeCollectionUndirectedGraph(Graph<T> anotherGraph) {
        super(anotherGraph);
        Iterator<Edge<T>> edgeIterator = anotherGraph.edgeIterator();
        while (edgeIterator.hasNext()) {
            Edge<T> edge = edgeIterator.next();
            edgeSet.add(edge);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return getCollection().iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        getCollection().forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return getCollection().spliterator();
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
        return edgeSet.iterator();
    }

    @Override
    public Spliterator<Edge<T>> edgeSpliterator() {
        return edgeSet.spliterator();
    }

    @Override
    public void foreach(Consumer<? super Edge<T>> edgeConsumer) {
        edgeSet.forEach(edgeConsumer);
    }

    private VerticeIterableCollection<T> getCollection() {
        return null;
    }

    /**
     * 遍历vertice的collection
     * 用于对外提供Iterator遍历器
     * @param <T>
     */
    private class VerticeIterableCollection<T> implements Iterable<T> {

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

        private class VerticeIterator<T> implements Iterator<T> {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        }
    }
}
