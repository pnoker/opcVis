package com.opcVis.model;

import java.io.Serializable;
import java.util.Date;

public class OperateLog implements Serializable {
    private String id;

    private String model;

    private String type;

    private String url;

    private String ip;

    private String form;

    private String method;

    private String description;

    private String data;

    private String username;

    private Date createtime;

    private String exception;

    private String role;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form == null ? null : form.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OperateLog other = (OperateLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getModel() == null ? other.getModel() == null : this.getModel().equals(other.getModel()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getForm() == null ? other.getForm() == null : this.getForm().equals(other.getForm()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getException() == null ? other.getException() == null : this.getException().equals(other.getException()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getModel() == null) ? 0 : getModel().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getForm() == null) ? 0 : getForm().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getException() == null) ? 0 : getException().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", model=").append(model);
        sb.append(", type=").append(type);
        sb.append(", url=").append(url);
        sb.append(", ip=").append(ip);
        sb.append(", form=").append(form);
        sb.append(", method=").append(method);
        sb.append(", description=").append(description);
        sb.append(", data=").append(data);
        sb.append(", username=").append(username);
        sb.append(", createtime=").append(createtime);
        sb.append(", exception=").append(exception);
        sb.append(", role=").append(role);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}