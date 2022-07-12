package example.micronaut.widget;

import example.micronaut.widget.entity.Widget;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface WidgetRepository extends CrudRepository<Widget, Long> {
}
