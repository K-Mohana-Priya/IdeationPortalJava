 function count(){
	var tt=setInterval(update,2000);
}
function update(){
	noti = document.getElementById("noti");
	msg = document.getElementById("msg");
	var xmlDoc,txt,x;
	var request=null;
	if (window.XMLHttpRequest) {
	request = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	request = new ActiveXObject("Microsoft.XMLHTTP");
	}

	   if(request)
	   {
	    var url="Message_Notify";
	   
	    request.onreadystatechange = function()
	    {
	      
	   if(request.readyState==4)
	      {
		  xmlDoc=request.responseXML;
		    txt="";
		    x=xmlDoc.getElementsByTagName("message");
		      txt= x[0].childNodes[0].nodeValue;
		      if(txt != 0)
		    document.getElementById("msg").innerHTML=txt;
		    txt="";
		    x=xmlDoc.getElementsByTagName("notify");
		      txt= x[0].childNodes[0].nodeValue;
		    document.getElementById("noti").innerHTML=txt;
	      }

	   }

	    request.open("GET",url,true);
	       request.send(null);
 }
}
   
function init() { 
	 $("#logout").onclick(function(){
		   history.pushState(null, null, 'Login.jsp');
		   window.addEventListener('popstate', function(event) {
		   history.pushState(null, null, 'Login.jsp');
		   });
	    	document.location="logoutcontroller";
	  
	    }); 
	 
	 count(); }
 
 window.onload = init(); 
 
 
 