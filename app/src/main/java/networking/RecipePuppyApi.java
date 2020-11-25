package networking;

import models.RecipePuppySearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipePuppyApi {
    @GET(" ")
    Call<RecipePuppySearchResponse> getRecipe(
            @Query("q") String title
    );
}
