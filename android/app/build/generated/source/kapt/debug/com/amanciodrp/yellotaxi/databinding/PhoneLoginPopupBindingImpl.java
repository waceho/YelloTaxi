package com.amanciodrp.yellotaxi.databinding;
import com.amanciodrp.yellotaxi.R;
import com.amanciodrp.yellotaxi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class PhoneLoginPopupBindingImpl extends PhoneLoginPopupBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.input_layout_code, 2);
        sViewsWithIds.put(R.id.txt_code, 3);
        sViewsWithIds.put(R.id.valider, 4);
        sViewsWithIds.put(R.id.textView4, 5);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public PhoneLoginPopupBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private PhoneLoginPopupBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.EditText) bindings[1]
            , (com.google.android.material.textfield.TextInputLayout) bindings[2]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[3]
            , (androidx.appcompat.widget.AppCompatButton) bindings[4]
            );
        this.edCode.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.userPhone == variableId) {
            setUserPhone((com.amanciodrp.yellotaxi.viewmodel.PhonePopUpViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUserPhone(@Nullable com.amanciodrp.yellotaxi.viewmodel.PhonePopUpViewModel UserPhone) {
        this.mUserPhone = UserPhone;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.userPhone);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUserPhoneUserPhoneNomber((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUserPhoneUserPhoneNomber(androidx.lifecycle.MutableLiveData<java.lang.String> UserPhoneUserPhoneNomber, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        com.amanciodrp.yellotaxi.viewmodel.PhonePopUpViewModel userPhone = mUserPhone;
        java.lang.String userPhoneUserPhoneNomberGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> userPhoneUserPhoneNomber = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (userPhone != null) {
                    // read userPhone.userPhoneNomber
                    userPhoneUserPhoneNomber = userPhone.getUserPhoneNomber();
                }
                updateLiveDataRegistration(0, userPhoneUserPhoneNomber);


                if (userPhoneUserPhoneNomber != null) {
                    // read userPhone.userPhoneNomber.getValue()
                    userPhoneUserPhoneNomberGetValue = userPhoneUserPhoneNomber.getValue();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edCode, userPhoneUserPhoneNomberGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): userPhone.userPhoneNomber
        flag 1 (0x2L): userPhone
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}