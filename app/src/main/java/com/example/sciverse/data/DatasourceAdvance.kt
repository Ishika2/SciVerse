package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleAdvance
import com.example.sciverse.model.moduleBasic

class DatasourceAdvance {
    fun loadModules(): List<moduleAdvance>{
        return listOf<moduleAdvance>(
            moduleAdvance(R.string.ATGC, R.drawable.bioinfo)
        )
    }
}