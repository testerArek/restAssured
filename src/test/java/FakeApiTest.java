import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FakeApiTest {


    @Test
   public void getSpecificComment() {
        Response response = get("http://localhost:3000/comments/1");

        Comment specificComment = response.as(Comment.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(specificComment);
        Assert.assertEquals(specificComment.getId(),"1");
    }

    @Test
    public void getAllComments() {
        Response response = get("http://localhost:3000/comments");

        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Comment> allComents = jsonPathEvaluator.getList(".", Comment.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(allComents.size()>1);
    }

}
