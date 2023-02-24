package org.tourGo.models.plan.tourList;

import java.time.LocalTime;

import org.tourGo.models.plan.details.PlanDetailsRq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TourListDto {
    private String title;
    private String address;
    private String firstimage;
    private String mapx;
    private String mapy;
    private String tel;
}
