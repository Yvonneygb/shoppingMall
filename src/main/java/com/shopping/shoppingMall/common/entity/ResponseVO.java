package com.shopping.shoppingMall.common.entity;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/29 17:32
 * @Version 1.0
 */
public class ResponseVO<T> {
    //成功处理
    private static final int SUCCESS = 0;
    //失败处理
    private static final int ERROR = 1;
    //警告处理
    private static final int WARN = 2;

    /** 消息 **/
    private String msg;

    /** 处理码 **/
    private int code;

    /** 返回数据 **/
    private T data;

    public ResponseVO() {
    }
    //构造函数
    public ResponseVO(T data) {
        this.msg = "操作成功";
        this.code = SUCCESS;
        this.data = data;
    }
    public ResponseVO(String msg, int resCode, T data) {
        this.msg = msg;
        this.code = resCode;
        this.data = data;
    }

    //成功，有参
    public static <T> ResponseVO ok(T data){
        return new ResponseVO("获取成功" , SUCCESS , data);
    }
    //成功，无参
    public static <T> ResponseVO ok(){
        return ResponseVO.ok("");
    }
    //微信审核返回  "成功提交，请耐心等待审核"
    public static <T> ResponseVO wxWait(){
        return ResponseVO.ok("成功提交，请耐心等待审核");
    }

    /**
     *     成功，有参
     */
    public static <T> ResponseVO wxWait(T data){
        ResponseVO vo = new ResponseVO();
        vo.setMsg("成功提交，请耐心等待审核");
        vo.setCode(0);
        vo.setData(data);
        return vo;
    }
    //失败，有参
    public static <T> ResponseVO error(String errorMsg){
        return new ResponseVO(errorMsg,ERROR,"");
    }
    //失败，无参
    public static <T> ResponseVO error(){
        return new ResponseVO("操作失败",ERROR , "");
    }
    //警告，有参
    public static <T> ResponseVO warn(String warnMsg){
        return new ResponseVO(warnMsg,WARN , "");
    }
    //警告，无参
    public static <T> ResponseVO warn(){
        return new ResponseVO("操作可能会引起错误，请注意",WARN , "");
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
