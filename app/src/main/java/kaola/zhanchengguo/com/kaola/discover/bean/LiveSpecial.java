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
public class LiveSpecial {

    /**
     * weburl :
     * area : null
     * listenNum : 0
     * rtype : 5
     * followedNum : 0
     * albumName :
     * cornerMark : 0
     * rid : 1516304033
     * host : []
     * pic : http://image.kaolafm.net/mz/images/201606/18f84259-4dff-48d0-b47d-7e915eaaebde/default.jpg
     * num : 0
     * tip :
     * mp3PlayUrl :
     * rvalue : 1516304033
     * rname : 崔子恩 做客直播间专访
     * des : 崔子恩 做客直播间专访
     */

    private String weburl;
    private Object area;
    private int listenNum;
    private int rtype;
    private int followedNum;
    private String albumName;
    private int cornerMark;
    private long  rid;
    private String pic;
    private int num;
    private String tip;
    private String mp3PlayUrl;
    private String rvalue;
    private String rname;
    private String des;
    private List<LiveDeep> host;




    /**
     * fansCount : 606
     * recommendReson :
     * isVanchor : 0
     * likedNum : 1088012
     * avatar : http://image.kaolafm.net/mz/images/201510/b1b0765d-fd3c-40a4-ac83-3c6ace2e2beb/default.jpg
     * liveStatus : 0
     * relation : 0
     * gender : 0
     * extraAttributes :
     * desc : TA懒懒的什么也没写
     * nickName : 疯狂直播间
     * uid : 2205009
     */

    private int fansCount;
    private String recommendReson;
    private int likedNum;
    private int liveStatus;
    private int relation;
    private String extraAttributes;
    private String desc;
    private String nickName;



    /**
     * dataList : []
     * dateDto : {}
     */

    private List<LiveDeep> dataList;


    public List<LiveDeep> getDataList() {
        return dataList;
    }

    public void setDataList(List<LiveDeep> dataList) {
        this.dataList = dataList;
    }



    /**
     * roomId : 209120
     * period : 2
     * isVanchor : 1
     * showStartTime : 已结束
     * liveDesc : 唐映红：警惕人格里的“潜在法西斯”
     * uid : 2897020
     * programName : 警惕人格裡的『潛在法西斯』
     * programDesc : 警惕人格裡的『潛在法西斯』
     * endtime : 2016-05-30 22:02:52
     * backLiveUrl : http://image.kaolafm.net/liveback/2016/05/30/387b4c0f-1cb7-4d5a-a9d4-6d5cc2ed8e19/playlist.m3u8
     * guests :
     * shareUrl : http://m.kaolafm.com/share/liveplay/index.html
     * gender : 0
     * isCanSubscribe : 0
     * bgColor :
     * liveId : 1454585994
     * startTime : 1464613200000
     * pushHost : bj.ugclbs.kaolafm.com
     * programPic : http://image.kaolafm.net/mz/images/201605/a82b5f51-be3f-4df0-9c22-9e822202a125/default.jpg
     * avatar : http://image.kaolafm.net/mz/images/201605/3ad516c3-4f5f-49e8-8158-53694320bfb9/default.jpg
     * lockType : 0
     * isAlreadyFollowed : 0
     * liveName : Noel的私人直播
     * accessKey : aaf30a55ec51aa6f
     * isAlreadySubscribe : 0
     * programSharedNum : 11
     * programFollowedNum : 29
     * liveUrl : http://ugclbs.kaolafm.com/aaf30a55ec51aa6f/1454585994_1560001578/playlist.m3u8
     * duration : 3772000
     * canPlayBack : 1
     * status : 0
     * onLineNum : 5137
     * finshTime : 1464616972000
     * programLikedNum : 8088
     * livePic : http://image.kaolafm.net/mz/images/201605/a82b5f51-be3f-4df0-9c22-9e822202a125/default.jpg
     * serveTime : 1466509982811
     * comperes : Noel
     * subscribeNum : 0
     * albumId : 0
     * programId : 1560001578
     * begintime : 2016-05-30 21:00:00
     * timeLength : 01:02:52
     */

    private int roomId;
    private int period;
    private int isVanchor;
    private String showStartTime;
    private String liveDesc;
    private int uid;
    private String programName;
    private String programDesc;
    private String endtime;
    private String backLiveUrl;
    private String guests;
    private String shareUrl;
    private int gender;
    private int isCanSubscribe;
    private String bgColor;
    private long liveId;
    private long startTime;
    private String pushHost;
    private String programPic;
    private String avatar;
    private int lockType;
    private int isAlreadyFollowed;
    private String liveName;
    private String accessKey;
    private int isAlreadySubscribe;
    private int programSharedNum;
    private int programFollowedNum;
    private String liveUrl;
    private int duration;
    private int canPlayBack;
    private int status;
    private int onLineNum;
    private long finshTime;
    private int programLikedNum;
    private String livePic;
    private long serveTime;
    private String comperes;
    private int subscribeNum;
    private long albumId;
    private long programId;
    private String begintime;
    private String timeLength;


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getIsVanchor() {
        return isVanchor;
    }

    public void setIsVanchor(int isVanchor) {
        this.isVanchor = isVanchor;
    }

    public String getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
    }

    public String getLiveDesc() {
        return liveDesc;
    }

    public void setLiveDesc(String liveDesc) {
        this.liveDesc = liveDesc;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDesc() {
        return programDesc;
    }

    public void setProgramDesc(String programDesc) {
        this.programDesc = programDesc;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getBackLiveUrl() {
        return backLiveUrl;
    }

    public void setBackLiveUrl(String backLiveUrl) {
        this.backLiveUrl = backLiveUrl;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getIsCanSubscribe() {
        return isCanSubscribe;
    }

    public void setIsCanSubscribe(int isCanSubscribe) {
        this.isCanSubscribe = isCanSubscribe;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public long getLiveId() {
        return liveId;
    }

    public void setLiveId(long liveId) {
        this.liveId = liveId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getPushHost() {
        return pushHost;
    }

    public void setPushHost(String pushHost) {
        this.pushHost = pushHost;
    }

    public String getProgramPic() {
        return programPic;
    }

    public void setProgramPic(String programPic) {
        this.programPic = programPic;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLockType() {
        return lockType;
    }

    public void setLockType(int lockType) {
        this.lockType = lockType;
    }

    public int getIsAlreadyFollowed() {
        return isAlreadyFollowed;
    }

    public void setIsAlreadyFollowed(int isAlreadyFollowed) {
        this.isAlreadyFollowed = isAlreadyFollowed;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public int getIsAlreadySubscribe() {
        return isAlreadySubscribe;
    }

    public void setIsAlreadySubscribe(int isAlreadySubscribe) {
        this.isAlreadySubscribe = isAlreadySubscribe;
    }

    public int getProgramSharedNum() {
        return programSharedNum;
    }

    public void setProgramSharedNum(int programSharedNum) {
        this.programSharedNum = programSharedNum;
    }

    public int getProgramFollowedNum() {
        return programFollowedNum;
    }

    public void setProgramFollowedNum(int programFollowedNum) {
        this.programFollowedNum = programFollowedNum;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCanPlayBack() {
        return canPlayBack;
    }

    public void setCanPlayBack(int canPlayBack) {
        this.canPlayBack = canPlayBack;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOnLineNum() {
        return onLineNum;
    }

    public void setOnLineNum(int onLineNum) {
        this.onLineNum = onLineNum;
    }

    public long getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(long finshTime) {
        this.finshTime = finshTime;
    }

    public int getProgramLikedNum() {
        return programLikedNum;
    }

    public void setProgramLikedNum(int programLikedNum) {
        this.programLikedNum = programLikedNum;
    }

    public String getLivePic() {
        return livePic;
    }

    public void setLivePic(String livePic) {
        this.livePic = livePic;
    }

    public long getServeTime() {
        return serveTime;
    }

    public void setServeTime(long serveTime) {
        this.serveTime = serveTime;
    }

    public String getComperes() {
        return comperes;
    }

    public void setComperes(String comperes) {
        this.comperes = comperes;
    }

    public int getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(int subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength;
    }

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


    public int getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(int likedNum) {
        this.likedNum = likedNum;
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







    public static LiveSpecial objectFromData(String str) {

        return new Gson().fromJson(str, LiveSpecial.class);
    }

    public static LiveSpecial objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getJSONObject(key).toString(), LiveSpecial.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<LiveSpecial> arrayLiveSpecialFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<LiveSpecial>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<LiveSpecial> arrayLiveSpecialFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<LiveSpecial>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getJSONArray(key).toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


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

    public List<LiveDeep> getHost() {
        return host;
    }

    public void setHost(List<LiveDeep> host) {
        this.host = host;
    }
}
