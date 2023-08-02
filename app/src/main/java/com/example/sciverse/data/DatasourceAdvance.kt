package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleAdvance
import com.example.sciverse.model.moduleBasic

class DatasourceAdvance {
    fun loadModules(): List<moduleAdvance>{
        return listOf<moduleAdvance>(
            moduleAdvance(R.string.ATGC,R.string.ATGC_summary, R.drawable.dna_strands_which_are_black_in_color_and_white_ful),
            moduleAdvance(R.string.DNAcutting,R.string.DNA_cutting, R.drawable._img_20230731_2214423),
            moduleAdvance(R.string.GCpercent,R.string.GC_percent, R.drawable.dna_strands1),
            moduleAdvance(R.string.RevComplement,R.string.rev_compliment, R.drawable.dna_strand5)
        )
    }
}