import main.propertytaxcalculator.Main;
import main.propertytaxcalculator.service.TaxSummaryService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServerTests {

    private ConfigurableApplicationContext context;

    // TODO - Tests for invalid POST and GET requests

    @Test
    public void validGetRequestReturnsCorrectStatusCode() throws IOException {
        context = SpringApplication.run(Main.class);
        URL url = new URL("http://127.0.0.1:8080/");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        boolean success = connection.getResponseCode() == 200;
        context.close();
        Assertions.assertTrue(success);
    }
    @Test
    public void invalidGetRequestReturnsCorrectStatusCode() throws IOException {
        context = SpringApplication.run(Main.class);
        URL url = new URL("http://127.0.0.1:8080/nothing-here");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        boolean success = connection.getResponseCode() != 200;
        context.close();
        Assertions.assertTrue(success);
    }

    @Test
    public void validPostRequestReturnsCorrectStatusCode() throws IOException {
        context = SpringApplication.run(Main.class);
        HttpClient httpclient;
        HttpPost httpPost;
        ArrayList<NameValuePair> postParameters;
        httpclient = HttpClientBuilder.create().build();;
        httpPost = new HttpPost("http://127.0.0.1:8080/");
        postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("propertyValue", "1000000"));
        postParameters.add(new BasicNameValuePair("taxType", "lbbt"));
        httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
        HttpResponse response = httpclient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        context.close();
        Assertions.assertEquals(statusCode, (HttpStatus.SC_OK));
    }

    @Test
    public void validGetRequestResultsInValidJSON() throws IOException {
        String json = scrape("http://127.0.0.1:8080/search?propertyValue=1000000&taxType=lbbt");
        System.out.println("JSON" + json);
        Gson gson = new GsonBuilder().create();
        TaxSummaryService tss = gson.fromJson(json, TaxSummaryService.class);
        Assertions.assertEquals("78,350.00", tss.getTaxAmount());
    }

    private String scrape(String urlToProcess) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet requestGet = new HttpGet(urlToProcess);
        HttpResponse response = client.execute(requestGet);
        HttpEntity entity = response.getEntity();
        context.close();
        return EntityUtils.toString(entity, "UTF-8");
    }


}



