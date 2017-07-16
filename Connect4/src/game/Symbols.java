package game;

/**
 * 
 * Enumerator for Connect Four game chips/symbols
 * Symbols is an Enum Type with the following predefined constants:
 * Red, Yellow, Space
 *
 */
//https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
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
	
	/**
	 * convert Symbols (Enum Type) to char
	 * @return chip as char data type
	 */
	public char toChar() {
        return this.chip;
    }
	
	/**
     * @Override
     * convert Symbols (Enum Type) to string
     * @return chip as String data type
     */
    public String toString() {
        return String.format("%c", this.chip);
    }
}
