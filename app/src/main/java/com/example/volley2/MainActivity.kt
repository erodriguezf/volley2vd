package com.example.volley2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            VolleySingleton.getInstance(this).addToRequestQueue(getJsonObjectRequest())
        }
    }

    fun getJsonObjectRequest(): JsonObjectRequest {
        val url ="https://randomuser.me/api/?results=1"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //parseObject(response)
                parseObjectG(response)
            },
            Response.ErrorListener{
                textView.text = "error"

            }
        )

        return jsonObjectRequest
    }

    fun  parseObjectG(response: JSONObject){
        val jsonArrayResult: JSONArray = response.getJSONArray("results")
        val size: Int = jsonArrayResult.length()
        val i: Int=0

        for (i in 0.. size-1){
            val userObject = jsonArrayResult.getJSONObject(i)
            val gender = userObject.getString("gender")
            val nameObject = userObject.getJSONObject("name")
            val firstName = nameObject.getString("first")
            Log.d("JSONparcing", gender + " "+ firstName)
            textView.text = gender + " " + firstName



        }
    }




}

