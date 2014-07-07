package ru.kds.domain;

import ru.kds.util.TagsHelper;

import javax.persistence.*;
import java.util.Date;
import java.util.SortedSet;

/**
 *
 */
@Entity
public class Bug implements Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User reporter;

    @ManyToOne(fetch = FetchType.EAGER)
    private User assignee;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    private String title;

    private String text;

    private String tags;

    @Transient
    private SortedSet<String> tagSet;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public SortedSet<String> getTagSet() {
        if (tagSet == null){
            tagSet = TagsHelper.parseTags(tags);
        }
        return tagSet;
    }

    public void setTagSet(SortedSet<String> tagSet) {
        this.tagSet = tagSet;
    }
}
