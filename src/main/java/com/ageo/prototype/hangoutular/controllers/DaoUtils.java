package com.ageo.prototype.hangoutular.controllers;

public class DaoUtils {

	private static final String TOKEN_JOKER = "%";
	
	public static final String jokerifyUpperCase(String text) {
		return TOKEN_JOKER + text.toUpperCase() + TOKEN_JOKER;
	}
}
