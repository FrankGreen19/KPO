package project.model;

import project.resource.UserResource;

public class User extends BaseEntity{

    private String email;
    private String password;
    private String phone;
    private boolean agreement;

    public User() {
        super(null);
    }

    public User(int id, String email, String password, String phone, boolean agreement) {
        super(id);
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.agreement = agreement;
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

    public UserResource toResource() {
        return new UserResource (
                this.getId(),
                this.email,
                this.password,
                this.password,
                this.agreement
        );
    }
}
