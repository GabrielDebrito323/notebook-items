package com.ed.globallogik.data;

import com.ed.globallogik.App;
import com.ed.globallogik.R;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCallback<T> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            onError(App.getInstance().getString(R.string.error_sending_data));
        } else if (response.body() != null) {
            onSuccess(response.body());
        } else {
            onError(App.getInstance().getString(R.string.error_sending_data));
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof IOException) {
            onError(App.getInstance().getString(R.string.connection_problem) + "\n" + t.getMessage());
        } else {
            onError(App.getInstance().getString(R.string.error_sending_data));
        }
    }

    public void onSuccess(T responseBody) {
    }

    public void onError(String errorMessage) {
    }
}
