package kaola.zhanchengguo.com.kaola.other.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/6/30.
 */
public class HistoryManager {

    private HistoryHelper helper;
    private SQLiteDatabase db;

    private static Context mContext;

    private static HistoryManager manager;

    /**
     * 获取manager单例
     * @return
     */
    public static HistoryManager getInstance()
    {
        if (manager == null)
        {
            synchronized (HistoryManager.class)
            {
                if(manager == null)
                {
                    manager = new HistoryManager(mContext);
                }
            }
        }

        return  manager;
    }

    /**
     * 初始化数据库
     * @param context
     */
    public static void init(Context context)
    {
        mContext = context;
    }


    private HistoryManager(Context contex)
    {
        helper = new HistoryHelper(contex);

    }

    /**
     *  添加一条播放历史
     * @param history
     */
    public void addHistory(History history)
    {
        db = helper.getWritableDatabase();
        db.insert(HistoryHelper.HistoryTable.TABLE_NAME,null,getContentValueByHistory(history));
        //数据库操作完成后记得关闭
        db.close();
    }
    private ContentValues getContentValueByHistory(History history) {

        ContentValues values = new ContentValues();
        values.put(HistoryHelper.HistoryTable.COLUMNS_RNAME,history.getRname());


        return values;
    }

    /**
     * 删除一条记录
     * @param history
     */
    public void deleteHistory(History history)
    {
        db = helper.getWritableDatabase();//可写的数据库操作
        db.delete(HistoryHelper.HistoryTable.TABLE_NAME,
                "where" + HistoryHelper.HistoryTable.COLUMNS_MP3_URL + " = ?",
                new String[]{history.getMp3Url()} );

        db.close();
    }


}
