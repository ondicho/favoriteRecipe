package networking;

import models.RecipePuppySearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipePuppyApi {
    @GET("/?q=")
    Call<RecipePuppySearchResponse> getRecipe(
            @Query("location") String title
    );
}
