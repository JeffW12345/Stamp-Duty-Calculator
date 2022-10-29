import main.propertytaxcalculator.Main;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ServerTests {
    @Test
    public void validGetRequestReturnsCorrectStatusCode() throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class);
        URL url = new URL("http://127.0.0.1:8080/");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        boolean success = connection.getResponseCode() == 200;
        ctx.close();
        Assertions.assertTrue(success);
    }
    @Test
    public void invalidGetRequestReturnsCorrectStatusCode() throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class);
        URL url = new URL("http://127.0.0.1:8080/nothing-here");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        boolean success = connection.getResponseCode() != 200;
        ctx.close();
        Assertions.assertTrue(success);
    }

    @Test
    public void validPostRequestReturnsCorrectStatusCode() throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class);
        HttpClient httpclient;
        HttpPost httpPost;
        ArrayList<NameValuePair> postParameters;
        httpclient = HttpClientBuilder.create().build();;
        httpPost = new HttpPost("http://127.0.0.1:8080/");
        postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("propertyValue", "1000000"));
        postParameters.add(new BasicNameValuePair("taxType", "lbbt"));
        httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
        HttpResponse response = httpclient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        ctx.close();
        Assertions.assertEquals(statusCode, (HttpStatus.SC_OK));
    }


}
