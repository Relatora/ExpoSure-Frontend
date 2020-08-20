package com.example.exposure.API;

import com.example.exposure.Model.ExpoEvent;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * ExpoEvent Interface REST plug to backend endpoints
 */
public interface IExpoEvents {
    @GET("Expo_Events")
    Observable<List<ExpoEvent>> getExpoEventList();

    @POST("expo_events_by_name")
    @FormUrlEncoded
    Observable<List<ExpoEvent>>  searchExpoEvent(@Field("name") String searchQuery);

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
    @POST("Expo_Events")
    Call<ExpoEvent> createEvent(@Body ExpoEvent expoEvent);
//    Observable<List<ExpoEvent>>  addExpoEvent(@FieldMap Map<String, String> params);

//    Observable<List<ExpoEvent>>  addExpoEvent(@Field("name") String searchQuery);


}
