/**
 * @version 1.0 : TestConnect4Field.java, 2015/09/12
 * @author Abhishek Indukumar
 * 
 *
 *//*

public class TestConnect4Field {

	public Connect4Field aConnect4Field = new Connect4Field(26, 9);
	public Player aPlayer = new Player(aConnect4Field, "A", '+');
	public Player bPlayer = new Player(aConnect4Field, "B", '*');

	public void dropTest(int column) {
		System.out.println("Can it be dropped in " + column + ": "
				+ aConnect4Field.checkIfPiecedCanBeDroppedIn(column));
	}

	public void testIt() {
		aConnect4Field = new Connect4Field(26, 9);
		System.out.println(aConnect4Field);
		dropTest(-1);
		dropTest(0);
		dropTest(1);
		aConnect4Field.dropPieces(1, '+');
		System.out.println(aConnect4Field);
		aConnect4Field.dropPieces(1, '*');
		System.out.println(aConnect4Field);
		aConnect4Field.didLastMoveWin();
		aConnect4Field.isItaDraw();
		aConnect4Field.init(aPlayer, bPlayer);
	}

	public static void main(String[] args) {
		new TestConnect4Field().testIt();
	}
}
*/
