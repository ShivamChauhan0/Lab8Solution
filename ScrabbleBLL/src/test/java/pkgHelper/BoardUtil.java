package pkgHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import pkgGame.Board;
import pkgGame.Letter;

public class BoardUtil {

	public static ArrayList<Letter> getTileBag(Board b) {
		ArrayList<Letter> letters = null;
		
		try {

			// Find method setLasteDrawCount with eDrawCount parameter
			Method mGetTileBag = b.getClass().getDeclaredMethod("getTileBag",null);

			// Change the visibility of 'setLasteDrawCount' to true *Good Grief!*
			mGetTileBag.setAccessible(true);

			// Invoke the method for a given instance of a class, set arguments
			letters = (ArrayList<Letter>) mGetTileBag.invoke(b, null);

		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (NoSuchMethodException e) {
		} catch (InvocationTargetException e) {
		}
		return letters;

	}

	
	public static Board RemoveTileFromBag(Board b, ArrayList<Letter> removeLetters) {
		try {

			// Find method setLasteDrawCount with eDrawCount parameter
			Method mRemoveLettersFromTileBag = b.getClass().getDeclaredMethod("RemoveLettersFromTileBag",
					new Class[] { ArrayList.class });

			// Change the visibility of 'setLasteDrawCount' to true *Good Grief!*
			mRemoveLettersFromTileBag.setAccessible(true);

			// Invoke the method for a given instance of a class, set arguments
			mRemoveLettersFromTileBag.invoke(b, removeLetters);

		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (NoSuchMethodException e) {
		} catch (InvocationTargetException e) {
		}
		return (Board) b;

	}
}
