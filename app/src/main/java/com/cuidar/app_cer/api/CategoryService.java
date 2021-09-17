package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.category.CategoriesPaginated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CategoryService {

    @GET("/category")
    Call<CategoriesPaginated> getCategories (
            @Query("page") String page,
            @Query("pageSize") String pageSize,
            @Header("Authorization") String token
    );
}
