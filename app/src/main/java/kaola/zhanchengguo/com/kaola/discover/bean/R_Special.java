package kaola.zhanchengguo.com.kaola.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 电台的专辑
 * Created by Administrator on 2016/6/12.
 */
public class R_Special {

    /**
     * weburl :
     * area : null
     * listenNum : 0
     * rtype : 11
     * followedNum : 0
     * albumName :
     * cornerMark : 3
     * rid : 1600000000510
     * host : []
     * pic : http://image.kaolafm.net/mz/images/201605/a9666b60-c81f-4e2c-8b3a-77d35c7f8173/default.jpg
     * num : 0
     * tip :
     * mp3PlayUrl :
     * rvalue : 1600000000510
     * rname : 央广中国之声
     * des : 中央人民广播电台中国之声
     */

    private String weburl;
    private Object area;
    private int listenNum;
    private int rtype;
    private int followedNum;
    private String albumName;
    private int cornerMark;
    private long rid;
    private String pic;
    private int num;
    private String tip;
    private String mp3PlayUrl;
    private String rvalue;
    private String rname;
    private String des;
    private List<?> host;


    /**
     * type : 1
     * typeName : 类型
     * dataList : []
     */

    private int type;
    private String typeName;
    private List<R_Deeper> dataList;



    /**
     * fansCount : 249
     * recommendReson : 资深情感节目主持人
     * isVanchor : 0
     * likedNum : 4
     * avatar : http://image.kaolafm.net/mz/images/201601/0dc7a2b7-e7f6-4aef-a09f-f79e093dcfcc/default.jpg
     * liveStatus : 0
     * relation : 0
     * gender : 3
     * extraAttributes :
     * desc : TA懒懒的什么也没写
     * nickName : 青音
     * uid : 2400521
     */

    private int fansCount;
    private String recommendReson;
    private int isVanchor;
    private int likedNum;
    private String avatar;
    private int liveStatus;
    private int relation;
    private int gender;
    private String extraAttributes;
    private String desc;
    private String nickName;
    private int uid;


    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public String getRecommendReson() {
        return recommendReson;
    }

    public void setRecommendReson(String recommendReson) {
        this.recommendReson = recommendReson;
    }

    public int getIsVanchor() {
        return isVanchor;
    }

    public void setIsVanchor(int isVanchor) {
        this.isVanchor = isVanchor;
    }

    public int getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(int likedNum) {
        this.likedNum = likedNum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(int liveStatus) {
        this.liveStatus = liveStatus;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getExtraAttributes() {
        return extraAttributes;
    }

    public void setExtraAttributes(String extraAttributes) {
        this.extraAttributes = extraAttributes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }






    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<R_Deeper> getDataList() {
        return dataList;
    }

    public void setDataList(List<R_Deeper> dataList) {
        this.dataList = dataList;
    }



    public static R_Special objectFromData(String str) {

        return new Gson().fromJson(str, R_Special.class);
    }

    public static List<R_Special> arrayR_SpeicalFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<R_Special>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public int getListenNum() {
        return listenNum;
    }

    public void setListenNum(int listenNum) {
        this.listenNum = listenNum;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }

    public int getFollowedNum() {
        return followedNum;
    }

    public void setFollowedNum(int followedNum) {
        this.followedNum = followedNum;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getCornerMark() {
        return cornerMark;
    }

    public void setCornerMark(int cornerMark) {
        this.cornerMark = cornerMark;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getMp3PlayUrl() {
        return mp3PlayUrl;
    }

    public void setMp3PlayUrl(String mp3PlayUrl) {
        this.mp3PlayUrl = mp3PlayUrl;
    }

    public String getRvalue() {
        return rvalue;
    }

    public void setRvalue(String rvalue) {
        this.rvalue = rvalue;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<?> getHost() {
        return host;
    }

    public void setHost(List<?> host) {
        this.host = host;
    }
}
