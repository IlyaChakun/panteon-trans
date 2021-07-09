package by.iba.notification.email.mailchimp.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpClient {

    protected final Log tptRequestLog = LogFactory.getLog("tptRequestLog");
    protected final Log tptFullRequestResponseLog = LogFactory.getLog("tptFullRequestResponseLog");

    public String get(String path, Map<String, String> mapHeaders, Map<String, String> params) {
        return get(path, mapHeaders, params, true);
    }

    public String get(String path, Map<String, String> mapHeaders, Map<String, String> params, boolean log) {
        long startTime = System.currentTimeMillis();
        String pathFull = getFullPath(path, params);
        HttpHeaders headers = getHeaders(mapHeaders);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        String respBody;
        try {
            ResponseEntity<String> responseEntity = new RestTemplate().exchange(pathFull, HttpMethod.GET, requestEntity, String.class);
            respBody = responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            respBody = e.getResponseBodyAsString();
        }
       // if (log)
          //  log(startTime, pathFull, null, respBody, HttpMethod.GET);
        return respBody;
    }

    public String get(String path, Map<String, String> params) {
        return get(path, null, params);
    }

    public String get(String path) {
        return get(path, null, null);
    }

    public String post(String path, String str, Map<String, String> mapHeaders, Map<String, String> params) {
        return post(path, str, mapHeaders, params, true);
    }

    public String post(String path, String str, Map<String, String> mapHeaders, Map<String, String> params, boolean log) {
        long startTime = System.currentTimeMillis();
        String pathFull = getFullPath(path, params);
        HttpHeaders headers = getHeaders(mapHeaders);
        HttpEntity<String> requestEntity = new HttpEntity<>(str, headers);
        String respBody;
        try {
            ResponseEntity<String> responseEntity = new RestTemplate().exchange(pathFull, HttpMethod.POST, requestEntity, String.class);
            respBody = responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            respBody = e.getResponseBodyAsString();
        }

       // if (log)
           // log(startTime, pathFull, str, respBody, HttpMethod.POST);
        return respBody;
    }

    public String delete(String path, Map<String, String> mapHeaders, Map<String, String> params) {
        long startTime = System.currentTimeMillis();
        String pathFull = getFullPath(path, params);
        HttpHeaders headers = getHeaders(mapHeaders);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        String respBody;
        try {
            ResponseEntity<String> responseEntity = new RestTemplate().exchange(pathFull, HttpMethod.DELETE, requestEntity, String.class);
            respBody = responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            respBody = e.getResponseBodyAsString();
        }
     //   log(startTime, pathFull, null, respBody, HttpMethod.DELETE);
        return respBody;
    }

    public String patch(String path, String str, Map<String, String> mapHeaders, Map<String, String> params) {
        long startTime = System.currentTimeMillis();
        String pathFull = getFullPath(path, params);
        HttpHeaders headers = getHeaders(mapHeaders);
        HttpEntity<String> requestEntity = new HttpEntity<>(str, headers);
        String respBody;
        try {
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            ResponseEntity<String> responseEntity = restTemplate.exchange(pathFull, HttpMethod.PATCH, requestEntity, String.class);
            respBody = responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            respBody = e.getResponseBodyAsString();
        }
       // log(startTime, pathFull, str, respBody, HttpMethod.PATCH);
        return respBody;
    }


    private String getFullPath(String path, Map<String, String> params) {
        StringBuilder paramData = new StringBuilder();
        if (params != null)
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (paramData.length() != 0)
                    paramData.append('&');
                else paramData.append('?');
                paramData.append(param.getKey());
                paramData.append('=');
                paramData.append(param.getValue());
            }
        return path + paramData.toString();
    }

    private HttpHeaders getHeaders(Map<String, String> mapHeaders) {
        HttpHeaders headers = new HttpHeaders();
        if (mapHeaders != null)
            for (String key : mapHeaders.keySet())
                headers.add(key, mapHeaders.get(key));
        return headers;
    }

//    private void log(long startTime, String pathFull, String reqBody, String respBody, HttpMethod httpMethod) {
//        long requestTime = (System.currentTimeMillis() - startTime);
//        int userId = 0;
//        String jwtId = null;
//        try {
//            userId = AuthorizedUser.id();
//            jwtId = AuthorizedUser.jwtId();
//            tptRequestLog.info("userId= {}, jwtId = {}, method = {} ,path = {} , reqTime= {}", userId, jwtId, httpMethod.name(), pathFull, requestTime);
//        } catch (Exception e) {
//
//        }
//
//        tptFullRequestResponseLog.info("userId= {}, jwtId = {}, method = {}, path = {} ,reqTime= {}, reqBody = {}, respBody = {}", userId, jwtId, httpMethod.name(), pathFull, requestTime, reqBody, respBody);
//    }
}
