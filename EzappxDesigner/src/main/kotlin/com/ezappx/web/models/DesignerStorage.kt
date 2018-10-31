package com.ezappx.web.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.annotation.JsonValue
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
data class DesignerStorage(
        val username: String,
        val projectName: String,
        @JsonValue @JsonRawValue val storage: String,
        @JsonIgnore @Id var id: String? = null
)

