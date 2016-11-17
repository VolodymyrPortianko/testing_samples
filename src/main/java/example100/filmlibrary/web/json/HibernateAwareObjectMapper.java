package example100.filmlibrary.web.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created on 17.11.2016.
 * Time 18:43.
 *
 * @author Volodymyr Portianko
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

    @PostConstruct
    public void init() {
        registerModule(new Hibernate5Module());

        setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
}
