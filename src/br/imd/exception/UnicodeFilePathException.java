package br.imd.exception;

public class UnicodeFilePathException extends Exception {
	public UnicodeFilePathException () {
		super("Can't open a file with Unicode characters in its path!");
	}
}
