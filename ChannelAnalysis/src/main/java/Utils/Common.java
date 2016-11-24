package Utils;

/**
 * Created by ouhiroshi on 2016/11/23.
 */
public class Common {
    /**
     * The constant URL.
     */
    public static final String DATA_BASE_URL = "jdbc:mysql://192.168.10.247:3306/sb250?useUnicode=true&characterEncoding=UTF8&useSSL=false";

    /**
     * The constant SOURCE_FILE_PATCH.
     */
/*Excel 文件所在路径*/
    public static final String  SOURCE_FILE_PATCH = "/Users/ouhiroshi/Desktop/apks/SRC/MST需求.xlsx";
    /**
     * The constant SOURCE_FILE_SHEET_NAME.
     */
    public static final String  SOURCE_FILE_SHEET_NAME = "url";

    /**
     * The constant APKS_FILE_PATCH.
     */
/*下载apks文件所在路径*/
    public static final String  APKS_FILE_PATCH = "/Users/ouhiroshi/Desktop/apks/APKS/";

    /**
     * The constant LOG_FILE_PATCH.
     */
    public static final String  LOG_FILE_PATCH = "/Users/ouhiroshi/Desktop/apks/Log/";


    /**
     * The constant APK_MAINIFEST.
     */
    public static final String APK_MAINIFEST = "AndroidManifest.xml";
    /**
     * The constant APK_VALUES.
     */
    public static final String APK_VALUES = "/res/values/strings.xml";
    /**
     * The constant APK_VALUES_RCN.
     */
    public static final String APK_VALUES_RCN = "/res/values-zh-rCN/strings.xml";

    /**
     * The constant CONTAIN_MAX_COUNT.
     */
/*设置最大处理数量*/
    public static final int CONTAIN_MAX_COUNT = 2;
    /**
     * The constant DONWLOAD_MAX_COUNT.
     */
    public static final int DONWLOAD_MAX_COUNT = 2;
    /**
     * The constant DECODE_MAX_COUNT.
     */
    public static final int DECODE_MAX_COUNT = 2;
}
