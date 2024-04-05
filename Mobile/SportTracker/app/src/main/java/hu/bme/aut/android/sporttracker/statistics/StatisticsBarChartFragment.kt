package hu.bme.aut.android.sporttracker.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import hu.bme.aut.android.sporttracker.databinding.StatisticsBarChartFragmentBinding
import hu.bme.aut.android.sporttracker.sport.Sport

class StatisticsBarChartFragment(private var dataSet: MutableList<Sport>) : Fragment() {
    companion object {
        private const val RUN = 1f
        private const val WALK = 2f
        private const val BIKING = 3f
        private const val SWIM = 4f
        private const val FOOTBALL = 5f
        private const val BASKETBALL = 6f

    }

    private lateinit var binding: StatisticsBarChartFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StatisticsBarChartFragmentBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayChartData()
    }

    private lateinit var barChart: BarChart

    private lateinit var distanceBar: BarDataSet
    private lateinit var calorieBar: BarDataSet

    private lateinit var barEntriesList: ArrayList<BarEntry>

    private var activities = arrayOf("Run", "Walk", "Biking", "Swim", "Football", "Basketball")

    inner class ProcessData(
        val category: Float,
        var distanceList: MutableList<Float>,
        var caloriesList: MutableList<Float>
    )

    private var processDataList = mutableListOf<ProcessData>()
    private fun displayChartData() {
        initDataList()
        sortData()
        barChart = binding.barChart

        distanceBar = BarDataSet(getAverageDistanceBar(), "Average Distance")
        distanceBar.color = Color.RED
        calorieBar = BarDataSet(getAverageCaloriesBurntBar(), "Average Calories Burned")
        calorieBar.color = Color.YELLOW

        val data = BarData(distanceBar, calorieBar)

        barChart.data = data

        barChart.description.isEnabled = false

        val xAxis = barChart.xAxis

        xAxis.valueFormatter = IndexAxisValueFormatter(activities)

        xAxis.setCenterAxisLabels(true)

        xAxis.position = XAxis.XAxisPosition.BOTTOM

        xAxis.granularity = 1f

        xAxis.isGranularityEnabled = true

        barChart.isDragEnabled = true

        barChart.setVisibleXRangeMaximum(3f)

        val barSpace = 0.1f

        val groupSpace = 0.5f

        data.barWidth = 0.15f

        barChart.xAxis.axisMinimum = 0f

        barChart.animate()

        barChart.groupBars(0f, groupSpace, barSpace)

        barChart.invalidate()
        calorieBar.setDrawValues(false)
        distanceBar.setDrawValues(false)
    }

    private fun initDataList() {
        processDataList.add(ProcessData(RUN, mutableListOf(), mutableListOf()))
        processDataList.add(ProcessData(WALK, mutableListOf(), mutableListOf()))
        processDataList.add(ProcessData(BIKING, mutableListOf(), mutableListOf()))
        processDataList.add(ProcessData(SWIM, mutableListOf(), mutableListOf()))
        processDataList.add(ProcessData(FOOTBALL, mutableListOf(), mutableListOf()))
        processDataList.add(ProcessData(BASKETBALL, mutableListOf(), mutableListOf()))
    }

    private fun sortData() {
        dataSet.forEach {
            when (it.category) {
                Sport.Type.RUN -> {
                    processDataList[0].distanceList.add(it.distance.toFloat())
                    processDataList[0].caloriesList.add(it.calories.toFloat())
                }

                Sport.Type.WALK -> {
                    processDataList[1].distanceList.add(it.distance.toFloat())
                    processDataList[1].caloriesList.add(it.calories.toFloat())
                }

                Sport.Type.BIKING -> {
                    processDataList[2].distanceList.add(it.distance.toFloat())
                    processDataList[2].caloriesList.add(it.calories.toFloat())
                }

                Sport.Type.SWM -> {
                    processDataList[3].distanceList.add(it.distance.toFloat())
                    processDataList[3].caloriesList.add(it.calories.toFloat())
                }

                Sport.Type.FOOTBALL -> {
                    processDataList[4].distanceList.add(it.distance.toFloat())
                    processDataList[4].caloriesList.add(it.calories.toFloat())
                }

                Sport.Type.BASKETBALL -> {
                    processDataList[5].distanceList.add(it.distance.toFloat())
                    processDataList[5].caloriesList.add(it.calories.toFloat())
                }
            }
        }
    }

    private fun getAverageDistanceBar(): ArrayList<BarEntry> {
        barEntriesList = ArrayList()

        for (processData in processDataList) {
            barEntriesList.add(
                BarEntry(
                    processData.category,
                    processData.distanceList.average().toFloat()
                )
            )
        }

        return barEntriesList
    }

    private fun getAverageCaloriesBurntBar(): ArrayList<BarEntry> {
        barEntriesList = ArrayList()

        for (processData in processDataList) {
            barEntriesList.add(
                BarEntry(
                    processData.category,
                    processData.caloriesList.average().toFloat()
                )
            )
        }
        return barEntriesList
    }

}
