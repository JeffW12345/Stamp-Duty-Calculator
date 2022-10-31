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
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.GsonBuilder;

public class ServerTests {

    private ConfigurableApplicationContext context;

    @Test
    public void homePageUrlReturns200StatusCode() throws IOException {
        int responseCode = responseCodeFromGET("http://127.0.0.1:8080/");
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void unusedLinkReturns404StatusCode() throws IOException {
        int responseCode = responseCodeFromGET("http://127.0.0.1:8080/nothing-here");
        Assert.assertEquals(404, responseCode);
    }

    @Test
    public void validSearchGETReturns200StatusCode() throws IOException {
        int responseCode = responseCodeFromGET("http://127.0.0.1:8080/search?propertyValue=1000000&taxType=lbbt");
        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void invalidGetRequestReturns500StatusCode() throws IOException {
        int responseCode = responseCodeFromGET("http://127.0.0.1:8080/search?propertyValue=1000000&taxType=none");
        Assert.assertEquals(500, responseCode);
    }

    @Test
    public void validPostRequestReturnsCorrectStatusCode() throws IOException {
        int statusCode = getStatusCodeForPostInputs("1000000", "lbbt");
        Assertions.assertEquals(HttpStatus.SC_OK, statusCode);
    }

    @Test
    public void invalidPostRequestReturnsCorrectStatusCode() throws IOException {
        int statusCode = getStatusCodeForPostInputs("1000000", "random");
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
    @DisplayName("If the user enters a property value that isn't a number, they should be redirected.")
    public void testIfRedirectWorks() throws IOException {
        context = SpringApplication.run(Main.class);
        ArrayList<NameValuePair> postParameters;
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/");
        postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("propertyValue", "100a"));
        postParameters.add(new BasicNameValuePair("taxType", "lbbt"));
        httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
        HttpResponse response = httpclient.execute(httpPost);
        String location = Arrays.toString(response.getHeaders("Location"));
        context.close();
        Assert.assertTrue(location.contains("http://127.0.0.1:8080/invalid-property-value"));
    }


    private String scrape(String urlToProcess) throws IOException {
        context = SpringApplication.run(Main.class);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet requestGet = new HttpGet(urlToProcess);
        HttpResponse response = client.execute(requestGet);
        HttpEntity entity = response.getEntity();
        context.close();
        return EntityUtils.toString(entity, "UTF-8");
    }

    private int getStatusCodeForPostInputs(String propertyValue, String taxType) throws IOException {
        context = SpringApplication.run(Main.class);
        ArrayList<NameValuePair> postParameters;
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/");
        postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("propertyValue", propertyValue));
        postParameters.add(new BasicNameValuePair("taxType", taxType));
        httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
        HttpResponse response = httpclient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        context.close();
        return statusCode;
    }

    private int responseCodeFromGET(String urlToCheck) throws IOException {
        context = SpringApplication.run(Main.class);
        URL url = new URL(urlToCheck);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        context.close();
        return responseCode;
    }
}



