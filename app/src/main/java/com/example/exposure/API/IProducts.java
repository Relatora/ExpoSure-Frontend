package com.example.exposure.API;

import com.example.exposure.Model.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Product Interface REST plug to backend endpoints
 */
public interface IProducts {
    @GET("products")
    Observable<List<Product>> getProductList();

    @POST("Product_by_name")
    @FormUrlEncoded
    Observable<List<Product>>  searchProducts(@Field("name") String searchQuery);

    /* {
            "name": "New Event",
            "location": "Bla St.",
            "contact_name": "Bla Contact",
            "contact_phone": "516-455-5688",
            "contact_email": "bla@bla.com",
            "date": "2020/12/02 13:00",
            "photo":""
        } */
//    @FormUrlEncoded
    @POST("products")
    Call<Product> createProduct(@Body Product product);
//    Observable<List<ExpoEvent>>  addExpoEvent(@FieldMap Map<String, String> params);

//    Observable<List<ExpoEvent>>  addExpoEvent(@Field("name") String searchQuery);
}
