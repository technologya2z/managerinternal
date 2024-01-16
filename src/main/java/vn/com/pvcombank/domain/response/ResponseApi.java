package vn.com.pvcombank.domain.response;

public class ResponseApi {

    private int code;
    private String response;

    public ResponseApi(int code, String response) {
        super();
        this.code = code;
        this.response = response;
    }

    public ResponseApi() {
        super();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ResponseApi [code=" + code + ", response=" + response + "]";
    }
}
