package route.com.gpstracker.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import route.com.gpstracker.DataBase.Dao.UsersDao;
import route.com.gpstracker.DataBase.Entities.User;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 5/12/2018.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */

@Database(entities = {User.class},version = 1,exportSchema = true)
public abstract class MyDataBase extends RoomDatabase {

    public abstract UsersDao usersDao();

    private static final String DATABASENAME="USERSDATABASE";
    private static MyDataBase myDataBase;

    public static void init(Context context){
        if (myDataBase==null){
            myDataBase=  Room.databaseBuilder(context,
                    MyDataBase.class, DATABASENAME)
                    .fallbackToDestructiveMigration()
                //    .allowMainThreadQueries()
                    .build();
        }
    }
    public static MyDataBase getInstance(){
        return myDataBase;

    }

}
