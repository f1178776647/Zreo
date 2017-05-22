package com.fz.zreo.utils;

import android.util.LruCache;

/**
 * Created by Zero on 2017/5/22.
 */

public class ImgLrcChche extends LruCache {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public ImgLrcChche(int maxSize) {
        super(maxSize);
    }
}
