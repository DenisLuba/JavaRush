package Test;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    String path = new File(this.getClass().getPackage().toString()).toString();
    public static void main(String[] args) throws MalformedURLException {
        String line = "https://javarush.com/quests/lectures/questcollections.level10.lecture07";
        URL url = new URL(line);
        System.out.println(url.getPath());
        System.out.println(url.getFile());
        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getDefaultPort());
        System.out.println(url.getProtocol());
        System.out.println(url.getUserInfo());
    }
}