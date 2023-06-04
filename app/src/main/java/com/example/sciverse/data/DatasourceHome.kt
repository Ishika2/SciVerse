package com.example.sciverse.data

import com.example.sciverse.Adapter.AdapterHome.Companion.FIRST_VIEW
import com.example.sciverse.Adapter.AdapterHome.Companion.SECOND_VIEW
import com.example.sciverse.Adapter.AdapterHome.Companion.THIRD_VIEW
import com.example.sciverse.R
import com.example.sciverse.model.moduleHomeCalculator
import com.example.sciverse.model.moduleHomeGeneric
import com.example.sciverse.model.moduleHomeModule

class DatasourceHome {
    fun loadModulesForCalculatorView(): List<moduleHomeCalculator> {
        return listOf(
            moduleHomeCalculator(
                FIRST_VIEW,
                R.string.molality,
                R.drawable._4072019_38,
                R.string.molarity,
                R.drawable._4072019_38,
                R.string.normality,
                R.drawable._4072019_38,
                R.string.dilution,
                R.drawable._4072019_38
            )
        )
    }

    fun loadModulesForGenericView(): List<moduleHomeGeneric> {
        return listOf(
            moduleHomeGeneric(
                SECOND_VIEW,
                R.drawable.bioinfo
            )
        )
    }

    fun loadModulesForModuleView(): List<moduleHomeModule> {
        return listOf(
            moduleHomeModule(
                THIRD_VIEW,
                R.string.ATGC,
                R.drawable.vecteezy_dna_helix_symbol_isolated_on_white_background_6154345,
                R.string.GCpercent,
                R.drawable.vecteezy_dna_helix_symbol_isolated_on_white_background_6154345,
                R.string.RevComplement,
                R.drawable.vecteezy_dna_helix_symbol_isolated_on_white_background_6154345,
                R.string.dummy,
                R.drawable.vecteezy_dna_helix_symbol_isolated_on_white_background_6154345
            )
        )
    }
}
