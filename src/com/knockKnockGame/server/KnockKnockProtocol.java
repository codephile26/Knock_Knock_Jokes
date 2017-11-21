package com.knockKnockGame.server;

public class KnockKnockProtocol {

	private static final int WAITING = 0;
	private static final int SENTKNOCKKNOCK = 1;
	private static final int SENTCLUE = 2;
	private static final int ANOTHER = 3;

	private int currentState = WAITING;

	private int numberOfJokes = 0;

	private String[] clues = { "Dexter", "Thor", "Shaktimaan", "Johny", "Batman" };

	private String[] answers = { "Dexter halls with boughs of holly.", "Thor the God of Thunder",
			"Shaktimaan dosto ka dosto dushmano ka dushman", "I m vengeance. I m the night. I am Batman" };

	public String processInput(String fromClient) {
		String output = null;
		if (currentState == WAITING) {
			output = "Knock! knock!";
			currentState = SENTKNOCKKNOCK;
			return output;
		} else if (currentState == SENTKNOCKKNOCK) {
			if (fromClient.equalsIgnoreCase("Who's there?")) {
				output = clues[numberOfJokes];
				currentState = SENTCLUE;
				return output;
			} else {
				output = "You are supposed to say" + "\"Who's there?\"";
				currentState = SENTKNOCKKNOCK;
				return output;
			}
		} else if (currentState == SENTCLUE) {
			if (fromClient.equalsIgnoreCase(clues[numberOfJokes] + " Who?")) {
				output = answers[numberOfJokes] + "Do you want more jokes? Say y/n?";
				numberOfJokes++;
				currentState = ANOTHER;
				return output;
			} else {
				output = "You are spoiling the game! Please say " + "\"" + clues[numberOfJokes] + " who?\"";
				currentState = SENTCLUE;
				return output;
			}
		} else if (currentState == ANOTHER) {
			if (fromClient.equalsIgnoreCase("y")) {
				output = "Knock! Knock!";
				currentState = SENTKNOCKKNOCK;
				return output;
			} else {
				output = "Bye";
				return output;
			}
		}
		return output;
	}
}
