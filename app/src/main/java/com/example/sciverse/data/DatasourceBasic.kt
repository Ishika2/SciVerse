package com.example.sciverse.data

import com.example.sciverse.R
import com.example.sciverse.model.moduleBasic

class DatasourceBasic {
    fun loadModules(): List<moduleBasic>{
        return listOf<moduleBasic>(
            moduleBasic(R.string.molality,R.string.this_is_a_summary_of_the_molarity_topic ,R.drawable.gradient_drawable1),
            moduleBasic(R.string.molarity,R.string.this_is_a_summary_of_the_molarity_topic, R.drawable.gradient_drawable2),
            moduleBasic(R.string.normality,R.string.this_is_a_summary_of_the_molarity_topic, R.drawable.gradient_drawable3),
            moduleBasic(R.string.dilution,R.string.this_is_a_summary_of_the_molarity_topic, R.drawable.abstract_flowing_waves_background_2006)
        )
    }
}