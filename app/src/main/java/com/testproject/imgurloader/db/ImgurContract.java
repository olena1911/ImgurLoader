package com.testproject.imgurloader.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ImgurContract {

    public static final String CONTENT_AUTHORITY = "com.testproject.imgurloader";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_LINKS = "links";

    private ImgurContract() {
    }

    public static class LinksEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LINKS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LINKS;

        public static final String TABLE_NAME = "links";

        public final static String _ID = BaseColumns._ID;
        public static final String COLUMN_LINK = "link";
    }

}
