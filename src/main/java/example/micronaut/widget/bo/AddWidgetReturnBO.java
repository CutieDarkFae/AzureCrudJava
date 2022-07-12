package example.micronaut.widget.bo;

import example.micronaut.widget.entity.Widget;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Introspected
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddWidgetReturnBO {
    private Widget widget;
}
