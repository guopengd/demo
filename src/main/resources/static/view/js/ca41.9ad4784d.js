(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["ca41"],{ca41:function(t,e,u){"use strict";u.r(e),u.d(e,"getActivityList",function(){return n}),u.d(e,"saveActivity",function(){return r}),u.d(e,"updateActivity",function(){return c}),u.d(e,"deleteActivity",function(){return s});var i=u("66df"),n=function(t){return i["default"].request({url:"sys/activity/list",data:t,method:"post"})},r=function(t){return i["default"].request({url:"sys/activity",data:t,method:"post"})},c=function(t){return i["default"].request({url:"sys/activity",data:t,method:"put"})},s=function(t){return i["default"].request({url:"sys/activity/"+t,method:"delete"})}}}]);