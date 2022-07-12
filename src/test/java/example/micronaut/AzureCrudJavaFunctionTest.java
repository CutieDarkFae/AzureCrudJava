package example.micronaut;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.microsoft.azure.functions.HttpStatus;
import io.micronaut.azure.function.http.HttpRequestMessageBuilder;
import io.micronaut.http.HttpMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AzureCrudJavaFunctionTest {

    @Test
    public void testFunction() throws Exception {
        try (Function function = new Function()) {
            HttpRequestMessageBuilder.AzureHttpResponseMessage response =
                function.request(HttpMethod.GET, "/azureCrudJava")
                        .invoke();

            assertEquals(HttpStatus.OK, response.getStatus());
        }
    }

    @Test
    public void testPostFunction() throws Exception {
        try (Function function = new Function()) {
            SampleInputMessage requestMessage = new SampleInputMessage("Kim");
            HttpRequestMessageBuilder.AzureHttpResponseMessage response =
                function.request(HttpMethod.POST, "/azureCrudJava").body(requestMessage).invoke();
            assertEquals(HttpStatus.OK, response.getStatus());
            String responseJSON = response.getBodyAsString();
            ObjectMapper mapper = new JsonMapper();
            SampleReturnMessage responseMessage = mapper.readValue(responseJSON, SampleReturnMessage.class);
            assertNotNull(responseMessage);
            assertEquals("Hello Kim, thank you for sending the message", responseMessage.getReturnMessage());
        }
    }
}
