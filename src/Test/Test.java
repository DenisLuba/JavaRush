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
        int c = 59;
        c = ++c == 61 ? 1 : c;
        System.out.println(c);
    }
}