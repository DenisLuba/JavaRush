package JSoupExample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

public class HTMLParser {
    public static void main(String args[]) throws IOException {

        // Parse HTML String using JSoup library

        String HTMLString = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>JSoup Example</title>"
                + "</head>"
                + "<body>"
                + "<table><tr><td><h1>Hello World</h1></tr>"
                + "</table>"
                + "</body>"
                + "</html>";

        Document html = Jsoup.parse(HTMLString);
        String title = html.title();
        String h1 = html.body().getElementsByTag("h1").text();

        System.out.println("Input HTML String to JSoup : " + HTMLString);
        System.out.println("After parsing, Title : " + title);
        System.out.println("After parsing, Heading : " + h1 + "\n");

        // JSoup Example 2 - Reading HTML page from URL

        Document doc;
        try {
            doc = Jsoup.connect("http://google.com/").get();
            title = doc.title();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Jsoup can read HTML page from URL, title : " + title + "\n");

        // JSoup Example 3 - Parsing an HTML file in Java
        // Document htmlFile = Jsoup.parse("login.html", "ISO-8854-1"); // wrong
        Document htmlFile = null;
        try {
            htmlFile = Jsoup.parse(new File("login.html"), "ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert htmlFile != null;
        title = htmlFile.title();
        Element div = htmlFile.getElementById("login");
        String cssClass = div.className(); // getting class from HTML element

        System.out.println("Jsoup can also parse HTML file directly");
        System.out.println("title : " + title);
        System.out.println("class of div tag : " + cssClass);
    }
}
