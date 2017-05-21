package example100.filmlibrary.testdata;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created on 17.11.2016.
 * Time 12:33.
 *
 * @author Volodymyr Portianko
 */
public class ModelMatcher<T, R> {
    protected Function<T, R> entityConverter;

    public ModelMatcher(Function<T, R> entityConverter) {
        this.entityConverter = entityConverter;
    }

    public void assertEquals(T expected, T actual) {
        Assert.assertEquals(entityConverter.apply(expected), entityConverter.apply(actual));
    }

    public void assertEquals(String message, T expected, T actual) {
        Assert.assertEquals(message, entityConverter.apply(expected), entityConverter.apply(actual));
    }

    public void assertListEquals(List<T> expected, List<T> actual) {
        Assert.assertEquals(map(expected, entityConverter), map(actual, entityConverter));
    }

    public static <S, T> List<T> map(List<S> list, Function<S, T> converter) {
        return list.stream().map(converter).collect(Collectors.toList());
    }


    public Matcher<T> getMatcherFor(T t1) {

        return new BaseMatcher<T>() {
            @Override
            public boolean matches(Object item) {
                return entityConverter.apply(t1).equals(entityConverter.apply((T)item));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(entityConverter.apply(t1).toString());
            }
        };
    }
}