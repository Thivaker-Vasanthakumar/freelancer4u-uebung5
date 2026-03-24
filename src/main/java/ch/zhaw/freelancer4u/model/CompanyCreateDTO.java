package ch.zhaw.freelancer4u.model;

public class CompanyCreateDTO {

    private String name;
    private String email;

    public CompanyCreateDTO() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}