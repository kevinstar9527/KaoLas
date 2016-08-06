package kaola.zhanchengguo.com.kaola.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 最底层专辑
 * Created by Administrator on 2016/6/12.
 */
public class R_Deeper {


    /**
     * type : 1
     * pic :
     * name : 国家台
     * id : 1
     */

    private int type;
    private String pic;
    private String name;
    private int id;

    public static R_Deeper objectFromData(String str) {

        return new Gson().fromJson(str, R_Deeper.class);
    }

    public static List<R_Deeper> arrayR_DeeperFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<R_Deeper>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
