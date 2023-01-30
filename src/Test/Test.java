package Test;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    String path = new File(this.getClass().getPackage().toString()).toString();
    public static void main(String[] args) {
        String line = "name=zapp&mood=good&locale=&id=777";
        List<NameValuePair> params = Arrays.stream(line.split("&"))
                .map(s -> new BasicNameValuePair(
                        s.split("=")[0],
                        s.split("=").length > 1 ?
                                s.split("=")[1] : ""
                )).collect(Collectors.toList());

        params.forEach(p -> System.out.println(p.getName() + " : " + p.getValue()));
    }
}