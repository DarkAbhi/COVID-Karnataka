package com.darkabhi.covidproject.app

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
object AppConfig {
    const val COVID_BASE_URL = "https://api.covid19india.org/"
    const val NEWS_BASE_URL = "https://newsapi.org/"
    const val NEWS_API_KEY = "39d9600a067048a287984ab5d8f91624"

    // Collection Names
    const val AMBULANCE_SERVICE_COLLECTION = "AmbulanceService"
    const val BED_AVAILABILITY_COLLECTION = "BedAvailability"
    const val BLOOD_DONORS_COLLECTION = "BloodDonors"
    const val FOOD_COLLECTION = "Food"
    const val HOME_TESTING_COLLECTION = "HomeTesting"
    const val MEDICINE_COLLECTION = "Medicine"
    const val ONLINE_DOC_COLLECTION = "OnlineDoctorConsultation"
    const val OXYGEN_COLLECTION = "Oxygen"
    const val PLASMA_DONORS_COLLECTION = "PlasmaDonors"
    const val REMEDESIVIR_COLLECTION = "Remdesivir"
    const val TELE_COUNSELLING_COLLECTION = "TeleCounselling"

    const val PREFERENCE_NAME = "COVID_APP"
}