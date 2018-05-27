package com.edu.system.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "infos")
@Data
public class Info extends AbstractCadr{

    @ManyToOne
    private Category category;

    @OneToMany
    private List<Attachment> attachment;
}
