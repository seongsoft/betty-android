package io.github.cbinarycastle.macao.data.mapper

import io.github.cbinarycastle.macao.data.OutCome

fun OutCome.toEntity() = io.github.cbinarycastle.macao.entity.OutCome.valueOf(name)