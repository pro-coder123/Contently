package com.stackroute.profileservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private List<Integer> viewId;
    private List<Integer> purchaseId;
}
