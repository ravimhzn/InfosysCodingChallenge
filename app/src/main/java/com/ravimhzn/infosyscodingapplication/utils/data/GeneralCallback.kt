package com.ravimhzn.infosyscodingapplication.utils.data

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonSyntaxException
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.utils.extension.fullErrorMessage
import com.ravimhzn.infosyscodingapplication.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GeneralCallback<T, S>(
    private val result: MutableLiveData<Result<S>>,
    private val transform: (T) -> S
) :
    Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        result.value = Result.Error(t, null)
        if (t is JsonSyntaxException) {
            Logger.e(t)
        }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        response.body()?.let {
            result.value = Result.Success(transform(it))
        } ?: run {
            Logger.e(response.fullErrorMessage())
            //TODO Provide detailed error info later if the backend is has with error message structure
            result.value = Result.Error(null, R.string.unknown_error_message)
        }
    }
}