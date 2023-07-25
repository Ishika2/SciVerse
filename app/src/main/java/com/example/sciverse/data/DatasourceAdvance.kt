package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleAdvance
import com.example.sciverse.model.moduleBasic

class DatasourceAdvance {
    fun loadModules(): List<moduleAdvance>{
        return listOf<moduleAdvance>(
            moduleAdvance(R.string.ATGC, R.drawable.abstract_flowing_waves_background_2006),
            moduleAdvance(R.string.DNAcutting, R.drawable.abstract_flowing_waves_background_2006),
            moduleAdvance(R.string.GCpercent, R.drawable.abstract_flowing_waves_background_2006),
            moduleAdvance(R.string.RevComplement, R.drawable.abstract_flowing_waves_background_2006)
        )
    }
}