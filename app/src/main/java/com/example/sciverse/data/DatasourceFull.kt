package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.allmodules
import com.example.sciverse.model.moduleBasic

class DatasourceFull {
    fun loadModules(): List<allmodules> {
        return listOf<allmodules>(
            allmodules(R.string.DNAcutting,R.string.DNA_cutting, R.drawable.dna_strands_which_are_black_in_color_and_white_ful__4_),
            allmodules(R.string.ATGC,R.string.ATGC_summary,R.drawable.dna_strands_which_are_black_in_color_and_white_ful__2_)
        )
    }
}