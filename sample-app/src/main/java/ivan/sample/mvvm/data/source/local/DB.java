package ivan.sample.mvvm.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import ivan.sample.mvvm.data.model.Repo;


@Database(entities = {Repo.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {

    public static final String DB_NAME = "MVVM.db";

    public abstract Dao dao();

    @android.arch.persistence.room.Dao
    interface Dao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertAll(List<Repo> repos);

        @Update
        int updateItem(Repo repo);

        @Query("DELETE FROM repo")
        void deleteAll();

        @Delete
        void deleteItem(Repo repo);

        @Query("DELETE FROM repo WHERE id = :id")
        int deleteItemById(int id);

        @Query("SELECT * FROM repo WHERE full_name LIKE :user")
        Flowable<List<Repo>> queryListRepos(String user);
    }

}
