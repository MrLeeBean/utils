package com.nookio.utils.http;

import com.alibaba.fastjson.JSON;
import com.nookio.utils.string.XmlUtil;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by nookio on 16/1/8.
 */
public class HttpUtil {

    public static Map<String, String> defaultHeader = new HashMap<String, String>();

    private static String ENCODING = "UTF-8";

    private static long HTTP_TIME_OUT = 10000l;

    private static Integer CONNECTION_TIME_OUT = 10000;

    private static Integer READ_TIME_OUT = 10000;


    public static String doGet(String veryfy_url, Map<String, String> headers) {
        return doPost(veryfy_url, null, headers);
    }

    public static String doPostAsJson(String url, Map<String, String> params, Map<String, String> headers){
        String data = JSON.toJSONString(params);
        String result = doPost(url, data, headers);
        return result;

    }

    public static Map<String, String> doPostAsXml(String url, Map<String, String> params, Map<String, String> headers){
        String data = XmlUtil.mapToXmlString(params, "root");
        String result = doPost(url, data, headers);
        return XmlUtil.decodeXmlToMap(result);
    }

    public static Map<String, String> doPostAsXml(String url, String xmlData, Map<String, String> headers){
        String result = doPost(url, xmlData, headers);
        return XmlUtil.decodeXmlToMap(result);
    }

    public static String doPost(String url, String data, Map<String, String> headers)  {
        URL requestUrl = null;
        HttpURLConnection connection = null;
        try {
            requestUrl = new URL(url);
            connection = createConnection(requestUrl, headers);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        String result = getRequest(connection, data, ENCODING);
        return result;
    }

    private static String getRequest(HttpURLConnection connection, String data,String encoding) {
        PrintStream out = null;
        InputStream in = null;
        StringBuilder sb = new StringBuilder(1024);
        String result = "";
        try{
            connection.connect();
            out = new PrintStream(connection.getOutputStream(), false, encoding);
            out.print(data);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.close();
        }

        try {
            if(200 == connection.getResponseCode()) {
                in = connection.getInputStream();
                sb.append(IOUtils.toString(in, encoding));
            } else {
                in = connection.getErrorStream();
                sb.append(IOUtils.toString(in, encoding));
            }
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(null != connection) {
                connection.disconnect();
            }
        }

        return result;
    }

    public static String doPostOnSSL(String url, String data, Map<String, String> headers){
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = createConnection(requestUrl, headers);
            if(null == connection) {
                throw new RuntimeException("创建联接失败");
            } else {
                String result = getRequest(connection, data, ENCODING);
                return  result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static HttpURLConnection createConnection(URL url, Map<String, String> header) throws ProtocolException {
        HttpURLConnection httpURLConnection = null;

        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
        } catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }

        httpURLConnection.setConnectTimeout(CONNECTION_TIME_OUT);
        httpURLConnection.setReadTimeout(READ_TIME_OUT);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        if (null == header) {
            header = new HashMap<String, String>();
            header.put("Content-type", "application/x-www-form-urlencoded;charset=" + ENCODING);
        }
        Iterator<String> headerIterator = header.keySet().iterator();
        while (headerIterator.hasNext()){
            String key = headerIterator.next();
            httpURLConnection.setRequestProperty(key, header.get(key));
        }
        httpURLConnection.setRequestMethod("POST");
        if("https".equalsIgnoreCase(url.getProtocol())) {
            HttpsURLConnection husn = (HttpsURLConnection)httpURLConnection;
            husn.setSSLSocketFactory(new BaseHttpSSLSocketFactory());
            husn.setHostnameVerifier(new BaseHttpSSLSocketFactory.TrustAnyHostnameVerifier());
            return husn;
        } else {
            return httpURLConnection;
        }
    }

    public static class BaseHttpSSLSocketFactory extends SSLSocketFactory {
        public BaseHttpSSLSocketFactory() {
        }

        private SSLContext getSSLContext() {
            return this.createEasySSLContext();
        }

        public Socket createSocket(InetAddress arg0, int arg1, InetAddress arg2, int arg3) throws IOException {
            return this.getSSLContext().getSocketFactory().createSocket(arg0, arg1, arg2, arg3);
        }

        public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3) throws IOException, UnknownHostException {
            return this.getSSLContext().getSocketFactory().createSocket(arg0, arg1, arg2, arg3);
        }

        public Socket createSocket(InetAddress arg0, int arg1) throws IOException {
            return this.getSSLContext().getSocketFactory().createSocket(arg0, arg1);
        }

        public Socket createSocket(String arg0, int arg1) throws IOException, UnknownHostException {
            return this.getSSLContext().getSocketFactory().createSocket(arg0, arg1);
        }

        public String[] getSupportedCipherSuites() {
            return null;
        }

        public String[] getDefaultCipherSuites() {
            return null;
        }

        public Socket createSocket(Socket arg0, String arg1, int arg2, boolean arg3) throws IOException {
            return this.getSSLContext().getSocketFactory().createSocket(arg0, arg1, arg2, arg3);
        }

        private SSLContext createEasySSLContext() {
            try {
                SSLContext e = SSLContext.getInstance("SSL");
                e.init((KeyManager[]) null, new TrustManager[]{BaseHttpSSLSocketFactory.MyX509TrustManager.manger}, (SecureRandom) null);
                return e;
            } catch (Exception var2) {
                var2.printStackTrace();
                return null;
            }
        }

        public static class TrustAnyHostnameVerifier implements HostnameVerifier {
            public TrustAnyHostnameVerifier() {
            }

            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }

        public static class MyX509TrustManager implements X509TrustManager {
            static BaseHttpSSLSocketFactory.MyX509TrustManager manger = new BaseHttpSSLSocketFactory.MyX509TrustManager();

            public MyX509TrustManager() {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }
    }

}
