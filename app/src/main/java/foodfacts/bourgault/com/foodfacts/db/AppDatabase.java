package foodfacts.bourgault.com.foodfacts.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import foodfacts.bourgault.com.foodfacts.db.dao.ProductDAO;
import foodfacts.bourgault.com.openfoodapi.models.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "product-database";

    private static AppDatabase INSTANCE;

    public abstract ProductDAO productDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            Context appContext = context.getApplicationContext();
            INSTANCE = Room.databaseBuilder(appContext, AppDatabase.class, DB_NAME).build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}