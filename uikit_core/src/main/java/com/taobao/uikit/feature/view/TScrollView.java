package com.taobao.uikit.feature.view;

import com.taobao.uikit.feature.callback.CanvasCallback;
import com.taobao.uikit.feature.callback.FocusCallback;
import com.taobao.uikit.feature.callback.InterceptTouchEventCallback;
import com.taobao.uikit.feature.callback.LayoutCallback;
import com.taobao.uikit.feature.callback.MeasureCallback;
import com.taobao.uikit.feature.callback.ScrollCallback;
import com.taobao.uikit.feature.callback.SetClipToPaddingCallBack;
import com.taobao.uikit.feature.callback.TouchEventCallback;
import com.taobao.uikit.feature.features.AbsFeature;
import com.taobao.uikit.utils.FeatureList;
import com.taobao.uikit.utils.IFeatureList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * TScrollView: UIKit's custom ScrollView
 *
 * @author jiajing
 */
public class TScrollView extends ScrollView implements ViewHelper, ViewGroupHelper, IFeatureList<ScrollView>
{

    private FeatureList<ScrollView> mFeatureList = new FeatureList<ScrollView>(this);

    private List<ViewGroup.OnHierarchyChangeListener> mOnHierarchyChangeListeners = new ArrayList<ViewGroup.OnHierarchyChangeListener>();

    public TScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mFeatureList.init(context, attrs, defStyle);
        super.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener()
        {
            @Override public void onChildViewAdded(View parent, View child)
            {
                for (ViewGroup.OnHierarchyChangeListener l : mOnHierarchyChangeListeners)
                {
                    l.onChildViewAdded(parent, child);
                }
            }

            @Override public void onChildViewRemoved(View parent, View child)
            {
                for (ViewGroup.OnHierarchyChangeListener l : mOnHierarchyChangeListeners)
                {
                    l.onChildViewRemoved(parent, child);
                }
            }
        });
    }

    public TScrollView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TScrollView(Context context)
    {
        this(context, null);
    }

    @Override public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener)
    {
        mOnHierarchyChangeListeners.add(listener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof MeasureCallback)
            {
                ((MeasureCallback) feature).beforeOnMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof MeasureCallback)
            {
                ((MeasureCallback) feature).afterOnMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof LayoutCallback)
            {
                ((LayoutCallback) feature).beforeOnLayout(changed, left, top, right, bottom);
            }
        }
        super.onLayout(changed, left, top, right, bottom);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof LayoutCallback)
            {
                ((LayoutCallback) feature).afterOnLayout(changed, left, top, right, bottom);
            }
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof CanvasCallback)
            {
                ((CanvasCallback) feature).beforeDraw(canvas);
            }
        }
        super.draw(canvas);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof CanvasCallback)
            {
                ((CanvasCallback) feature).afterDraw(canvas);
            }
        }

    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof CanvasCallback)
            {
                ((CanvasCallback) feature).beforeDispatchDraw(canvas);
            }
        }
        super.dispatchDraw(canvas);
        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof CanvasCallback)
            {
                ((CanvasCallback) feature).afterDispatchDraw(canvas);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof CanvasCallback)
            {
                ((CanvasCallback) feature).beforeOnDraw(canvas);
            }
        }

        super.onDraw(canvas);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof CanvasCallback)
            {
                ((CanvasCallback) feature).afterOnDraw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof TouchEventCallback)
            {
                ((TouchEventCallback) feature).beforeOnTouchEvent(event);
            }
        }
        boolean result = super.onTouchEvent(event);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof TouchEventCallback)
            {
                ((TouchEventCallback) feature).afterOnTouchEvent(event);
            }
        }
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof TouchEventCallback)
            {
                ((TouchEventCallback) feature).beforeDispatchTouchEvent(event);
            }
        }
        boolean result = super.dispatchTouchEvent(event);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof TouchEventCallback)
            {
                ((TouchEventCallback) feature).afterDispatchTouchEvent(event);
            }
        }
        return result;
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof FocusCallback)
            {
                ((FocusCallback) feature).beforeOnFocusChanged(gainFocus, direction, previouslyFocusedRect);
            }
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof FocusCallback)
            {
                ((FocusCallback) feature).afterOnFocusChanged(gainFocus, direction, previouslyFocusedRect);
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof FocusCallback)
            {
                ((FocusCallback) feature).beforeOnWindowFocusChanged(hasWindowFocus);
            }
        }
        super.onWindowFocusChanged(hasWindowFocus);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof FocusCallback)
            {
                ((FocusCallback) feature).afterOnWindowFocusChanged(hasWindowFocus);
            }
        }
    }

    @Override
    public void setMeasuredDimension(long width, long height)
    {
        super.setMeasuredDimension((int) width, (int) height);
    }

    public void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec, int reserve)
    {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    public boolean drawChild(Canvas canvas, View child, long drawingTime, int reserve)
    {
        return super.drawChild(canvas, child, drawingTime);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        boolean ret = super.onInterceptTouchEvent(ev);
        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof InterceptTouchEventCallback)
            {
                ret |= ((InterceptTouchEventCallback) feature).onInterceptTouchEvent(ev);
            }
        }
        return ret;
    }

    @Override
    public void computeScroll()
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof ScrollCallback)
            {
                ((ScrollCallback) feature).beforeComputeScroll();
            }
        }
        super.computeScroll();
        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof ScrollCallback)
            {
                ((ScrollCallback) feature).afterComputeScroll();
            }
        }
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof ScrollCallback)
            {
                ((ScrollCallback) feature).beforeOnScrollChanged(l, t, oldl, oldt);
            }
        }
        super.onScrollChanged(l, t, oldl, oldt);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof ScrollCallback)
            {
                ((ScrollCallback) feature).afterOnScrollChanged(l, t, oldl, oldt);
            }
        }
    }

    public void setClipToPadding(boolean clipToPadding)
    {
        if(mFeatureList == null)
        {
            super.setClipToPadding(clipToPadding);
            return;
        }
        for (AbsFeature<? super ScrollView> feature : mFeatureList)
        {
            if (feature instanceof SetClipToPaddingCallBack)
            {
                ((SetClipToPaddingCallBack) feature).beforeSetClipToPadding(clipToPadding);
            }
        }
        super.setClipToPadding(clipToPadding);

        for (int i = mFeatureList.size() - 1; i >= 0; i--)
        {
            AbsFeature<? super ScrollView> feature = mFeatureList.get(i);
            if (feature instanceof SetClipToPaddingCallBack)
            {
                ((SetClipToPaddingCallBack) feature).afterSetClipToPadding(clipToPadding);
            }
        }
    }

    @Override
    public boolean addFeature(AbsFeature<? super ScrollView> feature)
    {
        return mFeatureList.addFeature(feature);
    }

    @Override
    public AbsFeature<? super ScrollView> findFeature(Class<? extends AbsFeature<? super ScrollView>> clazz)
    {
        return mFeatureList.findFeature(clazz);
    }

    @Override
    public boolean removeFeature(Class<? extends AbsFeature<? super ScrollView>> clazz)
    {
        return mFeatureList.removeFeature(clazz);
    }

    @Override
    public void clearFeatures()
    {
        mFeatureList.clearFeatures();
    }

    @Override
    public void init(Context context, AttributeSet attrs, int defStyle)
    {
        mFeatureList.init(context, attrs, defStyle);
    }
}
