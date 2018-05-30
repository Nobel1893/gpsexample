package route.com.gpstracker.DataBase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import route.com.gpstracker.DataBase.Entities.User;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 5/12/2018.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */


@Dao
public interface UsersDao {

    @Query("select * from User")
    List<User> getAllUsers();

    @Insert
    void AddUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void removeUser(User user);

}
