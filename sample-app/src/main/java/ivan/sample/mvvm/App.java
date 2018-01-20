package ivan.sample.mvvm;

import android.app.Application;


public class App extends Application {

    public static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

}
