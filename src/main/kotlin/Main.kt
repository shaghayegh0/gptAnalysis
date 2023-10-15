
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

fun main(args: Array<String>) {

    val apiKey = "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"
    val prompt = "Translate the following English text to French: '${getUserInput()}'"

    val client = OkHttpClient()

    val url = "https://api.openai.com/v1/engines/davinci-codex/completions" // Adjust the URL as needed

    val requestBody = """
        {
            "prompt": "$prompt",
            "max_tokens": 50
        }
    """.trimIndent()

    val mediaType = "application/json; charset=utf-8".toMediaType()
    val request = Request.Builder()
        .url(url)
        .post(requestBody.toRequestBody(mediaType))
        .header("Authorization", "Bearer $apiKey")
        .build()

    try {
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val responseBody = response.body?.string()
            println("Response from ChatGPT: $responseBody")
        } else {
            println("Error: ${response.code}: ${response.message}")
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
fun getUserInput(): String {
    // You can implement code here to get user input
    // For simplicity, you can return a static input for testing
    return "Translate the following English text to French: 'Hello, World!'"
}

