package foodfacts.bourgault.com.foodfacts.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import foodfacts.bourgault.com.openfoodapi.models.Product;
import io.reactivex.Maybe;

@Dao
public interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Query("SELECT * FROM product WHERE code = :barcodeNumber")
    Maybe<Product> loadProduct(String barcodeNumber);

    @Query("SELECT * FROM product")
    Maybe<List<Product>> loadProducts();
}
