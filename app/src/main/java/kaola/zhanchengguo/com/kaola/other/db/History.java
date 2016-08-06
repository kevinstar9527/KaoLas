package kaola.zhanchengguo.com.kaola.other.db;

import kaola.zhanchengguo.com.kaola.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/30.
 */
public class History {

    private String rname;
    private String des;
    private String imagUrl;
    private String mp3Url;
    private long time;

    public History(String rname, String des, String imagUrl, String mp3Url, long time) {
        this.rname = rname;
        this.des = des;
        this.imagUrl = imagUrl;
        this.mp3Url = mp3Url;
        this.time = time;
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

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    /**
     * special转化为History
     * @param special
     * @return
     */
    public static History getHistoryBySpecial(Special special)
    {
        History history = new History(special.getRname(),special.getDes(),special.getWeburl(),special.getMp3PlayUrl(),special.getRid());
        return history;
    }
}
