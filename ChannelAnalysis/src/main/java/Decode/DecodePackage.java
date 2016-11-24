package Decode;

import Core.FileConsumer;
import Utils.Common;
import Utils.IRLog;
import brut.androlib.AndrolibException;
import brut.androlib.ApkDecoder;
import brut.androlib.res.data.ResPackage;
import brut.directory.DirectoryException;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ouhiroshi on 2016/11/16.
 */
public class DecodePackage {

    /**
     * Decode android manifest.
     *
     * @param filePatch the file patch
     * @return the package name
     */
    public static String getPackageName(String filePatch) {
        Element root = getRootElement(filePatch);
        /*
        * 解析包名
        */
        Attribute packageAttribute = root.attribute("package");
        String packageName = packageAttribute.getText();
        return packageName;
    }

    /**
     * Gets application name.
     *
     * @param filePatch the file patch
     * @return the application name
     */
    public static String getApplicationName(String filePatch) {
        Element root = getRootElement(filePatch);
        Element applicationElement = root.element("application");
        Attribute labelAttribute = applicationElement.attribute("label");
        String labelName = labelAttribute.getText();
        if (labelName.length() > 0) {
            labelName = labelName.split("/")[1];
            return decodeResStringsFile(filePatch, labelName);
        } else {
            return null;
        }
    }


    /**
     * View directory.
     *
     * @param filePatch the file patch
     * @param resPatch  the res patch
     * @return the string
     */
    private static String decodeResStringsFile(String filePatch, String resPatch) {
        String cnStringsPatch = filePatch + Common.APK_VALUES_RCN;
        String normalStringsPatch = filePatch + Common.APK_VALUES;
        String appName = private_getAppName(cnStringsPatch, resPatch);
        if (appName != null)
            return appName;
        return private_getAppName(normalStringsPatch, resPatch);
    }

    /**
     * Kernel code boolean.
     *
     * @param stringsPatch the strings patch
     * @param resPatch     the res patch
     */
    private static String private_getAppName(String stringsPatch, String resPatch) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(stringsPatch));
            Element root = document.getRootElement();
            List nodes = root.elements();
            for (Iterator it = nodes.iterator(); it.hasNext(); ) {
                Element element = (Element) it.next();
                Attribute attribute = element.attribute("name");
                /*
                * 判断渠道的属性名字是否与resPatch 相等如果相等则符合标准
                */
                if (resPatch.equals(attribute.getText())) {
                    String appName = element.getTextTrim();
                    return appName;
                }
            }
        } catch (DocumentException e) {
            IRLog.log(stringsPatch + " " + e);
            return null;
        }
        return null;
    }

    /**
     * Gets file size.
     *
     * @param filePatch the file patch
     * @return the file size
     */
    public static String getPackageSize(String filePatch) {
        File file = new File(filePatch + ".apk");
        String fileSize = "0";
        if (file.exists() && file.isFile()) {
            double dsize = (double) file.length();
            double size = dsize / 1024 / 1024;
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
            fileSize = df.format(size);
        }
        return fileSize;
    }

    private static Element getRootElement(String filePatch) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            String xmlPatch = filePatch + "/" + Common.APK_MAINIFEST;
            document = reader.read(new File(xmlPatch));
        } catch (DocumentException e) {
            IRLog.log(filePatch + " " + e);
        }
        Element root = document.getRootElement();
        return root;
    }

    /**
     * Decode file boolean.
     *
     * @param filePatch the file patch
     * @return the boolean
     */
    public static boolean decodeFile(String filePatch) {
        File inFile = new File(filePatch + ".apk");
        File outFile = new File(filePatch);
        ApkDecoder apkDecoder = new ApkDecoder();
        try {
            apkDecoder.setKeepBrokenResources(true);
            apkDecoder.setBaksmaliDebugMode(false);
            apkDecoder.setOutDir(outFile);
            apkDecoder.setApkFile(inFile);
            apkDecoder.decode();
        } catch (AndrolibException e) {
            IRLog.log(filePatch + " " + e);
            return false;
        } catch (IOException e) {
            IRLog.log(filePatch + " " + e);
            return false;
        } catch (DirectoryException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
