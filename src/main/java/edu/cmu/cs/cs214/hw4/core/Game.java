package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the whole game
 * 
 * @author xinyuewu
 *
 */
public class Game {
	private Board board;
	private Dictionary dictionary;
	private ArrayList<Player> playerList;
	private Player currentPlayer;
	private boolean isClockWise;
	private int currentPlayerIndex;
	private TileBag tileBag;
	private SpecialTileStore specialTileStore;
	private int playerNum;
	private boolean isGameOver;
	private int scoreMultiplier;
	private int passTime;
	private ArrayList<LetterTile> tempLetter;
	private SpecialTile tempSpecial;
	private static final String FILE_NAME = "src/main/resources/words.txt";
	private static final int MAX_PLAYER = 4;
	private static final int TILE_IN_RACK = 7;
	private List<GameChangeListener> gameChangeListener;

	/**
	 * Constructor
	 */
	public Game() {
		tileBag = new TileBag();
		playerNum = 0;
		playerList = new ArrayList<Player>();
		specialTileStore = new SpecialTileStore();
		dictionary = new Dictionary(FILE_NAME);
		isGameOver = false;
		isClockWise = true;
		scoreMultiplier = 1;
		passTime = 0;
		board = new Board();
		gameChangeListener = new ArrayList<GameChangeListener>();
		tempLetter = new ArrayList<LetterTile>();
	}

	/**
	 * initialize the game
	 * 
	 * @return true if initialize the game successfully
	 */
	public boolean initialGame() {
		if (playerNum > 1 && playerNum < MAX_PLAYER + 1) {
			currentPlayerIndex = 0;
			currentPlayer = playerList.get(currentPlayerIndex);
			return true;
		}
		return false;
	}

	/**
	 * add new player at the start of the game
	 * 
	 * @param n
	 *            is this player's name
	 */
	public void addPlayer(String n) {
		if (playerNum < MAX_PLAYER + 1) {
			Player player = new Player(n);
			ArrayList<LetterTile> startTile = tileBag
					.getRandomLetterTile(TILE_IN_RACK);
			player.refillRack(startTile);
			playerList.add(player);
			playerNum += 1;
		}
	}

	/**
	 * trigger next turn
	 */
	public void nextTurn() {
		if (isClockWise) {
			if (currentPlayerIndex < playerNum - 1 && currentPlayerIndex > -1) {
				currentPlayerIndex += 1;
			} else if (currentPlayerIndex == playerNum - 1) {
				currentPlayerIndex = 0;
			}
		} else {
			if (currentPlayerIndex > 0 && currentPlayerIndex < playerNum) {
				currentPlayerIndex -= 1;
			} else if (currentPlayerIndex == 0) {
				currentPlayerIndex = playerNum - 1;
			}
		}
		tempLetter.clear();
		currentPlayer = playerList.get(currentPlayerIndex);
		notifyBoardChanged();
		notifyRackChanged();
		notifyInventoryChanged();
	}

	/**
	 * If the player choose pass this turn
	 */
	public void pass() {
		passTime += 1;
		if (passTime == playerNum) {
			isGameOver = true;
		}
		if(isGameOver){
			Player winner = getWinner();
			notifyGameEnded(winner);
		}
		retrieveTiles();
		nextTurn();
		notifyPlayerChanged();

	}

	/**
	 * retrieve all invalid placements from move class, include letter tile and
	 * special tile
	 * Before click exchange button, retrieve all selected letter tiles
	 */
	public void retrieveTiles() {
		ArrayList<Location> locs = new ArrayList<Location>();
		if (currentPlayer.getMove().isEmpty() == false) {
			for (Placement p : currentPlayer.getMove().getLetterPlacements()) {
				currentPlayer.getRack().addLetterTile(p.getLetterTile());
				locs.add(p.getLocation());
			}
			for (Placement p : currentPlayer.getMove().getSpecialPlacements()) {
				currentPlayer.getInventory().addSpecialTile(p.getSpecialTile());
				locs.add(p.getLocation());
			}
			currentPlayer.getMove().clear();
		}
		for (Location loc : locs) {
			notifySquareChanged(loc.getRow(), loc.getCol());
		}
		tempLetter.clear();
		notifyRackChanged();
		notifyInventoryChanged();
	}

	/**
	 * If the player choose exchange tiles
	 * 
	 * @param tiles
	 *            is a list of tiles that the player want to exchange
	 * @return if exchange success
	 */
	public boolean exchange(ArrayList<LetterTile> tiles) {
		passTime = 0;
		if (tileBag.getSize() < TILE_IN_RACK) {
			isGameOver = true;
		}

		if (tiles.size() < tileBag.getSize()
				|| tiles.size() == tileBag.getSize()) {
			ArrayList<LetterTile> newTiles = new ArrayList<LetterTile>();
			newTiles = tileBag.getRandomLetterTile(tiles.size());
			currentPlayer.removeTileListFromRack(tiles);
			
			retrieveTiles();
			
			currentPlayer.refillRack(newTiles);
			tileBag.addTile(tiles);
			
			
			
			nextTurn();

			notifyPlayerChanged();
			return true;
		}
		isGameOver = true;
		if(isGameOver){
			Player winner = getWinner();
			notifyGameEnded(winner);
		}
		return false;

	}

	/**
	 * If the player choose play tiles in this turn
	 */
	public void play() {
		passTime = 0;
		Move move = currentPlayer.getMove();
		if (isValid(move)) {
			move(move);
			notifyPlayerChanged();
			notifyScoreChanged();
		} else {
			retrieveTiles();
		}

	}

	/**
	 * If player choose play tiles Check if these tiles are valid
	 * 
	 * @return true if these movement are valid
	 * @param move
	 *            is move in this turn
	 */
	public boolean isValid(Move move) {
		// check if these tiles are sub tile of player's rack

		// check if these letter tiles' location are collinear
		if (move.isCollinear() == false) {
			return false;
		}

		// check if these location of letter tiles are on the board
		if (board.isOnBoard(move) == false) {
			return false;
		}

		// check if these letter tiles' location have not been occupied
		if (board.isOccupied(move)) {
			return false;
		}

		// If in the first turn, there should have one letter tile placed at the
		// center of the board
		if (board.isEmpty()) {
			if (board.isCenter(move) == false) {
				return false;
			}
		}

		// Except the first turn
		// Check if at least one letter tile is adjacent to existing letter
		// tiles
		if (board.isEmpty() == false) {
			if (board.isAdjacentToExistingTile(move) == false) {
				return false;
			}
		}

		// Check if these letter tiles and original letter tiles are consecutive
		// No empty squares between these tiles
		if (board.isConsecutive(move) == false) {
			return false;
		}

		// Check if all words are in the dictionary
		ArrayList<Word> words = new ArrayList<Word>();
		words = board.getWords(move);
		if (words.size() == 0) {
			return false;
		}
		for (Word w : words) {
			String content = w.getContent(move);
			if (dictionary.isInDictionary(content) == false) {
				board.clearWord();
				return false;
			}
		}

		// Check validation of special tiles
		if (board.isSpecialValid(move) == false) {
			return false;
		}
		return true;
	}

	/**
	 * If there is special tile which will affect total score in this turn call
	 * this method
	 * 
	 * @param mul
	 *            is score multiplier
	 */
	public void setScoreMultilier(int mul) {
		scoreMultiplier = mul;
	}

	/**
	 * set the player's order clockwise or not
	 */
	public void reverseOrder() {
		if (isClockWise) {
			isClockWise = false;
		} else {
			isClockWise = true;
		}
	}

	/**
	 * perform move, trigger special tiles, compute score, trigger next round
	 * 
	 * @param move
	 *            is move in this turn
	 */
	public void move(Move move) {
		int totalScore = 0;
		board.placeTiles(move);
		board.trigerSpecialTileBefore(this, move);
		ArrayList<LetterTile> newTiles = tileBag.getRandomLetterTile(move
				.getLetterPlacements().size());
		currentPlayer.refillRack(newTiles);
		totalScore = board.computeScore();
		totalScore *= scoreMultiplier;
		scoreMultiplier = 1;
		currentPlayer.addScore(totalScore);
		board.trigerSpecialTileAfter(this, move);

		// clear
		board.clearWord();// clear all words in this turn
		move.clear();// clear local move
		currentPlayer.clearMove();// clear this player's move

		// next turn
		nextTurn();
	}

	/**
	 * buy special tile from special tile store
	 * 
	 * @param name
	 *            is the tile's name
	 * @return special tile
	 */
	public SpecialTile buySpecialTile(String name) {
		SpecialTile st = specialTileStore.newTile(name);
		if (currentPlayer.getScore() > st.getPrice()
				|| currentPlayer.getScore() == st.getPrice()) {
			st.setOwner(currentPlayer);
			currentPlayer.addSpecialTile(st);
			currentPlayer.addScore(-1 * st.getPrice());
			

			notifyScoreChanged();
			notifyInventoryChanged();
			return st;
		}
		return null;
	}

	/**
	 * if the game is over
	 * 
	 * @return true if game over
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * get the current player
	 * 
	 * @return current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * get tile bag from game
	 * 
	 * @return tile bag
	 */
	public TileBag getTileBag() {
		return tileBag;
	}

	/**
	 * get all players in the game
	 * 
	 * @return player
	 */
	public ArrayList<Player> getAllPlayer() {
		return playerList;
	}

	/**
	 * get the game's board
	 * 
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * get special tile store
	 * 
	 * @return special tile store
	 */
	public SpecialTileStore getSpecialTileStore() {
		return specialTileStore;
	}

	/**
	 * add new GameChangeListener
	 * 
	 * @param listener
	 *            is GameChangeListener
	 */
	public void addGameChangeListener(GameChangeListener listener) {
		gameChangeListener.add(listener);
	}

	/**
	 * notify GUI that square has changed
	 * 
	 * @param x
	 *            is the square's x axis
	 * @param y
	 *            is the square's y axis
	 */
	public void notifySquareChanged(int x, int y) {
		for (GameChangeListener gcl : gameChangeListener) {
			gcl.squareChanged(x, y);
		}
	}

	/**
	 * notify GUI that player has changed
	 */
	public void notifyPlayerChanged() {
		for (GameChangeListener gcl : gameChangeListener) {
			gcl.playerChanged(currentPlayer);
		}
	}

	/**
	 * notify GUI that rack has changed
	 */
	public void notifyRackChanged() {
		for (GameChangeListener gcl : gameChangeListener) {
			gcl.rackChanged();
		}
	}

	/**
	 * notify GUI that inventory has changed
	 */
	public void notifyInventoryChanged() {
		for (GameChangeListener gcl : gameChangeListener) {
			gcl.inventoryChanged();
		}
	}

	/**
	 * notify GUI that players' score has changed
	 */
	public void notifyScoreChanged() {
		for (GameChangeListener gcl : gameChangeListener) {
			gcl.scoreChanged();
		}
	}

	/**
	 * notify GUI that board changed because different player has different
	 * board
	 */
	public void notifyBoardChanged() {
		for (GameChangeListener gcl : gameChangeListener) {
			gcl.boardChanged();
		}
	}
	
	/**
	 * Notify GUI that game over, show winner and stop the game
	 * @param w is the winner
	 */
	public void notifyGameEnded(Player w){
		for (GameChangeListener gcl : gameChangeListener) {
			gcl.gameEnded(w);
		}
	}

	/**
	 * after player click a letter tile, add this tile into a list
	 * 
	 * @param i
	 *            is the index of the letter tile
	 */
	public void addTempLetterTile(int i) {
		tempSpecial = null;
		LetterTile lt = currentPlayer.getRack().getLetterTiles().get(i);
		if (tempLetter.contains(lt)) {
			tempLetter.remove(lt);
		}
		tempLetter.add(lt);
	}

	/**
	 * after player click a special tile, save this special tile
	 * 
	 * @param name
	 *            is the name of this special tile
	 */
	public void addTempSpeicalTile(String name) {
		tempLetter.clear();
		int index = -1;
		ArrayList<SpecialTile> specialTiles = currentPlayer.getInventory()
				.getSpecialTiles();
		for (int i = 0; i < specialTiles.size(); i++) {
			SpecialTile st = specialTiles.get(i);
			if (st.getName().equals(name)) {
				index = i;
			}
		}
		if (index > -1 && index < specialTiles.size()) {
			tempSpecial = specialTiles.get(index);
		}

	}

	/**
	 * After player click a square on the board, do a letter placement
	 * 
	 * @param r
	 *            is the row of this square
	 * @param c
	 *            is the column of this square
	 */
	public void placeLetterTile(int r, int c) {
		if (tempLetter.size() > 0) {
			Location loc = new Location(r, c);
			Placement p = new Placement(tempLetter.get(tempLetter.size() - 1),
					loc);
			currentPlayer.addLetterPlacement(p);

			//after place this letter tile, clear the temporary letter tile list
			tempLetter.clear();
			notifySquareChanged(r, c);
			notifyRackChanged();
		}

	}

	/**
	 * after player click a square on the board, do a special tile placement
	 * 
	 * @param r
	 *            is the row of this square
	 * @param c
	 *            is the column of this square
	 */
	public void placeSpecialTile(int r, int c) {
		boolean occupied = false;
		if (tempSpecial != null) {
			Location loc = new Location(r, c);
			if (currentPlayer.getMove().getLetterPlacements().size() > 0) {
				for (Placement pla : currentPlayer.getMove()
						.getLetterPlacements()) {
					if (pla.getLocation().equals(loc)) {
						occupied = true;
					}
				}
			}
			if (occupied == false) {
				Placement p = new Placement(tempSpecial, loc);
				currentPlayer.addSpecialPlacement(p);
				tempSpecial = null;
				notifySquareChanged(r, c);
				notifyInventoryChanged();
			}

		}
	}

	/**
	 * get the list which store temporary letter tiles
	 * 
	 * @return a list of letter tiles
	 */
	public ArrayList<LetterTile> getTempLetterTiles() {
		return tempLetter;
	}
	
	/**
	 * get winner of this game
	 * @return winner
	 */
	private Player getWinner(){
		Player winner = currentPlayer;
		int score = currentPlayer.getScore();
		for(int i=0;i<playerNum;i++){
			if(playerList.get(i).getScore()>score){
				winner = playerList.get(i);
			}
		}
		return winner;
	}
}
