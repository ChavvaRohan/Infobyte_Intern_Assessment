package com.chavvarohan.infobyteinternassessment

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("echo?user_content_key=qwYcXbEcYYgUZc1S-GzsUU779RGVyPLn7hmijUaOjFN6pJjSvVUp1tLIPQYdqwzQ7AkUEB7Dd1qfIb2bj1mG95cEdRLmXLS0m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnLLaPLSR1orrviIfdqbMVqnezuh_s26yzbzP1_QfvmA2sWZRCCTUDlAxB98yUCUmFTQrJMlj5zDWXRL6VL37MTTcpTNo-7Iq1kALmaJ23tE48xTm37iX3Fs&lib=MSKxoTD8KE-03f2YxPJKn5sQfnqTZGV-_")
    fun getData():Call<Data>


}