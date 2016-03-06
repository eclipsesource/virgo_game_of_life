function next(board) {
	var width = board[0].length,
		newBoard = board.map(function(row, rowIndex) {
			return row.map(function(cell, colIndex) {
				var topRow = (rowIndex - 1) >= 0 ? (rowIndex - 1) : board.length - 1,
					bottomRow = (rowIndex + 1) % board.length,
					leftCol = (colIndex - 1) >= 0 ? (colIndex - 1) : width - 1,
					rightCol = (colIndex + 1) % width,
					neighbors = [
						board[topRow][leftCol], board[topRow][colIndex], board[topRow][rightCol],
						board[rowIndex][leftCol], board[rowIndex][rightCol],
						board[bottomRow][leftCol], board[bottomRow][colIndex], board[bottomRow][rightCol]
					],
					neighborCount = neighbors.reduce(function(memo, neighbor) {
						return memo + neighbor;
					}, 0);
				if (cell === 0) {
					return neighborCount === 3 ? 1 : 0;
				}
				return (neighborCount === 2 || neighborCount === 3) ? 1 : 0;
			});
		});
    return newBoard;
}
