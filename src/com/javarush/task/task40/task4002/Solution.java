package com.javarush.task.task40.task4002;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("https://eolhkpz1d4algis.m.pipedream.net", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
//        Создаем объект HTTP-клиента, который отправляет серверу и получает от сервера HTTP сообщения
        HttpClient client = getHttpClient();
//        Создаем объект запроса POST
        HttpPost request = new HttpPost(url);
//        Получаем список из параметров, содержащий имена и значения этих параметров
        List<NameValuePair> parameters = Arrays.stream(urlParameters.split("&"))
                .map(urlParameter -> new BasicNameValuePair(
                        urlParameter.split("=")[0],
                        urlParameter.split("=").length > 1 ?
                                urlParameter.split("=")[1] : ""
                )).collect(Collectors.toList());
//        Добавляем параметры в объект запроса POST
        request.setEntity(new UrlEncodedFormEntity(parameters));
//        Добавляем заголовок в запрос
        request.addHeader("User-Agent", "Mozila/5.0");
//        Создаем объект-результат выполнения клиентом запроса POST
        HttpResponse response = client.execute(request);


        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
//
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result);
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}