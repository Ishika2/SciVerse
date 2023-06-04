package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleBasic

class DatasourceBasic {
    fun loadModules(): List<moduleBasic>{
        return listOf<moduleBasic>(
            moduleBasic(R.string.molality, R.drawable.bioinfo),
            moduleBasic(R.string.molarity, R.drawable.bioinfo),
            moduleBasic(R.string.normality, R.drawable.bioinfo),
            moduleBasic(R.string.dilution, R.drawable.bioinfo)
        )
    }
}