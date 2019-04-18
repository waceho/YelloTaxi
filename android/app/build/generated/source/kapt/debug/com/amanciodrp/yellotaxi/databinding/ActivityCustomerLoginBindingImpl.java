package com.amanciodrp.yellotaxi.databinding;
import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityCustomerLoginBindingImpl extends ActivityCustomerLoginBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.logo_text, 1);
        sViewsWithIds.put(R.id.google_login, 2);
        sViewsWithIds.put(R.id.phone_login, 3);
        sViewsWithIds.put(R.id.textView, 4);
        sViewsWithIds.put(R.id.input_layout_email, 5);
        sViewsWithIds.put(R.id.email, 6);
        sViewsWithIds.put(R.id.input_layout_password, 7);
        sViewsWithIds.put(R.id.password, 8);
        sViewsWithIds.put(R.id.login, 9);
        sViewsWithIds.put(R.id.no_account_text, 10);
        sViewsWithIds.put(R.id.registration, 11);
        sViewsWithIds.put(R.id.imageView2, 12);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityCustomerLoginBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private ActivityCustomerLoginBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.EditText) bindings[6]
            , (androidx.appcompat.widget.AppCompatButton) bindings[2]
            , (android.widget.ImageView) bindings[12]
            , (com.google.android.material.textfield.TextInputLayout) bindings[5]
            , (com.google.android.material.textfield.TextInputLayout) bindings[7]
            , (com.google.android.material.button.MaterialButton) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[10]
            , (android.widget.EditText) bindings[8]
            , (androidx.appcompat.widget.AppCompatButton) bindings[3]
            , (com.google.android.material.button.MaterialButton) bindings[11]
            , (android.widget.TextView) bindings[4]
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