/* 실습 목표: 엘리먼트를 찾는 코드를 단순화하자. 
 * 서버측 코드: node01t/src/ex6/test07.js
 */
refreshBoardTable();

bit('#addBtn')[0].onclick = function(event) {
	ajax('http://localhost:1337/board/add.do', {
		method: 'POST',
		data: {
			title: bit('#title')[0].value,
			content: bit('#content')[0].value
		},
		success: function(result) {
			refreshBoardTable();
			clearForm();
		},
		error: function(err) {
			alert('작업을 완료할 수 없습니다.\n' + 
				  '잠시 후 다시 시도하세요.\n' +
				  '계속 창이 뜬다면, 관리자에 문의하세요.(사내번호:1112)');
		}
	});
};

bit('#changeBtn')[0].onclick = function(event) {
	ajax('http://localhost:1337/board/change.do', {
		method: 'POST',
		data: {
			no: bit('#bno')[0].textContent,
			title: bit('#title')[0].value,
			content: bit('#content')[0].value
		},
		success: function(result) {
			refreshBoardTable();
			clearForm();
		},
		error: function(err) {
			alert('작업을 완료할 수 없습니다.\n' + 
				  '잠시 후 다시 시도하세요.\n' +
				  '계속 창이 뜬다면, 관리자에 문의하세요.(사내번호:1112)');
		}
	});
};

bit('#cancelBtn')[0].onclick = function(event) {
	toNewForm();
};

bit('#deleteBtn')[0].onclick = function(event) {
	var no = bit('#bno')[0].textContent;
	ajax('http://localhost:1337/board/delete.do?no=' + no, {
		method: 'GET',
		success: function(result) {
			refreshBoardTable();
			clearForm();
		},
		error: function(err) {
			alert('작업을 완료할 수 없습니다.\n' + 
				  '잠시 후 다시 시도하세요.\n' +
				  '계속 창이 뜬다면, 관리자에 문의하세요.(사내번호:1112)');
		}
	});
};
	
function detailInfo(event) {
	var no = event.target.getAttribute('data-no');
	ajax('http://localhost:1337/board/detail.do?no=' + no, {
		method: 'GET',
		success: function(result) {
			var rows = result.data;
		    bit('#bno')[0].innerHTML = rows[0].bno;
		    bit('#title')[0].value = rows[0].title;
		    bit('#content')[0].value = rows[0].content;
		    bit('#cdate')[0].innerHTML = rows[0].cdate;
		    bit('#views')[0].innerHTML = rows[0].views;
		    
		    toDetailForm();
		},
		error: function(err) {
			alert('작업을 완료할 수 없습니다.\n' + 
				  '잠시 후 다시 시도하세요.\n' +
				  '계속 창이 뜬다면, 관리자에 문의하세요.(사내번호:1112)');
		}
	});
}

function refreshBoardTable() {
	ajax('http://localhost:1337/board/list.do', {
		method: 'GET',
		success: function(result) {
			var rows = result.data;
		    var tr;
		    var table = bit('#boardTable')[0];
		    
		    var trList = bit('.dataRow');
		    for (var i = 0; i < trList.length; i++) {
		      table.removeChild(trList[i]);
		    }
		    
		    for (var i in rows) {
		      tr = document.createElement('tr');
		      tr.setAttribute('class', 'dataRow');
		      tr.innerHTML = '<td>' + rows[i].bno + '</td>\n' +
		         '<td><a href="#" onclick="detailInfo(event);" data-no="' + 
		         rows[i].bno + '">' + 
		         rows[i].title + '</a></td>\n' +
		         '<td>' + rows[i].cdate + '</td>\n';
		      table.appendChild(tr);
		    }
		},
		error: function(err) {
			alert('작업을 완료할 수 없습니다.\n' + 
				  '잠시 후 다시 시도하세요.\n' +
				  '계속 창이 뜬다면, 관리자에 문의하세요.(사내번호:1112)');
		}
	});
}

function toDetailForm() {
	var elementList = bit('.detail-items');
    for (var i = 0; i < elementList.length; i++) {
    	  if (elementList[i] instanceof HTMLDivElement) {
    		  elementList[i].style.display = 'block';
    	  } else {
    		  elementList[i].style.display = 'inline';
    	  }
    }
    var elementList = bit('.new-items');
    for (var i = 0; i < elementList.length; i++) {
        elementList[i].style.display = 'none';
    }
}

function toNewForm() {
  var elementList = bit('.new-items');
  for (var i = 0; i < elementList.length; i++) {
      if (elementList[i] instanceof HTMLDivElement) {
        elementList[i].style.display = 'block';
      } else {
        elementList[i].style.display = 'inline';
      }
  }
  var elementList = bit('.detail-items');
  for (var i = 0; i < elementList.length; i++) {
    elementList[i].style.display = 'none';
  }
}

function clearForm() {
  var clickEvent = new MouseEvent('click', { /* 이벤트 옵션 설정 */
	  bubbles: true,
	  cancelable: true,
	  view: window
  });
  bit('#cancelBtn')[0].dispatchEvent(clickEvent);
}

// 공통함수: 여러 프로젝트에 사용할 수 있는 함수
function ajax(url, settings) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
	  if (xhr.readyState == 4) { 
		  var result = JSON.parse(xhr.responseText);
		  if (result.status == 'success') {
			  if (settings.success) {
				  settings.success(result);
			  }
		  } else {
			  if (settings.error) {
				  settings.error(result.data);
			  }
		  }
	  }
	};
	xhr.open(settings.method, url, true);
	if (settings.method == 'POST') {
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		
		var params = '';
		if (settings.data) {
			for (var propName in settings.data) {
				if (params.length > 0) {
					params += '&';
				}
				params += propName + '=' + 
						  encodeURIComponent(settings.data[propName]);
			}
		}
		xhr.send(params);
		
	} else {
		xhr.send(null);
	}
}

function bit(selector) {
	var elementList = document.querySelectorAll(selector);
	return elementList;
}




























