package io.github.cbinarycastle.betty.data.mapper

import io.github.cbinarycastle.betty.PlaceDto
import io.github.cbinarycastle.betty.entity.Place

fun PlaceDto.toEntity() = Place(
    home = with(homeDto) {
        Place.Value(
            totalMatchCount = totalMatchCount,
            winMatchCount = winMatchCount,
            drawMatchCount = drawMatchCount,
            loseMatchCount = loseMatchCount,
            goalFor = goalFor,
            goalAgainst = goalAgainst,
            points = points,
        )
    },
    away = with(awayDto) {
        Place.Value(
            totalMatchCount = totalMatchCount,
            winMatchCount = winMatchCount,
            drawMatchCount = drawMatchCount,
            loseMatchCount = loseMatchCount,
            goalFor = goalFor,
            goalAgainst = goalAgainst,
            points = points,
        )
    }
)