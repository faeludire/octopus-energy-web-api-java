package com.wrapper.octopusenergy.request;

import java.io.IOException;

import com.wrapper.octopusenergy.OctopusEnergyApi;
import com.wrapper.octopusenergy.response.ApiError;
import com.wrapper.octopusenergy.response.ApiResponse;
import com.wrapper.octopusenergy.response.Response;
import retrofit2.Call;

public class Request<T extends Response<T>> {
    OctopusEnergyApi octopusEnergyApi;

    Request(OctopusEnergyApi octopusEnergyApi) {
        this.octopusEnergyApi = octopusEnergyApi;
    }

    protected T execute(Call<T> call, Class<T> clazz) {
        ApiResponse<T> response;

        try {
            response = new ApiResponse<>(call.execute());
        } catch (IOException e) {
            e.printStackTrace();
            response = new ApiResponse<>(null);
            ApiError error = new ApiError();
            error.setCode("NetworkError");
            error.setMessage(e.getMessage());
            response.setError(error);
        }

        T entity = response.body();
        if (entity == null) {
            try {
                entity = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (entity != null) {
            entity.setResponse(response);
        }

        return entity;
    }
}
