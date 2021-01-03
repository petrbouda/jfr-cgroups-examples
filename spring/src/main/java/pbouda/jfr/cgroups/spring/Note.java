package pbouda.jfr.cgroups.spring;


import org.springframework.data.annotation.Id;

public class Note {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String subject;
    private String content;

    public Note() {
    }

    public Note(String firstname, String lastname, String email, String subject, String content) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.subject = subject;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
               "firstname='" + firstname + '\'' +
               ", lastname='" + lastname + '\'' +
               ", email='" + email + '\'' +
               ", subject='" + subject + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}
