package ru.kds.util;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 */
public class TagsHelper {
    public static final String SEPARATOR = ",";

    public static SortedSet<String> parseTags(String tagString){
        SortedSet<String> tags = new TreeSet<String>();
        if (tagString == null || tagString.isEmpty()) return tags;

        String[] tmp = tagString.split(SEPARATOR);
        for (String s : tmp) {
            String t = s.trim();
            if (t.length() < 1) {
                t = null;
            }
            if (t != null){
                tags.add(t);
            }
        }
        return tags;
    }

    public static String toString(SortedSet<String> tags){
        if (tags.size() == 0) return null;
        StringBuilder b = new StringBuilder();
        for (Iterator<String> it = tags.iterator(); it.hasNext();) {
            b.append(it.next());
            if (it.hasNext()){
                b.append(SEPARATOR).append(" ");
            }
        }
        return b.toString();
    }
}
