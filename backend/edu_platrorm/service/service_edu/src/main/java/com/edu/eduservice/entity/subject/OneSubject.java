package com.edu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {
    private String id;

    private String title;

    private List<TwoSubject> child = new ArrayList<>();
}
