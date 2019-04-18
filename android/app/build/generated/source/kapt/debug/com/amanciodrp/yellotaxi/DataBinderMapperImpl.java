package com.amanciodrp.yellotaxi;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amanciodrp.yellotaxi.databinding.ActivityCostumerMapBindingImpl;
import com.amanciodrp.yellotaxi.databinding.ActivityCustomerLoginBindingImpl;
import com.amanciodrp.yellotaxi.databinding.ActivityOnBoardingBindingImpl;
import com.amanciodrp.yellotaxi.databinding.CustomerPickupDetailsPopupBindingImpl;
import com.amanciodrp.yellotaxi.databinding.DefaultDialogPopupBindingImpl;
import com.amanciodrp.yellotaxi.databinding.OnboardItemBindingImpl;
import com.amanciodrp.yellotaxi.databinding.PhoneLoginPopupBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYCOSTUMERMAP = 1;

  private static final int LAYOUT_ACTIVITYCUSTOMERLOGIN = 2;

  private static final int LAYOUT_ACTIVITYONBOARDING = 3;

  private static final int LAYOUT_CUSTOMERPICKUPDETAILSPOPUP = 4;

  private static final int LAYOUT_DEFAULTDIALOGPOPUP = 5;

  private static final int LAYOUT_ONBOARDITEM = 6;

  private static final int LAYOUT_PHONELOGINPOPUP = 7;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(7);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.amanciodrp.yellotaxi.R.layout.activity_costumer_map, LAYOUT_ACTIVITYCOSTUMERMAP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.amanciodrp.yellotaxi.R.layout.activity_customer_login, LAYOUT_ACTIVITYCUSTOMERLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.amanciodrp.yellotaxi.R.layout.activity_on_boarding, LAYOUT_ACTIVITYONBOARDING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.amanciodrp.yellotaxi.R.layout.customer_pickup_details_popup, LAYOUT_CUSTOMERPICKUPDETAILSPOPUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.amanciodrp.yellotaxi.R.layout.default_dialog_popup, LAYOUT_DEFAULTDIALOGPOPUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.amanciodrp.yellotaxi.R.layout.onboard_item, LAYOUT_ONBOARDITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.amanciodrp.yellotaxi.R.layout.phone_login_popup, LAYOUT_PHONELOGINPOPUP);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYCOSTUMERMAP: {
          if ("layout/activity_costumer_map_0".equals(tag)) {
            return new ActivityCostumerMapBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_costumer_map is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCUSTOMERLOGIN: {
          if ("layout/activity_customer_login_0".equals(tag)) {
            return new ActivityCustomerLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_customer_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYONBOARDING: {
          if ("layout/activity_on_boarding_0".equals(tag)) {
            return new ActivityOnBoardingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_on_boarding is invalid. Received: " + tag);
        }
        case  LAYOUT_CUSTOMERPICKUPDETAILSPOPUP: {
          if ("layout/customer_pickup_details_popup_0".equals(tag)) {
            return new CustomerPickupDetailsPopupBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for customer_pickup_details_popup is invalid. Received: " + tag);
        }
        case  LAYOUT_DEFAULTDIALOGPOPUP: {
          if ("layout/default_dialog_popup_0".equals(tag)) {
            return new DefaultDialogPopupBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for default_dialog_popup is invalid. Received: " + tag);
        }
        case  LAYOUT_ONBOARDITEM: {
          if ("layout/onboard_item_0".equals(tag)) {
            return new OnboardItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for onboard_item is invalid. Received: " + tag);
        }
        case  LAYOUT_PHONELOGINPOPUP: {
          if ("layout/phone_login_popup_0".equals(tag)) {
            return new PhoneLoginPopupBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for phone_login_popup is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "userPhone");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(7);

    static {
      sKeys.put("layout/activity_costumer_map_0", com.amanciodrp.yellotaxi.R.layout.activity_costumer_map);
      sKeys.put("layout/activity_customer_login_0", com.amanciodrp.yellotaxi.R.layout.activity_customer_login);
      sKeys.put("layout/activity_on_boarding_0", com.amanciodrp.yellotaxi.R.layout.activity_on_boarding);
      sKeys.put("layout/customer_pickup_details_popup_0", com.amanciodrp.yellotaxi.R.layout.customer_pickup_details_popup);
      sKeys.put("layout/default_dialog_popup_0", com.amanciodrp.yellotaxi.R.layout.default_dialog_popup);
      sKeys.put("layout/onboard_item_0", com.amanciodrp.yellotaxi.R.layout.onboard_item);
      sKeys.put("layout/phone_login_popup_0", com.amanciodrp.yellotaxi.R.layout.phone_login_popup);
    }
  }
}
