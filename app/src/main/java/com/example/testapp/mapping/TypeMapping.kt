package com.example.testapp.mapping

import com.example.testapp.repository.model.Profile

fun Profile.toDomainModel(): com.example.testapp.domain.model.Profile =
        com.example.testapp.domain.model.Profile("$firstName $lastName", email)