import java.util.Random;


public class Sudoku4x4 {
	private int S4x4 [][] = {
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0}
							};
	
	private Random rnd = new Random();
	
	private int constant = 4;
	
	public Sudoku4x4(){
		//this.S4x4 = S; 
		//Konstruktor für spätere Ausgabe
	}
	
	public void printSudoku4x4(){
		for(int r = 0; r < constant; r++){
			for(int c = 0; c < constant; c++){
				System.out.print(S4x4[r][c] + " ");
			}
			System.out.println();
		}
	}
	
	public void generateSudoku(){
		//diagonal(); funktioniert, erzeugt aber einen fehler wenn später alles generiert werden soll
		fill();
		//delete();
	}
	
	//um abfragen zu sparen, werden zuerst die diagonalen Boxen gefüllt
	private void diagonal(){
		fillbox(0,0); //fülle obere linke Ecke
		fillbox(2,2); //fülle untere rechte Ecke
	}
	
	//restliche Boxen werden gefüllt, nach allen drei Spielregeln
	private void fill(){
		int number;
		for(int r = 0; r < constant; r++){
			for(int c = 0; c < constant; c++){	
				if(S4x4[r][c] == 0){	
					do{
						number = randomNumber();
					} while(!isValid(r,c,number) == true);{				
						S4x4[r][c] = number;
							
					}
				}
			}
		}
	}
	
	//füllt eine Box nach den Spielregeln, 2x2
	private void fillbox(int rStart, int cStart) {
		int number;
		for(int r = 0; r < constant; r++){
			for(int c = 0; c < constant; c++){
				
				do{
					number = randomNumber();
				} while(!checkBox(rStart,cStart,number) == true);{				
					S4x4[rStart + r][cStart + c] = number;
					
				}
			}
		}	
	}
	
	//setzt wert auf oben links in der aktuellen Box zurück
	private int adjustForCheckBox(int v) {
		return v - (v % (int)Math.sqrt(constant));
	}
	
	//prüft ob generierte Zahl nach den Spielregeln konform ist
	private boolean isValid(int r, int c, int testNumber){
		return checkrow(r,testNumber) && checkcoloumn(c,testNumber) && checkBox(adjustForCheckBox(r), adjustForCheckBox(c), testNumber);
	}
	
	//prüft Reihen Regel
	private boolean checkrow(int r, int testNumber) {
		for(int i = 0; i < constant; i++){
			if(S4x4[r][i] == testNumber){
				return false;
			}
		}
		return true;
	}
	
	//prüft Spalten Regel
	private boolean checkcoloumn(int c, int testNumber) {
		for(int i = 0; i < constant; i++){
			if(S4x4[i][c] == testNumber){
				return false;
			}
		}
		return true;
	}
	
	//prüft Box Regel
	private boolean checkBox(int rStart, int cStart, int testNumber){
		for(int r = 0; r < (int) Math.sqrt(constant); r++){
			for(int c = 0; c < (int) Math.sqrt(constant); c++){
				if(S4x4[rStart + r][cStart + c] == testNumber){
					return false;
				}
			}
		}
		return true;
	}
	
	private int randomNumber(){
		return(rnd.nextInt(constant) + 1);
	}
	
}
