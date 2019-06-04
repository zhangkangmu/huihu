#### 类说明  
---

提供切换界面的View  
- 切换无网络界面   
- 切换无数据界面  
- 切换网络错误界面  
- 切换加载界面
- 切换内容界面  


#### 类的位置  
---
- commonLib  
   - com.bailun.commonlibrary  
      - customview
         - statelayout  
         

#### 使用的方法 
- 构建方法 
<pre>
StateLayout stateLayout = new StateLayout.Builder(view)
                .setEmptyData()
                .setNoNetwork()
                .create();</pre>   
其中Builder中传入的参数为View，一般为根据状态不同显示不同的布局的View。目前有无数据和无网络的默认样式。  
 
- 切换无网络界面
<pre>stateLayout.showNoNetwork();</pre>
- 切换无数据界面
<pre>stateLayout.showEmptyData();</pre>
- 切换内容界面  
<pre>stateLayout.showContent();</pre>
- 自定义部分
<pre>StateLayout stateLayout = new StateLayout.Builder(view)
                .setLoadingData(R.layout.xxx)
                .setNetworkError(R.layout.xxx)
                .setEmptyData(R.layout.xxx)
                .setNoNetwork(R.layout.xxx)
                .create();</pre>  
可以传入相应的布局文件来修改不同状态的布局。  
目前提供四种布局：  
1.无数据  
2.无网络  
3.网络错误  
4.加载中


  
