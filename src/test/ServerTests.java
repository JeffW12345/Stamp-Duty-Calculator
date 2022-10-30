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
        ArrayList<NameValuePair> postParameters;
        HttpClient httpclient = HttpClientBuilder.create().build();;
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/");
        postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("propertyValue", "1000000"));
        postParameters.add(new BasicNameValuePair("taxType", "lbbt"));
        httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
        HttpResponse response = httpclient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        context.close();
        Assertions.assertEquals(HttpStatus.SC_OK, statusCode);
    }

    @Test
    public void invalidPostRequestReturnsCorrectStatusCode() throws IOException {
        context = SpringApplication.run(Main.class);
        ArrayList<NameValuePair> postParameters;
        HttpClient httpclient = HttpClientBuilder.create().build();;
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/");
        postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("propertyValue", "1000000"));
        postParameters.add(new BasicNameValuePair("taxType", "random"));
        httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
        HttpResponse response = httpclient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        context.close();
        Assertions.assertEquals(500, statusCode);
    }

    @Test
    public void validGetRequestResultsInValidJSON() throws IOException {
        String json = scrape("http://127.0.0.1:8080/search?propertyValue=1000000&taxType=lbbt");
        TaxSummaryService tss = new GsonBuilder().create().fromJson(json, TaxSummaryService.class);
        Assertions.assertEquals("78,350.00", tss.getTaxAmount());
    }

    @Test
    public void invalidGetRequestResultsInNoTaxInfo() throws IOException {
        String json = scrape("http://127.0.0.1:8080/search?propertyValue=1000000&taxType=none");
        TaxSummaryService tss = new GsonBuilder().create().fromJson(json, TaxSummaryService.class);
        Assertions.assertNull(tss.getTaxAmount());
    }

    @Test
    public void invalidGetRequestResultsIn500Error() throws IOException {
        String url = "http://127.0.0.1:8080/search?propertyValue=1000000&taxType=none";
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet requestGet = new HttpGet(url);
        HttpResponse response = client.execute(requestGet);
        int statusCode = response.getStatusLine().getStatusCode();
        context.close();
        Assertions.assertEquals(500, statusCode);
    }

    @Test
    public void validGetRequestResultsIn200StatusCode() throws IOException {
        String url = "http://127.0.0.1:8080/search?propertyValue=1000000&taxType=lbbt";
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet requestGet = new HttpGet(url);
        HttpResponse response = client.execute(requestGet);
        int statusCode = response.getStatusLine().getStatusCode();
        context.close();
        Assertions.assertEquals(200, statusCode);
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



