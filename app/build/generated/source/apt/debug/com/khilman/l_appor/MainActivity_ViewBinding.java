// Generated code from Butter Knife. Do not modify!
package com.khilman.l_appor;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131624060;

  private View view2131624062;

  private View view2131624067;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.pilihImage, "field 'pilihImage' and method 'onPilihImageClicked'");
    target.pilihImage = Utils.castView(view, R.id.pilihImage, "field 'pilihImage'", TextView.class);
    view2131624060 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPilihImageClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.imgLapor, "field 'imgLapor' and method 'onImgLaporClicked'");
    target.imgLapor = Utils.castView(view, R.id.imgLapor, "field 'imgLapor'", ImageView.class);
    view2131624062 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onImgLaporClicked();
      }
    });
    target.txtKet = Utils.findRequiredViewAsType(source, R.id.txtKet, "field 'txtKet'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.btnSubmit, "field 'btnSubmit' and method 'onBtnSubmitClicked'");
    target.btnSubmit = Utils.castView(view, R.id.btnSubmit, "field 'btnSubmit'", Button.class);
    view2131624067 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnSubmitClicked();
      }
    });
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.panelUplad = Utils.findRequiredViewAsType(source, R.id.panelUplad, "field 'panelUplad'", LinearLayout.class);
    target.txtFilename = Utils.findRequiredViewAsType(source, R.id.txtFilename, "field 'txtFilename'", TextView.class);
    target.txtProges = Utils.findRequiredViewAsType(source, R.id.txtProges, "field 'txtProges'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.pilihImage = null;
    target.imgLapor = null;
    target.txtKet = null;
    target.btnSubmit = null;
    target.progressBar = null;
    target.panelUplad = null;
    target.txtFilename = null;
    target.txtProges = null;

    view2131624060.setOnClickListener(null);
    view2131624060 = null;
    view2131624062.setOnClickListener(null);
    view2131624062 = null;
    view2131624067.setOnClickListener(null);
    view2131624067 = null;
  }
}
