package com.makentoshe.androidgithubcitemplate

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.nio.charset.StandardCharsets

object Connection {
    const val POST = "POST"
    const val GET = "GET"
    const val PUT = "PUT"
    const val DELETE = "DELETE"

    @Throws(IOException::class)
    fun connect(url: String, type: String, data: String?, properties: Map<String, String>?): HttpURLConnection {
        val Url = URL(url)
        val con = Url.openConnection() as HttpURLConnection
        //        Bundle params = new Bundle();
//        params.putString("host", con.getURL().getHost());
//        params.putString("path", con.getURL().getPath());
//        params.putString("sum", cons.toString());
//        Pair<String, String> p = new Pair<>(con.getURL().getHost(), con.getURL().getPath());
//        if(!cons.containsKey(p))
//            cons.put(p, 0);
//        Integer i = cons.get(p);
//        cons.put(p, (i != null ? i : 0) +1);
//        FirebaseAnalytics.getInstance(c).logEvent("connection", params);
        con.requestMethod = type
        con.instanceFollowRedirects = false
        if (properties != null) for ((key, value) in properties) con.setRequestProperty(key, value)
        if (data != null) {
            con.doOutput = true
            val os = con.outputStream
            os.write(data.toByteArray())
        }
        con.connect()
        return con
    }

    @Throws(IOException::class)
    fun streamToString(`is`: InputStream): String {
        val result = StringBuilder()
        BufferedReader(InputStreamReader(`is`)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) result.append(line)
        }
        return result.toString()
    }

    class File(var name: String, var code: String)
}