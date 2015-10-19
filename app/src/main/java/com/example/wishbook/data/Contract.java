package com.example.wishbook.data;

import android.provider.BaseColumns;

/**
 * Created by manaday on 18/10/15.
 */
public class Contract {

    public static abstract class WishEntry implements BaseColumns {

        public static final String TABLE_NAME = "wish";

        public static final String COLUMN_WISH_ID = "_id";

        public static final String COLUMN_WISH_TITLE = "title";

        public static final String COLUMN_WISH_DESC = "description";
    }
}
