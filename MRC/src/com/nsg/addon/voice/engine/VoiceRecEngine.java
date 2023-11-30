package com.nsg.addon.voice.engine;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
 
import com.google.gson.Gson;


public class VoiceRecEngine {

    private String openApiURL;
    private String accessKey;
    private String languageCode;

    public VoiceRecEngine() {
        openApiURL = "http://aiopen.etri.re.kr:8000/WiseASR/Recognition";
        accessKey = "c8550ba5-2fa7-404e-9679-cc19126a8881";
        languageCode = "korean";
    }

    /*���� ����*/
    public void extractText(String audioFilePath) {
        Gson gson = new Gson();
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();
        String audioContents = null;

        // ���� ���� �о�� Base64�� ���ڵ�
        try {
            Path path = Paths.get(audioFilePath);
            byte[] audioBytes = Files.readAllBytes(path);
            audioContents = Base64.getEncoder().encodeToString(audioBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        argument.put("language_code", languageCode);
        argument.put("audio", audioContents);

        request.put("argument", argument);

        URL url;
        Integer responseCode = null;  // ���� �ڵ�
        String responseBody = null;  // ���� ����

        HttpURLConnection con = null;

        try {

            // API�� HTTP POST ��û ����, �ʿ��� ��� ����
            url = new URL(openApiURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);  // ������ ������� ���� ����
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", accessKey);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                //wr.write(gson.toJson(request).getBytes("UTF-8"));  // ��û�� ��� �����͸� JSON���� �ٲٰ�, ���ڵ��ϰ� ������ ����
                wr.flush();  // ��� ���� ����, ������ ����
            }  // try-with-resources�� ����Ͽ� �ڵ����� close

            responseCode = con.getResponseCode();  // ���� ���� �ڵ� ex. 200, 404

            try (InputStream is = con.getInputStream()) {
                byte[] buffer = new byte[1024];  // ���� ũ���� ����
                int bytesRead;
                StringBuilder responseStringBuilder = new StringBuilder();

                while ((bytesRead = is.read(buffer)) != -1) {
                    responseStringBuilder.append(new String(buffer, 0, bytesRead, "UTF-8"));
                }

                responseBody = responseStringBuilder.toString();
            }  // try-with-resources�� ����Ͽ� �ڵ����� close

            // ��� ���
            System.out.println("[responseCode] " + responseCode);
            System.out.println("[responBody]");
            System.out.println(responseBody);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();  // ���� ����
            }
        }
    }
}
