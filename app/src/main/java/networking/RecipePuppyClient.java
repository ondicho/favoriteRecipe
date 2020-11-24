package networking;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import Constants.Constants;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipePuppyClient {
    private static Retrofit retrofit=null;

    public  static RecipePuppyApi getClient(){
        if(retrofit==null){
            OkHttpClient okHttpClient=new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request newRequest=chain.request().newBuilder()
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();
            retrofit=new Retrofit.Builder()
                    .baseUrl(Constants.RECIPE_PUPPY_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RecipePuppyApi.class);
    }
}
