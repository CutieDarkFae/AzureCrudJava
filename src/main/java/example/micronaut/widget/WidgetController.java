package example.micronaut.widget;

import example.micronaut.widget.bo.AddWidgetRequestBO;
import example.micronaut.widget.bo.AddWidgetReturnBO;
import example.micronaut.widget.entity.Widget;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.AllArgsConstructor;

import java.util.Date;

@ExecuteOn(TaskExecutors.IO)
@Controller("/widget")
@AllArgsConstructor
public class WidgetController {

    private final WidgetRepository widgetRepository;

    @Produces(MediaType.APPLICATION_JSON)
    @Get
    public Iterable<Widget> index() {
        return widgetRepository.findAll();
    }

    @Post(value = "/add", consumes = MediaType.APPLICATION_JSON)
    public AddWidgetReturnBO addWidget(@Body AddWidgetRequestBO request){
        Widget widget = new Widget(null, request.getName(), request.getData(), new Date(), null);
        return new AddWidgetReturnBO(widgetRepository.save(widget));
    }
}