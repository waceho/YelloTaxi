package com.amanciodrp.yellotaxi.databinding;
import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityOnBoardingBindingImpl extends ActivityOnBoardingBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.onboard_img, 1);
        sViewsWithIds.put(R.id.onboardCard, 2);
        sViewsWithIds.put(R.id.onboard3RL, 3);
        sViewsWithIds.put(R.id.card_title, 4);
        sViewsWithIds.put(R.id.option_msg, 5);
        sViewsWithIds.put(R.id.chips_layout, 6);
        sViewsWithIds.put(R.id.chipgroup, 7);
        sViewsWithIds.put(R.id.passager, 8);
        sViewsWithIds.put(R.id.conducteur, 9);
        sViewsWithIds.put(R.id.onboard4RL, 10);
        sViewsWithIds.put(R.id.title, 11);
        sViewsWithIds.put(R.id.subtitle, 12);
        sViewsWithIds.put(R.id.act1, 13);
        sViewsWithIds.put(R.id.pager_introduction, 14);
        sViewsWithIds.put(R.id.viewPagerCountDots, 15);
        sViewsWithIds.put(R.id.ll_footer, 16);
        sViewsWithIds.put(R.id.btn_get_started, 17);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityOnBoardingBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }
    private ActivityOnBoardingBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[13]
            , (com.google.android.material.button.MaterialButton) bindings[17]
            , (android.widget.TextView) bindings[4]
            , (com.google.android.material.chip.ChipGroup) bindings[7]
            , (android.widget.LinearLayout) bindings[6]
            , (com.google.android.material.chip.Chip) bindings[9]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.RelativeLayout) bindings[3]
            , (android.widget.RelativeLayout) bindings[10]
            , (com.google.android.material.card.MaterialCardView) bindings[2]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[1]
            , (android.widget.TextView) bindings[5]
            , (com.amanciodrp.yellotaxi.onboarding.CustomViewPager) bindings[14]
            , (com.google.android.material.chip.Chip) bindings[8]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[11]
            , (android.widget.LinearLayout) bindings[15]
            );
        this.coordinator.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}