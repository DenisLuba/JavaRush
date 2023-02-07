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
        String date = "21.4.2014 15:56:45";
        String formatDate = "\\d{1,2}.\\d{1,2}.\\d{4}";
        String formatTime = "\\d{1,2}:\\d{1,2}:\\d{1,2}";
        String pattern = "";
        pattern += date.matches(formatDate) ? "d.M.YYYY " : "";
        pattern += date.matches(formatTime) ? "H:m:s" : "";
        pattern = date.matches(formatDate + " " + formatTime) ? "d.M.YYYY H:m:s" : "";
        pattern = pattern.trim();
        System.out.println(pattern);
    }
}