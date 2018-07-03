package com.ezapp.webdesigner.repositories

import com.ezapp.webdesigner.models.MobileConfig
import org.springframework.data.repository.CrudRepository

interface MobileConfigRepo : CrudRepository<MobileConfig, Long>