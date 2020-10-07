package com.example.hca_stackexchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hca_stackexchange.adapter.RecyclerViewAdapter
import com.example.hca_stackexchange.client.ApiClient
import com.example.hca_stackexchange.model.StackExchangeX
import com.example.hca_stackexchange.service.StackExchangeService
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val clientId = 18887;
    val clientSecret = "rliMjIKGhyQiM4*Bzub6)A(("
    val redirectUrl = "hcateststack.com"
    var questionItemsLlist = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//       val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://stackoverflow.com/oauth" + "?client_id="+clientId +"&scope=no_expiry,private_info&redirect_uri="+redirectUrl))
//        startActivity(intent)

        val stackExchangeService = ApiClient.buildService(StackExchangeService::class.java)
        val requestCall = stackExchangeService.getStackExchangeResponse()

        requestCall.enqueue(object : retrofit2.Callback<StackExchangeX> {
            override fun onFailure(call: Call<StackExchangeX>, t: Throwable) {
                Log.d("Raj Error", t.message)
            }

            override fun onResponse(
                call: Call<StackExchangeX>,
                response: Response<StackExchangeX>
            ) {
                Log.d("Raj Response", response.body().toString())
                val jsonResponse = Gson().toJson(response.body())
                val stackExchangeX = Gson().fromJson(jsonResponse, StackExchangeX::class.java)
                for (i in 0..stackExchangeX.items.size - 1)
                    if (stackExchangeX.items[i].answerCount > 1 || stackExchangeX.items[i].owner.let { it.acceptRate >= 1 }) {
                        questionItemsLlist.add(stackExchangeX.items[i].title)
                    }

                stackRecyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter =
                        RecyclerViewAdapter(
                            this@MainActivity,
                            questionItemsLlist
                        )
                }
            }

        })

    }

    override fun onResume() {
        super.onResume()
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(redirectUrl)) {
            val code = uri.getQueryParameter("code")
            Toast.makeText(this, "raj", Toast.LENGTH_LONG).show()
        }
    }

}