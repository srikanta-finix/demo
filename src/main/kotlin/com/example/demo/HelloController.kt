package com.example.demo

import okhttp3.Credentials
import org.springframework.web.bind.annotation.*
import java.util.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.OkHttpClient
import okhttp3.Request

@RestController
@CrossOrigin(origins = arrayOf("*"), allowedHeaders = arrayOf("*"))
class HelloController {

    val finixProdPIURL = "https://finix.live-payments-api.com/payment_instruments";
    val credential = Credentials.basic("USmnKjQb4txYsVtmezp7asqn", "eeace2b6-6d4d-407f-8a21-e012ec0a35e1") // Credentials for express Daphne's corner

    @GetMapping("/")
    fun hello() = "Hello from Docker";

    @GetMapping("/Session")
    fun getSession() = UUID.randomUUID().toString()

    @PostMapping(value = ["/createPaymentInstrument"])
    fun createPaymentInstrunet(@RequestBody req: String) : String {
        val okHttpClient = OkHttpClient()
        val requestBody = req.toRequestBody()
        val request = Request.Builder()
            .method("POST", requestBody)
            .addHeader("Authorization", credential)
            .addHeader("Content-Type", "application/json")
            .url(finixProdPIURL)
            .build()
        val response = okHttpClient.newCall(request).execute().body!!.string()
        System.out.println(response)
        return response
    }
}
