import org.junit.Test;
import org.smart4j.framework.helper.BeanHelper;
import ru.lanwen.verbalregex.VerbalExpression;

/**
 * Created by niceyuanze on 17-5-21.
 */
public class TestIoncontainer {
    @Test
    public void testIoc(){
        VerbalExpression testRegex = VerbalExpression.regex()
                .wordChar()
                .build();
        System.out.println(testRegex.test("a:"));
        System.out.println(testRegex.toString());
    }
}
