package example.micronaut;

import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.MediaType;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller("/azureCrudJava")
public class AzureCrudJavaController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    public String index() {
        return "Example Response";
    }

    @Post
    public SampleReturnMessage postMethod(@Body SampleInputMessage inputMessage){
      SampleReturnMessage retMessage = new SampleReturnMessage();
      retMessage.setReturnMessage("Hello " + inputMessage.getName() + ", thank you for sending the message");
      return retMessage;
    }
}

@Introspected
@NoArgsConstructor
@AllArgsConstructor
@Data
class SampleInputMessage{
    private String name;
}

@Introspected
@Data
class SampleReturnMessage{
    private String returnMessage;
}
