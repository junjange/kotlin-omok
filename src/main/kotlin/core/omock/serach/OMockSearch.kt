package core.omock.serach

import core.omock.Direction
import core.omock.OMockResult

abstract class OMockSearch {
    abstract fun loadMap(
        stoneStates: List<List<Int>>,
        row: Int,
        column: Int,
    ): Map<Direction, OMockResult>

    companion object Default : OMockSearch() {
        override fun loadMap(
            stoneStates: List<List<Int>>,
            row: Int,
            column: Int,
        ): Map<Direction, OMockResult> = OMockSearchImpl.loadMap(stoneStates, row, column)
    }
}
