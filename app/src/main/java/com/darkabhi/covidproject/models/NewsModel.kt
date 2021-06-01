package com.darkabhi.covidproject.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsModel(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "author")
    val author: String? = null,
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "publishedAt")
    val publishedAt: String? = null,
    @Json(name = "source")
    val source: Source,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "urlToImage")
    val urlToImage: String? = null
)

@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "id")
    val id: Any? = null,
    @Json(name = "name")
    val name: String? = null
)
