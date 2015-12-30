package ru.byters.view;

import android.content.Context;
import android.view.View;

public class ModelView {
    int ColorTextPanel;
    int ColorTitle;
    int ColorDescription;
    int ColorBackground;
    int ColorDrawableTitle;
    float tvPrimaryFontSize;
    float tvHugeFontSize;
    int tvPrimaryMaxLines;
    int tvHugeMaxLines;
    int tvDescriptionVisibility;
    int tvDrawableTitleVisibility;
    int imgViewVisibility;

    public ModelView(Context ctx) {
        ColorTextPanel = ctx.getResources().getColor(R.color.TextPanel);
        ColorTitle = ctx.getResources().getColor(android.R.color.white);
        ColorDescription = ctx.getResources().getColor(android.R.color.darker_gray);
        ColorBackground = ctx.getResources().getColor(android.R.color.transparent);
        ColorDrawableTitle = ctx.getResources().getColor(android.R.color.white);

        tvPrimaryFontSize = ctx.getResources().getDimension(R.dimen.title_textsize);
        tvHugeFontSize = ctx.getResources().getDimension(R.dimen.title_huge_textsize);

        tvPrimaryMaxLines = ctx.getResources().getInteger(R.integer.title_max_lines);
        tvHugeMaxLines = ctx.getResources().getInteger(R.integer.title_huge_max_lines);

        tvDescriptionVisibility = View.INVISIBLE;
        tvDrawableTitleVisibility = View.INVISIBLE;
        imgViewVisibility = View.VISIBLE;
    }


}
