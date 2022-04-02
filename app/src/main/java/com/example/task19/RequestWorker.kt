package com.example.task19

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    override fun doWork(): Result = runBlocking {
        return@runBlocking try {
            val job = launch(start = CoroutineStart.LAZY) {
                if(!MsgAdapter.getList().isNullOrEmpty()) {
                    printMsgJson()
                }
            }
            getData()
            job.start()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun getData(){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(apiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MsgItem>?> {
            override fun onResponse(
                call: Call<List<MsgItem>?>,
                response: Response<List<MsgItem>?>
            ) {
                val responseBody = response.body()!!
                MsgAdapter.setList(responseBody)
            }

            override fun onFailure(call: Call<List<MsgItem>?>, t: Throwable) {
                Log.d("MsgList", "onFailure: " + t.message)
            }
        })
    }

    private suspend fun printMsgJson() {
        for (msg in MsgAdapter.getList()!!) {
            Log.d("json", Gson().toJson(msg))
            delay(100)
        }
    }
}