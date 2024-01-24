package com.orderWithDataBase.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AttachAlgoOrder
{
    private String attachAlgoClOrdId;
    private String slTriggerPx;
    private String slOrdPx;
}