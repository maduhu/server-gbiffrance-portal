import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import controllers.Search.GeoBound;

import org.elasticsearch.common.geo.GeoPoint;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void subGeoBoundCheck() {
        GeoBound global = new GeoBound(4.0, 0.0, 0.0, 4.0);

        assertThat(global.subBound(0, 0, 4, 4)).isEqualTo(new GeoBound(1.0, 0.0, 0.0, 1.0));
        assertThat(global.subBound(0, 3, 4, 4)).isEqualTo(new GeoBound(1.0, 3.0, 0.0, 4.0));
        assertThat(global.subBound(1, 1, 4, 4)).isEqualTo(new GeoBound(2.0, 1.0, 1.0, 2.0));
        assertThat(global.subBound(3, 0, 4, 4)).isEqualTo(new GeoBound(4.0, 0.0, 3.0, 1.0));
        assertThat(global.subBound(3, 3, 4, 4)).isEqualTo(new GeoBound(4.0, 3.0, 3.0, 4.0));
    }

    @Test
    public void geoCenterCheck() {
        GeoBound global = new GeoBound(4.0, 0.0, 0.0, 4.0);

        assertThat(global.center()).isEqualTo(new GeoPoint(2.0, 2.0));
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }


}
