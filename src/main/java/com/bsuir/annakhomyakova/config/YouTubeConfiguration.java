package com.bsuir.annakhomyakova.config;

import org.testcontainers.shaded.okhttp3.OkHttpClient;

import java.io.IOException;

public class YouTubeConfiguration {

    public YouTubeConfiguration() {
    }
    private static final String API_KEY="AIzaSyBx049jWqf7czPFEEeTiWeONtAAWbo9P2I";

    public static String getApiKey() {
        return API_KEY;
    }


    private String apikey;

//    public YouTubeAPI(String key) {
//        this.apikey = key;
//    }
//
//    private static OkHttpClient client = new OkHttpClient();
//
//    public YTSearch searchVideo(String keyword) throws YouTubeException {
//        String json = null;
//        try{
//            json = getJSONGET("https://www.googleapis.com/youtube/v3/search?part=snippet&q="+keyword+
//                "&type=video&key=" + apikey);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        Gson gson = new Gson();
//        YTSearch profile = gson.fromJson(json, YTSearch.class);
//        if(profile.kind==null) {
//            YouTubeError err = gson.fromJson(json, YouTubeError.class);
//            throw new YouTubeException(err.getError().getErrors().get(0).getMessage(), err.getError().getErrors().get(0).getReason());
//        }
//        return profile;
//    }
//
//    public YTSearch searchChannel(String keyword) throws YouTubeException {
//        String json = null;
//        try{
//            json = getJSONGET("https://www.googleapis.com/youtube/v3/search?part=snippet&q="+keyword+
//                "&type=channel&key=" + apikey);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        Gson gson = new Gson();
//        YTSearch profile = gson.fromJson(json, YTSearch.class);
//        if(profile.kind==null) {
//            YouTubeError err = gson.fromJson(json, YouTubeError.class);
//            throw new YouTubeException(err.getError().getErrors().get(0).getMessage(), err.getError().getErrors().get(0).getReason());
//        }
//        return profile;
//    }
//
//    public YouTubeVideos getVideo(String videoId) throws YouTubeException {
//        String json = null;
//        try{
//            json = getJSONGET("https://www.googleapis.com/youtube/v3/videos?part=snippet,contentDetails,statistics&id="+videoId+"&key=" + apikey);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        Gson gson = new Gson();
//        YouTubeVideos profile = gson.fromJson(json, YouTubeVideos.class);
//        if(profile.kind==null) {
//            YouTubeError err = gson.fromJson(json, YouTubeError.class);
//            throw new YouTubeException(err.getError().getErrors().get(0).getMessage(), err.getError().getErrors().get(0).getReason());
//        }
//        return profile;
//    }
//
//    public YouTubeChannels getChannel(String channelId) throws YouTubeException {
//        String json = null;
//        try{
//            json = getJSONGET("https://www.googleapis.com/youtube/v3/channels?part=snippet,statistics,brandingSettings&id="+channelId+"&key=" + apikey);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        Gson gson = new Gson();
//        YouTubeChannels profile = gson.fromJson(json, YouTubeChannels.class);
//        if(profile.kind==null) {
//            YouTubeError err = gson.fromJson(json, YouTubeError.class);
//            throw new YouTubeException(err.getError().getErrors().get(0).getMessage(), err.getError().getErrors().get(0).getReason());
//        }
//        return profile;
//    }
//
//    private static String getJSONGET(String url) throws IOException {
//        Request request = new Request.Builder().url(url).build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }
}
