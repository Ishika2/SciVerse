package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleBasic

class DatasourceBasic {
    fun loadModules(): List<moduleBasic>{
        return listOf<moduleBasic>(
            moduleBasic(R.string.molality,R.string.molality_summary ,R.drawable.img_20230731_221442),
            moduleBasic(R.string.molarity,R.string.molalrity_summary, R.drawable._img_20230731_2214423),
            moduleBasic(R.string.normality,R.string.normality_summary, R.drawable.dna_strands_which_are_black_in_color_and_white_ful),
            moduleBasic(R.string.dilution,R.string.dilution_summary, R.drawable._img_20230731_221442)
        )
    }
}