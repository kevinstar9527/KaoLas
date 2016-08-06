package kaola.zhanchengguo.com.kaola.other.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/6/30.
 */
public class HistoryHelper extends SQLiteOpenHelper {

    private static final String DB_NAME= "history_db";

    private static final int VERSION = 1;

    public HistoryHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /**
     * 数据库创建
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(HistoryTable.table_sql);
    }

    /**
     * 数据库更新
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion == 1 && newVersion == 2)
        {
            //添加一个列
        }
    }

    public static class HistoryTable implements BaseColumns
    {
        public static final String TABLE_NAME = "history_table";

        public static final String COLUMNS_RNAME = "ranem";

        public static final String COLUMNS_DES = "des";

        public static final String COLUMNS_IMGE_URL="image_url";

        public static final String COLUMNS_MP3_URL = "mp3_url";

        public static final String COLUMNS_TIME = "time";

        public  static String table_sql = null;

        static {
            //创建表的sql语句
            StringBuffer buffer = new StringBuffer();
            buffer.append("create table " + TABLE_NAME + " (");
            buffer.append(_ID + " integer primary key autoincrement,");
            buffer.append(COLUMNS_RNAME + " varchar(50),");
            buffer.append(COLUMNS_DES + " varchar(50),");
            buffer.append(COLUMNS_IMGE_URL + " varchar(500),");
            buffer.append(COLUMNS_MP3_URL + " varchar(50),");
            buffer.append(COLUMNS_TIME + " long)");

            table_sql = buffer.toString();

        }
    }
}
