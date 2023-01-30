package com.javarush.task.task40.task4011;

import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URL;

/*
- protocol
- authority
- file
- host
- path
- port
- default port
- query
- ref

or an error
 */

public class Solution {
    public static void main(String[] args) throws IOException {
        decodeURLString("https://www.amrood.com/index.htm?language=en#j2se");
    }

    public static void decodeURLString(String s) {
        try {
            URL url = new URL(s);
            String builder = url.getProtocol() + '\n' +
                    url.getAuthority() + '\n' +
                    url.getFile() + '\n' +
                    url.getHost() + '\n' +
                    url.getPath() + '\n' +
                    url.getPort() + '\n' +
                    url.getDefaultPort() + '\n' +
                    url.getQuery() + '\n' +
                    url.getRef();

            System.out.println(builder);
        } catch(MalformedURLException e) {
            System.out.println("Parameter " + s + " is not a valid URL.");
        }

    }
}
