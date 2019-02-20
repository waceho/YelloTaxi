package com.amanciodrp.yellotaxi.utils;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.amanciodrp.yellotaxi.R;

/**
 *
 */
public class SnackbarUtils
{
	private SnackbarUtils() {}

	private static void setupAppearance(Snackbar pSnackbar, final int pBackgroundColor, final int pTextColor, final int pGravity) {

		View snackView = pSnackbar.getView();
		TextView snackTextView = snackView.findViewById(android.support.design.R.id.snackbar_text);
		snackTextView.setGravity(Gravity.CENTER);
		snackTextView.setTypeface(FontCache.getFont(snackView.getContext(), "arial.ttf"));

		if (pBackgroundColor != 0)
			snackView.setBackgroundColor(pBackgroundColor);

		if (pTextColor != 0)
			snackTextView.setTextColor(pTextColor);

		if (pGravity != 0)
			snackTextView.setGravity(pGravity);
	}

	/**
	 * Display the given message into a toast snarback.
	 * Has to be caled from UI Thread.
	 * @param pActivity current activity
	 * @param pMessage The text to display
	 */
	public static void displayInfo(final Activity pActivity, final int pMessage) {
		displayInfo(pActivity, pMessage, Snackbar.LENGTH_LONG);
	}

	public static void displayInfo(final Activity pActivity, final int pMessage, final int pDuration) {
		displayInfo(pActivity, pMessage, pDuration, 0, null);
	}

	public static void displayInfo(final Activity pActivity, final int pMessage, final int pDuration, final int pActionString, final View.OnClickListener pListener) {

		Snackbar snackbar = Snackbar.make(pActivity.findViewById(R.id.coordinator), pMessage, pDuration);

		if (pActionString != 0 && pListener != null) {
			snackbar.setAction(pActionString, pListener);
			snackbar.setActionTextColor(Color.WHITE);
		}

		setupAppearance(snackbar, pActivity.getResources().getColor(R.color.green), Color.WHITE, Gravity.CENTER_HORIZONTAL);
		snackbar.show();
	}

	public static void displayInfo(final Activity pActivity, @NonNull CharSequence pMessage, final int pDuration) {
		displayInfo(pActivity, pMessage, pDuration, 0, null);
	}

	public static void displayInfo(final Activity pActivity, @NonNull CharSequence pMessage, final int pDuration, final int pActionString, final View.OnClickListener pListener) {

		Snackbar snackbar = Snackbar.make(pActivity.findViewById(R.id.coordinator), pMessage, pDuration);

		if (pActionString != 0 && pListener != null) {
			snackbar.setAction(pActionString, pListener);
			snackbar.setActionTextColor(Color.WHITE);
		}

		setupAppearance(snackbar, pActivity.getResources().getColor(R.color.green), Color.WHITE, Gravity.CENTER_HORIZONTAL);
		snackbar.show();
	}
	/**
	 * Display the given message into a toast snackbar.
	 * Has to be caled from UI Thread.
	 * @param pActivity The current activity
	 * @param pMessage The text to display
	 */
	public static void displayWarning(final Activity pActivity, final int pMessage) {
		displayWarning(pActivity, pMessage, Snackbar.LENGTH_LONG);
	}

	public static void displayWarning(final Activity pActivity, final int pMessage, final int pDuration) {
		displayWarning(pActivity, pMessage, pDuration, 0, null);
	}

	public static void displayWarning(final Activity pActivity, final int pMessage, final int pDuration, final int pActionString, final View.OnClickListener pListener) {

		Snackbar snackbar = Snackbar.make(pActivity.findViewById(R.id.coordinator), pMessage, pDuration);

		if (pActionString != 0 && pListener != null) {
			snackbar.setAction(pActionString, pListener);
			snackbar.setActionTextColor(Color.WHITE);
		}

		setupAppearance(snackbar, pActivity.getResources().getColor(R.color.yellow), Color.WHITE, Gravity.CENTER_HORIZONTAL);
		snackbar.show();
	}

	public static void displayWarning(final Activity pActivity, @NonNull CharSequence pMessage, final int pDuration) {
		displayWarning(pActivity, pMessage, pDuration, 0, null);
	}

	public static void displayWarning(final Activity pActivity, @NonNull CharSequence pMessage, final int pDuration, final int pActionString, final View.OnClickListener pListener) {

		Snackbar snackbar = Snackbar.make(pActivity.findViewById(R.id.coordinator), pMessage, pDuration);

		if (pActionString != 0 && pListener != null) {
			snackbar.setAction(pActionString, pListener);
			snackbar.setActionTextColor(Color.WHITE);
		}

		setupAppearance(snackbar, pActivity.getResources().getColor(R.color.yellow), Color.WHITE, Gravity.CENTER);
		snackbar.show();
	}

	/**
	 * Display the given message into a toast snarback with red color.
	 * Has to be caled from UI Thread.
	 * @param pActivity The current activity
	 * @param pMessage The text to display
	 */
	public static void displayError(final Activity pActivity, final int pMessage) {
		displayError(pActivity, pMessage, Snackbar.LENGTH_LONG);
	}

	public static void displayError(final Activity pActivity, final int pMessage, final int pDuration) {
		displayError(pActivity, pMessage, pDuration, 0, null);
	}

	public static void displayError(final Activity pActivity, final int pMessage, final int pDuration, final int pActionString, final View.OnClickListener pListener) {

		Snackbar snackbar = Snackbar.make(pActivity.findViewById(R.id.coordinator), pMessage, pDuration);

		if (pActionString != 0 && pListener != null) {
			snackbar.setAction(pActionString, pListener);
			snackbar.setActionTextColor(Color.WHITE);
		}

		setupAppearance(snackbar, Color.BLUE, Color.WHITE, Gravity.CENTER_HORIZONTAL);
		snackbar.show();
	}

	public static void displayError(final Activity pActivity, @NonNull CharSequence pMessage, final int pDuration) {
		displayError(pActivity, pMessage, pDuration, 0, null);
	}

	public static void displayError(final Activity pActivity, @NonNull CharSequence pMessage, final int pDuration, final int pActionString, final View.OnClickListener pListener) {

		Snackbar snackbar = Snackbar.make(pActivity.findViewById(R.id.coordinator), pMessage, pDuration);

		if (pActionString != 0 && pListener != null) {
			snackbar.setAction(pActionString, pListener);
			snackbar.setActionTextColor(Color.WHITE);
		}

		setupAppearance(snackbar, Color.BLUE, Color.WHITE, Gravity.CENTER_HORIZONTAL);
		snackbar.show();

	}
}
