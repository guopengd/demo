(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d217319"],{c64d:function(e,o,t){"use strict";t.r(o);t("a481");o["default"]=function(e){(function(o){o(e)})(function(e){function o(e){e.state.placeholder&&(e.state.placeholder.parentNode.removeChild(e.state.placeholder),e.state.placeholder=null)}function t(e){o(e);var t=e.state.placeholder=document.createElement("pre");t.style.cssText="height: 0; overflow: visible; color: #80848f;",t.style.direction=e.getOption("direction"),t.className="CodeMirror-placeholder";var n=e.getOption("placeholder");"string"===typeof n&&(n=document.createTextNode(n)),t.appendChild(n),e.display.lineSpace.insertBefore(t,e.display.lineSpace.firstChild)}function n(e){a(e)&&t(e)}function r(e){var n=e.getWrapperElement(),r=a(e);n.className=n.className.replace(" CodeMirror-empty","")+(r?" CodeMirror-empty":""),r?t(e):o(e)}function a(e){return 1===e.lineCount()&&""===e.getLine(0)}e.defineOption("placeholder","",function(t,a,c){var l=c&&c!==e.Init;if(a&&!l)t.on("blur",n),t.on("change",r),t.on("swapDoc",r),r(t);else if(!a&&l){t.off("blur",n),t.off("change",r),t.off("swapDoc",r),o(t);var i=t.getWrapperElement();i.className=i.className.replace(" CodeMirror-empty","")}a&&!t.hasFocus()&&n(t)})})}}}]);