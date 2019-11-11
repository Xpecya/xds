package com.xpecya.xds;

/**
 * 树
 * 树是一种特殊的有向图
 * 从根指向叶子
 * 树这种图的特性在于，存在层级的关系
 * 边由上级结点指向下级节点
 * 同层的结点之间一般不连接（B+树这个异端烧掉）
 * jdk针对红黑树做了treeMap, TreeSet, 在HashMap里也做了个红黑树
 * 竟然没有一个抽象的Tree接口
 * 不可思议，无法理解
 * fuck jcp
 */
public interface Tree<T> extends DirectedGraph<T> {
}
