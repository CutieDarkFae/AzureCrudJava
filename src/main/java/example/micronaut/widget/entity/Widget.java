package example.micronaut.widget.entity;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Introspected
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Widget {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String data;
    private Date startDate;
    private Date endDate;
}
