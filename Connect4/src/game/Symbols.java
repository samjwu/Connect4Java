package game;

/**
 * 
 * Enumerator for Connect Four game chips/symbols
 *
 */
public enum Symbols {
	//symbol codes at http://www.alt-codes.net/
	
	//default symbols
	Red('☻'), Yellow('☺'), Space('*');
	
	//symbols for Cp1252 (Eclipse default) or non UTF-8 encoding
	//Red('X'), Yellow('O'), Space(' ');

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
