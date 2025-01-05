package com.punny.commonuser.common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    /**
     * 状态码
     * 1:成功
     * -1:失败
     */
    private String code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;
    public static <T> Result<T> success(){
        return new Result<>("1","success",null);
    }
    public static <T> Result<T> success(T data){
        return new Result<>("1","success",data);
    }
    public static <T> Result<T> success(String msg){
        return new Result<>("1",msg,null);
    }
    public static <T> Result<T> success(T data,String msg){
        return new Result<>("1",msg,data);
    }
    public static <T> Result<T> failure(){
        return new Result<>("-1","failure",null);
    }
    public static <T> Result<T> failure(T data){
        return new Result<>("-1","failure",data);
    }
    public static <T> Result<T> failure(String msg){
        return new Result<>("-1",msg,null);
    }
    public static <T> Result<T> failure(T data,String msg){
        return new Result<>("-1",msg,data);
    }
}
