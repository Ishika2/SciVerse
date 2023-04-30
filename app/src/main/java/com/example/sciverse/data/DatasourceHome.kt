package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleBasic
import com.example.sciverse.model.moduleHome

class DatasourceHome {
    fun loadModules(): List<moduleHome>{
        return listOf<moduleHome>(
            moduleHome(R.string.ATGC, R.drawable.bioinfo)
        )
    }
}