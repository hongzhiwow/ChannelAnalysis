package Download;

import Utils.IRLog;
import Utils.MD5;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * Created by ouhiroshi on 2016/11/23.
 */
public class DownloadCore {
    public static boolean downLoad(String urlString, String filePatch) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(urlString);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            InputStream in = entity.getContent();
            long length = entity.getContentLength();
            if (length <= 0) {
                IRLog.log(MD5.getMD5(urlString) + " 下载文件不存在！" + urlString);
                return false;
            }
            OutputStream out = new FileOutputStream(new File(filePatch));
            saveTo(in, out);
        } catch (IOException e) {
            IRLog.log(MD5.getMD5(urlString) + " 文件写入错误 ！" + e);
            return false;
        } catch (Exception e) {
            IRLog.log(MD5.getMD5(urlString) + " " + e);
            return false;
        }
        return true;
    }

    public static void saveTo(InputStream in, OutputStream out) throws Exception {
        byte[] data = new byte[1024 * 1024];
        int index = 0;
        while ((index = in.read(data)) != -1) {
            out.write(data, 0, index);
        }
        in.close();
        out.close();
    }
}
