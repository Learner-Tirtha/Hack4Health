package com.example.diacare.ui

class DiabetesRiskCalculator {

    fun calculateRisk(
        smoking: String?,
        waist: Int?,
        pregnancies: String?,
        activity: Int?,
        familyHistory: Int?,
        hdl: Double?,
        pcos: String?
    ): String {
        var riskScore = 0

        if (smoking == "yes") riskScore += 1
        if (waist == 3) riskScore += 2 else if (waist == 2) riskScore += 1
        if (pregnancies == "more than 3") riskScore += 2 else if (pregnancies in listOf("2", "3")) riskScore += 1
        if (activity == 3) riskScore += 2 else if (activity == 2) riskScore += 1
        if (familyHistory == 3) riskScore += 2 else if (familyHistory == 2) riskScore += 1
        if (hdl != null && hdl < 40) riskScore += 2 else if (hdl in 40.0..50.0) riskScore += 1
        if (pcos == "yes") riskScore += 1

        return when {
            riskScore >= 6 -> "High"
            riskScore in 3..5 -> "Moderate"
            else -> "Low"
        }
    }
}