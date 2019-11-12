package com.xpecya.xds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * 邻接链表构成的无向图
 * @param <T> 结点数据类型
 */
public class AdjacencyLinkedListUndirectedGraph<T> extends AbstractUndirectedGraph<T> {

    // 邻接链表的结构，底层是一个结点数组，数组的元素是邻接数组的链表
    // 简便起见，结点数组使用java.util.ArrayList实现
    private ArrayList<T> valueArray = new ArrayList<>();
    private ArrayList<LinkedListVertice> verticeArray = new ArrayList<>();

    /**
     * 构造一个默认的邻接链表无向图
     */
    public AdjacencyLinkedListUndirectedGraph() {
        super();
    }

    /**
     * 转换一个图的底层结构
     * 分两次遍历，第一次，将所有结点放在verticeArray中
     * 第二次，将所有边的关系加入进去
     * @param anotherGraph 另一个图
     */
    public AdjacencyLinkedListUndirectedGraph(Graph<T> anotherGraph) {
        super(anotherGraph);

        // 第一次，将所有点加入进去
        Iterator<T> graphIterator = anotherGraph.iterator();
        while (graphIterator.hasNext()) {
            T next = graphIterator.next();
            add(next);
        }

        // 第二次，将所有边加入进去
        Iterator<Edge<T>> edgeIterator = anotherGraph.edgeIterator();
        while (edgeIterator.hasNext()) {
            Edge<T> next = edgeIterator.next();
            T start = next.start();
            T end = next.end();
            add(start, end);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new AdjacencyLinkedListIterator();
    }

    @Override
    public boolean contains(T vertice) {
        return valueArray.contains(vertice);
    }

    @Override
    public boolean contains(T start, T end) {
        int startIndex = valueArray.indexOf(start);
        if (startIndex == -1) {
            return false;
        }
        LinkedListVertice startVertice = verticeArray.get(startIndex);
        LinkedListVertice next;
        while ((next = startVertice.next) != null) {
            if (Objects.equals(end, valueArray.get(next.valueIndex))) {
                return true;
            }
            startVertice = next;
        }
        return false;
    }

    @Override
    public boolean isConnect(T start, T end) {
        // TODO 实现
        return false;
    }

    @Override
    public void add(T vertice) {
        int index = valueArray.size();
        valueArray.add(vertice);
        verticeArray.add(new LinkedListVertice(index));
    }

    @Override
    public void add(T start, T end) {
        LinkedListVertice startVertice;
        LinkedListVertice endVertice;
        int startIndex = valueArray.indexOf(start);
        int endIndex = valueArray.indexOf(end);
        if (startIndex < 0) {
            startVertice = new LinkedListVertice(valueArray.size());
            valueArray.add(start);
            verticeArray.add(startVertice);
        } else {
            startVertice = verticeArray.get(startIndex);
        }
        if (endIndex < 0) {
            endVertice = new LinkedListVertice(valueArray.size());
            valueArray.add(end);
            verticeArray.add(endVertice);
        } else {
            endVertice = verticeArray.get(endIndex);
        }
        LinkedListVertice currentVertice = startVertice;
        LinkedListVertice nextIndex = -1;
        while ((nextIndex = currentVertice.nextIndex) >= 0) {
            currentVertice = verticeArray.get(nextIndex);
        }
        currentVertice.nextIndex = endIndex;
        currentVertice = endVertice;
        while ((next = currentVertice.next) != null) {
            currentVertice = next;
        }
        currentVertice.next = new LinkedListVertice(start);
    }

    @Override
    public void remove(T vertice) {
        int verticeIndex = index(vertice);
        LinkedListVertice linkedListVertice = verticeArray.get(verticeIndex);
        verticeArray.remove(linkedListVertice);
        LinkedListVertice next;
        while ((next = linkedListVertice.next) != null) {
            removeEdge(vertice, next.value);
        }
    }

    @Override
    public void removeEdge(T start, T end) {
        int startIndex = index(start);
        int endIndex = index(end);
        if (startIndex == -1) {
            // start 不存在
            // 处理end
            // 如果两个结点都不存在，就什么都不做
            if (endIndex > -1) {
                LinkedListVertice endVertice = verticeArray.get(endIndex);
                LinkedListVertice next;
                while ((next = endVertice.next) != null) {
                    if (Objects.equals(next.value, start)) {
                        endVertice.next = next.next;
                        break;
                    }
                    endVertice = next;
                }
            }
        } else {
            LinkedListVertice startVertice = verticeArray.get(startIndex);
            LinkedListVertice next;
            while ((next = startVertice.next) != null) {
                if (Objects.equals(next.value, end)) {
                    startVertice.next = next.next;
                }
                startVertice = next;
            }
            if (endIndex > -1) {
                LinkedListVertice endVertice = verticeArray.get(endIndex);
                while ((next = endVertice.next) != null) {
                    if (Objects.equals(next.value, start)) {
                        endVertice.next = next.next;
                        break;
                    }
                    endVertice = next;
                }
            }
        }
    }

    @Override
    public Iterator<Edge<T>> edgeIterator() {
        return new AdjacencyLinkedListEdgeIterator();
    }

    /**
     * 构成链表关系的结点
     */
    private class LinkedListVertice {

        /**
         * 这个结点值的下标
         */
        private int valueIndex;

        /**
         * 下个结点值的下标
         */
        private LinkedListVertice next;

        private LinkedListVertice(int valueIndex) {
            this.valueIndex = valueIndex;
        }
    }

    private class AdjacencyLinkedListIterator implements Iterator<T> {
        private Iterator<LinkedListVertice> verticeIterator = verticeArray.iterator();

        @Override
        public boolean hasNext() {
            return verticeIterator.hasNext();
        }

        @Override
        public T next() {
            return verticeIterator.next().value;
        }
    }

    private class AdjacencyLinkedListEdgeIterator implements Iterator<Edge<T>> {
        private Iterator<LinkedListVertice> verticeIterator = verticeArray.iterator();
        private LinkedListVertice currentStartVertice;
        private LinkedListVertice currentEndVertice;
        private Edge<T> currentEdge;

        @Override
        public boolean hasNext() {
            if (currentStartVertice == null) {
                if (nextStart()) {
                    return false;
                }
            }
            if (currentEndVertice == null) {
                if (!nextEnd()) {
                    return false;
                }
            }
            currentEdge = new DefaultEdge(currentStartVertice.value, currentEndVertice.value);
            return true;
        }

        @Override
        public Edge<T> next() {
            if (currentEdge == null) {
                if (!hasNext()) {
                    return null;
                }
            }
            return currentEdge;
        }

        private boolean nextStart() {
            boolean result = true;
            if (verticeIterator.hasNext()) {
                currentStartVertice = verticeIterator.next();
                result = false;
            }
            return result;
        }

        private boolean nextEnd() {
            if (currentStartVertice == null) {
                if (nextStart()) {
                    return false;
                }
            }
            LinkedListVertice next;
            while ((next = currentStartVertice.next) == null) {
                if (nextStart()) {
                    return false;
                }
            }
            currentEndVertice = next;
            return true;
        }
    }
}
