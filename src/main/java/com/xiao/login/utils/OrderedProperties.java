package com.xiao.login.utils;

import java.util.*;

/**
 * 有序properties
 *
 * @author xiao_elevener
 * @date 2017-12-20 1:17
 */
public class OrderedProperties extends Properties {

    private static final long serialVersionUID = -4627607243846121965L;

    private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

    @Override
    public Enumeration<Object> keys() {
        return Collections.<Object>enumeration(keys);
    }

    @Override
    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    @Override
    public Set<Object> keySet() {
        return keys;
    }

    @Override
    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<String>();

        for (Object key : this.keys) {
            set.add((String) key);
        }

        return set;
    }

    public LinkedHashMap<String, String> getLinkedMap() {
        if (this.keys.size() == 0) {
            return null;
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (Object key : this.keys
                ) {
            map.put((String) key, (String) get(key));
        }
        return map;
    }

}
