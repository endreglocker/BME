package hu.bme.aut.android.sporttracker.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import hu.bme.aut.android.sporttracker.database.join_table.UserSport
import hu.bme.aut.android.sporttracker.databinding.StatisticsPieChartFragmentBinding
import hu.bme.aut.android.sporttracker.sport.Sport

class StatisticsPieChartFragment(private var dataSet: MutableList<Sport>) : Fragment() {

    private lateinit var binding: StatisticsPieChartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StatisticsPieChartFragmentBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayChartData()

    }

    inner class PieData(var value: Float, val label: String)

    private val list = listOf(
        PieData(0f, "Run"),
        PieData(0f, "Walk"),
        PieData(0f, "Biking"),
        PieData(0f, "Swim"),
        PieData(0f, "Football"),
        PieData(0f, "Basketball")
    )

    private fun displayChartData() {
        dataSet.forEach {
            when (it.category) {
                Sport.Type.RUN -> list[0].value += 1f
                Sport.Type.WALK -> list[1].value += 1f
                Sport.Type.BIKING -> list[2].value += 1f
                Sport.Type.SWM -> list[3].value += 1f
                Sport.Type.FOOTBALL -> list[4].value += 1f
                Sport.Type.BASKETBALL -> list[5].value += 1f
            }
        }

        val entries: MutableList<PieEntry> = mutableListOf()

        for (i in list) {
            if (i.value > 0f) {
                entries.add(PieEntry(i.value, i.label))
            }
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()

        dataSet.colors = listOf(
            Color.rgb(255, 0, 0),
            Color.rgb(255, 165, 0),
            Color.rgb(255, 100, 0),
            Color.rgb(0, 128, 0),
            Color.rgb(0, 0, 255),
            Color.rgb(75, 0, 130)
        )

        val data = PieData(dataSet)
        binding.pieChart.data = data
        binding.pieChart.data.setValueTextSize(0f)
        binding.pieChart.invalidate()
    }

}