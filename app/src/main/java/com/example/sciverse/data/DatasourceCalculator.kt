package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleCalculator

class DatasourceCalculator {
    fun loadModules(): List<moduleCalculator>{
        return listOf<moduleCalculator>(
            moduleCalculator(R.string.molality, R.drawable.abstract_flowing_waves_background_2006),
            moduleCalculator(R.string.molarity, R.drawable.abstract_flowing_waves_background_2006),
            moduleCalculator(R.string.normality, R.drawable.abstract_flowing_waves_background_2006),
            moduleCalculator(R.string.dilution, R.drawable.abstract_flowing_waves_background_2006)
        )
    }
}