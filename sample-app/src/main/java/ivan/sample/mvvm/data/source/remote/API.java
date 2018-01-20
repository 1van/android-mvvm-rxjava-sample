package ivan.sample.mvvm.data.source.remote;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import ivan.sample.mvvm.data.model.Repo;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface API {

    enum Client {
        INSTANT;

        private static final String HOST = "https://api.github.com";

        private OkHttpClient client;
        private Retrofit retrofit;

        public Retrofit obtain() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(9, TimeUnit.SECONDS)
                        .build();
            }
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(HOST)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

    interface Service {
        @GET("users/{user}/repos")
        Flowable<List<Repo>> queryListRepos(@Path("user") String user);
    }

}
