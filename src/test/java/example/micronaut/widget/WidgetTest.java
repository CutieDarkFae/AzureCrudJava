package example.micronaut.widget;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.microsoft.azure.functions.HttpStatus;
import example.micronaut.Function;
import example.micronaut.widget.bo.AddWidgetRequestBO;
import example.micronaut.widget.bo.AddWidgetReturnBO;
import example.micronaut.widget.entity.Widget;
import io.micronaut.azure.function.http.HttpRequestMessageBuilder;
import io.micronaut.http.HttpMethod;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WidgetTest {
    @Test
    public void testIndex() throws IOException {
        try (Function function = new Function()) {
            HttpRequestMessageBuilder.AzureHttpResponseMessage response =
                function.request(HttpMethod.GET, "/widget")
                    .invoke();

            assertEquals(HttpStatus.OK, response.getStatus());
        }
    }

    @Test
    public void testAddWidget() throws IOException {
        try (Function function = new Function()) {
            String name = "It's a test";
            String data = "¿Who can this be?";
            AddWidgetRequestBO requestBO = new AddWidgetRequestBO(name, data);
            HttpRequestMessageBuilder.AzureHttpResponseMessage response =
                function.request(HttpMethod.POST, "/widget/add")
                    .body(requestBO)
                    .invoke();
            assertEquals(HttpStatus.OK, response.getStatus());
            ObjectMapper mapper = new JsonMapper();
            AddWidgetReturnBO returnBO = mapper.readValue(response.getBodyAsString(), AddWidgetReturnBO.class);
            assertNotNull(returnBO);
            assertNotNull(returnBO.getWidget());
            Widget widget = returnBO.getWidget();
            assertNotNull(widget.getId());
            assertEquals(name, widget.getName());
            assertEquals(data, widget.getData());
            assertNotNull(widget.getStartDate());
            assertEquals(null, widget.getEndDate());
        }
    }
}
