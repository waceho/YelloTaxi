package com.amanciodrp.yellotaxi.databinding;
import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityCostumerMapBindingImpl extends ActivityCostumerMapBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.menu_yellow, 1);
        sViewsWithIds.put(R.id.logout, 2);
        sViewsWithIds.put(R.id.account, 3);
        sViewsWithIds.put(R.id.history, 4);
        sViewsWithIds.put(R.id.payement, 5);
        sViewsWithIds.put(R.id.myLocation, 6);
        sViewsWithIds.put(R.id.linearLayout, 7);
        sViewsWithIds.put(R.id.start, 8);
        sViewsWithIds.put(R.id.end, 9);
        sViewsWithIds.put(R.id.driverInfo, 10);
        sViewsWithIds.put(R.id.driverProfileImage, 11);
        sViewsWithIds.put(R.id.driverName, 12);
        sViewsWithIds.put(R.id.driverPhone, 13);
        sViewsWithIds.put(R.id.driverCar, 14);
        sViewsWithIds.put(R.id.ratingBar, 15);
        sViewsWithIds.put(R.id.radioGroup, 16);
        sViewsWithIds.put(R.id.UberX, 17);
        sViewsWithIds.put(R.id.UberBlack, 18);
        sViewsWithIds.put(R.id.UberXl, 19);
        sViewsWithIds.put(R.id.request, 20);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityCostumerMapBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private ActivityCostumerMapBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.RadioButton) bindings[18]
            , (android.widget.RadioButton) bindings[17]
            , (android.widget.RadioButton) bindings[19]
            , (com.github.clans.fab.FloatingActionButton) bindings[3]
            , (android.widget.TextView) bindings[14]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[13]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.TextView) bindings[9]
            , (com.github.clans.fab.FloatingActionButton) bindings[4]
            , (android.widget.LinearLayout) bindings[7]
            , (com.github.clans.fab.FloatingActionButton) bindings[2]
            , (com.github.clans.fab.FloatingActionMenu) bindings[1]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[6]
            , (com.github.clans.fab.FloatingActionButton) bindings[5]
            , (android.widget.RadioGroup) bindings[16]
            , (android.widget.RatingBar) bindings[15]
            , (android.widget.Button) bindings[20]
            , (android.widget.TextView) bindings[8]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
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