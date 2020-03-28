package com.main.logic.domain;

import com.main.logic.utils.RatingEnum;

public class Rating {
    private RatingEnum ratingType;
    private User rater;
    private Video rated;

    public RatingEnum getRatingType() {
        return ratingType;
    }

    public void setRatingType(RatingEnum ratingType) {
        this.ratingType = ratingType;
    }

    public User getRater() {
        return rater;
    }

    public void setRater(User rater) {
        this.rater = rater;
    }

    public Video getRated() {
        return rated;
    }

    public void setRated(Video rated) {
        this.rated = rated;
    }
}
