package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleCalculator

class DatasourceCalculator {
    fun loadModules(): List<moduleCalculator>{
        return listOf<moduleCalculator>(
            moduleCalculator(R.string.molality, R.drawable.bioinfo),
            moduleCalculator(R.string.molarity, R.drawable.bioinfo),
            moduleCalculator(R.string.normality, R.drawable.bioinfo),
            moduleCalculator(R.string.dilution, R.drawable.bioinfo)
        )
    }
}