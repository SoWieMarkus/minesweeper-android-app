package markus.wieland.minesweeper;

import markus.wieland.games.game.view.GameBoardInteractionListener;
import markus.wieland.minesweeper.view.MinesweeperCellView;

public interface MinesweeperGameBoardFieldInteractionListener extends GameBoardInteractionListener {

    void onClick(MinesweeperCellView minesweeperCellView);

    void onLongClick(MinesweeperCellView minesweeperCellView);

}
