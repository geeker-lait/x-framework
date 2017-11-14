package com.tasfe.framework.support.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Lait on 2017/8/8.
 */
//@Access(AccessType.FIELD)
@MappedSuperclass
@Data
public abstract class IdEntity<PK extends Serializable> /*implements Persistable<PK>*/ {

    //@GenericGenerator(name = "generatedkey", strategy = "uuid")
    //@GenericGenerator(name = "slid", strategy = IdGenerator.TYPE)
    @GeneratedValue(generator = "slid")
    @Column(length = 32)
    @Id
    private PK id;

}
