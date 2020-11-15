package project.resource;

import project.model.User;

public class UserResource extends BaseResource {

    private Integer id;
    private String email;
    private String password;
    private String phone;
    private boolean agreement;

    public UserResource() {
    }

    public UserResource(Integer id, String email, String password, String phone, boolean agreement) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.agreement = agreement;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAgreement() {
        return agreement;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public User toEntity() {
        return new User (
                this.id,
                this.email,
                this.password,
                this.password,
                this.agreement
        );
    }
}
