package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.allmodules
import com.example.sciverse.model.moduleBasic

class DatasourceFull {
    fun loadModules(): List<allmodules> {
        return listOf<allmodules>(
            allmodules(R.string.dilution),
            allmodules(R.string.ATGC),
            allmodules(R.string.DNA_cutting),
            allmodules(R.string.GC_percent),
            allmodules(R.string.RevComplement),
            allmodules(R.string.molality),
            allmodules(R.string.molarity),
            allmodules(R.string.normality)
        )
    }
}