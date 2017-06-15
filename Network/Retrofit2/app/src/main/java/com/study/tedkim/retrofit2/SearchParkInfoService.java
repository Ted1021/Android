package com.study.tedkim.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tedkim on 2017. 6. 14..
 */

public interface SearchParkInfoService {

    @GET("{Auth_Key}/json/SearchParkInfoService/1/20/")
    Call<ParkData> getParkData (@Path("Auth_Key") String auth_key);
}
