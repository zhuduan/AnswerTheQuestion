package utils;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 *         created at 2/7/18
 */
public class HttpConnectionUtil {
    
    private static final String RES_METHOD = "GET";
    private static final String RES_AGENT_INFO = "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)";
    
    private static final int TIME_OUT = 2000;
    
    private static final String PROXY_ADDR = "sheraton.h.timonit.cn";
    private static final int PROXY_PORT = 15944;
    private static final String PROXY_USER = "duotai";
    private static final String PROXY_PASS = "xTXQ8-Atp747-Br2dJV";

    public static InputStream getInfo(URL url){
        try {
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod(RES_METHOD);
            httpConn.setRequestProperty("User-Agent", RES_AGENT_INFO);
            httpConn.setConnectTimeout(TIME_OUT);
            
            // logger.info(httpConn.getResponseMessage());
            InputStream inputStream = httpConn.getInputStream();
            return inputStream;
        } catch (Exception exp){
            
        }
        return null;
    }

    public static InputStream getInfoWithProxy(URL url){
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_ADDR, PROXY_PORT));
            Authenticator authenticator = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return (new PasswordAuthentication(PROXY_USER, PROXY_PASS.toCharArray()));
                }
            };
            Authenticator.setDefault(authenticator);
            
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(proxy);
            httpConn.setFollowRedirects(true);
            httpConn.setRequestMethod(RES_METHOD);
            httpConn.setRequestProperty("User-Agent", RES_AGENT_INFO);
            httpConn.setConnectTimeout(TIME_OUT);

            // logger.info(httpConn.getResponseMessage());
            InputStream inputStream = httpConn.getInputStream();
            return inputStream;
        } catch (Exception exp){

        }
        return null;
    }
}
