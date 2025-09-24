package org.opensourcearcade.jinvaders;

import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class HighScores {

	private static final int NUMBER_LINES_SCORE = 2;

	protected static final int MAX_ENTRIES = 10;

	protected static final int MAX_ENTRIES_ALLOCATION = NUMBER_LINES_SCORE*MAX_ENTRIES;

	protected static final String SEPARATOR = "#";
	protected ArrayList<String> highScores = new ArrayList<String>();
	
	protected abstract boolean loadScores(); 
	
	protected abstract void saveScores(String playerName, int score);
	
	public Object[] getHighScores() {
		return highScores.toArray();
	}

	public int getHighScore() {
		return (highScores!=null&&highScores.size()>0) ? Integer.parseInt((String) highScores.get(1)) : 0;
	}
	
	protected String serializeScores(ArrayList<String> scores)
	{
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i<scores.size(); i++) {
			buf.append(scores.get(i));
			buf.append(SEPARATOR);
		}
		return buf.toString();
	}
	
	protected boolean deserializeScores(String content) {
		if (content!=null) {
			highScores.clear();
			StringTokenizer st = new StringTokenizer(content, SEPARATOR);

			//for (int i = 0; st.hasMoreTokens()&&i<MAX_ENTRIES*2; i++) {
			for (int i = 0; st.hasMoreTokens()&&i<MAX_ENTRIES_ALLOCATION; i++) {
				try {
					String name = st.nextToken();
					String score = st.nextToken();
					Integer.parseInt(score); // check for valid integer!
					highScores.add(name.toUpperCase());
					highScores.add(score);
				}
				catch (Exception e) {
					System.err.println("Highscore data corrupted");
					highScores.clear();
				}
			}
			return true;
		}
		return false;
	}
	
	public void postHighScore(String playerName, int newScore) {

		if (newScore<=0) {
			return;
		}

		if (highScores.isEmpty()) {
			highScores.add(playerName);
			highScores.add("" + newScore);
			saveScores(playerName, newScore);
			return;
		}

		try {
			calcHighScore(playerName, newScore);
		}catch (Exception e){
			System.err.println("postHighScore: Error parsing scores");
		}
	}

	private void calcHighScore(String playerName, int newScore){

		boolean added = false;
		for (int i=1; i < highScores.size() && !added; i+=NUMBER_LINES_SCORE) {
			final int scoreValue = Integer.parseInt(highScores.get(i));
			if (newScore > scoreValue) {
				highScores.add(i-1, playerName);
				highScores.add(i, "" + newScore);
				added = true;
				continue;
			}

			if (newScore==scoreValue) {
				highScores.set(i-1, playerName);
				highScores.set(i, "" + newScore);
				added = true;
			}
		}

		if (!added) {
			highScores.add(playerName);
			highScores.add("" + newScore);
		}

		while (highScores.size() > MAX_ENTRIES*NUMBER_LINES_SCORE){
			highScores.remove(highScores.size()-1);
		}

		saveScores(playerName, newScore);
	}
}
