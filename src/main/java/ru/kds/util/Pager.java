package ru.kds.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class Pager<T> implements Iterable<T> {

    private List<T> data;

    private int page;

    private int perPage;

    private int total;

    public Pager(List<T> data, int page, int perPage, int total) {
        this.data = data;
        this.page = page;
        this.perPage = perPage;
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotalPages() {
        return perPage == 0 ? 1 : (int) Math.ceil((double) total / (double) perPage);
    }

    public boolean hasPreviousPage() {
        return getPage() > 0;
    }

    public boolean hasNextPage() {
        return getPage() + 1 < getTotalPages();
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }
}
