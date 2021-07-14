package pkgHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import pkgGame.Board;
import pkgGame.Game;
import pkgGame.Letter;
import pkgGame.Player;

public class GameUtil {

	
	public static ArrayList<Letter> getPlayersTiles(Game g, Player p) {
		ArrayList<Letter> letters = null;
		
		try {

			// Find method setLasteDrawCount with eDrawCount parameter
			Method mgetPlayersTiles = g.getClass().getDeclaredMethod("getPlayersTiles",
					new Class[] { Player.class });

			// Change the visibility of 'setLasteDrawCount' to true *Good Grief!*
			mgetPlayersTiles.setAccessible(true);

			Object[] cArgs = new Object[] { p};
			
			// Invoke the method for a given instance of a class, set arguments
			letters = (ArrayList<Letter>) mgetPlayersTiles.invoke(g, cArgs);

		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (NoSuchMethodException e) {
		} catch (InvocationTargetException e) {
		}
		return letters;

	}
	
	public static void addPlayerTile(Game g, Player p, Letter l) {
		ArrayList<Letter> letters = null;
		
		try {

			// Find method setLasteDrawCount with eDrawCount parameter
			Method maddPlayerTile = g.getClass().getDeclaredMethod("addPlayerTile",
					new Class[] { Player.class, Letter.class });

			// Change the visibility of 'setLasteDrawCount' to true *Good Grief!*
			maddPlayerTile.setAccessible(true);

			Object[] cArgs = new Object[] { p, l };
			
			// Invoke the method for a given instance of a class, set arguments
			maddPlayerTile.invoke(g, cArgs);

		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (NoSuchMethodException e) {
		} catch (InvocationTargetException e) {
		}
	}
	
	public static void removePlayerTile(Game g, Player p, Letter l) {
		ArrayList<Letter> letters = null;
		
		try {

			// Find method setLasteDrawCount with eDrawCount parameter
			Method mremovePlayerTile = g.getClass().getDeclaredMethod("removePlayerTile",
					new Class[] { Player.class, Letter.class });

			// Change the visibility of 'setLasteDrawCount' to true *Good Grief!*
			mremovePlayerTile.setAccessible(true);

			Object[] cArgs = new Object[] { p, l };
			
			// Invoke the method for a given instance of a class, set arguments
			mremovePlayerTile.invoke(g, cArgs);

		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (NoSuchMethodException e) {
		} catch (InvocationTargetException e) {
		}
	}
}
