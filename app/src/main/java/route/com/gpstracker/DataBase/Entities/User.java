package route.com.gpstracker.DataBase.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 5/12/2018.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
