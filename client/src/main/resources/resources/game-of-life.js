var socket = new SockJS("/gol/ws");
var stompClient = Stomp.over(socket);

function drawCell(cell, colIndex, rowIndex, ctx, cellWidth, cellHeight) {
	ctx.fillStyle = cell ? '#006702' : '#fff';
	ctx.fillRect(colIndex*cellWidth, rowIndex*cellHeight, cellWidth, cellHeight);
	ctx.strokeRect(colIndex*cellWidth, rowIndex*cellHeight, cellWidth, cellHeight);
}

function generateBoard(board, ctx, width, height, callback) {

	var cellHeight = height/ board.length,
		cellWidth = width / board[0].length;

	// Loop through the board and draw each cell
	board.forEach(function(row, rowIndex) {
		row.forEach(function(cell, colIndex) {
			drawCell(cell, colIndex, rowIndex, ctx, cellWidth, cellHeight);
		});
	});

	if (callback) {
		callback(board);
	}
}

/**
 * Setup default canvas styles
 */
function setupCanvas(canvas, ctx, onClick) {
	ctx.lineWidth = 1;
	ctx.strokeStyle = "#ccc";

	if (onClick) {
		canvas.addEventListener('click', onClick);
	}
}

function getClickedCell(event, board, cellWidth, cellHeight) {

	var canvas = event.target,

		leftOffSet = canvas.offsetLeft,
		topOffset = canvas.offsetTop,

		leftClickPoint = event.pageX,
		topClickPoint = event.pageY,

		x = leftClickPoint - leftOffSet,
		y = topClickPoint - topOffset;

	return {
		col: ~~(x/cellWidth),
		row: ~~(y/cellHeight)
	};
}

var canvas = document.getElementById('gameOfLife'),
	broadcastBoard = document.getElementById('broadcastedMessages'),
	ctx = canvas.getContext('2d'),
	width = canvas.width,
	height = canvas.height,
	currentBoard;

/**
 * On click, emit an event that will update a cell on the server
 * @param event
 */
function canvasOnClick(event) {

	var canvas = event.target,
		width = canvas.width,
		height = canvas.height,
		newCellVal = 0,
		cellHeight,
		cellWidth,
		cell;

	if (currentBoard) {

		cellHeight = height/ currentBoard.length;
		cellWidth = width / currentBoard[0].length;

		cell = getClickedCell(event, currentBoard, cellWidth, cellHeight);
		newCellVal = currentBoard[cell.row][cell.col] === 1 ? 0 : 1;

		stompClient.send("/app/updateCell", {}, JSON.stringify({ 'row': cell.row, 'col': cell.col }));
		drawCell(newCellVal, cell.col, cell.row, ctx, cellWidth, cellHeight);
	}
}

setupCanvas(canvas, ctx, canvasOnClick);

function generateBoardCallback (newBoard) {
	currentBoard = newBoard;
}

function onMessageAnimationEnd() {
	this.remove();
}

// Callback function to be called when stomp client is connected to server
var connectCallback = function() {
    stompClient.subscribe('/topic/newBoard', function (frame) {
        var board = JSON.parse(frame.body)
	    generateBoard(board, ctx, width, height, generateBoardCallback);
    });

    stompClient.subscribe('/topic/userModifiedCell', function (frame) {
        var cell = JSON.parse(frame.body)
	    var msg = 'Someone Changed [' + cell.row + ', ' + cell.col + ']',
		    $message = document.createElement('div');

    	$message.textContent = msg;

	    broadcastBoard.appendChild($message);
	    setTimeout(function() {
		    $message.className = 'fadeOut';

		    $message.addEventListener("transitionend", onMessageAnimationEnd, false);
		    $message.addEventListener("webkitTransitionEnd", onMessageAnimationEnd, false);

     	}, 1000);
    });
};

// Callback function to be called when stomp client could not connect to server
var errorCallback = function(error) {
    alert(error.headers.message);
};

// Connect to server via websocket
stompClient.connect("guest", "guest", connectCallback, errorCallback);
