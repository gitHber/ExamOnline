
/*
 * html 特殊字符转义。
 * 
 * @param strdata
 */

function convertToHtml(strdata) {
	if (strdata == null)
		return "";
	var sb = "";
	for (i = 0; i < strdata.length; i++) {
		var c = strdata.charAt(i);
		switch (c) {
		case '<':
			sb += ("&lt;");
			continue;
		case '>':
			sb += ("&gt;");
			continue;
		case '&':
			sb += ("&amp;");
			continue;
		case '\'':
			sb += ("&apos;");
			continue;
		case '"':
			sb += ("&quot;");
			continue;
		case '\r':
			sb += ("&#xd;");
			continue;
		case '\n':
			sb += ("&#xa;");
			continue;
		default:
			sb += (c);
			continue;
		}
	}

	return sb;
}

/*
 * 空值判断
 */
function isNotNull(value) {
	if (null != value && "null" != value && "" != value && undefined != value
			&& "undefined" != value) {
		return true;
	} else {
		return false;
	}
}

/*
 * 判断值是否为空并返回值
 */
function isNotNullReturnValue(value) {
	if (null != value && "null" != value && "" != value && undefined != value
			&& "undefined" != value) {
		return value;
	} else {
		return "";
	}
}

/*
 * url参数解析
 */
function urlParamParse(key) {
	var paramValue = null;
	if (null != key && undefined != key && "" != key && "null" != key && "undefined" != key
			&& key.length > 0) {
		var param = document.location.search;
		if (null != param && undefined != param && "" != param && "null" != param
				&& "undefined" != param && param.length > 0) {
			param = unescape(param);
			param = param.substring(param.indexOf("?", 0) + 1, param.length);
			for (var i = 0; i < param.split("&").length; i++) {
				if (null != String(param.split("&")[i]) && undefined != String(param.split("&")[i])
						&& "" != String(param.split("&")[i])
						&& "null" != String(param.split("&")[i])
						&& "undefined" != String(param.split("&")[i])
						&& String(param.split("&")[i]).length > 0
						&& String(param.split("&")[i]).split("=").length > 0) {
					var paramkey = String(param.split("&")[i]).split("=")[0];
					//
					if (null != paramkey && undefined != paramkey && "" != paramkey
							&& "null" != paramkey && "undefined" != paramkey && paramkey.length > 0
							&& paramkey.toUpperCase() == key.toUpperCase()) {
						paramValue = String(param.split("&")[i]).split("=")[1];
						break;
					}
				}
			}
		}
	}

	return paramValue;
}

/*
 * 动态加载页面内容
 */
function dynmaicLoadPageContent(url, param, elementId) {
	// 我的大赛——右侧内容显示
	if (isNotNull(url)) {
		
		$.post(url, param, function(resultData, resultStatus) {
			if (isNotNull(resultStatus) && resultStatus.toUpperCase() == "SUCCESS") {
				$("#" + elementId).html(resultData);
			} else {
				alert('bbbb');
				$("#" + elementId).html(
						"<span style='font-size: 15px; margin: 5px 5px auto auto; "
								+ "color: #FF0000;'>Sorry！加载失败</span>");
			}
		}, "text");
	} else {
		window.location.href = window.location.href.split("?")[0];
	}
}

$.jheartbeat = {
	options : {
		delay : 10000
	},
	beatfunction : function() {
	},
	timeoutobj : {
		id : -1
	},

	set : function(options, onbeatfunction) {
		if (this.timeoutobj.id > -1) {
			clearTimeout(this.timeoutobj);
		}
		if (options) {
			$.extend(this.options, options);
		}
		if (onbeatfunction) {
			this.beatfunction = onbeatfunction;
		}

		this.timeoutobj.id = setTimeout("$.jheartbeat.beat();", this.options.delay);
	},

	beat : function() {
		this.timeoutobj.id = setTimeout("$.jheartbeat.beat();", this.options.delay);
		this.beatfunction();
	}
};

/**
 * 每隔一定的时间 执行func
 */
function lanqiaotimer(func, interval) {
	$.jheartbeat.set({
		delay : interval
	}, func);
}

function html_encode(str) {
	var s = "";
	if (str.length == 0)
		return "";
	s = str.replace(/&/g, "&amp;");
	s = s.replace(/</g, "&lt;");
	s = s.replace(/>/g, "&gt;");
	// s = s.replace(/ /g, "&nbsp;");
	s = s.replace(/\'/g, "&#39;");
	s = s.replace(/\"/g, "&quot;");
	// s = s.replace(/\n/g, "<br>");
	return s;
}

function html_decode(str) {
	var s = "";
	if (str.length == 0)
		return "";
	s = str.replace(/&gt;/g, "&");
	s = s.replace(/&lt;/g, "<");
	s = s.replace(/&gt;/g, ">");
	s = s.replace(/&nbsp;/g, " ");
	s = s.replace(/&#39;/g, "\'");
	s = s.replace(/&quot;/g, "\"");
	s = s.replace(/<br>/g, "\n");
	return s;
}

/**
 * 
 * 类似 java 的hashmap ，
 * 
 */
function HashMap() {

	/** Map 大小 * */
	var size = 0;

	/** 对象 * */
	var entry = new Object();

	/** 存 * */
	this.put = function(key, value) {
		if (!this.containsKey(key)) {
			size++;
		}
		entry[key] = value;
	}

	/** 取 * */
	this.get = function(key) {
		if (this.containsKey(key)) {
			return entry[key];
		} else {
			return null;
		}
	}

	/** 删除 * */
	this.remove = function(key) {
		if (delete entry[key]) {
			size--;
		}
	}

	/** 是否包含 Key * */
	this.containsKey = function(key) {
		return (key in entry);
	}

	/** 是否包含 Value * */
	this.containsValue = function(value) {
		for ( var prop in entry) {
			if (entry[prop] == value) {
				return true;
			}
		}
		return false;
	}

	/** 所有 Value * */
	this.values = function() {
		var values = new Array(size);
		for ( var prop in entry) {
			values.push(entry[prop]);
		}
		return values;
	}

	/** 所有 Key * */
	this.keys = function() {
		var keys = new Array(size);
		for ( var prop in entry) {
			keys.push(prop);
		}
		return keys;
	}

	/** Map Size * */
	this.size = function() {
		return size;
	}
};

/**
 * 去除字符串中的空格  
 */
String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

// 返回某一个页面中form 内的所有数据项, 每个input , textarea, select 拼装为一个对象. 
// 并返回. 以方便ajax 进行网络传输

function getFormFileds(formid)
{
	var aret={};
	
	$("#"+formid +" input").each(function(i){
	  var sname = $(this).attr("name");
	  if(sname !=null && sname !="")
		{
		  var strtype = $(this).attr("type").toLowerCase();
		  if(strtype =="email" ||strtype =="text" || strtype =="hidden"||strtype =="password"||strtype =="number"||strtype =="datetime-local")
			  {
			  	if( aret[sname]!=""&& aret[sname]!=null){
			  			aret[sname]=  aret[sname]+","+ $(this).val();
				  		 console.log("checkbox:"+aret[sname]); 
			  			 
			  		 }
			  		  
			  		 else{
			  			 
			  			  aret[sname]= $(this).val(); 
			  			 console.log("checkbox:"+aret[sname]);
			  		 }
			  	// aret[sname]= $(this).val();
			  	//alert("----"+aret[sname]);
			  	
			  }
		  else if(strtype =="checkbox")
			  {
			  	 var b = $(this).is(':checked');
			  	 if(b)
			  		 {
			  		 if( aret[sname]!=""&& aret[sname]!=null){
			  			aret[sname]=  aret[sname]+","+ $(this).val();
				  		 console.log("checkbox:"+aret[sname]); 
			  			 
			  		 }
			  		  
			  		 else{
			  			 
			  			  aret[sname]= $(this).val(); 
			  			 console.log("checkbox:"+aret[sname]);
			  		 }
			  		 }
				 
			  }
		  
		  else if(strtype =="radio")
		  {
		  	 var b = $(this).is(':checked');
		  	 if(b)
		 	 aret[sname]= $(this).val();
			  
		  }
		  
		  
		}
		
	});
	
	
	$("#"+formid +" select").each(function(i){
		  var sname = $(this).attr("name");
		  if(sname !=null && sname !="")
			{
			  aret[sname]= $(this).val();  
			}
			
		});
		
	$("#"+formid +" textarea").each(function(i){
		  var sname = $(this).attr("name");
		  if(sname !=null && sname !="")
			{
			  aret[sname]= $(this).val();  
			}
			
		});
	
	
	return aret;
}
	

// 调试, 打印日志
function print(obj)
{
	var sret ="";
	for(var s in obj)
		{
		 sret +=s +":" + obj[s]+"\r\n";
		
		}
	
	return sret;
}

/**
 * 获取url参数 http://localhost:8089/1012/pages/userList.html?username=aaa&pwd=123
 * var pwd=$_GET['pwd'];
 * var type = $_GET['type'];
 */
var $_GET = (function() {
		var url = window.document.location.href.toString();
		var u = url.split("?");
		if (typeof (u[1]) == "string") {
			u = u[1].split("&");
			var get = {};
			for ( var i in u) {
				var j = u[i].split("=");
				get[j[0]] = j[1];
			}
			return get;
		} else {
			return {};
		}
	})();
	
	
	
/**
 * cookie
 * name  cookie 的key(名字)
 * value cookie的 value（数据）
 * options 数组  可选属性 expires(cookie有效期 以天为单位）  path（创建一个cookie并设置 cookie的有效路径） domain: 'example.com' （创建cookie所在网页所拥有的域名；secure：默认是false，如果为true，cookie的传输协议需为https）  raw:false(默认为false，读取和写入时候自动进行编码和解码（使用encodeURIComponent编码，使用decodeURIComponent解码），关闭这个功能，请设置为true。)
 * 
 * 
 * 例子
 * $.cookie('the_cookie'); // 读取 cookie
 * $.cookie('the_cookie', 'the_value'); // 存储 cookie
 * $.cookie('the_cookie', 'the_value', { expires: 7 }); // 存储一个带7天期限的 cookie
 * $.cookie('the_cookie', '', { expires: -1 }); // 删除 cookie
 * 
 */
jQuery.cookie = function(name, value, options) {
	//alert("  111"+value);
	if (typeof value != 'undefined') {
	   options = options || {};
	   if (value === null) {
	    value = '';
	    options = $.extend({}, options);
	    options.expires = -1;
	   }
	   var expires = '';
	   if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
	    var date;
	    if (typeof options.expires == 'number') {
	     date = new Date();
	     date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
	    } else {
	     date = options.expires;
	    }
	    expires = '; expires=' + date.toUTCString();
	   
	   }
	   var path = options.path ? '; path=' + (options.path) : '';
	   var domain = options.domain ? '; domain=' + (options.domain) : '';
	   var secure = options.secure ? '; secure' : '';
	   document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
	} else {
	   var cookieValue = null;
	   if (document.cookie && document.cookie != '') {
	    var cookies = document.cookie.split(';');
	    for (var i = 0; i < cookies.length; i++) {
	     var cookie = jQuery.trim(cookies[i]);
	     if (cookie.substring(0, name.length + 1) == (name + '=')) {
	      cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
	      break;
	     }
	    }
	   }
	   return cookieValue;
	}
	};
	
/**
 * 保存json对象到cookie
 * @param strkey
 * @param jsonobject
 * @param cookietime
 */
function addCooike(strkey , jsonobject,cookietime)
{     
	var cookieOptions={};
	cookieOptions.path="/";
	if(cookietime==undefined){
		cookieOptions.expires=7;
	}else{
		cookieOptions.expires=cookietime;	
	}
	
	$.cookie(strkey, JSON.stringify(jsonobject),cookieOptions); 
}
/**
 * 删除cookie 对象. 
 * @param strkey
 */
function deleteCooike(strkey)
{
	$.cookie(strkey, "", {expires: -1}); 
}
/**
 * 从cookie 载入json对象。 
 * @param strkey
 */
function loadJSONFromCookie(strkey)
{
	var reCookie=JSON.parse($.cookie(strkey));
	return reCookie;
}


