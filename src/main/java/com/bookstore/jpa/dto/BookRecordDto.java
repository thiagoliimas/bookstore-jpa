package com.bookstore.jpa.dto;

import com.bookstore.jpa.models.AuthorModel;
import com.bookstore.jpa.models.PublisherModel;
import com.bookstore.jpa.models.ReviewModel;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public record BookRecordDto(String title,
                            UUID publisherId,
                            Set<UUID> authorsIds,
                            String reviewComment
                            ) implements Serializable {
}