(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3278","chunk-03b4","chunk-70e1","chunk-3c39","chunk-505c","chunk-7b1a","8e9a","chunk-b239","chunk-2684","649f","chunk-d594"],{"16e0":function(t,e,o){},4249:function(t,e,o){},"45e7":function(t,e,o){"use strict";o.r(e);var r=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("Row",{attrs:{gutter:20}},t._l(t.inforCardData,function(e,r){return o("i-col",{key:"infor-"+r,staticStyle:{height:"120px","padding-bottom":"10px"},attrs:{xs:12,md:8,lg:4}},[o("infor-card",{attrs:{shadow:"",color:e.color,icon:e.icon,"icon-size":36}},[o("count-to",{attrs:{end:e.count,"count-class":"count-style"}}),o("p",[t._v(t._s(e.title))])],1)],1)})),o("Row",{staticStyle:{"margin-top":"10px"},attrs:{gutter:20}},[o("i-col",{staticStyle:{"margin-bottom":"20px"},attrs:{md:24,lg:8}},[o("Card",{attrs:{shadow:""}},[o("chart-pie",{staticStyle:{height:"300px"},attrs:{value:t.pieData,text:"用户访问来源"}})],1)],1),o("i-col",{staticStyle:{"margin-bottom":"20px"},attrs:{md:24,lg:16}},[o("Card",{attrs:{shadow:""}},[o("chart-bar",{staticStyle:{height:"300px"},attrs:{value:t.barData,text:"每周用户活跃量"}})],1)],1)],1),o("Row",[o("Card",{attrs:{shadow:""}},[o("example",{staticStyle:{height:"310px"}})],1)],1)],1)},a=[],n=o("63a4"),i=o("9349"),l=o("c9ae"),c=o("649f"),s={name:"home",components:{InforCard:n["default"],CountTo:i["default"],ChartPie:l["ChartPie"],ChartBar:l["ChartBar"],Example:c["default"]},data:function(){return{inforCardData:[{title:"新增用户",icon:"md-person-add",count:803,color:"#2d8cf0"},{title:"累计点击",icon:"md-locate",count:232,color:"#19be6b"},{title:"新增问答",icon:"md-help-circle",count:142,color:"#ff9900"},{title:"分享统计",icon:"md-share",count:657,color:"#ed3f14"},{title:"新增互动",icon:"md-chatbubbles",count:12,color:"#E46CBB"},{title:"新增页面",icon:"md-map",count:14,color:"#9A66E4"}],pieData:[{value:335,name:"直接访问"},{value:310,name:"邮件营销"},{value:234,name:"联盟广告"},{value:135,name:"视频广告"},{value:1548,name:"搜索引擎"}],barData:{Mon:13253,Tue:34235,Wed:26321,Thu:12340,Fri:24643,Sat:1322,Sun:1324}}},mounted:function(){}},u=s,d=(o("ad89"),o("2877")),m=Object(d["a"])(u,r,a,!1,null,null,null);m.options.__file="home.vue";e["default"]=m.exports},"504c":function(t,e,o){var r=o("0d58"),a=o("6821"),n=o("52a7").f;t.exports=function(t){return function(e){var o,i=a(e),l=r(i),c=l.length,s=0,u=[];while(c>s)n.call(i,o=l[s++])&&u.push(t?[o,i[o]]:i[o]);return u}}},5416:function(t,e,o){"use strict";o.r(e);var r=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{ref:"dom",staticClass:"charts chart-bar"})},a=[],n=(o("8615"),o("ac6a"),o("456d"),o("313e")),i=o.n(n),l=o("8e9a"),c=o("90de");i.a.registerTheme("tdTheme",l);var s={name:"ChartBar",props:{value:Object,text:String,subtext:String},data:function(){return{dom:null}},methods:{resize:function(){this.dom.resize()}},mounted:function(){var t=this;this.$nextTick(function(){var e=Object.keys(t.value),o=Object.values(t.value),r={title:{text:t.text,subtext:t.subtext,x:"center"},xAxis:{type:"category",data:e},yAxis:{type:"value"},series:[{data:o,type:"bar"}]};t.dom=i.a.init(t.$refs.dom,"tdTheme"),t.dom.setOption(r),Object(c["on"])(window,"resize",t.resize)})},beforeDestroy:function(){Object(c["off"])(window,"resize",this.resize)}},u=s,d=o("2877"),m=Object(d["a"])(u,r,a,!1,null,null,null);m.options.__file="bar.vue";e["default"]=m.exports},"57f2":function(t,e,o){var r,a;!function(n,i){r=i,a="function"===typeof r?r.call(e,o,e,t):r,void 0===a||(t.exports=a)}(0,function(t,e,o){var r=function(t,e,o,r,a,n){for(var i=0,l=["webkit","moz","ms","o"],c=0;c<l.length&&!window.requestAnimationFrame;++c)window.requestAnimationFrame=window[l[c]+"RequestAnimationFrame"],window.cancelAnimationFrame=window[l[c]+"CancelAnimationFrame"]||window[l[c]+"CancelRequestAnimationFrame"];window.requestAnimationFrame||(window.requestAnimationFrame=function(t,e){var o=(new Date).getTime(),r=Math.max(0,16-(o-i)),a=window.setTimeout(function(){t(o+r)},r);return i=o+r,a}),window.cancelAnimationFrame||(window.cancelAnimationFrame=function(t){clearTimeout(t)});var s=this;for(var u in s.options={useEasing:!0,useGrouping:!0,separator:",",decimal:".",easingFn:null,formattingFn:null},n)n.hasOwnProperty(u)&&(s.options[u]=n[u]);""===s.options.separator&&(s.options.useGrouping=!1),s.options.prefix||(s.options.prefix=""),s.options.suffix||(s.options.suffix=""),s.d="string"==typeof t?document.getElementById(t):t,s.startVal=Number(e),s.endVal=Number(o),s.countDown=s.startVal>s.endVal,s.frameVal=s.startVal,s.decimals=Math.max(0,r||0),s.dec=Math.pow(10,s.decimals),s.duration=1e3*Number(a)||2e3,s.formatNumber=function(t){var e,o,r,a;if(t=t.toFixed(s.decimals),t+="",e=t.split("."),o=e[0],r=e.length>1?s.options.decimal+e[1]:"",a=/(\d+)(\d{3})/,s.options.useGrouping)for(;a.test(o);)o=o.replace(a,"$1"+s.options.separator+"$2");return s.options.prefix+o+r+s.options.suffix},s.easeOutExpo=function(t,e,o,r){return o*(1-Math.pow(2,-10*t/r))*1024/1023+e},s.easingFn=s.options.easingFn?s.options.easingFn:s.easeOutExpo,s.formattingFn=s.options.formattingFn?s.options.formattingFn:s.formatNumber,s.version=function(){return"1.7.1"},s.printValue=function(t){var e=s.formattingFn(t);"INPUT"===s.d.tagName?this.d.value=e:"text"===s.d.tagName||"tspan"===s.d.tagName?this.d.textContent=e:this.d.innerHTML=e},s.count=function(t){s.startTime||(s.startTime=t),s.timestamp=t;var e=t-s.startTime;s.remaining=s.duration-e,s.options.useEasing?s.countDown?s.frameVal=s.startVal-s.easingFn(e,0,s.startVal-s.endVal,s.duration):s.frameVal=s.easingFn(e,s.startVal,s.endVal-s.startVal,s.duration):s.countDown?s.frameVal=s.startVal-(s.startVal-s.endVal)*(e/s.duration):s.frameVal=s.startVal+(s.endVal-s.startVal)*(e/s.duration),s.countDown?s.frameVal=s.frameVal<s.endVal?s.endVal:s.frameVal:s.frameVal=s.frameVal>s.endVal?s.endVal:s.frameVal,s.frameVal=Math.round(s.frameVal*s.dec)/s.dec,s.printValue(s.frameVal),e<s.duration?s.rAF=requestAnimationFrame(s.count):s.callback&&s.callback()},s.start=function(t){return s.callback=t,s.rAF=requestAnimationFrame(s.count),!1},s.pauseResume=function(){s.paused?(s.paused=!1,delete s.startTime,s.duration=s.remaining,s.startVal=s.frameVal,requestAnimationFrame(s.count)):(s.paused=!0,cancelAnimationFrame(s.rAF))},s.reset=function(){s.paused=!1,delete s.startTime,s.startVal=e,cancelAnimationFrame(s.rAF),s.printValue(s.startVal)},s.update=function(t){cancelAnimationFrame(s.rAF),s.paused=!1,delete s.startTime,s.startVal=s.frameVal,s.endVal=Number(t),s.countDown=s.startVal>s.endVal,s.rAF=requestAnimationFrame(s.count)},s.printValue(s.startVal)};return r})},"63a4":function(t,e,o){"use strict";o.r(e);o("cadf"),o("551c"),o("097d");var r=o("a819");e["default"]=r["default"]},"649f":function(t,e,o){"use strict";o.r(e);var r=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{ref:"dom"})},a=[],n=o("313e"),i=o.n(n),l=o("90de"),c={name:"serviceRequests",data:function(){return{dom:null}},methods:{resize:function(){this.dom.resize()}},mounted:function(){var t=this,e={tooltip:{trigger:"axis",axisPointer:{type:"cross",label:{backgroundColor:"#6a7985"}}},grid:{top:"3%",left:"1.2%",right:"1%",bottom:"3%",containLabel:!0},xAxis:[{type:"category",boundaryGap:!1,data:["周一","周二","周三","周四","周五","周六","周日"]}],yAxis:[{type:"value"}],series:[{name:"运营商/网络服务",type:"line",stack:"总量",areaStyle:{normal:{color:"#2d8cf0"}},data:[120,132,101,134,90,230,210]},{name:"银行/证券",type:"line",stack:"总量",areaStyle:{normal:{color:"#10A6FF"}},data:[257,358,278,234,290,330,310]},{name:"游戏/视频",type:"line",stack:"总量",areaStyle:{normal:{color:"#0C17A6"}},data:[379,268,354,269,310,478,358]},{name:"餐饮/外卖",type:"line",stack:"总量",areaStyle:{normal:{color:"#4608A6"}},data:[320,332,301,334,390,330,320]},{name:"快递/电商",type:"line",stack:"总量",label:{normal:{show:!0,position:"top"}},areaStyle:{normal:{color:"#398DBF"}},data:[820,645,546,745,872,624,258]}]};this.$nextTick(function(){t.dom=i.a.init(t.$refs.dom),t.dom.setOption(e),Object(l["on"])(window,"resize",t.resize)})},beforeDestroy:function(){Object(l["off"])(window,"resize",this.resize)}},s=c,u=o("2877"),d=Object(u["a"])(s,r,a,!1,null,null,null);d.options.__file="example.vue";e["default"]=d.exports},"79bd":function(t,e,o){},"7d78":function(t,e,o){"use strict";o.r(e);var r=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"count-to-wrapper"},[t._t("left"),o("p",{staticClass:"content-outer"},[o("span",{class:["count-to-count-text",t.countClass],attrs:{id:t.counterId}},[t._v(t._s(t.init))]),o("i",{class:["count-to-unit-text",t.unitClass]},[t._v(t._s(t.unitText))])]),t._t("right")],2)},a=[],n=(o("c5f6"),o("57f2")),i=o.n(n),l=(o("16e0"),{name:"CountTo",props:{init:{type:Number,default:0},startVal:{type:Number,default:0},end:{type:Number,required:!0},decimals:{type:Number,default:0},decimal:{type:String,default:"."},duration:{type:Number,default:2},delay:{type:Number,default:0},uneasing:{type:Boolean,default:!1},usegroup:{type:Boolean,default:!1},separator:{type:String,default:","},simplify:{type:Boolean,default:!1},unit:{type:Array,default:function(){return[[3,"K+"],[6,"M+"],[9,"B+"]]}},countClass:{type:String,default:""},unitClass:{type:String,default:""}},data:function(){return{counter:null,unitText:""}},computed:{counterId:function(){return"count_to_".concat(this._uid)}},methods:{getHandleVal:function(t,e){return{endVal:parseInt(t/Math.pow(10,this.unit[e-1][0])),unitText:this.unit[e-1][1]}},transformValue:function(t){var e=this.unit.length,o={endVal:0,unitText:""};if(t<Math.pow(10,this.unit[0][0]))o.endVal=t;else for(var r=1;r<e;r++)t>=Math.pow(10,this.unit[r-1][0])&&t<Math.pow(10,this.unit[r][0])&&(o=this.getHandleVal(t,r));return t>Math.pow(10,this.unit[e-1][0])&&(o=this.getHandleVal(t,e)),o},getValue:function(t){var e=0;if(this.simplify){var o=this.transformValue(t),r=o.endVal,a=o.unitText;this.unitText=a,e=r}else e=t;return e}},mounted:function(){var t=this;this.$nextTick(function(){var e=t.getValue(t.end);t.counter=new i.a(t.counterId,t.startVal,e,t.decimals,t.duration,{useEasing:!t.uneasing,useGrouping:t.useGroup,separator:t.separator,decimal:t.decimal}),setTimeout(function(){t.counter.error||t.counter.start()},t.delay)})},watch:{end:function(t){var e=this.getValue(t);this.counter.update(e)}}}),c=l,s=o("2877"),u=Object(s["a"])(c,r,a,!1,null,null,null);u.options.__file="count-to.vue";e["default"]=u.exports},8615:function(t,e,o){var r=o("5ca1"),a=o("504c")(!1);r(r.S,"Object",{values:function(t){return a(t)}})},"8e9a":function(t){t.exports={color:["#2d8cf0","#19be6b","#ff9900","#E46CBB","#9A66E4","#ed3f14"],backgroundColor:"rgba(0,0,0,0)",textStyle:{},title:{textStyle:{color:"#516b91"},subtextStyle:{color:"#93b7e3"}},line:{itemStyle:{normal:{borderWidth:"2"}},lineStyle:{normal:{width:"2"}},symbolSize:"6",symbol:"emptyCircle",smooth:!0},radar:{itemStyle:{normal:{borderWidth:"2"}},lineStyle:{normal:{width:"2"}},symbolSize:"6",symbol:"emptyCircle",smooth:!0},bar:{itemStyle:{normal:{barBorderWidth:0,barBorderColor:"#ccc"},emphasis:{barBorderWidth:0,barBorderColor:"#ccc"}}},pie:{itemStyle:{normal:{borderWidth:0,borderColor:"#ccc"},emphasis:{borderWidth:0,borderColor:"#ccc"}}},scatter:{itemStyle:{normal:{borderWidth:0,borderColor:"#ccc"},emphasis:{borderWidth:0,borderColor:"#ccc"}}},boxplot:{itemStyle:{normal:{borderWidth:0,borderColor:"#ccc"},emphasis:{borderWidth:0,borderColor:"#ccc"}}},parallel:{itemStyle:{normal:{borderWidth:0,borderColor:"#ccc"},emphasis:{borderWidth:0,borderColor:"#ccc"}}},sankey:{itemStyle:{normal:{borderWidth:0,borderColor:"#ccc"},emphasis:{borderWidth:0,borderColor:"#ccc"}}},funnel:{itemStyle:{normal:{borderWidth:0,borderColor:"#ccc"},emphasis:{borderWidth:0,borderColor:"#ccc"}}},gauge:{itemStyle:{normal:{borderWidth:0,borderColor:"#ccc"},emphasis:{borderWidth:0,borderColor:"#ccc"}}},candlestick:{itemStyle:{normal:{color:"#edafda",color0:"transparent",borderColor:"#d680bc",borderColor0:"#8fd3e8",borderWidth:"2"}}},graph:{itemStyle:{normal:{borderWidth:0,borderColor:"#ccc"}},lineStyle:{normal:{width:1,color:"#aaa"}},symbolSize:"6",symbol:"emptyCircle",smooth:!0,color:["#2d8cf0","#19be6b","#f5ae4a","#9189d5","#56cae2","#cbb0e3"],label:{normal:{textStyle:{color:"#eee"}}}},map:{itemStyle:{normal:{areaColor:"#f3f3f3",borderColor:"#516b91",borderWidth:.5},emphasis:{areaColor:"rgba(165,231,240,1)",borderColor:"#516b91",borderWidth:1}},label:{normal:{textStyle:{color:"#000"}},emphasis:{textStyle:{color:"rgb(81,107,145)"}}}},geo:{itemStyle:{normal:{areaColor:"#f3f3f3",borderColor:"#516b91",borderWidth:.5},emphasis:{areaColor:"rgba(165,231,240,1)",borderColor:"#516b91",borderWidth:1}},label:{normal:{textStyle:{color:"#000"}},emphasis:{textStyle:{color:"rgb(81,107,145)"}}}},categoryAxis:{axisLine:{show:!0,lineStyle:{color:"#cccccc"}},axisTick:{show:!1,lineStyle:{color:"#333"}},axisLabel:{show:!0,textStyle:{color:"#999999"}},splitLine:{show:!0,lineStyle:{color:["#eeeeee"]}},splitArea:{show:!1,areaStyle:{color:["rgba(250,250,250,0.05)","rgba(200,200,200,0.02)"]}}},valueAxis:{axisLine:{show:!0,lineStyle:{color:"#cccccc"}},axisTick:{show:!1,lineStyle:{color:"#333"}},axisLabel:{show:!0,textStyle:{color:"#999999"}},splitLine:{show:!0,lineStyle:{color:["#eeeeee"]}},splitArea:{show:!1,areaStyle:{color:["rgba(250,250,250,0.05)","rgba(200,200,200,0.02)"]}}},logAxis:{axisLine:{show:!0,lineStyle:{color:"#cccccc"}},axisTick:{show:!1,lineStyle:{color:"#333"}},axisLabel:{show:!0,textStyle:{color:"#999999"}},splitLine:{show:!0,lineStyle:{color:["#eeeeee"]}},splitArea:{show:!1,areaStyle:{color:["rgba(250,250,250,0.05)","rgba(200,200,200,0.02)"]}}},timeAxis:{axisLine:{show:!0,lineStyle:{color:"#cccccc"}},axisTick:{show:!1,lineStyle:{color:"#333"}},axisLabel:{show:!0,textStyle:{color:"#999999"}},splitLine:{show:!0,lineStyle:{color:["#eeeeee"]}},splitArea:{show:!1,areaStyle:{color:["rgba(250,250,250,0.05)","rgba(200,200,200,0.02)"]}}},toolbox:{iconStyle:{normal:{borderColor:"#999"},emphasis:{borderColor:"#666"}}},legend:{textStyle:{color:"#999999"}},tooltip:{axisPointer:{lineStyle:{color:"#ccc",width:1},crossStyle:{color:"#ccc",width:1}}},timeline:{lineStyle:{color:"#8fd3e8",width:1},itemStyle:{normal:{color:"#8fd3e8",borderWidth:1},emphasis:{color:"#8fd3e8"}},controlStyle:{normal:{color:"#8fd3e8",borderColor:"#8fd3e8",borderWidth:.5},emphasis:{color:"#8fd3e8",borderColor:"#8fd3e8",borderWidth:.5}},checkpointStyle:{color:"#8fd3e8",borderColor:"rgba(138,124,168,0.37)"},label:{normal:{textStyle:{color:"#8fd3e8"}},emphasis:{textStyle:{color:"#8fd3e8"}}}},visualMap:{color:["#516b91","#59c4e6","#a5e7f0"]},dataZoom:{backgroundColor:"rgba(0,0,0,0)",dataBackgroundColor:"rgba(255,255,255,0.3)",fillerColor:"rgba(167,183,204,0.4)",handleColor:"#a7b7cc",handleSize:"100%",textStyle:{color:"#333"}},markPoint:{label:{normal:{textStyle:{color:"#eee"}},emphasis:{textStyle:{color:"#eee"}}}}}},9349:function(t,e,o){"use strict";o.r(e);o("cadf"),o("551c"),o("097d");var r=o("7d78");e["default"]=r["default"]},a189:function(t,e,o){"use strict";var r=o("79bd"),a=o.n(r);a.a},a819:function(t,e,o){"use strict";o.r(e);var r=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("Card",{staticClass:"info-card-wrapper",attrs:{shadow:t.shadow,padding:0}},[o("div",{staticClass:"content-con"},[o("div",{staticClass:"left-area",style:{background:t.color,width:t.leftWidth}},[o("common-icon",{staticClass:"icon",attrs:{type:t.icon,size:t.iconSize,color:"#fff"}})],1),o("div",{staticClass:"right-area",style:{width:t.rightWidth}},[o("div",[t._t("default")],2)])])])},a=[],n=(o("c5f6"),o("cb21")),i={name:"InforCard",components:{CommonIcon:n["default"]},props:{left:{type:Number,default:36},color:{type:String,default:"#2d8cf0"},icon:{type:String,default:""},iconSize:{type:Number,default:20},shadow:{type:Boolean,default:!1}},computed:{leftWidth:function(){return"".concat(this.left,"%")},rightWidth:function(){return"".concat(100-this.left,"%")}}},l=i,c=(o("a189"),o("2877")),s=Object(c["a"])(l,r,a,!1,null,null,null);s.options.__file="infor-card.vue";e["default"]=s.exports},ad89:function(t,e,o){"use strict";var r=o("4249"),a=o.n(r);a.a},c9ae:function(t,e,o){"use strict";o.r(e);o("cadf"),o("551c"),o("097d");var r=o("f698");o.d(e,"ChartPie",function(){return r["default"]});var a=o("5416");o.d(e,"ChartBar",function(){return a["default"]})},f698:function(t,e,o){"use strict";o.r(e);var r=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{ref:"dom",staticClass:"charts chart-pie"})},a=[],n=(o("7f7f"),o("313e")),i=o.n(n),l=o("8e9a"),c=o("90de");i.a.registerTheme("tdTheme",l);var s={name:"ChartPie",props:{value:Array,text:String,subtext:String},data:function(){return{dom:null}},methods:{resize:function(){this.dom.resize()}},mounted:function(){var t=this;this.$nextTick(function(){var e=t.value.map(function(t){return t.name}),o={title:{text:t.text,subtext:t.subtext,x:"center"},tooltip:{trigger:"item",formatter:"{a} <br/>{b} : {c} ({d}%)"},legend:{orient:"vertical",left:"left",data:e},series:[{type:"pie",radius:"55%",center:["50%","60%"],data:t.value,itemStyle:{emphasis:{shadowBlur:10,shadowOffsetX:0,shadowColor:"rgba(0, 0, 0, 0.5)"}}}]};t.dom=i.a.init(t.$refs.dom,"tdTheme"),t.dom.setOption(o),Object(c["on"])(window,"resize",t.resize)})},beforeDestroy:function(){Object(c["off"])(window,"resize",this.resize)}},u=s,d=o("2877"),m=Object(d["a"])(u,r,a,!1,null,null,null);m.options.__file="pie.vue";e["default"]=m.exports}}]);