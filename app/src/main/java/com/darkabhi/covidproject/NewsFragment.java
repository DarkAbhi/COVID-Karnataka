package com.darkabhi.covidproject;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.darkabhi.covidproject.adapter.NewsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class NewsFragment extends Fragment {

    private static final String NEWSAPIKEY = "";

    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private ArrayList<NewsModel> mNewsList;

    private RequestQueue mRequestQueue;

    public static final String TAG = HomeFragment.class.getSimpleName();

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRequestQueue = Volley.newRequestQueue(getActivity());

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        mRecyclerView = view.findViewById(R.id.newsRecyclerView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mNewsList = new ArrayList<>();

        requestNews();

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onRefresh() {
                pullToRefresh.setRefreshing(true);
                mNewsList.clear();
                if (mNewsAdapter != null)
                    mNewsAdapter.notifyDataSetChanged();
                requestNews();
                if (!isConnected()) {
                    Toast.makeText(getActivity(), "Please check your connection", Toast.LENGTH_LONG).show();
                }
                pullToRefresh.setRefreshing(false);
            }
        });

        return view;

    }

    private void requestNews() {

        String URL = "http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=" + NEWSAPIKEY;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e(TAG, "onResponse: " + response.getString("status"));
                    if (response.getString("status").equals("ok")) {
                        JSONArray articles = response.getJSONArray("articles");
                        for (int i = 0; i < articles.length(); i++) {

                            JSONObject jo = articles.getJSONObject(i);

                            String author = jo.getString("author");

                            JSONObject source = jo.getJSONObject("source");
                            String sourceName = source.getString("name");

                            String title = jo.getString("title");

                            String description = jo.getString("description");
                            String url = jo.getString("url");
                            String urlImage = jo.getString("urlToImage");
                            String publishTime = jo.getString("publishedAt");

                            Log.i(TAG, "Source:" + sourceName + "\nAuthor: " + author + "\nTitle: " + title + "\ndescription: " + description + "\nURL: " + url + "\nIMAGE: " + urlImage + "\nTIME: " + publishTime);

                            if (sourceName.equals("null") || sourceName.equals("") || title.equals("null") || title.equals("") || description.equals("null") || description.equals("") || url.equals("null") || url.equals("") || urlImage.equals("null") || urlImage.equals("")) {

                            } else
                                mNewsList.add(new NewsModel(title, description, urlImage, url, sourceName));
                        }

                        mNewsAdapter = new NewsAdapter(getActivity(), mNewsList);
                        mRecyclerView.setAdapter(mNewsAdapter);

                        mNewsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                NewsModel clickedItem = mNewsList.get(position);
                                Uri uri = Uri.parse(clickedItem.getNewsUrl());
                                Intent goToNewsIntent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(goToNewsIntent);
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONObject(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException | JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            protected void deliverResponse(JSONObject response) {
                super.deliverResponse(response);
            }

            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                7000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null) {
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true;
                } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true;
                } else
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
            }
        }
        return false;
    }

}