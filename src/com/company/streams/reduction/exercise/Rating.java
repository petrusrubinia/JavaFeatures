package com.company.streams.reduction.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * random comment.
 */
public class Rating {

    double points;
    List<Review> reviewList = new ArrayList<>();

    public void add(Review review) {
        reviewList.add(review);
        computeRating();
    }

    private double computeRating() {
        int totalPoints = reviewList.stream().reduce(
                0, (a, b) -> a + b.getPoints(), Integer::sum);
        this.points = totalPoints / reviewList.size();
        return points;
    }

//    public static Rating average(){
//
//    }
}
