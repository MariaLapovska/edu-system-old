package com.edu.system.vo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.edu.system.vo.types.LinkCadrType;

import lombok.Data;

@Entity
@Table(name = "link_cadr")
@Data
public class LinkCadr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private AbstractCadr fromCadr;

    @ManyToOne
    private AbstractCadr toCadr;

    @ManyToOne
    private Article article;

    @Enumerated(EnumType.STRING)
    private LinkCadrType type;
}
