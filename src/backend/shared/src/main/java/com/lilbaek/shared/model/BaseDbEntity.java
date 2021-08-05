package com.lilbaek.shared.model;

import javax.persistence.*;

public class BaseDbEntity<TId> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private TId Id;

    public TId getId() {
        return Id;
    }

    public void setId(TId id) {
        Id = id;
    }
}
