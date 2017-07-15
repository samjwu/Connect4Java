package game;

/**
 * 
 * Enumerator for Connect Four game chips/symbols
 *
 */
public enum Symbols {
	//http://www.alt-codes.net/
	Red('☻'), Yellow('☺'), Space('*');

	private char chip;
	
	//constructor
	Symbols(char chip) {
		this.chip = chip;
	}
	
	//convert Symbols to char
	public char toChar() {
        return this.chip;
    }
	
	@Override
    //convert Symbols to string
    public String toString() {
        return String.format("%c", this.chip);
    }
}
