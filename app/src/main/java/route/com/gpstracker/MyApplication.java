package route.com.gpstracker;

import android.app.Application;

import route.com.gpstracker.DataBase.MyDataBase;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 5/12/2018.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyDataBase.init(this);

    }
}
