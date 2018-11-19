package it59070098.kmitl.healthy;

public class postclass {
    private String id;
    private String title;
    private String body;
    private String name;
    private String email;

    public postclass(){}

    public postclass(String id, String title, String body, String name, String email){
        this.id = id;
        this.title = title;
        this.body = body;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
