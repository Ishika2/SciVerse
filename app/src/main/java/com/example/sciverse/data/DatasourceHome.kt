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
                R.string.ATGC,
                R.drawable.bioinfo,
                R.string.molality,
                R.drawable.bioinfo,
                R.string.molarity,
                R.drawable.bioinfo,
                R.string.atoms,
                R.drawable.bioinfo
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
                R.drawable.bioinfo,
                R.string.molality,
                R.drawable.bioinfo,
                R.string.molarity,
                R.drawable.bioinfo,
                R.string.atoms,
                R.drawable.bioinfo
            )
        )
    }
}
