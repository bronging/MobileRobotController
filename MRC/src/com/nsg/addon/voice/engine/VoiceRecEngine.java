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

    /*문장 추출*/
    public void extractText(String audioFilePath) {
        Gson gson = new Gson();
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();
        String audioContents = null;

        // 음성 파일 읽어와 Base64로 인코딩
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
        Integer responseCode = null;  // 응답 코드
        String responseBody = null;  // 응답 본문

        HttpURLConnection con = null;

        try {

            // API에 HTTP POST 요청 생성, 필요한 헤더 설정
            url = new URL(openApiURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);  // 서버로 출력할지 여부 설정
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", accessKey);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(gson.toJson(request).getBytes("UTF-8"));  // 요청에 담긴 데이터를 JSON으로 바꾸고, 인코딩하고 서버로 전송
                wr.flush();  // 출력 버퍼 비우고, 데이터 전송
            }  // try-with-resources를 사용하여 자동으로 close

            responseCode = con.getResponseCode();  // 서버 응답 코드 ex. 200, 404

            try (InputStream is = con.getInputStream()) {
                byte[] buffer = new byte[1024];  // 고정 크기의 버퍼
                int bytesRead;
                StringBuilder responseStringBuilder = new StringBuilder();

                while ((bytesRead = is.read(buffer)) != -1) {
                    responseStringBuilder.append(new String(buffer, 0, bytesRead, "UTF-8"));
                }

                responseBody = responseStringBuilder.toString();
            }  // try-with-resources를 사용하여 자동으로 close

            // 결과 출력
            System.out.println("[responseCode] " + responseCode);
            System.out.println("[responBody]");
            System.out.println(responseBody);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();  // 연결 종료
            }
        }
    }
}
