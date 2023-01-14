package org.example.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Review extends BaseEntity {


    @JoinColumn(name = "review_user_id")
    @ManyToOne
    private AppUser appUser;
    private Integer ratting;
    @Column(name = "date_of_review")
    private Date dateOfReview;
    @Column(name = "content_of_review")
    private String contentOfReview;
    @JoinColumn(name = "movie_review_id")
    @ManyToOne
    private Movie movie;

    public Review() {
    }

    public Review(AppUser appUser, Integer ratting, Date dateOfReview, String contentOfReview, Movie movie) {
        this.appUser = appUser;
        this.ratting = ratting;
        this.dateOfReview = dateOfReview;
        this.contentOfReview = contentOfReview;
        this.movie = movie;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Integer getRatting() {
        return ratting;
    }

    public void setRatting(Integer ratting) {
        this.ratting = ratting;
    }

    public Date getDateOfReview() {
        return dateOfReview;
    }

    public void setDateOfReview(Date dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    public String getContentOfReview() {
        return contentOfReview;
    }

    public void setContentOfReview(String contentOfReview) {
        this.contentOfReview = contentOfReview;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Review{" +
                "appUser=" + appUser +
                ", ratting=" + ratting +
                ", dateOfReview=" + dateOfReview +
                ", contentOfReview='" + contentOfReview + '\'' +
                ", movie=" + movie +
                '}';
    }
}
