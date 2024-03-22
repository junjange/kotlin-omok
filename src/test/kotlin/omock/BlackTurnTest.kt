package omock

import omock.model.Board
import omock.model.Column
import omock.model.Row
import omock.model.state.Stone
import omock.model.turn.BlackTurn
import omock.model.turn.Turn
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackTurnTest {
    private lateinit var player: Turn
    private lateinit var board: Board

    @BeforeEach
    fun setUp() {
        player = BlackTurn()
        board = Board.from()
    }

    @Test
    fun `BlackPlayer가 돌을 놓을 때, 놓은 지점이 Board에서 3-3이라면, 예외를 발생시킨다 `() {
        board.makeStones(
            player = player,
            stones =
                arrayOf(
                    Stone.from(Row("12"), Column("C")),
                    Stone.from(Row("12"), Column("E")),
                    Stone.from(Row("13"), Column("D")),
                    Stone.from(Row("14"), Column("D")),
                ),
        )

        val stone = Stone.from(Row("12"), Column("D"))
        board.setStoneState(player, stone)
        val visited = board.loadMap(stone)

        assertThrows<IllegalArgumentException> { player.judgementResult(visited) }
    }

    @Test
    fun `BlackPlayer가 돌을 놓을 때, 놓은 지점이 Board에서 장목이라면, 예외를 발생시킨다 `() {
        board.makeStones(
            player = player,
            stones =
                arrayOf(
                    Stone.from(Row("15"), Column("C")),
                    Stone.from(Row("14"), Column("C")),
                    Stone.from(Row("12"), Column("C")),
                    Stone.from(Row("11"), Column("C")),
                    Stone.from(Row("10"), Column("C")),
                ),
        )

        val stone = Stone.from(Row("13"), Column("C"))
        board.setStoneState(player, stone)

        val visited = board.loadMap(stone)

        assertThrows<IllegalArgumentException> { player.judgementResult(visited) }
    }

    @Test
    fun `BlackPlayer가 돌을 놓을 때, 놓은 지점이 Board에서 열린 4라면, 예외를 발생시킨다 `() {
        board.makeStones(
            player = player,
            stones =
                arrayOf(
                    Stone.from(Row("6"), Column("B")),
                    Stone.from(Row("5"), Column("C")),
                    Stone.from(Row("6"), Column("E")),
                    Stone.from(Row("5"), Column("E")),
                ),
        )

        val stone = Stone.from(Row("3"), Column("E"))
        board.setStoneState(player, stone)

        val visited = board.loadMap(stone)

        assertThrows<IllegalArgumentException> { player.judgementResult(visited) }
    }
}
