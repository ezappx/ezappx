package com.ezapp.webdesigner.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class MobileConfig(
        val platform: String,
        val platformVersion: String,
        val htmlContent: String,
        @Id @GeneratedValue val id: Long? = null)