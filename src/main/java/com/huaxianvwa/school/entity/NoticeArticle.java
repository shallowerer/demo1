package com.huaxianvwa.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author zsj
 * @date 2020/3
 */
@Entity
@Table(name = "notice_article")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class NoticeArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Article title.
     */
    private String articleTitle;

    /**
     * Article content after render to html.
     */
    private String articleContentHtml;

    /**
     * Article content in markdown syntax.
     */
    private String articleContentMd;

    /**
     * Article abstract.
     */
    private String articleAbstract;

    /**
     * Article cover's url.
     */
    private String articleCover;

    /**
     * Article release date.
     */
    private Date articleDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContentHtml() {
        return articleContentHtml;
    }

    public void setArticleContentHtml(String articleContentHtml) {
        this.articleContentHtml = articleContentHtml;
    }

    public String getArticleContentMd() {
        return articleContentMd;
    }

    public void setArticleContentMd(String articleContentMd) {
        this.articleContentMd = articleContentMd;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }
}
