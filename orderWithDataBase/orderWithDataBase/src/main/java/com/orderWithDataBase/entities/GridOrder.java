package com.orderWithDataBase.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GridOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "dd/yy/mm")
    private LocalDate localDate;
    private String algoId;
    private String instId;
    private String algoOrdType;
    private String maxPx;
    private String minPx;
    private String gridNum;
    private String runType;
    private String tpTriggerPx;
    private String slTriggerPx;
    private String tag;
    private String quoteSz;
    private String baseSz;
    private String sz;
    private String direction;
    private String lever;
    private String basePos;
    private String state;
}
