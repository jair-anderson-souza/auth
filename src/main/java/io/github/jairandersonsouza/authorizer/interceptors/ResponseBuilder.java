package io.github.jairandersonsouza.authorizer.interceptors;


public class ResponseBuilder {

    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResponseBuilder code(String code) {
        this.code = code;
        return this;
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    public ResponseBuilder build() {
        return this;
    }
}
