package com.xpecya.xds;

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

    /**
     * Edge的默认实现
     * 子类可以根据实际情况自己另外实现
     * @param <T>
     */
    class DefaultEdge<T> implements Graph.Edge<T> {

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
