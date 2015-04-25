# 仿堆糖App UI
该App为模仿堆糖App，仅包含首页四个界面以及用户页的UI。
仅个人学习之用，若侵则删。

## 开源库
* StaggeredGridView
* SwipeRefreshLayout
* viewpagerindicator
* pagerslidingtabstrip
* butterknife
* picasso
* circleimageview

## 技巧
#### 瀑布流
使用瀑布流展示图片时，应在加载图片前确定图片的长宽比。如开源库的StaggeredGridView，就可使用DynamicHeightImageView的setHeightRatio方法，然后再使用Picasso进行图片加载。
DynamicHeightImageView其实只是继承了ImageView，并重写了onMeasure方法，让Height等于测量的Width乘上长宽比。

#### ViewPager和SwipeRefreshLayout手势冲突
在不进行处理时，热门页上面的ViewPager进行滑动时，Touch事件很容易被SwipeRefreshLayout抢走。
个人的做法是给ViewPager设置onTouchListener，对手势进行判断。当判定为水平滑动时，SwipeRefreshLayout.setEnable(false)。

#### 设置布局阴影
原本给布局增加阴影时，采用的是background添加layer_list的方法。但是这种方法会导致视图的过度绘制。于是改为background使用9.png的方法。

#### 热门页滑动图片标题显示
由于各种图片的颜色、亮度不同，白色标题可能存在看不清楚的时候。给ImageView添加ColorFilter，即减少图片的亮度，使标题显示清楚。

#### 设置ActionBar Overlay以及随滑动透明度改变的效果
参考[官方文档](https://developer.android.com/training/basics/actionbar/overlaying.html)以及[Making Your ActionBar Not Boring](http://www.cnblogs.com/xyzlmn/p/3684814.html)，
通过重写ScrollView的onScrollChanged方法来监听滑动距离，并根据该距离设置ActionBar的BackgroundDrawble的透明度以及TitleTextView的透明度。
