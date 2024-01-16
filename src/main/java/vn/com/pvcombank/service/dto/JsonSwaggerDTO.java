package vn.com.pvcombank.service.dto;

public class JsonSwaggerDTO {

    private String pathApi;

    private String method;

    private String nameApi;

    public JsonSwaggerDTO(String pathApi, String method, String nameApi) {
        this.pathApi = pathApi;
        this.method = method;
        this.nameApi = nameApi;
    }

    public String getPathApi() {
        return pathApi;
    }

    public void setPathApi(String pathApi) {
        this.pathApi = pathApi;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNameApi() {
        return nameApi;
    }

    public void setNameApi(String nameApi) {
        this.nameApi = nameApi;
    }
}
