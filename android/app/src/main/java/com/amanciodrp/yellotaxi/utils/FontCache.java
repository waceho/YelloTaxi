package com.amanciodrp.yellotaxi.utils;

import android.content.Context;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;

import com.amanciodrp.yellotaxi.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FX11846 on 26/02/2016.
 */
public class FontCache {
    private static Map<String, Typeface> fontMap = new HashMap<String, Typeface>();

    public static Typeface getFont(Context context, String fontName){

        if (fontMap.containsKey(fontName)){
            return fontMap.get(fontName);
        }
        else {
            Typeface tf = ResourcesCompat.getFont(context, R.font.arial);
            if (null != tf)
            fontMap.put(fontName, tf);
            return tf;
        }
    }
}
