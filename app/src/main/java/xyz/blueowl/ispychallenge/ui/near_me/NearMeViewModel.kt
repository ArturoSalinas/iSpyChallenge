package xyz.blueowl.ispychallenge.ui.near_me

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.blueowl.ispychallenge.data.repository.DataRepository
import xyz.blueowl.ispychallenge.ui.data_browser.adapter_items.NewAdapter

class NearMeViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _data = MutableLiveData<List<NewAdapter.NMItems>>().apply {
        value = createListInformation()
    }

    val list : LiveData<List<NewAdapter.NMItems>> = _data

    private fun createListInformation() : List<NewAdapter.NMItems>{
        val listResult = mutableListOf<NewAdapter.NMItems>()

        dataRepository.getAllChallenges().forEach {
            val wins = it.matches.filter { getTrue -> getTrue.verified }.size
            val average = it.ratings.map {
                it.stars
            }.average()
            val arrayResult = FloatArray(2)
            Location.distanceBetween(1.5,-2.5,it.latitude,it.longitude, arrayResult)
            val distance = arrayResult[0]
            val hint = it.hint
            val creator = dataRepository.getUserByUserId(it.userId)?.username ?: "default"

            listResult.add(NewAdapter.NMItems(wins,average,distance.toString(),hint, creator))
        }
        return listResult
    }
}