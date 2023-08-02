package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleCalculator

class DatasourceCalculator {
    fun loadModules(): List<moduleCalculator>{
        return listOf<moduleCalculator>(
            moduleCalculator(R.string.molality,R.string.this_is_a_summary_of_the_molarity_topic, R.drawable.dna_strand4),
            moduleCalculator(R.string.molarity,R.string.this_is_a_summary_of_the_molarity_topic, R.drawable.dna_strand6),
            moduleCalculator(R.string.normality,R.string.this_is_a_summary_of_the_molarity_topic, R.drawable.dna_strands2),
            moduleCalculator(R.string.dilution,R.string.this_is_a_summary_of_the_molarity_topic, R.drawable.dna_strands3)
        )
    }
}