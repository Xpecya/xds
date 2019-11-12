package com.xpecya.xds;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

abstract class AbstractGraph<T> implements Graph<T> {

    /**
     * 转换一个图的底层结构
     * @param anotherGraph 另一个图
     */
    AbstractGraph(Graph<T> anotherGraph) {}

    /**
     * 不含任何参数的构造函数
     */
    AbstractGraph() {}

    @Override
    public Spliterator<Edge<T>> edgeSpliterator() {
        return Spliterators.spliteratorUnknownSize(this.edgeIterator(), 0);
    }

    @Override
    public void forEachEdge(Consumer<? super Edge<T>> edgeConsumer) {
        Objects.requireNonNull(edgeConsumer);
        Iterator<Edge<T>> iterator = this.edgeIterator();

        while(iterator.hasNext()) {
            Edge<T> edge = iterator.next();
            edgeConsumer.accept(edge);
        }
    }

    /**
     * Edge的默认实现
     * 子类可以根据实际情况自己另外实现
     */
    class DefaultEdge implements Graph.Edge<T> {

        private T start;
        private T end;

        DefaultEdge(T start, T end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public T start() {
            return start;
        }

        @Override
        public T end() {
            return end;
        }
    }
}
