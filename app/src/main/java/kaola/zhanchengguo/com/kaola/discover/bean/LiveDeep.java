package kaola.zhanchengguo.com.kaola.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
public class LiveDeep {




    /**
     * des :
     * img :
     * name : 崔子恩
     */

    private String des;
    private String img;
    private String name;


    /**
     * weekDay : 后天
     * dateTime : 06月23日
     * time : 2016-06-23
     */

    private String weekDay;
    private String dateTime;
    private String time;



    public static LiveDeep objectFromData(String str) {

        return new Gson().fromJson(str, LiveDeep.class);
    }

    public static List<LiveDeep> arrayLiveDeepFromData(String str) {

        Type listType = new TypeToken<ArrayList<LiveDeep>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
