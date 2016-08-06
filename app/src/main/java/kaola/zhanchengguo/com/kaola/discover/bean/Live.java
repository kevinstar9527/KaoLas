package kaola.zhanchengguo.com.kaola.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
public class Live {

    /**
     * contentType : 1
     * relatedValue : 0
     * icon :
     * id : 346
     * componentType : 13
     * desc :
     * pic :
     * dataList : []
     * moreType : 1
     * count : 0
     * contentSourceId : 6
     * name : 滚动banner
     * hasmore : 0
     */

    private int contentType;
    private String relatedValue;
    private String icon;
    private int id;
    private int componentType;
    private String desc;
    private String pic;
    private int moreType;
    private int count;
    private int contentSourceId;
    private String name;
    private int hasmore;
    private List<LiveSpecial> dataList;

    public static Live objectFromData(String str) {

        return new Gson().fromJson(str, Live.class);
    }

    public static Live objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), Live.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Live> arrayLiveFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<Live>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Live> arrayLiveFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Live>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getRelatedValue() {
        return relatedValue;
    }

    public void setRelatedValue(String relatedValue) {
        this.relatedValue = relatedValue;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComponentType() {
        return componentType;
    }

    public void setComponentType(int componentType) {
        this.componentType = componentType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getMoreType() {
        return moreType;
    }

    public void setMoreType(int moreType) {
        this.moreType = moreType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getContentSourceId() {
        return contentSourceId;
    }

    public void setContentSourceId(int contentSourceId) {
        this.contentSourceId = contentSourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHasmore() {
        return hasmore;
    }

    public void setHasmore(int hasmore) {
        this.hasmore = hasmore;
    }

    public List<LiveSpecial> getDataList() {
        return dataList;
    }

    public void setDataList(List<LiveSpecial> dataList) {
        this.dataList = dataList;
    }

    /**
     * 直播页面显示的item内容所对应的类型常量
     */
    public static class ComponentType
    {
        public static final int TYPE_NOW_LIVE = 14;

        public static final int TYPE_BANNER = 13;

        public static final int TYPE_IMAG = 16;

        public static final int TYPE_LINE = 36;

        public static final int TYPE_HOT = 18;

        public static final int TYPE_FORESHOW = 35;



    }
}
