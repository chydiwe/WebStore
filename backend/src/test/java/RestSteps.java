import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.jackass.RestAPI.conf.ConditionsConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RestSteps {

    private String basePath;
    private HttpClient httpClient;
    private ObjectMapper jsonConverter;

    @Given("running server")
    public void init() {
        // activate in memory flag
        ConditionsConfig.IN_MEMORY = true;

        this.basePath = "http://localhost:8080/api";
        this.httpClient = HttpClients.createDefault();
        this.jsonConverter = new ObjectMapper();

        SpringApplication.run(InMemoryWebStoreApplication.class);
    }

    @When("POST $path return status code $status")
    public void postWithStatus(String path, String status) throws IOException {
        HttpPost post = new HttpPost(basePath + path);
        HttpResponse response = httpClient.execute(post);
        assertTrue(response.getStatusLine().getStatusCode() == Integer.valueOf(status));
    }

    @When("DELETE $path return status code $status")
    public void deleteWithStatus(String path, String status) throws IOException {
        HttpDelete delete = new HttpDelete(basePath + path);
        HttpResponse response = httpClient.execute(delete);
        assertTrue(response.getStatusLine().getStatusCode() == Integer.valueOf(status));
    }


    @When("GET $path then $key has value $value")
    public void get(String path, String key, String value) throws IOException {
        HttpGet get = new HttpGet(basePath + path);
        HttpResponse response = httpClient.execute(get);
        byte[] result = EntityUtils.toByteArray(response.getEntity());
        JsonNode node = jsonConverter.readTree(result);
        assertTrue(node.get(key).equals(value));
    }

    @When("GET $path then json has values $keyToValue")
    public void getHas(String path, String objects) throws IOException {
        HttpGet get = new HttpGet(basePath + path);
        HttpResponse response = httpClient.execute(get);
        byte[] result = EntityUtils.toByteArray(response.getEntity());
        JsonNode json = jsonConverter.readTree(result);
        Arrays.stream(objects.split("###")).forEach(object -> {
            String[] keyToValues = object.split(";");
            for (String keyToValue : keyToValues){
                assertTrue(hasValue(json, keyToValue.split("=")));
            }
        });
    }

    @When("GET $path then json hasn't values $keyToValue")
    public void getHasNot(String path, String objects) throws IOException {
        HttpGet get = new HttpGet(basePath + path);
        HttpResponse response = httpClient.execute(get);
        byte[] result = EntityUtils.toByteArray(response.getEntity());
        JsonNode json = jsonConverter.readTree(result);
        Arrays.stream(objects.split("###")).forEach(object -> {
            String[] keyToValues = object.split(";");
            for (String keyToValue : keyToValues){
                assertFalse(hasValue(json, keyToValue.split("=")));
            }
        });
    }

    private boolean hasValue(JsonNode json, String[] keyToValue) {
        if (json.getNodeType() == JsonNodeType.OBJECT) {
            return keyToValue[1].equals(json.get(keyToValue[0]).asText());
        } else if (json.getNodeType() == JsonNodeType.ARRAY){
            for (int i = 0; i < json.size(); i++){
                if(hasValue(json.get(i), keyToValue)){
                    return true;
                }
            }
        }

        return false;
    }

}
