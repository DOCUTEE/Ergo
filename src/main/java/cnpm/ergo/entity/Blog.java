package cnpm.ergo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogId")
    private int blogId;

    @Column(name = "postingDate", columnDefinition = "DATE NOT NULL")
    private LocalDate postingDate;

    @Column(name = "blogTitle", columnDefinition = "NVARCHAR(200) NOT NULL")
    private String blogTitle;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "approval", columnDefinition = "BIT")
    private boolean approval;

    // Getters and Setters
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }
}
