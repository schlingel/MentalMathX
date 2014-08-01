package net.schlingel.bplaced.mentalmathx.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import net.schlingel.bplaced.mentalmathx.model.Score;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zombie on 01.08.14.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "mentalmathx.db";
    private static final int VERSION = 1;
    private final Context context;
    private Dao<Score, Long> dao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    public Dao<Score, Long> getDao() throws SQLException {
        if(dao == null) {
            dao = getDao(Score.class);
        }

        return dao;
    }

    public List<Score> fetchAll() throws SQLException {
        return getDao().queryForAll();
    }

    public void persist(Score score) throws SQLException {
        getDao().createOrUpdate(score);
    }

    public void delete(Score score) throws SQLException {
        if(score == null || score.getId() == null) {
            throw new IllegalArgumentException("Delete must not be called on null or objects without ID.");
        }

        getDao().delete(score);
        Log.i(TAG, String.format("Deleted score %d", score.getId()));
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Score.class);
            Log.i(TAG, "Created scores database");
        } catch (SQLException e) {
            Log.e(TAG, "Couldn't create score database", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Score.class, true);
            onCreate(database, connectionSource);

            Log.i(TAG, "Updated scores database");
        } catch (SQLException e) {
            Log.e(TAG, "Couldn't update scores database", e);
        }
    }
}
