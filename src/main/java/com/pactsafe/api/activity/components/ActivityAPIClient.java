package com.pactsafe.api.activity.components;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pactsafe.api.activity.domain.ParameterStore;
import com.pactsafe.api.activity.domain.Payload;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * created by Michael Welling on 8/22/16.
 */
public class ActivityAPIClient {

//    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//    private static final MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");

    private String baseUrl;
//    private OkHttpClient client;
    private ObjectMapper mapper;

//    import java.net.HttpURLConnection; ().openConnectio HTTPURLCOnnection

    public ActivityAPIClient() {
//        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
    }

    public void send(ParameterStore parameters) throws PactSafeActivityException {
//        try {
//            RequestBody body = RequestBody.create(TEXT, new Payload(parameters).toString());
//            Request request = new Request.Builder()
//                    .url(baseUrl + "/send")
//                    .post(body)
//                    .build();
//            Response response = client.newCall(request).execute();
//            if (response.code() >= 400) {
//                throw new PactSafeActivityException("send", response.code(), response.message());
//            }
//        } catch (IOException e) {
//            throw new PactSafeActivityException("Could not complete send action.", e);
//        }
    }

    public Map<Integer, String> retrieve(ParameterStore parameters) throws PactSafeActivityException {
        try {
//            RequestBody body = RequestBody.create(TEXT, new Payload(parameters).toString());
//            Request request = new Request.Builder()
//                    .url(baseUrl + "/retrieve")
//                    .post(body)
//                    .build();
//            Response response = client.newCall(request).execute();
//            if (response.code() >= 400) {
//                throw new PactSafeActivityException("retrieve", response.code(), response.message());
//            }
//            return mapper.readValue(response.body().string(), new TypeReference<Map<Integer, String>>(){});
            return mapper.readValue("Hello", new TypeReference<Map<Integer, String>>(){});
        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete retrieve action.", e);
        }
    }

    public Map<Integer, Boolean> latest(ParameterStore parameters) throws PactSafeActivityException {
        try {
//            RequestBody body = RequestBody.create(TEXT, new Payload(parameters).toString());
//            Request request = new Request.Builder()
//                    .url(baseUrl + "/latest")
//                    .post(body)
//                    .build();
//            Response response = client.newCall(request).execute();
//            if (response.code() >= 400) {
//                throw new PactSafeActivityException("latest", response.code(), response.message());
//            }
//            return mapper.readValue(response.body().string(), new TypeReference<Map<Integer, Boolean>>(){});
            return mapper.readValue("Hello", new TypeReference<Map<Integer, Boolean>>(){});
        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete latest action.", e);
        }
    }

    public ParameterStore load(ParameterStore parameters) throws PactSafeActivityException {

//        https://stackoverflow.com/a/4206094/5045191
        try {
            String postData = new Payload(parameters).toString();
            byte[] postDataBytes = postData.getBytes("UTF-8");
            int postDataLength = postDataBytes.length;
            String request = baseUrl + "/load/json";
            URL url = new URL(request);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.getOutputStream().write(postDataBytes);
//            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
//                wr.write(data);
//            }

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            for (int c; (c = in.read()) >= 0;)
                System.out.print((char)c);

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;)
                sb.append((char)c);
            String response = sb.toString();

            System.out.print(response);

            return mapper.readValue(response, ParameterStore.class);

        } catch (IOException e) {
            throw new PactSafeActivityException("Could not complete load action.", e);
        }

//        try {
//            RequestBody body = RequestBody.create(TEXT, new Payload(parameters).toString());
//            Request request = new Request.Builder()
//                    .url(baseUrl + "/load/json")
//                    .post(body)
//                    .build();
//            Response response = client.newCall(request).execute();
//            if (response.code() >= 400) {
//                throw new PactSafeActivityException("load", response.code(), response.message());
//            }
//            return mapper.readValue(response.body().string(), ParameterStore.class);
//        } catch (IOException e) {
//            throw new PactSafeActivityException("Could not complete load action.", e);
//        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
