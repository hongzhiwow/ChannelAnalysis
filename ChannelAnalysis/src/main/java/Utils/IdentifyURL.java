package Utils;

/**
 * Created by ouhiroshi on 2016/11/17.
 */
public class IdentifyURL {

    /**
     * Instantiates a new String.
     *
     * @param urlString the url string
     * @return the string
     */
    public static String handleURLString(String urlString) {
        if (urlString.contains("dd.myapp.com")) {
            return "应用宝";
        }
        if (urlString.contains("gdown.baidu.com/data/wisegame")) {
            return "百度手机助手 || 安卓市场";
        }
        if (urlString.contains("m.shouji.360tpcdn.com")) {
            return "360手机助手";
        }
        if (urlString.contains("appdl.hicloud.com/dl/appdl/application/apk")) {
            return "华为应用市场";
        }
        if (urlString.contains("storedl1.nearme.com.cn/uploadFiles/Pfiles")) {
            return "可可软件商店";
        }
        if (urlString.contains("apis.wandoujia.com/apps/v1/")) {
            return "豌豆荚";
        }
        if (urlString.contains("apk1.cdn.anzhi.com/data2/apk")){
            return "安智市场";
        }
        if (urlString.contains("dl.zhushou.sogou.com/open/files/")) {
            return "搜狗市场";
        }
        if (urlString.contains("dl.zhushou.sogou.com/oglxr/open/files/")) {
            return "搜狗手机助手";
        }
        if (urlString.contains("apk1.mmarket.com/rs/")){
            return "Mobile Market移";
        }
        if (urlString.contains("estoredwnld7.189store.com/static/apks/")){
            return "天翼空间";
        }
        if (urlString.contains("coopbig/adown.myaora.net/test/upload/open/")) {
            return "易用汇";
        }
        if (urlString.contains("apk.lenovomm.com")){
            return "乐商店";
        }
        if (urlString.contains("dl3.wostore.cn")){
            return "沃商店";
        }
        if (urlString.contains("yingyonghui.com/")){
            return "APP CHINA应用汇";
        }
        if (urlString.contains("app.17wo.cn/download/Apps")){
            return "一起沃";
        }
        if (urlString.contains("ucdl.25pp.com")){
            return "淘宝手机助手";
        }
        if (urlString.contains("apk.r1.market.hiapk.com/data/upload/apkres")){
            return "中兴应用商店";
        }
        if (urlString.contains("app.2345.cn/appsoft/")){
            return "2345应用宝";
        }
        return "无";
    }
}




