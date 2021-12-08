package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
    private Integer credentialId;
    private String url;
    private String urlUserName;
    private String key;
    private String urlPassWord;
    private Integer userId;

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlUserName() {
        return urlUserName;
    }

    public void setUrlUserName(String urlUserName) {
        this.urlUserName = urlUserName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrlPassWord() {
        return urlPassWord;
    }

    public void setUrlPassWord(String urlPassWord) {
        this.urlPassWord = urlPassWord;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
