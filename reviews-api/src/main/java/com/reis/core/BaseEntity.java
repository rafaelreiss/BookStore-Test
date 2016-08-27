package com.reis.core;

import javax.persistence.*;

/**
 * Created by reis on 8/20/2016.
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @Version
    private Long version;

    protected BaseEntity(){
        id = null;
    }

}
