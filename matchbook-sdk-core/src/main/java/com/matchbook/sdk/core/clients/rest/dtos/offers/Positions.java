package com.matchbook.sdk.core.clients.rest.dtos.offers;

import java.util.List;

public class Positions {

    private Long userId;
    private List<Position> positions;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
