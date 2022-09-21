package com.laituo.cmsFile.common;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//统一返回结果

public class R extends ResponseEntity<Object> {


    //构造方法
    public R(HttpStatus status) {
        super(status);
    }


    public R(Integer code, String msg) {

        super(Message.custom(msg), Message.num2HttpStatus(code));

    }

    public R(Object data) {
        super(data, Message.num2HttpStatus(ResultCode.SUCCESS));

    }
    //成功方法
    public static R success(Object data) {
        return new R (data);
    }
    public static R ok(String msg){
        Map<String,String> data =new HashMap<>();
        data.put("messages",msg);
        return new R (data);
    }

    //失败方法
    public static <T> R fail(Integer code,String msg) {
        return new R (code,msg);
    }

    /**
     * 直接输出
     * @param code              状态码
     * @param msg               消息
     * @param servletResponse   HttpServletResponse
     * @throws IOException
     */
    public static void populateResponse(Integer code,String msg, HttpServletResponse servletResponse) throws IOException {

        ResponseEntity responseEntity = new R(code,msg);
        for (Map.Entry<String, List<String>> header : responseEntity.getHeaders().entrySet()) {
            String chave = header.getKey();
            for (String valor : header.getValue()) {
                servletResponse.addHeader(chave, valor);
            }
        }
        servletResponse.setContentType("application/json;charset=utf-8");
        servletResponse.setStatus(responseEntity.getStatusCodeValue());
        servletResponse.getWriter().write(JSON.toJSONString(responseEntity.getBody()));
    }


    @Data
    static class Message<T> {

        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private String message;
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private Object data;
        //向前端返回的内容
        public Message() {
        }

        public Message(String message) {
            this.message = message;
        }

        public static Object custom(Object data) {
            return data;
        }
        public static Message custom(String msg) {

            Message message=new Message();
            message.setMessage(msg);
            return message;
        }
        public static HttpStatus num2HttpStatus(Integer code) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            for (HttpStatus httpStatus : HttpStatus.values()) {
                boolean b = code == httpStatus.value();
                if (b) {
                    return httpStatus;
                }
            }
            return status;
        }
    }
}
