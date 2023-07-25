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
                R.color.white,
                R.string.molarity,
                R.color.white,
                R.string.normality,
                R.color.white,
                R.string.dilution,
                R.color.white
            )
        )
    }

    fun loadModulesForGenericView(): List<moduleHomeGeneric> {
        return listOf(
            moduleHomeGeneric(
                SECOND_VIEW,
                R.drawable._4072019_38
            )
        )
    }

    fun loadModulesForModuleView(): List<moduleHomeModule> {
        return listOf(
            moduleHomeModule(
                THIRD_VIEW,
                R.string.ATGC,
                R.drawable.red,
                R.string.GCpercent,
                R.drawable.blue,
                R.string.RevComplement,
                R.drawable.teal,
                R.string.dummy,
                R.drawable.red
            )
        )
    }
}
