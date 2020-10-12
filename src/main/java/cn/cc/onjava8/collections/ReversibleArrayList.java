package cn.cc.onjava8.collections;// collections/AdapterMethodIdiom.java
// The "Adapter Method" idiom uses for-in
// with additional kinds of Iterables

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

class ReversibleArrayList<T> extends ArrayList<T> {
    ReversibleArrayList(Collection<T> c) {
        super(c);
    }
    public Iterable<T> reversed() {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                // 重写这个接口的三个方法
                return new Iterator<T>() {
                    // 因为在使用遍历的时候 就是使用了这三个方法  ，先用hasNext 判断时候有下一个，如果有的话用next，循环取出所有元素 --remove会删除next取出的的元素 --最后一个会使用remove
                    int current = size() - 1;
                    public boolean hasNext() {
                        return current > -1;
                    }
                    public T next() { return get(current--); }
                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}