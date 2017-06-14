//初始化
$(function(){
	var bindTel = $(".bindTelForm").Validform({
		tiptype:function(msg,o,cssctl){
			var objtip=$("#msg");
			cssctl(objtip,o.type);
			objtip.text(msg);
		},
		ajaxPost:true
	});
	bindTel.config={
	    ajaxpost:{
	    	url:"",
	        timeout:1000,
	        success:function(data,obj){
	            //data是返回的json数据;
	            //obj是当前表单的jquery对象;
	        },
	        error:function(data,obj){
	            //data是{ status:**, statusText:**, readyState:**, responseText:** };
	            //obj是当前表单的jquery对象;
	        }
	    },
	    ajaxurl:{
	        success:function(data,obj){
	            //data是返回的json数据;
	            //obj是当前正做实时验证表单元素的jquery对象;
	            //注意：5.3版中，实时验证的返回数据须是含有status值的json数据！
	            //跟callback里的ajax返回数据格式统一，建议不再返回字符串"y"或"n"。目前这两种格式的数据都兼容。
	        }
	    }
	}
})
var countdown = 120; 
function settime(obj) { 
    if (countdown == 0) { 
        obj.removeAttribute("disabled");    
        obj.value="重新发送"; 
        countdown = 120; 
        return;
    } else { 
        obj.setAttribute("disabled", true); 
        obj.value="( " + countdown + " )"; 
        countdown--; 

        /*
        * ajax
        */
        

    } 
	setTimeout(function() { 
    	settime(obj) 
	},1000) 
}