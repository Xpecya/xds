package com.xpecya.xds;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * graph api
 * 会自动去重
 * 对于任意 node A equals node B
 * 在图中只保留node A
 * 是否接受null对象取决于实现类
 * Iterator遍历的是结点
 * 对边的遍历请使用edgeIterator方法
 * 不管哪个遍历，对于接口调用方而言，都可以认为是无序的
 * @param <T> 结点数据类型
 */
public interface Graph<T> extends Iterable<T> {

    /**
     * 检查指定结点是否在图中
     * @return 检查结果
     */
    boolean contains(T node);

    /**
     * 检查指定边是否在图中
     * 如果任意一点不在图中 返回false
     * 如果在有向图中，只存在end指向start的边，返回false
     * @param start 边开始结点
     * @param end 边结束结点
     * @return 检查结果
     */
    boolean contains(T start, T end);

    /**
     * 检查两个结点是否连接
     * 对于有向图，则检查从start到end的路径是否存在
     * @param start 起始结点
     * @param end 终止结点
     * @return 检查结果
     */
    boolean isConnect(T start, T end);

    /**
     * 向图中增加一个结点
     * 此结点不与图中任何其他结点连接
     * 如果图中已经存在该结点，则什么都不做
     * @param node 结点
     */
    void add(T node);

    /**
     * 向图中增加包含两个结点的一条边
     * 对于有向图，生成的边从start结点指向end结点
     * 图中不存在的结点将自动新增在图中
     * 有向图中，如果已经存在end指向start的边
     * 则将该单向边变为双向边
     * 如果已经存在start指向end的边，则什么都不做
     * @param start 线段开始结点
     * @param end 线段结束结点
     */
    void add(T start, T end);

    /**
     * 删除一个结点，同时删除该结点上所有的边
     * @param node 被删除的结点
     */
    void remove(T node);

    /**
     * 删除一条边
     * 注意，只删除边，start和end两个结点将保留
     * 在有向图中，只会删除指定方向的边
     * 如果start和end已经有双向边连接的关系
     * 则将双向边转化为单向边，方向为end指向start
     * @param start 边起始结点
     * @param end 边结束结点
     */
    void removeEdge(T start, T end);

    /**
     * 获取边的遍历器
     * @return 边的遍历器
     */
    Iterator<Edge<T>> edgeIterator();

    /**
     * 获取边的并行遍历器
     * @return 边的并行遍历器
     */
    Spliterator<Edge<T>> edgeSpliterator();

    /**
     * 边的foreach遍历器
     * @param edgeConsumer edge消费者
     */
    void foreach(Consumer<? super Edge<T>> edgeConsumer);

    /**
     * 图的边
     * 边由两个点构成
     * 分别称作start和end
     * 对于有向图，该边从start指向end
     * 双向边将由两条单向边表示
     * 对于无向图，start 和 end 的顺序由实现类决定
     * 对于调用方而言，可以认为是无序的
     * @param <T> 结点类型
     */
    interface Edge<T> {

        /**
         * 获取起始结点
         * @return 起始结点
         */
        T start();

        /**
         * 获取终止结点
         * @return 终止结点
         */
        T end();
    }
}
