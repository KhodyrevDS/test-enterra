package ru.kds.util;

import java.util.List;

/**
 *
 */
public class PagedList<T> {

    private List<T> list;

    private int total;

    public PagedList(List<T> list, int total) {
        this.list = list;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public int getTotal() {
        return total;
    }
}
