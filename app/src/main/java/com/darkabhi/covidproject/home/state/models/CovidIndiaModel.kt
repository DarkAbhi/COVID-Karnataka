package com.darkabhi.covidproject.home.state.models

import com.darkabhi.covidproject.data.room.models.StateDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CovidIndiaModel(
    /*@Json(name = "cases_time_series")
    val casesTimeSeries: List<CasesTimeSery>,*/
    @Json(name = "statewise")
    val statewise: List<Statewise>,
    /*@Json(name = "tested")
    val tested: List<Tested>*/
) {
    /*@JsonClass(generateAdapter = true)
    data class CasesTimeSery(
        @Json(name = "dailyconfirmed")
        val dailyconfirmed: String,
        @Json(name = "dailydeceased")
        val dailydeceased: String,
        @Json(name = "dailyrecovered")
        val dailyrecovered: String,
        @Json(name = "date")
        val date: String,
        @Json(name = "dateymd")
        val dateymd: String,
        @Json(name = "totalconfirmed")
        val totalconfirmed: String,
        @Json(name = "totaldeceased")
        val totaldeceased: String,
        @Json(name = "totalrecovered")
        val totalrecovered: String
    )*/

    @JsonClass(generateAdapter = true)
    data class Statewise(
        @Json(name = "active")
        val active: String,
        @Json(name = "confirmed")
        val confirmed: String,
        @Json(name = "deaths")
        val deaths: String,
        @Json(name = "deltaconfirmed")
        val deltaconfirmed: String,
        @Json(name = "deltadeaths")
        val deltadeaths: String,
        @Json(name = "deltarecovered")
        val deltarecovered: String,
        @Json(name = "lastupdatedtime")
        val lastupdatedtime: String,
        @Json(name = "migratedother")
        val migratedother: String,
        @Json(name = "recovered")
        val recovered: String,
        @Json(name = "state")
        val state: String,
        @Json(name = "statecode")
        val statecode: String,
        @Json(name = "statenotes")
        val statenotes: String
    )

    /*@JsonClass(generateAdapter = true)
    data class Tested(
        @Json(name = "dailyrtpcrsamplescollectedicmrapplication")
        val dailyrtpcrsamplescollectedicmrapplication: String,
        @Json(name = "firstdoseadministered")
        val firstdoseadministered: String,
        @Json(name = "frontlineworkersvaccinated1stdose")
        val frontlineworkersvaccinated1stdose: String,
        @Json(name = "frontlineworkersvaccinated2nddose")
        val frontlineworkersvaccinated2nddose: String,
        @Json(name = "healthcareworkersvaccinated1stdose")
        val healthcareworkersvaccinated1stdose: String,
        @Json(name = "healthcareworkersvaccinated2nddose")
        val healthcareworkersvaccinated2nddose: String,
        @Json(name = "over45years1stdose")
        val over45years1stdose: String,
        @Json(name = "over45years2nddose")
        val over45years2nddose: String,
        @Json(name = "over60years1stdose")
        val over60years1stdose: String,
        @Json(name = "over60years2nddose")
        val over60years2nddose: String,
        @Json(name = "positivecasesfromsamplesreported")
        val positivecasesfromsamplesreported: String,
        @Json(name = "registration18-45years")
        val registration1845years: String,
        @Json(name = "registrationabove45years")
        val registrationabove45years: String,
        @Json(name = "registrationflwhcw")
        val registrationflwhcw: String,
        @Json(name = "registrationonline")
        val registrationonline: String,
        @Json(name = "registrationonspot")
        val registrationonspot: String,
        @Json(name = "samplereportedtoday")
        val samplereportedtoday: String,
        @Json(name = "seconddoseadministered")
        val seconddoseadministered: String,
        @Json(name = "source")
        val source: String,
        @Json(name = "source2")
        val source2: String,
        @Json(name = "source3")
        val source3: String,
        @Json(name = "source4")
        val source4: String,
        @Json(name = "testedasof")
        val testedasof: String,
        @Json(name = "testsconductedbyprivatelabs")
        val testsconductedbyprivatelabs: String,
        @Json(name = "to60yearswithco-morbidities1stdose")
        val to60yearswithcoMorbidities1stdose: String,
        @Json(name = "to60yearswithco-morbidities2nddose")
        val to60yearswithcoMorbidities2nddose: String,
        @Json(name = "totaldosesadministered")
        val totaldosesadministered: String,
        @Json(name = "totaldosesavailablewithstates")
        val totaldosesavailablewithstates: String,
        @Json(name = "totaldosesavailablewithstatesprivatehospitals")
        val totaldosesavailablewithstatesprivatehospitals: String,
        @Json(name = "totaldosesinpipeline")
        val totaldosesinpipeline: String,
        @Json(name = "totaldosesprovidedtostatesuts")
        val totaldosesprovidedtostatesuts: String,
        @Json(name = "totalindividualsregistered")
        val totalindividualsregistered: String,
        @Json(name = "totalindividualstested")
        val totalindividualstested: String,
        @Json(name = "totalpositivecases")
        val totalpositivecases: String,
        @Json(name = "totalrtpcrsamplescollectedicmrapplication")
        val totalrtpcrsamplescollectedicmrapplication: String,
        @Json(name = "totalsamplestested")
        val totalsamplestested: String,
        @Json(name = "totalsessionsconducted")
        val totalsessionsconducted: String,
        @Json(name = "totalvaccineconsumptionincludingwastage")
        val totalvaccineconsumptionincludingwastage: String,
        @Json(name = "updatetimestamp")
        val updatetimestamp: String,
        @Json(name = "years1stdose")
        val years1stdose: String,
        @Json(name = "years2nddose")
        val years2nddose: String
    )*/
}

fun CovidIndiaModel.Statewise.toStateDetail(): StateDetail {
    return StateDetail(
        state = state,
        stateCode = statecode,
        active = active,
        confirmed = confirmed,
        deceased = deaths,
        recovered = recovered
    )
}
